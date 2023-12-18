package com.jeffrey.searchbeandemo.entity;

import cn.zhxu.bs.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 10:59
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_people")
public class People {

    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t_name")
    private String name;


    @Column(name = "t_age")
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "t_birthday")
    private Date birthday;

    @Column(name = "t_address")
    private String address;



}
