package com.jeffrey.searchbeandemo.dao.second;

import com.jeffrey.searchbeandemo.common.cons.CommonConst;
import com.jeffrey.searchbeandemo.entity.second.PeopleManyDataSourceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/9/2
 * @time 18:20
 * @week 星期一
 * @description
 **/
@Repository(value = CommonConst.SECOND_DAO_BEAN_NAME)
public interface PeopleSecondRepository extends JpaRepository<PeopleManyDataSourceVO,Integer> {
    List<PeopleManyDataSourceVO> findAllById(Integer id);
}
