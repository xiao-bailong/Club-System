package cn.edu.zucc.dto;

import cn.edu.zucc.entity.Activity;

import java.util.List;

public class ActivityResult {
    //状态码： 0--成功 1--失败
    private int status;
    //消息
    private String msg;
    //数据
    private List<Activity> activityList;

    public ActivityResult(int status, String msg,List<Activity> activityList) {
        this.status = status;
        this.msg = msg;
        this.activityList = activityList;
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

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public String toString() {
        return "ActivityResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", activityList=" + activityList +
                '}';
    }
}
