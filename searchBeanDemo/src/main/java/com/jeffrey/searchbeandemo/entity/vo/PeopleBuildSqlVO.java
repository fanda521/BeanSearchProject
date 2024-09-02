package com.jeffrey.searchbeandemo.entity.vo;

import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/9/2
 * @time 14:26
 * @week 星期一
 * @description 自定义sql
 **/

@SearchBean(tables = "t_people")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleBuildSqlVO {

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
