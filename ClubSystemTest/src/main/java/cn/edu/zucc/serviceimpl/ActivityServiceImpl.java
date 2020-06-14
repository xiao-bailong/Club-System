package cn.edu.zucc.serviceimpl;

import cn.edu.zucc.dao.ActivityDao;
import cn.edu.zucc.dao.ClubDao;
import cn.edu.zucc.entity.Activity;
import cn.edu.zucc.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;
    @Override
    public int createActivity(Activity activity) {
        return activityDao.createActivity(activity);
    }
    @Override
    public List<Activity> listClubActivity(int clubId) {
        return activityDao.listClubActivity(clubId);
    }
    @Override
    public int addMember(int activityid) {
        return activityDao.addMember(activityid);
    }
    @Override
    public int deleteMember(int activityid) {
        return activityDao.deleteMember(activityid);
    }
    @Override
    public int count() {
        return activityDao.count();
    }
    @Override
    public List<Activity> listMyactivity(int clubId,List<Integer> activityidlist) {
        return activityDao.listMyactivity(clubId,activityidlist);
    }
    @Override
    public List<Activity> search(Activity activity) {
        return activityDao.search(activity);
    }
}
