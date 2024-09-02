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
 * @time 10:39
 * @week 星期一
 * @description
 **/

@SearchBean(tables = " t_people t " +
        " left join (select u.t_id,u.t_number,u.t_people_id from t_idcard u) as u on u.t_people_id = t.t_id"

)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleManyTableVO {
    @DbField("t.t_id")
    private Integer id;


    @DbField("t.t_name")
    private String name;


    @DbField("t.t_age")
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DbField("t.t_birthday")
    private Date birthday;

    @DbField("t.t_address")
    private String address;

    @DbField("u.t_number")
    private String cardNumber;
}
