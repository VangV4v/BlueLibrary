package com.vang.adminservice.command.aggregate;

import com.vang.adminservice.command.command.CreateAdminCommand;
import com.vang.adminservice.command.command.DeleteAdminCommand;
import com.vang.adminservice.command.command.UpdateAdminCommand;
import com.vang.adminservice.command.event.AdminCreatedEvent;
import com.vang.adminservice.command.event.AdminDeletedEvent;
import com.vang.adminservice.command.event.AdminUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class AdminAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String adminId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String createdDate;
    private String lastModified;
    private String role;
    private int activeStatus;
    private byte[] image;

    public AdminAggregate() {}

    @CommandHandler
    public AdminAggregate(CreateAdminCommand command) {

        AdminCreatedEvent event = new AdminCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(UpdateAdminCommand command) {

        AdminUpdatedEvent event = new AdminUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(DeleteAdminCommand command) {

        AdminDeletedEvent event = new AdminDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AdminCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.adminId = event.getAdminId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.role = event.getRole();
        this.activeStatus = event.getActiveStatus();
    }

    @EventSourcingHandler
    public void handle(AdminUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.adminId = event.getAdminId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.role = event.getRole();
        this.activeStatus = event.getActiveStatus();
    }

    @EventSourcingHandler
    public void handle(AdminDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.adminId = event.getAdminId();
    }

}