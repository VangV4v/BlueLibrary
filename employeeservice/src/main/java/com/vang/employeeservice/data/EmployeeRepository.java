package com.vang.employeeservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employees, String> {

    @Query(value = "select count(empl.Username) from Employees empl where empl.Username = ?1 ", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(empl.Email) from Employees empl where empl.Email = ?1 ", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select count(empl.Phone) from Employees empl where empl.Phone = ?1 ", nativeQuery = true)
    long countByPhone(String phone);

    @Query(value = "select count(empl.Email) from Employees empl where empl.Email = ?1 and empl.Email != ?2", nativeQuery = true)
    long countByEmailAndOld(String email, String old);

    @Query(value = "select count(empl.Phone) from Employees empl where empl.Phone = ?1 and empl.Phone != ?2", nativeQuery = true)
    long countByPhoneAndOld(String phone, String old);

    @Query(value = "select top 1 empl.employeeId from Employees empl order by empl.employeeId desc ", nativeQuery = true)
    String getLatestEmployeeId();

    @Query(value = "select empl.Password from Employees empl where empl.Username = ?1 and empl.ActiveStatus = 1", nativeQuery = true)
    String getPasswordByUsername(String username);
}