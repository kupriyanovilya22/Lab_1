package com.example.springlab.model.dto;

import com.example.springlab.model.type.DocumentType;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class DocumentDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private DocumentType type;

    @NotNull
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Instant signedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Instant createdAt;

}

