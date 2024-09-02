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
 * @time 11:09
 * @week 星期一
 * @description
 **/

@SearchBean(tables = " :tables: "

)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleEmbedParams2VO {
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

    @DbField(value = "select c.t_number from t_idcard c where c.t_people_id = t.t_id", alias = "cardNumber")
    private String cardNumber;
}
