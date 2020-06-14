package cn.edu.zucc.controller;

import cn.edu.zucc.dto.UserResult;
import cn.edu.zucc.entity.Club;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.service.ActivityService;
import cn.edu.zucc.service.ClubService;
import cn.edu.zucc.service.UserService;
import cn.edu.zucc.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private ClubService clubService;
    @Resource
    private ActivityService activityService;

    @RequestMapping("/login")//web端登录
    public void login(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userName");
        String password = request.getParameter("password");
        User user=new User();
        user.setUserId(userId);
        user.setPassword(password);
        User loginUser=userService.check(user);
        if(loginUser==null){
            UserResult result = new UserResult(1,"用户名或密码错误",null);
            JsonUtils.returnJson(response,result);
        }
        else if(loginUser.getType()==3){
            UserResult result = new UserResult(1,"普通用户禁止登录管理界面",null);
            JsonUtils.returnJson(response,result);
        }else{
            UserResult result = new UserResult(0,"成功",loginUser);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/login2")//微信小程序端登录
    public void login2(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userName");
        String password = request.getParameter("password");
        User user=new User();
        user.setUserId(userId);
        user.setPassword(password);
        User loginUser=userService.check(user);
        if(loginUser==null){
            UserResult result = new UserResult(1,"用户名或密码错误",null);
            JsonUtils.returnJson(response,result);
        }
        else if(loginUser.getType()==1){
            UserResult result = new UserResult(1,"管理员请登录web端",null);
            JsonUtils.returnJson(response,result);
        }else{
            UserResult result = new UserResult(0,"成功",loginUser);
            JsonUtils.returnJson(response,result);
        }
    }

    @RequestMapping("/registe")
    public void add(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName").trim();
        String password = request.getParameter("password");
        String type = request.getParameter("type");
//        System.out.println(userName+" "+password);
        //验证
        if(RegisteCheckId(userId,request,response).equals("false")){
            UserResult result = new UserResult(1,"账号已存在",null);
            JsonUtils.returnJson(response,result);
//            return "reback";
        }else if(RegisteCheckName(userName,request,response).equals("false")){
            UserResult result = new UserResult(1,"用户名已存在",null);
            JsonUtils.returnJson(response,result);
        }
        else{
//            request.setAttribute("registeErrorMsg", "注册失败，请检查格式");
//            return "registe";
            User user=new User();
            user.setUserId(userId);
            user.setUsername(userName);
            user.setPassword(password);
            user.setType(Integer.parseInt(type));
            userService.add(user);
            UserResult result = new UserResult(0,"成功",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/modify")//修改个人信息
    public void modify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String type = request.getParameter("type");
//        System.out.println(userid+" "+username+" "+password+" "+type);
        User user=new User();
        user.setUserId(userid);
        user.setUsername(username);
        user.setPassword(password);
//        User primaryUser = userService.checkid(userid);
//        if(username!=null){
//            user.setUsername(username);
//        }else{
//            user.setUsername(primaryUser.getUsername());
//        }
//        if(password!=null){
//            user.setPassword(password);
//        }else{
//            user.setPassword(primaryUser.getPassword());
//        }
//        if(type!=null){
//            user.setType(Integer.parseInt(type));
//        }else{
//            user.setType(primaryUser.getType());
//        }
        int rerurnResult=userService.modify(user);
//        int rerurnResult=this.Modify(userid,username,password,type);
        if(rerurnResult!=0){
            UserResult result = new UserResult(0,"修改完成",null);
            JsonUtils.returnJson(response,result);
        }else{
            UserResult result = new UserResult(1,"修改失败",null);
            JsonUtils.returnJson(response,result);
        }
    }
    @RequestMapping("/checkUserId")
    @ResponseBody
    public String RegisteCheckId(String userid,HttpServletRequest request,HttpServletResponse response){
        User checkId = userService.checkid(userid);
        if(checkId == null){//无此账号才允许注册。
            return "true";
        }
        return "false";
    }
    @RequestMapping("/checkUserName")
    @ResponseBody
    public String RegisteCheckName(String username,HttpServletRequest request,HttpServletResponse response){
        User checkId = userService.checkname(username);
        if(checkId == null){//无此账号才允许注册。
            return "true";
        }
        return "false";
    }
    @RequestMapping("/list")//罗列用户
    @ResponseBody
    public List<User> list(){
        List<User> userList=userService.list();
        return userList;
    }
    @RequestMapping("/reset")//重置密码为123456
    public void reset(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        int rerurnResult = userService.resetPassword(userid);
        if (rerurnResult != 0) {
            UserResult result = new UserResult(0, "完成重置", null);
            JsonUtils.returnJson(response, result);
        } else {
            UserResult result = new UserResult(1, "密码重置出错了", null);
            JsonUtils.returnJson(response, result);
        }
    }
    @RequestMapping("/delete")//删除用户
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
//        User user=userService.checkid(userid);
//        if(user.getType()==2){
//
//        }
        int rerurnResult = userService.delete(userid);
        if (rerurnResult != 0) {
            UserResult result = new UserResult(0, "已删除", null);
            JsonUtils.returnJson(response, result);
        } else {
            UserResult result = new UserResult(1, "用户删除失败", null);
            JsonUtils.returnJson(response, result);
        }
    }
    @RequestMapping("/search")//搜索用户
    @ResponseBody
    public List<User> search(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String context = request.getParameter("context");
        List<User> userList = userService.search(context);
        return userList;
    }
    @RequestMapping("/statistics")//统计用户数，活动数，社团数
    @ResponseBody
    public List<Integer> statistics(){
        System.out.println("tong-ji");
        List<Integer> tongji=new ArrayList<Integer>();
        tongji.add(userService.count());
        tongji.add(clubService.count2());
        tongji.add(activityService.count());
        tongji.add(clubService.count1());
        System.out.println(tongji);
        return tongji;
    }
}
