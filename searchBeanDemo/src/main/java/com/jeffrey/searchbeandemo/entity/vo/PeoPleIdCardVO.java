package com.jeffrey.searchbeandemo.entity.vo;

import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2023/12/18
 * @time 12:37
 * @week 星期一
 * @description
 **/

@SearchBean(tables = " t_people p left join t_idcard c on c.t_people_id = p.t_id ")
public class PeoPleIdCardVO {
    @DbField("p.t_id")
    private Integer id;


    @DbField("p.t_name")
    private String name;


    @DbField("p.t_age")
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DbField("p.t_birthday")
    private Date birthday;

    @DbField("p.t_address")
    private String address;


    @DbField("c.t_number")
    private String number;

    @DbField("c.t_id")
    private int idCardId;
}
