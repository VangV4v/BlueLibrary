package com.vang.publisherservice.command.aggregate;

import com.vang.publisherservice.command.command.CreatePublisherCommand;
import com.vang.publisherservice.command.command.DeletePublisherCommand;
import com.vang.publisherservice.command.command.UpdatePublisherCommand;
import com.vang.publisherservice.command.event.PublisherCreatedEvent;
import com.vang.publisherservice.command.event.PublisherDeletedEvent;
import com.vang.publisherservice.command.event.PublisherUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class PublisherAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String publisherId;
    private String name;
    private String description;
    private String address;
    private int countOfBook;

    public PublisherAggregate() {}

    @CommandHandler
    public PublisherAggregate(CreatePublisherCommand command) {

        PublisherCreatedEvent event = new PublisherCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public PublisherAggregate(UpdatePublisherCommand command) {

        PublisherUpdatedEvent event = new PublisherUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public PublisherAggregate(DeletePublisherCommand command) {

        PublisherDeletedEvent event = new PublisherDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(PublisherCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.publisherId = event.getPublisherId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.address = event.getAddress();
        this.countOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(PublisherUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.publisherId = event.getPublisherId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.address = event.getAddress();
        this.countOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(PublisherDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.publisherId = event.getPublisherId();
    }

}