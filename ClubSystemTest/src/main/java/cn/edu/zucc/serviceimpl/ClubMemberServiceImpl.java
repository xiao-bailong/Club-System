package cn.edu.zucc.serviceimpl;

import cn.edu.zucc.dao.ClubMemberDao;
import cn.edu.zucc.entity.ClubMember;
import cn.edu.zucc.service.ClubMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("clubMemberService")
public class ClubMemberServiceImpl implements ClubMemberService {
    @Resource
    private ClubMemberDao clubMemberDao;
    @Override
    public int addClubMember(ClubMember clubMember) {
        return clubMemberDao.addClubMember(clubMember);
    }
    @Override
    public int deleteClubMember(int id) {
        return clubMemberDao.deleteClubMember(id);
    }
    @Override
    public ClubMember check(ClubMember clubMember) {
        return clubMemberDao.check(clubMember);
    }
    @Override
    public List<Integer> listMyclub(String userId) {
        return clubMemberDao.listMyclub(userId);
    }
    @Override
    public List<ClubMember> selectNoInUserByClubid(int clubId) {
        return clubMemberDao.selectNoInUserByClubid(clubId);
    }
    @Override
    public int changeState(ClubMember clubMember) {
        return clubMemberDao.changeState(clubMember);
    }
    @Override
    public List<ClubMember> selectMemberByClubid(ClubMember clubMember) {
        return clubMemberDao.selectMemberByClubid(clubMember);
    }
}
