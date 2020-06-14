package cn.edu.zucc.entity;

import lombok.Data;

@Data
public class ActivityMember {
    private int id;
    private int activityid;
    private String userId;
    private String username;
    private int state;
}
