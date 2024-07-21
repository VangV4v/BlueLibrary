package com.vang.publisherservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PublisherRepository extends JpaRepository<Publishers, String> {

    @Query(value = "select top 1 p.PublisherId from Publishers p order by p.PublisherId DESC", nativeQuery = true)
    String getLatestPublisherId();

    @Modifying
    @Query(value = "update Publishers set CountOfBook = CountOfBook + 1 where PublisherId = ?1", nativeQuery = true)
    int updateIncrementByPublisherId(String publisherId);

    @Modifying
    @Query(value = "update Publishers set CountOfBook = CountOfBook - 1 where PublisherId = ?1", nativeQuery = true)
    int updateDecrementByPublisherId(String publisherId);
}