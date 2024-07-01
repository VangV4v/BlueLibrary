package com.vang.bookservice.command.aggregate;

import com.vang.bookservice.command.command.CreateBookCommand;
import com.vang.bookservice.command.command.DeleteBookCommand;
import com.vang.bookservice.command.command.UpdateBookCommand;
import com.vang.bookservice.command.event.BookCreatedEvent;
import com.vang.bookservice.command.event.BookDeletedEvent;
import com.vang.bookservice.command.event.BookUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class BookAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String bookId;
    private String name;
    private String authorId;
    private String authorDetail;
    private String typeId;
    private String typeDetail;
    private String publisherId;
    private String publisherDetail;
    private String description;
    private String image;
    private int quantity;
    private int status;
    private String createdDate;
    private String lastModified;
    private byte[] imageData;

    public BookAggregate() {}

    @CommandHandler
    public BookAggregate(CreateBookCommand command) {

        BookCreatedEvent event = new BookCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand command) {

        BookUpdatedEvent event = new BookUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public BookAggregate(DeleteBookCommand command) {

        BookDeletedEvent event = new BookDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(BookCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.authorId = event.getAuthorId();
        this.authorDetail = event.getAuthorDetail();
        this.typeId = event.getTypeId();
        this.typeDetail = event.getTypeDetail();
        this.publisherId = event.getPublisherId();
        this.publisherDetail = event.getPublisherDetail();
        this.description = event.getDescription();
        this.image = event.getImage();
        this.quantity = event.getQuantity();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.imageData = event.getImageData();
    }

    @EventSourcingHandler
    public void handle(BookUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.authorId = event.getAuthorId();
        this.authorDetail = event.getAuthorDetail();
        this.typeId = event.getTypeId();
        this.typeDetail = event.getTypeDetail();
        this.publisherId = event.getPublisherId();
        this.publisherDetail = event.getPublisherDetail();
        this.description = event.getDescription();
        this.image = event.getImage();
        this.quantity = event.getQuantity();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.imageData = event.getImageData();
    }

    @EventSourcingHandler
    public void handle(BookDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.bookId = event.getBookId();
        this.image = event.getImage();
    }

}