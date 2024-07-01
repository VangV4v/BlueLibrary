package com.vang.bookservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Books, String> {

    @Query(value = "select top 1 bk.authorId from Books bk order by bk.authorId desc", nativeQuery = true)
    String getLatestId();
}