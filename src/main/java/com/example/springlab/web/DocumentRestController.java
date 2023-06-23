package com.example.springlab.web;

import com.example.springlab.mapper.DocumentMapper;
import com.example.springlab.model.dto.DocumentDto;
import com.example.springlab.persistence.DocumentEntity;
import com.example.springlab.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentRestController {

    private final DocumentMapper documentMapper;
    private final DocumentService documentService;

    @GetMapping("/by-login")
    public List<DocumentDto> getAllByLogin(@RequestParam("login") String login) {
        List<DocumentEntity> documents = documentService.findAllByLogin(login);
        return documents.stream()
                .map(documentMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-login/signed")
    public List<DocumentDto> getAllByLoginAndSigned(@RequestParam("login") String login) {
        List<DocumentEntity> signedDocuments = documentService.findAllSignedByLogin(login);
        return signedDocuments.stream()
                .map(documentMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-login/not-signed")
    public List<DocumentDto> getAllByLoginAndNotSigned(@RequestParam("login") String login) {
        List<DocumentEntity> notSignedDocuments = documentService.findAllNotSignedByLogin(login);
        return notSignedDocuments.stream()
                .map(documentMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-date-range")
    public List<DocumentDto> getAllByDateRange(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        Instant fromDate = Instant.ofEpochMilli(from);
        Instant toDate = Instant.ofEpochMilli(to);
        List<DocumentEntity> documentsInRange = documentService.findAllCreatedBetween(fromDate, toDate);
        return documentsInRange.stream()
                .map(documentMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DocumentDto create(@Valid @RequestBody DocumentDto dto) {
        DocumentEntity entity = documentMapper.map(dto);
        DocumentEntity savedEntity = documentService.saveDocument(entity);
        return documentMapper.map(savedEntity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long documentId, @RequestBody DocumentDto dto) {
        DocumentEntity document = documentService.findDocumentById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        documentMapper.update(dto, document);
        documentService.saveDocument(document);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long documentId) {
        documentService.deleteDocumentById(documentId);
    }

}

