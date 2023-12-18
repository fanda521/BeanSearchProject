package com.jeffrey.searchbeandemo.controller;

import cn.zhxu.bs.MapSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapUtils;
import com.jeffrey.searchbeandemo.entity.People;
import com.jeffrey.searchbeandemo.entity.vo.PeoPleIdCardVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
