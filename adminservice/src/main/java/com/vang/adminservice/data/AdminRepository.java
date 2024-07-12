package com.vang.adminservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admins, String> {

    @Query(value = "select count(ad.Username) from Admins ad where ad.Username = ?1", nativeQuery = true)
    long getCountByUsername(String username);

    @Query(value = "select count(ad.Email) from Admins ad where ad.Email = ?1", nativeQuery = true)
    long getCountByEmail(String email);

    @Query(value = "select count(ad.Phone) from Admins ad where ad.Phone = ?1", nativeQuery = true)
    long getCountByPhone(String phone);

    @Query(value = "select top 1 ad.AdminId from Admins ad order by ad.AdminId DESC", nativeQuery = true)
    String getLatestId();

    @Query(value = "select ad.Password from Admins ad where (ad.Username = ?1 or ad.Email = ?1 or ad.Phone = ?1) and ad.ActiveStatus = 1", nativeQuery = true)
    String getPasswordByUsername(String username);
}