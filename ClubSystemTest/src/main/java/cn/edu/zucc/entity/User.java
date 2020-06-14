package cn.edu.zucc.entity;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String username;
    private String password;
    private int type;
}
