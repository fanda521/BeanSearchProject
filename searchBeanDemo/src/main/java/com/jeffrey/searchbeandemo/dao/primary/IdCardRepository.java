package com.jeffrey.searchbeandemo.dao.primary;


import com.jeffrey.searchbeandemo.entity.primary.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:19
 * @description
 */
@Repository
public interface IdCardRepository extends JpaRepository<IdCard,Integer> {
}
