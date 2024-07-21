package com.vang.borrowservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrows, Integer> {

    @Query(value = "select b.BorrowId,b.CreatedDate,b.LastModified,b.ReturnDate,b.BookId,b.BookDetail,b.UserId,b.UserDetail,b.ConfirmStatus from Borrows b where b.UserId = ?1", nativeQuery = true)
    List<Borrows> findAllByUserid(String userId);
}