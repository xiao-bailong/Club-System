package cn.edu.zucc.dao;

import cn.edu.zucc.entity.User;

import java.util.List;

public interface UserDao {
    public void add(User user);
    public User check(User user);
    public User checkid(String userid);
    public User checkname(String username);
    public int modify(User user);
    public int modify2(User user);
    public int resetPassword(String userid);
    public List<User> list();
    public int delete(String userid);
    public List<User> search(String context);
//    public List<User> listMyClubApprovalUser(List<String>  userIdlist);
    public int count();
}
