package com.vang.borrowservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrows, Integer> {
}