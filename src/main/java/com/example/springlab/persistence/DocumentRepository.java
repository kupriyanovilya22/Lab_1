package com.example.springlab.persistence;

import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {

    List<DocumentEntity> findAllByLogin(String login);

    List<DocumentEntity> findAllByLoginAndSignedAtIsNull(String login);

    List<DocumentEntity> findAllByLoginAndSignedAtIsNotNull(String login);

    List<DocumentEntity> findAllByCreatedAtBetween(Instant from, Instant to);

}
