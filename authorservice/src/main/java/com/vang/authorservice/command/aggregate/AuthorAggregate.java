package com.vang.authorservice.command.aggregate;

import com.vang.authorservice.command.command.CreateAuthorCommand;
import com.vang.authorservice.command.command.DeleteAuthorCommand;
import com.vang.authorservice.command.command.UpdateAuthorCommand;
import com.vang.authorservice.command.event.AuthorCreatedEvent;
import com.vang.authorservice.command.event.AuthorDeletedEvent;
import com.vang.authorservice.command.event.AuthorUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class AuthorAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String authorId;
    private String fullName;
    private String dateOfBirth;
    private String country;
    private int countOfBook;

    public AuthorAggregate() {}

    @CommandHandler
    public AuthorAggregate(CreateAuthorCommand command) {

        AuthorCreatedEvent event = new AuthorCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AuthorAggregate(UpdateAuthorCommand command) {

        AuthorUpdatedEvent event = new AuthorUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AuthorAggregate(DeleteAuthorCommand command) {

        AuthorDeletedEvent event = new AuthorDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AuthorCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.authorId = event.getAuthorId();
        this.fullName = event.getFullName();
        this.dateOfBirth = event.getDateOfBirth();
        this.country = event.getCountry();
        this.countOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(AuthorUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.authorId = event.getAuthorId();
        this.fullName = event.getFullName();
        this.dateOfBirth = event.getDateOfBirth();
        this.country = event.getCountry();
        this.countOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(AuthorDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.authorId = event.getAuthorId();
    }
}