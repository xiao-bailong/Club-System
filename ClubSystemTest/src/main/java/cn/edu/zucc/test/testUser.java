package cn.edu.zucc.test;

import cn.edu.zucc.dao.ActivityDao;
import cn.edu.zucc.dao.ClubDao;
import cn.edu.zucc.dao.ClubMemberDao;
import cn.edu.zucc.dao.UserDao;
import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.utils.DateUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

public class testUser {
    @Test
    public void TestAdd() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = act.getBean(UserDao.class);
        User user=new User();
        user.setUserId("31701057");
        user.setUsername("俞凌健");
        user.setPassword("123");
        user.setType(3);
        int result=userDao.modify(user);
        System.out.println(result);
        System.out.println("*********Test*********");
    }
    @Test
    public void TestClub() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        ClubDao clubDao = act.getBean(ClubDao.class);
        System.out.println(clubDao.listClub(1));
        System.out.println("*********TestClub*********");
    }
    @Test
    public void TestListUser() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = act.getBean(UserDao.class);
        System.out.println(userDao.list());
        System.out.println("*********TestListUser*********");
    }
    @Test
    public void TestReset() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = act.getBean(UserDao.class);
        System.out.println(userDao.resetPassword("31701003"));
        System.out.println("*********TestReset*********");
    }
    @Test
    public void TestListMyClubIds() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        ClubMemberDao clubMemberDao = act.getBean(ClubMemberDao.class);
        System.out.println(clubMemberDao.listMyclub("31701057"));
        System.out.println("*********TestListMyClubIds*********");
    }
    @Test
    public void TestDatetime() throws Exception {
        ApplicationContext act =new ClassPathXmlApplicationContext("applicationContext.xml");
        ActivityDao activityDao = act.getBean(ActivityDao.class);
//        Activity activity=new Activity();
//        activity.setClubId(4);
//        activity.setTitle("篮球赛2");
//        activity.setDescription("举办一场篮球赛2");
//        activity.setLocation("篮球场");
//        activity.setStarttime(new Timestamp(DateUtils.str2Date("Tue Jun 10 2020 00:00:00 GMT+0800 (中国标准时间)").getTime()));
//        activity.setEndtime(new Timestamp(DateUtils.str2Date("Tue Jun 10 2020 02:00:00 GMT+0800 (中国标准时间)").getTime()));
//        int returnResult=activityDao.createActivity(activity);
//        activity=activityDao.check(2);
//        System.out.println(activity);
//        if (activity.getEndtime().before(activity.getStarttime())) {
//            System.out.println("前比后早");
//        }else{
//            System.out.println("前比后晚");
//        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String str = "2020/06/10 14:00:00";
        java.util.Date starttime= sdf.parse(str);
        java.util.Date now=new Date();
        System.out.println(starttime+" "+now);
        if (now.before(starttime)) {
            System.out.println("前比后早");
        }else{
            System.out.println("前比后晚");
        }
        System.out.println("*********TestDatetime*********");
    }
}
