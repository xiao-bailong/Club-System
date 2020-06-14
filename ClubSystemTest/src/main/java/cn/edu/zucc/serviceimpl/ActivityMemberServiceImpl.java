package cn.edu.zucc.serviceimpl;

import cn.edu.zucc.dao.ActivityMemberDao;
import cn.edu.zucc.entity.ActivityMember;
import cn.edu.zucc.service.ActivityMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("activityMemberService")
public class ActivityMemberServiceImpl implements ActivityMemberService {
    @Resource
    private ActivityMemberDao activityMemberDao;
    @Override
    public int addActivityMember(ActivityMember activityMember) {
        return activityMemberDao.addActivityMember(activityMember);
    }
    @Override
    public ActivityMember check(ActivityMember activityMember) {
        return activityMemberDao.check(activityMember);
    }
    @Override
    public int changeState(ActivityMember activityMember) {
        return activityMemberDao.changeState(activityMember);
    }
    @Override
    public List<ActivityMember> selectNoInUserByActivityid(int activityid) {
        return activityMemberDao.selectNoInUserByActivityid(activityid);
    }
    @Override
    public List<Integer> listMyactivity(String userId) {
        return activityMemberDao.listMyactivity(userId);
    }
    @Override
    public int deleteActivityMember(ActivityMember activityMember) {
        return activityMemberDao.deleteActivityMember(activityMember);
    }
}
