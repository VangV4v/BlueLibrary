package com.vang.typeservice.command.event;

import com.vang.typeservice.data.TypeRepository;
import com.vang.typeservice.data.Types;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeEventsHandler {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeEventsHandler(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @EventHandler
    public void handle(TypeCreatedEvent event) {
        Types types = new Types();
        BeanUtils.copyProperties(event, types);
        typeRepository.save(types);
    }

    @EventHandler
    public void handle(TypeUpdatedEvent event) {
        Types types = new Types();
        BeanUtils.copyProperties(event, types);
        typeRepository.save(types);
    }

    @EventHandler
    public void handle(TypeDeleteEvent event) {
        typeRepository.deleteById(event.getTypeId());
    }
}