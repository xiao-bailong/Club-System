package cn.edu.zucc.service;

import cn.edu.zucc.entity.ClubMember;

import java.util.List;

public interface ClubMemberService {
    public int addClubMember(ClubMember clubMember);
    public int deleteClubMember(int id);
    public ClubMember check(ClubMember clubMember);
    public List<Integer>  listMyclub(String userId);
    public List<ClubMember> selectNoInUserByClubid(int clubId);
    public int changeState(ClubMember clubMember);
    public List<ClubMember> selectMemberByClubid(ClubMember clubMember);
}
