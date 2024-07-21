package com.vang.confirmbookservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Books, String> {


    @Modifying
    @Query(value = "update Books set Status = ?2 where BookId = ?1", nativeQuery = true)
    int updateByBookIdAndStatus(String bookId, int status);
}