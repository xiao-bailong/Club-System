package cn.edu.zucc.controller;

import cn.edu.zucc.dao.UserDao;
import cn.edu.zucc.dto.ClubResult;
import cn.edu.zucc.entity.Club;
import cn.edu.zucc.entity.ClubMember;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.service.ClubMemberService;
import cn.edu.zucc.service.ClubService;
import cn.edu.zucc.service.UserService;
import cn.edu.zucc.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/club")
public class ClubController {
    @Resource
    private UserService userService;
    @Resource
    private ClubService clubService;
    @Resource
    private ClubMemberService clubMemberService;
    @RequestMapping("/create")//创建社团
    public void create(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ownerId = request.getParameter("ownerId");
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Club club=new Club();
        club.setDescription(description);
        club.setName(name);
        club.setOwnerId(ownerId);
        club.setState(1);
        Club reClub=clubService.check(name);
        if(reClub!=null){
            ClubResult result = new ClubResult(1,"该社团已存在",null);
            JsonUtils.returnJson(response,result);
        }else{
            clubService.createClub(club);
            Club newclub=clubService.check(name);
            ClubMember clubMember=new ClubMember();
            clubMember.setClubId(newclub.getClubId());
            clubMember.setUserId(ownerId);
            clubMember.setUsername(username);
            clubMember.setState(1);
            clubMemberService.addClubMember(clubMember);//往社团成员中添加社长
            ClubResult result = new ClubResult(0,"成功",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/list")//罗列社团
    public void list(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String state = request.getParameter("state");
        System.out.println(state);
        List<Club> clubList=clubService.listClub(Integer.parseInt(state));
//        if(clubList!=null){  //null == list判断是否有这个容器，而list.size() ==0判断这个容器有没有东西，两者是不一样的意思,而list.size() ==0与list.isEmpty()没有区别
        if(!clubList.isEmpty()){
            ClubResult result = new ClubResult(0,"成功",clubList);
            JsonUtils.returnJson(response,result);
        }else{
            ClubResult result = new ClubResult(1,"失败",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/listMyClub")//罗列我的社团（普通成员/社长）
    public void listMyClub(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        List<Integer> clubIdlist=clubMemberService.listMyclub(userid);
        System.out.println("my-club-ids"+clubIdlist);
        if(clubIdlist.isEmpty()){
            ClubResult result = new ClubResult(1,"你还未加入任何社团",null);
            JsonUtils.returnJson(response,result);
        }else{
            List<Club> clubList=clubService.listMyclub(clubIdlist);
            ClubResult result = new ClubResult(0,"成功",clubList);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/listMyClubApprovalUser")//罗列我的社团的申请用户
    @ResponseBody
    public List<ClubMember> listMyClubApprovalUser(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        List<ClubMember> userlist=clubMemberService.selectNoInUserByClubid(Integer.parseInt(clubId));
        if(userlist.isEmpty()){
            return null;
        }else{
            return userlist;
        }
    }
    @RequestMapping("/listMyClubInfo")//我的社团信息
    @ResponseBody
    public Club listMyClubInfo(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
//        Club club=new Club();
//        club.setName(name);
        Club reClub=clubService.check(name);
        return reClub;
    }
    @RequestMapping("/listMyClubMember")//罗列我的社团成员
    @ResponseBody
    public List<ClubMember> listMyClubMember(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String userId = request.getParameter("userId");
        ClubMember clubMember=new ClubMember();
        clubMember.setClubId(Integer.parseInt(clubId));
        clubMember.setUserId(userId);
        List<ClubMember> userlist=clubMemberService.selectMemberByClubid(clubMember);
        if(userlist.isEmpty()){
            return null;
        }else{
            return userlist;
        }
    }
    @RequestMapping("/complete")//完成入社申请的审批
    @ResponseBody
    public List<ClubMember> complete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String id = request.getParameter("id");
        ClubMember clubMember=new ClubMember();
        clubMember.setId(Integer.parseInt(id));
        clubMember.setState(2);
        clubMemberService.changeState(clubMember);//改变state
        clubService.addMember(Integer.parseInt(clubId));//club增加人数
        List<ClubMember> userlist=clubMemberService.selectNoInUserByClubid(Integer.parseInt(clubId));
        return userlist;
    }
    @RequestMapping("/kickMember")//踢出社团成员
    @ResponseBody
    public List<ClubMember> kickMember(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        clubMemberService.deleteClubMember(Integer.parseInt(id));//踢出
        clubService.deleteMember(Integer.parseInt(clubId));//club减少人数
        ClubMember clubMember=new ClubMember();
        clubMember.setClubId(Integer.parseInt(clubId));
        clubMember.setUserId(userId);
        List<ClubMember> userlist=clubMemberService.selectMemberByClubid(clubMember);
        return userlist;
    }
    @RequestMapping("/quit")//退出社团
    public void quit(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String clubId = request.getParameter("clubId");
        String userId = request.getParameter("userId");
//        Club club=new Club();
//        club.setName(name);
//        System.out.println(clubService.check(club).getOwnerId());
//        System.out.println(name+" "+userId);
        if(clubService.check(name).getOwnerId().equals(userId)){
//            System.out.println("true");
            ClubResult result = new ClubResult(1,"你是该社社长，无法退出",null);
            JsonUtils.returnJson(response,result);
        }else{
//            System.out.println("false");
            ClubMember clubMember= new ClubMember();
            clubMember.setUserId(userId);
            clubMember.setClubId(Integer.parseInt(clubId));
            ClubMember reclubMember=clubMemberService.check(clubMember);
            clubMemberService.deleteClubMember(reclubMember.getId());//退出,根据clubId,userId找出id，然后根据id删(懒得再写个方法了）
            clubService.deleteMember(Integer.parseInt(clubId));//club减少人数
            ClubResult result = new ClubResult(0,"退社成功",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/listMyClub2")//罗列我的社团（社长）
    @ResponseBody
    public List<Club> listMyClub2(@RequestBody Map map,HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userid= (String) map.get("userid");
        System.out.println("userid="+userid);
        List<Club> clubList=clubService.listMyclub2(userid);
        return clubList;
//        ClubResult result = new ClubResult(0,"成功",clubList);
//        JsonUtils.returnJson(response,result);
//        if(clubList.isEmpty()){
//            ClubResult result = new ClubResult(1,"你的社团还未通过申请",null);
//            JsonUtils.returnJson(response,result);
//        }else{
//            ClubResult result = new ClubResult(0,"成功",clubList);
//            JsonUtils.returnJson(response,result);
//        }
    }
    @RequestMapping("/list2")//罗列社团
    @ResponseBody
    public List<Club> list2(@RequestBody Map map, HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        int state= (int) map.get("state");
//        System.out.println(state);
        List<Club> clubList=clubService.listClub(state);
//        System.out.println(clubList.get(0));
//        System.out.println(clubList.size());
        return clubList;
    }
    @RequestMapping("/update")//社团审批
    public void update(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String ownerId = request.getParameter("ownerId");
        Club club=new Club();
        club.setCreateTime(new java.sql.Date(new java.util.Date().getTime()));
        club.setState(2);
        club.setAmount(1);
        club.setClubId(Integer.parseInt(clubId));
        int rerurnResult=clubService.upadte(club);
        if(rerurnResult!=0){
            ClubMember clubMember=new ClubMember();
            clubMember.setClubId(Integer.parseInt(clubId));
            clubMember.setUserId(ownerId);
            ClubMember newclubmember=clubMemberService.check(clubMember);//找到clubmember中的对应申请单
            newclubmember.setState(2);
            clubMemberService.changeState(newclubmember);//将对应申请单的state改为2
            User user=new User();
            user.setUserId(ownerId);
            user.setType(2);
            userService.modify2(user);//把user表中的type改为社长
            ClubResult result = new ClubResult(0,"已完成审批",null);
            JsonUtils.returnJson(response,result);
        }else{
            ClubResult result = new ClubResult(1,"审批出错，请重试",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/update2")//更新社团信息
    public void update2(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String description = request.getParameter("description");
        String notice = request.getParameter("notice");
        Club club=new Club();
        club.setDescription(description);
        club.setNotice(notice);
        club.setClubId(Integer.parseInt(clubId));
        int rerurnResult=clubService.update2(club);
        if(rerurnResult!=0){
            ClubResult result = new ClubResult(0,"更新成功",null);
            JsonUtils.returnJson(response,result);
        }else{
            ClubResult result = new ClubResult(1,"社团信息更新出错",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/add")//申请加入社团
    public void add(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String userId = request.getParameter("userid");
        String username = request.getParameter("username");
        ClubMember clubMember=new ClubMember();
        clubMember.setClubId(Integer.parseInt(clubId));
        clubMember.setUserId(userId);
        clubMember.setUsername(username);
        clubMember.setState(1);
        if(clubMemberService.check(clubMember)==null){
            int rerurnResult=clubMemberService.addClubMember(clubMember);
            if(rerurnResult!=0){
                ClubResult result = new ClubResult(0,"已提交申请",null);
                JsonUtils.returnJson(response,result);
            }else{
                ClubResult result = new ClubResult(1,"出错了请重试",null);
                JsonUtils.returnJson(response,result);
            }

        }else if(clubMemberService.check(clubMember).getState()==1){
            ClubResult result = new ClubResult(1,"已提交申请，请等待审批",null);
            JsonUtils.returnJson(response,result);
        }else if(clubMemberService.check(clubMember).getState()==2){
            ClubResult result = new ClubResult(1,"你已是该社团成员，无法再次加入",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/replace")//更换社长
    public void replace(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String newOwnerId = request.getParameter("userId");
        String oldOwnerId=clubService.check2(Integer.parseInt(clubId)).getOwnerId();
        if(clubService.count3(oldOwnerId)>1){
            System.out.println("clubs>1");
            Club club=new Club();
            club.setOwnerId(newOwnerId);
            club.setClubId(Integer.parseInt(clubId));
            int rerurnResult=clubService.replace(club);
            if(rerurnResult!=0){
                User user=new User();
                user.setType(2);
                user.setUserId(newOwnerId);
                userService.modify2(user);
                ClubResult result = new ClubResult(0,"更换成功",null);
                JsonUtils.returnJson(response,result);
            }else{
                ClubResult result = new ClubResult(1,"社长更换出错",null);
                JsonUtils.returnJson(response,result);
            }
        }else{
            System.out.println("clubs=1");
            Club club=new Club();
            club.setOwnerId(newOwnerId);
            club.setClubId(Integer.parseInt(clubId));
            int rerurnResult=clubService.replace(club);
            if(rerurnResult!=0){
                User user=new User();
                user.setType(2);
                user.setUserId(newOwnerId);
                userService.modify2(user);
                User user2=new User();
                user2.setType(3);
                user2.setUserId(oldOwnerId);
                userService.modify2(user2);
                ClubResult result = new ClubResult(0,"更换成功",null);
                JsonUtils.returnJson(response,result);
            }else{
                ClubResult result = new ClubResult(1,"社长更换出错",null);
                JsonUtils.returnJson(response,result);
            }
        }

    }
}

