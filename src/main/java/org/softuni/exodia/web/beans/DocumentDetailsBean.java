package org.softuni.exodia.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.models.view.DocumentDetailsViewModel;
import org.softuni.exodia.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean {

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentDetailsViewModel renderDetail(String id) {
        return this.modelMapper.map(this.documentService.getDocumentById(id), DocumentDetailsViewModel.class);
    }
}
