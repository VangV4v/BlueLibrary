package com.vang.publisherservice.command.event;

import com.vang.publisherservice.common.ServiceCommon;
import com.vang.publisherservice.data.PublisherRepository;
import com.vang.publisherservice.data.Publishers;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherEventsHandler {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherEventsHandler(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @EventHandler
    public void handler(PublisherCreatedEvent event) {

        Publishers publishers = new Publishers();
        BeanUtils.copyProperties(event, publishers);
        publishers.setPublisherId(generateId());
        publisherRepository.save(publishers);
    }

    @EventHandler
    public void handler(PublisherUpdatedEvent event) {

        Publishers publishers = new Publishers();
        BeanUtils.copyProperties(event, publishers);
        publisherRepository.save(publishers);
    }

    @EventHandler
    public void handler(PublisherDeletedEvent event) {

        publisherRepository.deleteById(event.getPublisherId());
    }

    private String generateId() {

        String latestId = publisherRepository.getLatestPublisherId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "PUBLISHER001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {

            return "PUBLISHER00"+(id + 1);
        } else if(id > 9 && id < 99) {

            return "PUBLISHER0"+(id + 1);
        } else {

            return "PUBLISHER"+(id + 1);
        }
    }
}