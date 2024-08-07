package com.vang.userservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value = "select count(u.userId) from Users u where u.username = ?1", nativeQuery = true)
    long getCountByUsername(String username);

    @Query(value = "select count(u.userId) from Users u where u.email = ?1", nativeQuery = true)
    long getCountByEmail(String email);

    @Query(value = "select count(u.userId) from Users u where u.phone = ?1", nativeQuery = true)
    long getCountByPhone(String phone);

    @Query(value = "select top 1 u.userId from Users u order by u.userId desc", nativeQuery = true)
    String getLatestId();

    @Query(value = "select u.password from Users u where (u.username = ?1 or u.phone = ?1 or u.email = ?1) and u.activeStatus = 1", nativeQuery = true)
    String getPasswordByUsername(String username);

    @Query(value = "select u.userId,u.firstName,u.lastName,u.dateOfBirth,u.username,u.email,u.phone,u.password,u.type,u.createdDate,u.lastModified,u.role,u.avatar,u.activeStatus,u.countOfExpired from Users u where u.username = ?1 and u.activeStatus = 1", nativeQuery = true)
    Users findByUsername(String username);
}