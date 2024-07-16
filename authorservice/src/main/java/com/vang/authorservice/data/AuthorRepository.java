package com.vang.authorservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Authors, String> {

    @Query(value = "select top 1 a.authorId from Authors a order by a.AuthorId DESC ", nativeQuery = true)
    String getLatestId();

    @Modifying
    @Query(value = "update Authors set CountOfBook = CountOfBook + 1 where AuthorId = ?1", nativeQuery = true)
    int updateByAuthorId(String authorId);
}