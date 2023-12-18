package com.jeffrey.searchbeandemo.entity.vo;

import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2023/12/18
 * @time 12:18
 * @week 星期一
 * @description
 **/
@SearchBean(tables = "t_people")
public class PeopleVO {

    @DbField("t_id")
    private Integer id;


    @DbField("t_name")
    private String name;


    @DbField("t_age")
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DbField("t_birthday")
    private Date birthday;

    @DbField("t_address")
    private String address;
}
