package cn.edu.zucc.dto;

import cn.edu.zucc.entity.Club;

import java.util.List;

public class ClubResult {
    //状态码： 0--成功 1--失败
    private int status;
    //消息
    private String msg;
    //数据
    private List<Club> clubList;

    public ClubResult(int status, String msg,List<Club> clubList) {
        this.status = status;
        this.msg = msg;
        this.clubList = clubList;
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

    public List<Club> getClubList() {
        return clubList;
    }

    public void setClubList(List<Club> clubList) {
        this.clubList = clubList;
    }

    @Override
    public String toString() {
        return "ClubResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", clubList=" + clubList +
                '}';
    }
}
