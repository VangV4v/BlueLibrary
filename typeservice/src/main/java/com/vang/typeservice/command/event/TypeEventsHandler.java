package com.vang.typeservice.command.event;

import com.vang.typeservice.common.TypeServiceCommon;
import com.vang.typeservice.data.TypeRepository;
import com.vang.typeservice.data.Types;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeEventsHandler {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeEventsHandler(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @EventHandler
    public void handle(TypeCreatedEvent event) {
        event.setTypeId(autoGenerateId());
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

    private String autoGenerateId() {

        String latestId = typeRepository.getLatestId();
        int id;
        if(StringUtils.isEmpty(latestId)) {
            return "TYPE001";
        }
        id = Integer.parseInt(latestId.substring(TypeServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {
            return "TYPE00"+(id + 1);
        } else if(id >= 10 && id < 99) {
            return "TYPE0"+(id + 1);
        } else {
            return "TYPE"+(id + 1);
        }
    }
}