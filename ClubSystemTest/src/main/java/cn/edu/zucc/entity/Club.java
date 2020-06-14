package cn.edu.zucc.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Club {
    private int clubId;
    private Date createTime;
    private String ownerId;
    private String description;
    private String name;
    private int state;
    private int amount;
    private String notice;
}
