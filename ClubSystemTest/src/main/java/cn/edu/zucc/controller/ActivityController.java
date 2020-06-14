package cn.edu.zucc.controller;

import cn.edu.zucc.dto.ActivityResult;
import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.ActivityMember;
import cn.edu.zucc.service.ActivityMemberService;
import cn.edu.zucc.service.ActivityService;
import cn.edu.zucc.service.UserService;
import cn.edu.zucc.utils.DateUtils;
import cn.edu.zucc.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private UserService userService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityMemberService activityMemberService;

    @RequestMapping("/create")//创建活动
    public void create(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String location = request.getParameter("location");
        Timestamp starttime2=new Timestamp(DateUtils.str2Date(starttime).getTime());//时间类型转换
        Timestamp endtime2=new Timestamp(DateUtils.str2Date(endtime).getTime());
        if (endtime2.before(starttime2)) {
            ActivityResult result = new ActivityResult(1,"结束时间不能比开始时间早",null);
            JsonUtils.returnJson(response,result);
        }else{
            Activity activity=new Activity();
            activity.setClubId(Integer.parseInt(clubId));
            activity.setTitle(title);
            activity.setDescription(description);
            activity.setLocation(location);
            activity.setStarttime(starttime2);
            activity.setEndtime(endtime2);
            int returnResult=activityService.createActivity(activity);
            if(returnResult!=0){
                ActivityResult result = new ActivityResult(0,"活动创建成功",null);
                JsonUtils.returnJson(response,result);
            }else{
                ActivityResult result = new ActivityResult(1,"活动创建失败,请重试",null);
                JsonUtils.returnJson(response,result);
            }
        }
    }
    @RequestMapping("/listClubActivity")//罗列社团活动
    public void listClubActivity(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        List<Activity> activityList=activityService.listClubActivity(Integer.parseInt(clubId));
        System.out.println(activityList);
        if(activityList.isEmpty()){
            ActivityResult result = new ActivityResult(1,"暂无活动",null);
            JsonUtils.returnJson(response,result);
        }else{
            ActivityResult result = new ActivityResult(0,"成功",activityList);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/listClubActivity2")//罗列社团活动
    @ResponseBody
    public List<Activity> listClubActivity2(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        List<Activity> activityList=activityService.listClubActivity(Integer.parseInt(clubId));
        System.out.println(activityList);
        if(activityList.isEmpty()){
            System.out.println(1);
            return null;
        }else{
            System.out.println(2);
            return activityList;
        }
    }
    @RequestMapping("/listClubActivity3")//罗列社团活动
    @ResponseBody
    public List<Activity> listClubActivity3(@RequestBody Map map,HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        int clubId= (int) map.get("clubId");
        List<Activity> activityList=activityService.listClubActivity(clubId);
        System.out.println(activityList);
        return activityList;
    }
    @RequestMapping("/attend")//参加活动
    public void attend(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String activityid = request.getParameter("activityid");
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String starttime = request.getParameter("starttime");
//        System.out.println(starttime);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        java.util.Date starttime2= sdf.parse(starttime);
        java.util.Date now=new Date();
        if (now.before(starttime2)) {
//            System.out.println("前比后早");
            ActivityMember activityMember=new ActivityMember();
            activityMember.setActivityid(Integer.parseInt(activityid));
            activityMember.setUserId(userId);
            activityMember.setUsername(username);
            activityMember.setState(1);
            if(activityMemberService.check(activityMember)==null){
                int rerurnResult=activityMemberService.addActivityMember(activityMember);
                if(rerurnResult!=0){
                    ActivityResult result = new ActivityResult(0,"已提交报名申请",null);
                    JsonUtils.returnJson(response,result);
                }else{
                    ActivityResult result = new ActivityResult(1,"出错了请重试",null);
                    JsonUtils.returnJson(response,result);
                }
            }else if(activityMemberService.check(activityMember).getState()==1){
                ActivityResult result = new ActivityResult(1,"已提交申请，请等待审批",null);
                JsonUtils.returnJson(response,result);
            }else if(activityMemberService.check(activityMember).getState()==2){
                ActivityResult result = new ActivityResult(1,"你已参加该活动，无法再次报名",null);
                JsonUtils.returnJson(response,result);
            }
        }else{
//            System.out.println("前比后晚");
            ActivityResult result = new ActivityResult(1,"该活动已在进行中或已结束，无法报名",null);
            JsonUtils.returnJson(response,result);
        }
//
    }
    @RequestMapping("/listActivityApprovalUser")//罗列社团活动的申请用户
    @ResponseBody
    public List<ActivityMember> listActivityApprovalUser(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String activityid = request.getParameter("activityid");
        List<ActivityMember> userlist=activityMemberService.selectNoInUserByActivityid(Integer.parseInt(activityid));
        if(userlist.isEmpty()){
            return null;
        }else{
            return userlist;
        }
    }
    @RequestMapping("/complete")//完成活动申请的审批
    @ResponseBody
    public List<ActivityMember> complete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String activityid = request.getParameter("activityid");
        String id = request.getParameter("id");
        ActivityMember activityMember=new ActivityMember();
        activityMember.setId(Integer.parseInt(id));
        activityMember.setState(2);
        activityMemberService.changeState(activityMember);//改变state
        activityService.addMember(Integer.parseInt(activityid));//activity增加人数
        List<ActivityMember> userlist=activityMemberService.selectNoInUserByActivityid(Integer.parseInt(activityid));
        return userlist;
    }
    @RequestMapping("/listMyActivity")//罗列我已参加的社团活动
    public void listMyActivity(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clubId = request.getParameter("clubId");
        String userId = request.getParameter("userId");
        List<Integer> activityidlist=activityMemberService.listMyactivity(userId);
        System.out.println("activityidlist"+activityidlist);
        if(activityidlist.isEmpty()){
            ActivityResult result = new ActivityResult(1,"你还未参加该社团的任何活动",null);
            JsonUtils.returnJson(response,result);
        }else{
            List<Activity> activityList=activityService.listMyactivity(Integer.parseInt(clubId),activityidlist);
            ActivityResult result = new ActivityResult(0,"成功",activityList);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/search")//搜索活动
    @ResponseBody
    public List<Activity> search(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String context = request.getParameter("context");
        String clubId = request.getParameter("clubId");
        Activity activity=new Activity();
        activity.setClubId(Integer.parseInt(clubId));
        activity.setTitle(context);
        List<Activity> activityList = activityService.search(activity);
        return activityList;
    }
    @RequestMapping("/quit")//退出活动
    public void quit(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String activityid = request.getParameter("activityid");
        String userId = request.getParameter("userId");
        ActivityMember activityMember= new ActivityMember();
        activityMember.setActivityid(Integer.parseInt(activityid));
        activityMember.setUserId(userId);
        int reResult= activityMemberService.deleteActivityMember(activityMember);
        if(reResult!=0){
            activityService.deleteMember(Integer.parseInt(activityid));
            ActivityResult result = new ActivityResult(0,"退出成功",null);
            JsonUtils.returnJson(response,result);
        }else{
            ActivityResult result = new ActivityResult(1,"退出失败，请重试",null);
            JsonUtils.returnJson(response,result);
        }
    }
}
