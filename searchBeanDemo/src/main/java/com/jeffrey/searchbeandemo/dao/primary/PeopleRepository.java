package com.jeffrey.searchbeandemo.dao.primary;


import com.jeffrey.searchbeandemo.entity.primary.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 11:18
 * @description
 */
@Repository(value = "peopleRepository")
public interface PeopleRepository extends JpaRepository<People,Integer> {
}
