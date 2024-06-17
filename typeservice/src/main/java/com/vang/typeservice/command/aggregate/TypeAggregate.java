package com.vang.typeservice.command.aggregate;

import com.vang.typeservice.command.command.CreateTypeCommand;
import com.vang.typeservice.command.command.DeleteTypeCommand;
import com.vang.typeservice.command.command.UpdateTypeCommand;
import com.vang.typeservice.command.event.TypeCreatedEvent;
import com.vang.typeservice.command.event.TypeDeleteEvent;
import com.vang.typeservice.command.event.TypeUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class TypeAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String TypeId;
    private String TypeName;
    private String TypeDescription;
    private int CountOfBook;

    public TypeAggregate() {}

    @CommandHandler
    public TypeAggregate(CreateTypeCommand command) {

        TypeCreatedEvent event = new TypeCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public TypeAggregate(UpdateTypeCommand command) {

        TypeUpdatedEvent event = new TypeUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public TypeAggregate(DeleteTypeCommand command) {

        TypeDeleteEvent event = new TypeDeleteEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(TypeCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.TypeId = event.getTypeId();
        this.TypeName = event.getTypeName();
        this.TypeDescription = event.getTypeDescription();
        this.CountOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(TypeUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.TypeId = event.getTypeId();
        this.TypeName = event.getTypeName();
        this.TypeDescription = event.getTypeDescription();
        this.CountOfBook = event.getCountOfBook();
    }

    @EventSourcingHandler
    public void handle(TypeDeleteEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.TypeId = event.getTypeId();
    }
}