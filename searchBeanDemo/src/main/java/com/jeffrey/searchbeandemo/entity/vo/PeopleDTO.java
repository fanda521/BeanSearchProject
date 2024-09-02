package com.jeffrey.searchbeandemo.entity.vo;

import cn.zhxu.bs.bean.DbField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/9/2
 * @time 11:51
 * @week 星期一
 * @description 默认值缺省
 **/


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleDTO {

    private Integer tId;


    @DbField("t_name")
    private String name;


    private int tAge;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DbField("t_birthday")
    private Date birthday;

    @DbField("t_address")
    private String address;

}
