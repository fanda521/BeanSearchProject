package com.jeffrey.searchbeandemo.controller;

import cn.zhxu.bs.MapSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapUtils;
import com.jeffrey.searchbeandemo.common.CustomerHttpServletRequest;
import com.jeffrey.searchbeandemo.entity.vo.PeoPleIdCardVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonSelectVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonWhereVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleVO;
import com.jeffrey.searchbeandemo.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2023/12/18
 * @time 12:06
 * @week 星期一
 * @description
 **/

@RestController
@RequestMapping("/beanSearchPeople")
public class PeopleBeanSearchController {

    @Autowired
    private MapSearcher mapSearcher;

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/index")
    public SearchResult<Map<String, Object>> index(HttpServletRequest request) {
        // 一行代码，实现一个用户检索接口（MapUtils.flat 只是收集前端的请求参数）
        return mapSearcher.search(PeopleVO.class, MapUtils.flat(request.getParameterMap()));
    }

    @GetMapping("/peopleAndIdCard")
    public SearchResult<Map<String, Object>> peopleAndIdCard(HttpServletRequest request) {
        // 一行代码，实现一个用户检索接口（MapUtils.flat 只是收集前端的请求参数）
        return mapSearcher.search(PeoPleIdCardVO.class, MapUtils.flat(request.getParameterMap()));
    }


    @GetMapping("/people")
    @Operation(summary = "people-条件分页查询", description = "people-条件分页查询")
    public Page<PeopleVO> getPeopleCondition(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleCondition(customerHttpServletRequest);
    }

    @GetMapping("/people/sonSelect")
    @Operation(summary = "people-条件分页查询sonSelect", description = "people-条件分页查询sonSelect")
    public Page<PeopleSonSelectVO> getPeopleConditionSonSelect(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleConditionSonSelect(customerHttpServletRequest);
    }


    @GetMapping("/people/sonWhere")
    @Operation(summary = "people-条件分页查询sonWhere", description = "people-条件分页查询sonWhere")
    public Page<PeopleSonWhereVO> getPeopleConditionSonWhere(HttpServletRequest request) {
        Map<String, String[]> map = new HashMap<>();
        map.put("orderBy",new String[]{"cardNumber:desc"});
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request,map);
        return peopleService.getPeopleConditionSonWhere(customerHttpServletRequest);
    }



}
