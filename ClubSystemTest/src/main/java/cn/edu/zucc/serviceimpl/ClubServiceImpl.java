package cn.edu.zucc.serviceimpl;

import cn.edu.zucc.dao.ClubDao;
import cn.edu.zucc.entity.Club;
import cn.edu.zucc.service.ClubService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("clubService")
public class ClubServiceImpl implements ClubService {
    @Resource
    private ClubDao clubDao;
    @Override
    public void createClub(Club club) {
        clubDao.createClub(club);
    }
    @Override
    public Club check(String name) {
        return clubDao.check(name);
    }
    @Override
    public Club check2(int clubId) {
        return clubDao.check2(clubId);
    }
    @Override
    public List<Club> listClub(int state) {
        return clubDao.listClub(state);
    }
    @Override
    public int upadte(Club club) {
        return clubDao.upadte(club);
    }
    @Override
    public int update2(Club club) {
        return clubDao.update2(club);
    }
    @Override
    public List<Club> listMyclub(List<Integer>  clubIdlist) {
        return clubDao.listMyclub(clubIdlist);
    }
    @Override
    public List<Club> listMyclub2(String ownerId) {
        return clubDao.listMyclub2(ownerId);
    }
    @Override
    public int addMember(int clubId) {
        return clubDao.addMember(clubId);
    }
    @Override
    public int deleteMember(int clubId) {
        return clubDao.deleteMember(clubId);
    }
    @Override
    public int count1() {
        return clubDao.count1();
    }
    @Override
    public int count2() {
        return clubDao.count2();
    }
    @Override
    public int count3(String ownerId) {
        return clubDao.count3(ownerId);
    }
    @Override
    public int replace(Club club) {
        return clubDao.replace(club);
    }
}
