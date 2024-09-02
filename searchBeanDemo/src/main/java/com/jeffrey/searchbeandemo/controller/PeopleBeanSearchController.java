package com.jeffrey.searchbeandemo.controller;

import cn.zhxu.bs.MapSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapUtils;
import com.jeffrey.searchbeandemo.common.CustomerHttpServletRequest;
import com.jeffrey.searchbeandemo.dao.primary.PeoplePrimaryRepository;
import com.jeffrey.searchbeandemo.dao.second.PeopleSecondRepository;
import com.jeffrey.searchbeandemo.entity.primary.PeopleManyDataSourceVO;
import com.jeffrey.searchbeandemo.entity.vo.PeoPleIdCardVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleBuildSqlVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleDTO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams2VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams3VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams4VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParamsVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleLogicGroupVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleManyTableVO;
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
import java.util.Iterator;
import java.util.List;
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

    @Autowired
    private PeoplePrimaryRepository peoplePrimaryRepository;

    @Autowired
    private PeopleSecondRepository peopleSecondRepository;

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


    /**
     * 多表关联
     */
    @GetMapping("/people/manyTables")
    @Operation(summary = "people-条件分页查询-多表关联", description = "people-条件分页查询-多表关联")
    public Page<PeopleManyTableVO> getPeopleConditionManyTables(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleConditionManyTables(customerHttpServletRequest);
    }


    /**
     * 嵌入参数
     */

    @GetMapping("/people/embedParams")
    @Operation(summary = "people-条件分页查询-嵌入参数", description = "people-条件分页查询-嵌入参数")
    public Page<PeopleEmbedParamsVO> getPeopleEmbedParams(HttpServletRequest request) {
        Map<String, String[]> map = new HashMap<>();
        map.put("peopleId",new String[]{"1"});
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request,map);
        return peopleService.getPeopleEmbedParams(customerHttpServletRequest);
    }

    @GetMapping("/people/embedParams2")
    @Operation(summary = "people-条件分页查询-嵌入参数2", description = "people-条件分页查询-嵌入参数2")
    public Page<PeopleEmbedParams2VO> getPeopleEmbedParams2(HttpServletRequest request) {
        Map<String, String[]> map = new HashMap<>();
        // tables 不能再使用参数，识别不了
        map.put("tables",new String[]{"(select * from t_people t where t.t_id > 1) t"});
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request,map);
        return peopleService.getPeopleEmbedParams2(customerHttpServletRequest);
    }

    @GetMapping("/people/embedParams3")
    @Operation(summary = "people-条件分页查询-嵌入参数3", description = "people-条件分页查询-嵌入参数3")
    public Page<PeopleEmbedParams3VO> getPeopleEmbedParams3(HttpServletRequest request) {
        Map<String, String[]> map = new HashMap<>();
        // 双重参数
        map.put("tables",new String[]{"(select * from t_people) t"});
        map.put("peopleId",new String[]{"1"});
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request,map);
        return peopleService.getPeopleEmbedParams3(customerHttpServletRequest);
    }

    @GetMapping("/people/embedParams4")
    @Operation(summary = "people-条件分页查询-嵌入参数4", description = "people-条件分页查询-嵌入参数4")
    public Page<PeopleEmbedParams4VO> getPeopleEmbedParams4(HttpServletRequest request) {
        Map<String, String[]> map = new HashMap<>();
        // 三重参数
        map.put("tables",new String[]{"(select * from t_people) t"});
        map.put("peopleId",new String[]{"1"});
        map.put("field1",new String[]{"t.t_age"});
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request,map);
        return peopleService.getPeopleEmbedParams4(customerHttpServletRequest);
    }

    /**
     * 省略注解，默认值
     */
    @GetMapping("/people/DefaultValue")
    @Operation(summary = "people-省略注解，默认值", description = "people-省略注解，默认值")
    public Page<PeopleDTO> getPeopleDefaultValue(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleDefaultValue(customerHttpServletRequest);
    }

    /**
     * 自定义sql
     */
    @GetMapping("/people/buildSql")
    @Operation(summary = "people-自定义sql", description = "people-自定义sql")
    public Page<PeopleBuildSqlVO> getPeopleBuildSql(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleBuildSql(customerHttpServletRequest);
    }

    /**
     * 逻辑分组
     */

    @GetMapping("/people/logicGroup")
    @Operation(summary = "people-逻辑分组", description = "people-逻辑分组")
    public Page<PeopleLogicGroupVO> getPeopleLogicGroup(HttpServletRequest request) {
        CustomerHttpServletRequest customerHttpServletRequest = new CustomerHttpServletRequest(request);
        return peopleService.getPeopleLogicGroup(customerHttpServletRequest);
    }

    /**
     * 多数据源
     */
    @GetMapping("/people/manyDataSourcePrimary")
    @Operation(summary = "people-多数据源", description = "people-多数据源")
    public List<PeopleManyDataSourceVO> getPeopleManyDataSource(HttpServletRequest request) {
        return peoplePrimaryRepository.findAllById(2);
    }

    @GetMapping("/people/manyDataSourceSecond")
    @Operation(summary = "people-多数据源second", description = "people-多数据源second")
    public List<com.jeffrey.searchbeandemo.entity.second.PeopleManyDataSourceVO> getPeopleManyDataSourceSecond(HttpServletRequest request) {
        return peopleSecondRepository.findAllById(2);
    }


}
