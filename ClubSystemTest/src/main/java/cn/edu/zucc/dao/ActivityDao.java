package cn.edu.zucc.dao;

import cn.edu.zucc.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDao {
    public int createActivity(Activity activity);
    public Activity check(int activityid);
    public List<Activity> listClubActivity(int clubId);
    public int addMember(int activityid);
    public int deleteMember(int activityid);
    public int count();
    public List<Activity> listMyactivity(@Param("clubId")int clubId, @Param("activityidlist")List<Integer>  activityidlist);
    public List<Activity> search(Activity activity);
}
