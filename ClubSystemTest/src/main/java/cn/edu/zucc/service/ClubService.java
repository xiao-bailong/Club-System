package cn.edu.zucc.service;

import cn.edu.zucc.entity.Club;

import java.util.List;

public interface ClubService {
    public void createClub(Club club);
    public Club check(String name);
    public Club check2(int clubId);
    public List<Club> listClub(int state);
    public int upadte(Club club);
    public int update2(Club club);
    public List<Club> listMyclub(List<Integer>  clubIdlist);
    public List<Club> listMyclub2(String ownerId);
    public int addMember(int clubId);
    public int deleteMember(int clubId);
    public int count1();
    public int count2();
    public int count3(String ownerId);
    public int replace(Club club);
}
