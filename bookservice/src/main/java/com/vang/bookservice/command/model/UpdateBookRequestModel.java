package com.vang.bookservice.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookRequestModel extends BookRequestModel {

    private String hdnAuthorId;
    private String hdnTypeId;
    private String hdnPublisherId;
}