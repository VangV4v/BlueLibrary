package com.vang.employeeservice.command.aggregate;

import com.vang.employeeservice.command.command.CreateEmployeeCommand;
import com.vang.employeeservice.command.command.DeleteEmployeeCommand;
import com.vang.employeeservice.command.command.UpdateEmployeeCommand;
import com.vang.employeeservice.command.event.EmployeeCreatedEvent;
import com.vang.employeeservice.command.event.EmployeeDeletedEvent;
import com.vang.employeeservice.command.event.EmployeeUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class EmployeeAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String employeeId;
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
    private String avatar;
    private int activeStatus;
    private byte[] image;
    private String imageName;

    public EmployeeAggregate() {}

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {

        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public EmployeeAggregate(UpdateEmployeeCommand command) {

        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public EmployeeAggregate(DeleteEmployeeCommand command) {

        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(EmployeeCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.employeeId = event.getEmployeeId();
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
        this.avatar = event.getAvatar();
        this.activeStatus = event.getActiveStatus();
    }

    @EventSourcingHandler
    public void handle(EmployeeUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.employeeId = event.getEmployeeId();
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
        this.avatar = event.getAvatar();
        this.activeStatus = event.getActiveStatus();
        this.image = event.getImage();
        this.imageName = event.getImageName();
    }

    @EventSourcingHandler
    public void handle(EmployeeDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.employeeId = event.getEmployeeId();
        this.avatar = event.getAvatar();
    }

}