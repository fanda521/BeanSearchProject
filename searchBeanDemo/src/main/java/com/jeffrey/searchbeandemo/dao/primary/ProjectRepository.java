package com.jeffrey.searchbeandemo.dao.primary;


import com.jeffrey.searchbeandemo.entity.primary.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/13 14:14
 * @description
 */
public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
