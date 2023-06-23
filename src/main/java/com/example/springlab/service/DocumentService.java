package com.example.springlab.service;

import com.example.springlab.persistence.DocumentEntity;
import com.example.springlab.persistence.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<DocumentEntity> findAllByLogin(String login) {
        return documentRepository.findAllByLogin(login);
    }

    public List<DocumentEntity> findAllSignedByLogin(String login) {
        return documentRepository.findAllByLoginAndSignedAtIsNotNull(login);
    }

    public List<DocumentEntity> findAllNotSignedByLogin(String login) {
        return documentRepository.findAllByLoginAndSignedAtIsNull(login);
    }

    public List<DocumentEntity> findAllCreatedBetween(Instant from, Instant to) {
        return documentRepository.findAllByCreatedAtBetween(from, to);
    }

    public DocumentEntity saveDocument(DocumentEntity entity) {
        return documentRepository.save(entity);
    }

    public Optional<DocumentEntity> findDocumentById(long id) {
        return documentRepository.findById(id);
    }

    public void deleteDocumentById(long id) {
        documentRepository.deleteById(id);
    }

}

