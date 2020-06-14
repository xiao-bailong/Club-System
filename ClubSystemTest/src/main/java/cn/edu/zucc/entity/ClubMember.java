package cn.edu.zucc.entity;

import lombok.Data;

@Data
public class ClubMember{
    private int id;
    private int clubId;
    private String userId;
    private String username;
    private int state;
}
