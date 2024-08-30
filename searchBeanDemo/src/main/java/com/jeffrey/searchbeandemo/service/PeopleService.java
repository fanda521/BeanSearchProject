package com.jeffrey.searchbeandemo.service;

import com.jeffrey.searchbeandemo.common.CustomerHttpServletRequest;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonSelectVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleSonWhereVO;
import com.jeffrey.searchbeandemo.entity.vo.PeopleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
         * 加上去重
         * sql: [select distinct t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4, (select c.t_number from t_idcard c where c.t_people_id = t.t_id)
         * from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * limit ?, ?]
         * params: [0, 100]
         */

        /**
         * 加上分组
         * sql: [select distinct t.t_id c_0, t.t_name c_1, t.t_age c_2, t.t_birthday c_3, t.t_address c_4,
         * (select c.t_number from t_idcard c where c.t_people_id = t.t_id) c_5 from t_people t
         * where ((select c.t_id from t_idcard c where c.t_people_id = t.t_id) >= 2)
         * group by t.t_age having (sum(t.t_age) > 10)
         * limit ?, ?]
         * params: [0, 100]
         */
    }
}
