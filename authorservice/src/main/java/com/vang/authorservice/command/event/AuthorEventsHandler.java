package com.vang.authorservice.command.event;

import com.vang.authorservice.common.ServiceCommon;
import com.vang.authorservice.data.AuthorRepository;
import com.vang.authorservice.data.Authors;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventsHandler {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorEventsHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @EventHandler
    public void handler(AuthorCreatedEvent event) {

        event.setAuthorId(generateId());
        Authors authors = new Authors();
        BeanUtils.copyProperties(event, authors);
        authorRepository.save(authors);
    }

    @EventHandler
    public void handler(AuthorUpdatedEvent event) {

        Authors authors = new Authors();
        BeanUtils.copyProperties(event, authors);
        authorRepository.save(authors);
    }

    @EventHandler
    public void handler(AuthorDeletedEvent event) {

        authorRepository.deleteById(event.getAuthorId());
    }

    private String generateId() {

        String latestId = authorRepository.getLatestId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "AUTHOR001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id  > 0 && id < 9) {

            return "AUTHOR00"+(id + 1);
        } else if(id  > 9 && id < 99) {

            return "AUTHOR0"+(id + 1);
        } else {

            return "AUTHOR"+(id + 1);
        }
    }

}