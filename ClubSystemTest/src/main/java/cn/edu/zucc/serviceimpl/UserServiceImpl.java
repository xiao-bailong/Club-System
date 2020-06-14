package cn.edu.zucc.serviceimpl;

import cn.edu.zucc.dao.UserDao;
import cn.edu.zucc.entity.User;
import cn.edu.zucc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public void add(User user) {
        userDao.add(user);
    }
    @Override
    public User check(User user) {
        return userDao.check(user);
    }
    @Override
    public User checkid(String userid) {
        return userDao.checkid(userid);
    }
    @Override
    public User checkname(String username) {
        return userDao.checkname(username);
    }
    @Override
    public int modify(User user) {
        return userDao.modify(user);
    }
    @Override
    public int modify2(User user) {
        return userDao.modify2(user);
    }
    @Override
    public int resetPassword(String userid) {
        return userDao.resetPassword(userid);
    }
    @Override
    public List<User> list() {
        return userDao.list();
    }
    @Override
    public int delete(String userid) {
        return userDao.delete(userid);
    }
    @Override
    public List<User> search(String context) {
        return userDao.search(context);
    }
    @Override
    public int count() {
        return userDao.count();
    }
//    @Override
//    public List<User> listMyClubApprovalUser(List<String> userIdlist) {
//        return userDao.listMyClubApprovalUser(userIdlist);
//    }

}
