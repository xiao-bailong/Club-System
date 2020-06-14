package cn.edu.zucc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Activity {
    private int activityid;
    private String title;
    private String description;
    private int clubId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//前后端传输时间的时候自动转换类型
    private Timestamp starttime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp endtime;
    private String location;
    private int amount;
}
