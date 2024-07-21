package com.vang.bookservice.command.event;

import com.vang.bookservice.common.ServiceCommon;
import com.vang.bookservice.data.BookRepository;
import com.vang.bookservice.data.Books;
import com.vang.bookservice.grpc.grpc.ImageClientImpl;
import com.vang.bookservice.grpc.grpc.UpdateCountAuthorClientImpl;
import com.vang.bookservice.grpc.grpc.UpdateCountPublisherClientImpl;
import com.vang.bookservice.grpc.grpc.UpdateCountTypeClientImpl;
import org.apache.commons.lang.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class BookEventsHandler {

    private final BookRepository bookRepository;
    private final ImageClientImpl imageClient;
    private final UpdateCountTypeClientImpl updateCountTypeClient;
    private final UpdateCountPublisherClientImpl updateCountPublisherClient;
    private final UpdateCountAuthorClientImpl updateCountAuthorClient;

    @Autowired
    public BookEventsHandler(BookRepository bookRepository, ImageClientImpl imageClient, UpdateCountTypeClientImpl updateCountTypeClient, UpdateCountPublisherClientImpl updateCountPublisherClient, UpdateCountAuthorClientImpl updateCountAuthorClient) {
        this.bookRepository = bookRepository;
        this.imageClient = imageClient;
        this.updateCountTypeClient = updateCountTypeClient;
        this.updateCountPublisherClient = updateCountPublisherClient;
        this.updateCountAuthorClient = updateCountAuthorClient;
    }

    @EventHandler
    public void handle(BookCreatedEvent event) {

        event.setBookId(generateId());
        Books books = new Books();
        BeanUtils.copyProperties(event, books);
        bookRepository.save(books);
        updateCountOfBookCreate(event);
        String urlImage = uploadImage(event.getImageData(), event.getImageName());
        books.setImage(urlImage);
        bookRepository.save(books);
    }

    @EventHandler
    public void handle(BookUpdatedEvent event) {

        Books books = new Books();
        BeanUtils.copyProperties(event, books);
        bookRepository.save(books);
        updateCountOfBookUpdate(event);
        if(!ObjectUtils.isEmpty(event.getImageData()) && !StringUtils.isEmpty(event.getImage())) {

           String urlImage = imageClient.uploadImage(event.getImageData(), event.getImageName());
           books.setImage(urlImage);
           bookRepository.save(books);
           deleteImage(event.getImage());
        }
    }

    @EventHandler
    public void handle(BookDeletedEvent event) {

        bookRepository.deleteById(event.getBookId());
        deleteImage(event.getImage());
    }

    private void updateCountOfBookCreate(BookCreatedEvent event) {

        updateCountTypeClient.updateCountType(event.getTypeId(), 1);
        updateCountPublisherClient.updateCountPublisher(event.getPublisherId(), 1);
        updateCountAuthorClient.updateCountAuthor(event.getAuthorId(), 1);
    }

    private void updateCountOfBookUpdate(BookUpdatedEvent event) {

        if(!event.getTypeId().equals(event.getHdnTypeId())) {

            updateCountTypeClient.updateCountType(event.getTypeId(), 1);
            updateCountTypeClient.updateCountType(event.getHdnTypeId(), 2);
        }
        if(!event.getPublisherId().equals(event.getHdnPublisherId())) {

            updateCountPublisherClient.updateCountPublisher(event.getPublisherId(), 1);
            updateCountPublisherClient.updateCountPublisher(event.getHdnPublisherId(), 2);
        }
        if(!event.getAuthorId().equals(event.getHdnAuthorId())) {
            updateCountAuthorClient.updateCountAuthor(event.getAuthorId(), 1);
            updateCountAuthorClient.updateCountAuthor(event.getHdnAuthorId(), 2);
        }
    }

    private void deleteImage(String image) {

        imageClient.deleteImage(image);
    }

    private String uploadImage(byte[] image, String imageName) {

        return imageClient.uploadImage(image, imageName);
    }

    private String generateId() {

        String latestId = bookRepository.getLatestId();
        int id = 0;
        if(StringUtils.isEmpty(latestId)) {

            return "BOOK00001";
        }
        id = Integer.parseInt(latestId.substring(ServiceCommon.getIndexById(latestId)));
        if(id > 0 && id < 9) {

            return "BOOK0000"+(id + 1);
        } else if(id > 9 && id < 99) {

            return "BOOK000"+(id + 1);
        } else if(id > 99 && id < 999) {

            return "BOOK00"+(id + 1);
        } else if(id > 999 && id < 9999) {

            return "BOOK0"+(id + 1);
        } else {

            return "BOOK"+(id + 1);
        }
    }
}