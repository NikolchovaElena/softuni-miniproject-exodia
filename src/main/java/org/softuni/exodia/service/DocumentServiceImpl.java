package org.softuni.exodia.service;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.entities.Document;
import org.softuni.exodia.domain.models.service.DocumentServiceModel;
import org.softuni.exodia.repository.DocumentRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel getDocumentById(String id) {
        Document document = this.documentRepository.findById(id);
        if (document != null) {
            return this.modelMapper.map(document, DocumentServiceModel.class);
        }
        return null;
    }

    @Override
    public DocumentServiceModel createDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);

        return this.modelMapper.map(
                this.documentRepository.save(document), DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments() {
        return this.documentRepository
                .findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.documentRepository.delete(id);
    }
}
