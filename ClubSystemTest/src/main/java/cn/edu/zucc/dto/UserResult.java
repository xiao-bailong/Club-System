package cn.edu.zucc.dto;

import cn.edu.zucc.entity.User;

public class UserResult {
    //状态码： 0--成功 1--失败
    private int status;
    //消息
    private String msg;
    //数据
    private User user;

    public UserResult(int status, String msg, User user) {
        this.status = status;
        this.msg = msg;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "UserResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", user=" + user +
                '}';
    }
}
