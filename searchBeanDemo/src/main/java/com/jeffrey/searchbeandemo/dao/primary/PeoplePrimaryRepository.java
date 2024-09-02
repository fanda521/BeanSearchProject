package com.jeffrey.searchbeandemo.dao.primary;

import com.jeffrey.searchbeandemo.common.cons.CommonConst;
import com.jeffrey.searchbeandemo.entity.primary.PeopleManyDataSourceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2024/9/2
 * @time 18:14
 * @week 星期一
 * @description
 **/
@Repository(value = CommonConst.PRIMARY_DAO_BEAN_NAME)
public interface PeoplePrimaryRepository extends JpaRepository<PeopleManyDataSourceVO, Integer> {

    List<PeopleManyDataSourceVO> findAllById(Integer id);
}
