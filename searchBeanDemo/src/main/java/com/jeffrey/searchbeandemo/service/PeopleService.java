package com.jeffrey.searchbeandemo.service;

import cn.zhxu.bs.util.MapUtils;
import com.jeffrey.searchbeandemo.common.CustomerHttpServletRequest;
import com.jeffrey.searchbeandemo.entity.vo.PeopleBuildSqlVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleDTO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams2VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams3VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParams4VO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleEmbedParamsVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleLogicGroupVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleManyDataSourceDTO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleManyTableVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonSelectVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonWhereVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/8/30
 * @time 18:10
 * @week 星期五
 * @description
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class PeopleService {

    private final CommonSearchBeanService commonSearchBeanService;


    public Page<PeopleVO> getPeopleCondition(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleVO.class);
    }

    public Page<PeopleSonSelectVO> getPeopleConditionSonSelect(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleSonSelectVO.class);
        /**
         * sql: [select t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4,
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) c_5
         * from t_people t where (t.t_name like ?) limit ?, ?]
         * params: [%j%, 0, 100]
         */
    }

    public Page<PeopleSonWhereVO> getPeopleConditionSonWhere(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleSonWhereVO.class);
        /**
         * sql: [select t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4, (select c.t_number from t_idcard c where c.t_people_id = t.t_id)
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * limit ?, ?]
         * params: [0, 100]
         */


        /**
         * 再加上去重
         * sql: [select distinct t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4, (select c.t_number from t_idcard c where c.t_people_id = t.t_id)
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * limit ?, ?]
         * params: [0, 100]
         */

        /**
         * 再加上分组
         * sql: [select distinct t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4,
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) c_5 from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * group by t.t_age having (sum(t.t_age) > 10)
         * limit ?, ?]
         * params: [0, 100]
         */

        /** 再加上别名
         * sql: [select distinct (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * group by t.t_age
         * having (sum(t.t_age) > 10)
         * limit ?, ?]
         * params: [0, 100]
         */

        /** 再加上默认的固定排序
         * sql: [select distinct (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * group by t.t_age having (sum(t.t_age) > 10)
         * order by t_id desc limit ?, ?]
         * params: [0, 100]
         */

        /**
         * 再加上自定义的排序，会覆盖前面默认的固定排序
         * sql: [select distinct (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * group by t.t_age having (sum(t.t_age) > 10)
         * order by cardNumber desc limit ?, ?]
         * params: [0, 100]
         *
         */
    }

    public Page<PeopleManyTableVO> getPeopleConditionManyTables(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleManyTableVO.class);

        /** 子查询的关联
         * ql: [select t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4, u.t_number c_5
         * from t_people t  left join (select u.t_id,u.t_number,u.t_people_id from t_idcard u) as u
         * on u.t_people_id = t.t_id
         * limit ?, ?]
         * params: [0, 100]
         */


    }

    public Page<PeopleEmbedParamsVO> getPeopleEmbedParams(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleEmbedParamsVO.class);

        /**
         * sql: [select
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from (select * from t_people t where t.t_id > ?) t
         * limit ?, ?]
         * params: [1, 0, 100]
         */
    }

    public Page<PeopleEmbedParams2VO> getPeopleEmbedParams2(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleEmbedParams2VO.class);

        /**
         * sql: [select
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from (select * from t_people t where t.t_id > ?) t
         * limit ?, ?]
         * params: [1, 0, 100]
         */
    }

    public Page<PeopleEmbedParams3VO> getPeopleEmbedParams3(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleEmbedParams3VO.class);
        /**
         * sql: [select
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from (select * from t_people) t
         * where (t.t_id > ?)
         * limit ?, ?]
         * params: [1, 0, 100]
         */
    }

    public Page<PeopleEmbedParams4VO> getPeopleEmbedParams4(CustomerHttpServletRequest customerHttpServletRequest) {
        return  commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest, PeopleEmbedParams4VO.class);

        /**
         * sql: [select
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) cardNumber,
         * t.t_id c_1, t.t_name c_2, t.t_age c_3, t.t_birthday c_4, t.t_address c_5
         * from (select * from t_people) t
         * where (t.t_id > ?)
         * limit ?, ?]
         * params: [1, 0, 100]
         */
    }

    public Page<PeopleDTO> getPeopleDefaultValue(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest,PeopleDTO.class);
        /**
         * 返回的字段没有首字母大写
         * {
         *             "name": "jeffrey",
         *             "birthday": "2023-09-14 03:44:46",
         *             "address": "江西南昌",
         *             "tid": 1,
         *             "tage": 23
         *         }
         */
    }

    public Page<PeopleBuildSqlVO> getPeopleBuildSql(CustomerHttpServletRequest customerHttpServletRequest) {
        Map<String, Object> params = MapUtils.builder()
                // 生成 SQL 条件：id < ? or age > ?，两个占位符参数分别为：100，10
                .field(PeopleBuildSqlVO::getId, PeopleBuildSqlVO::getAge).sql("$1 < ? or $2 > ?", 100, 10)
                .build();
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest, PeopleBuildSqlVO.class,params);

        /**
         * sql: [select t_id c_0, t_name c_1, t_age c_2, t_birthday c_3, t_address c_4
         * from t_people
         * where (t_id < ? or t_age > ?)
         * limit ?, ?]
         * params: [100, 10, 0, 100]
         */


    }

    public Page<PeopleLogicGroupVO> getPeopleLogicGroup(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest, PeopleLogicGroupVO.class);

        /**
         * http://localhost:8888/beanSearchPeople/people/logicGroup?page=0&size=100&A.id=1&A.id-op=gt&B.name=je&B.name-op=ct&C.age=5&gexpr=(A%7CB)%26C&C.age-op=gt
         * sql: [select t_id c_0, t_name c_1, t_age c_2, t_birthday c_3, t_address c_4
         * from t_people
         * where (((t_id > ?) or (t_name like ?)) and (t_age > ?))
         * limit ?, ?]
         * params: [1, %je%, 5, 0, 100]
         */
    }

    public Page<PeopleManyDataSourceDTO> getPeopleManyDataSource(CustomerHttpServletRequest customerHttpServletRequest) {
        return commonSearchBeanService.searchAndWarpToJpaPage(customerHttpServletRequest, PeopleManyDataSourceDTO.class);
    }
}
