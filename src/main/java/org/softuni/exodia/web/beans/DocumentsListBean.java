package org.softuni.exodia.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.models.view.DocumentViewModel;
import org.softuni.exodia.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DocumentsListBean extends BaseBean {

    private DocumentService documentService;
    private List<DocumentViewModel> documents;
    private ModelMapper modelMapper;

    public DocumentsListBean() {
    }

    @Inject
    public DocumentsListBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setDocuments(this.documentService
                .getAllDocuments()
                .stream()
                .map(d -> this.modelMapper.map(d, DocumentViewModel.class))
                .collect(Collectors.toList()));
        this.getDocuments().forEach(d -> d.setTitle(this.formatTitle(d.getTitle())));
    }

    public List<DocumentViewModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentViewModel> documents) {
        this.documents = documents;
    }

    private String formatTitle(String title) {
        if (title.length() > 12) {
            return title.substring(0, 9) + "...";
        }
        return title;
    }
}
