package com.vang.publisherservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublisherRepository extends JpaRepository<Publishers, String> {

    @Query(value = "select top 1 p.PublisherId from Publishers p order by p.PublisherId DESC", nativeQuery = true)
    String getLatestPublisherId();
}