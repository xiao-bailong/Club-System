package cn.edu.zucc.service;

import cn.edu.zucc.entity.Activity;

import java.util.List;

public interface ActivityService {
    public int createActivity(Activity activity);
    public List<Activity> listClubActivity(int clubId);
    public int addMember(int activityid);
    public int deleteMember(int activityid);
    public int count();
    public List<Activity> listMyactivity(int clubId, List<Integer>  activityidlist);
    public List<Activity> search(Activity activity);
}
