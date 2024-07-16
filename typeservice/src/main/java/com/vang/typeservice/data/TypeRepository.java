package com.vang.typeservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Types, String> {

    @Query(value = "select top 1 t.TypeId from Types t order by t.TypeId DESC", nativeQuery = true)
    String getLatestId();

    @Modifying
    @Query(value = "update Types set CountOfBook = CountOfBook + 1 where TypeId = ?1", nativeQuery = true)
    int updateByTypeId(String typeId);
}