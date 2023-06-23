package com.example.springlab.mapper;

import com.example.springlab.model.dto.DocumentDto;
import com.example.springlab.persistence.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentEntity map(DocumentDto dto);

    DocumentDto map(DocumentEntity entity);

    @Mapping(target = "id", ignore = true)
    void updateDocumentFromDto(DocumentDto dto, @MappingTarget DocumentEntity document);

}

