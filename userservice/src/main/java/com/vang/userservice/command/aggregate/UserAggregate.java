package com.vang.userservice.command.aggregate;

import com.vang.userservice.command.command.CreateUserCommand;
import com.vang.userservice.command.command.DeleteUserCommand;
import com.vang.userservice.command.command.UpdateUserCommand;
import com.vang.userservice.command.event.UserCreatedEvent;
import com.vang.userservice.command.event.UserDeletedEvent;
import com.vang.userservice.command.event.UserUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class UserAggregate {

    @AggregateIdentifier
    private Long generateAggregateId;
    private String userId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String email;
    private String phone;
    private String password;
    private int type;
    private String createdDate;
    private String lastModified;
    private String role;
    private String avatar;
    private int activeStatus;
    private byte[] imageData;

    public UserAggregate() {}

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {

        UserCreatedEvent event = new UserCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(UpdateUserCommand command) {

        UserUpdatedEvent event = new UserUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(DeleteUserCommand command) {

        UserUpdatedEvent event = new UserUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(UserCreatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.type = event.getType();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.role = event.getRole();
        this.avatar = event.getAvatar();
        this.activeStatus = event.getActiveStatus();
    }

    @EventSourcingHandler
    public void handle(UserUpdatedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.password = event.getPassword();
        this.type = event.getType();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        this.role = event.getRole();
        this.avatar = event.getAvatar();
        this.activeStatus = event.getActiveStatus();
        this.imageData = event.getImageData();
    }

    @EventSourcingHandler
    public void handle(UserDeletedEvent event) {

        this.generateAggregateId = event.getGenerateAggregateId();
        this.userId = event.getUserId();
        this.avatar = event.getAvatar();
    }

}