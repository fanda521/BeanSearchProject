package com.jeffrey.searchbeandemo.entity.primary;

import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/9/2
 * @time 17:29
 * @week 星期一
 * @description 多数据源
 **/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="t_people")
@SearchBean(tables = "t_people",dataSource = "primaryDataSource")
public class PeopleManyDataSourceVO {

    @DbField("t_id")
    @Id
    @Column(name = "t_id")
    private Integer id;


    @DbField("t_name")
    @Column(name = "t_name")
    private String name;


    @DbField("t_age")
    @Column(name = "t_age")
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DbField("t_birthday")
    @Column(name = "t_birthday")
    private Date birthday;

    @DbField("t_address")
    @Column(name = "t_address")
    private String address;
}
