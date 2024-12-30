package com.example.Comerstone.repository;

import com.example.Comerstone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    //String updateUserentityLastLoginTime = "update userentity set last_login_time = NOW() where username = :username";

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}