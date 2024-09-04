package com.jeffrey.searchbeandemo.controller;

import com.jeffrey.searchbeandemo.dao.primary.PeopleRepository;
import com.jeffrey.searchbeandemo.entity.primary.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2023/12/18
 * @time 11:47
 * @week 星期一
 * @description
 **/
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @GetMapping("/getAll")
    public List<People> getPeopleList() {
        List<People> all = peopleRepository.findAll();
        return all;
    }



}
