package org.softuni.exodia.service;

import org.softuni.exodia.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel getDocumentById(String id);

    DocumentServiceModel createDocument(DocumentServiceModel documentServiceModel);

    List<DocumentServiceModel> getAllDocuments();

    void delete(String id);
}
