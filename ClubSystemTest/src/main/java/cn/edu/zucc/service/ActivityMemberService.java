package cn.edu.zucc.service;

import cn.edu.zucc.entity.ActivityMember;

import java.util.List;

public interface ActivityMemberService {
    public int addActivityMember(ActivityMember activityMember);
    public ActivityMember check(ActivityMember activityMember);
    public int changeState(ActivityMember activityMember);
    public List<ActivityMember> selectNoInUserByActivityid(int activityid);
    public List<Integer> listMyactivity(String userId);
    public int deleteActivityMember(ActivityMember activityMember);
}
