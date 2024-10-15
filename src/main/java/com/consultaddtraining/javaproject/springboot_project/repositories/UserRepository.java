package com.consultaddtraining.javaproject.springboot_project.repositories;

import com.consultaddtraining.javaproject.springboot_project.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // ORM queries, (save, find, delete..)
    // SQL queries
//    @Query

}
