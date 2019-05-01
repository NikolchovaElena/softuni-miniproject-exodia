package org.softuni.exodia.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.models.binding.DocumentBindingModel;
import org.softuni.exodia.domain.models.service.DocumentServiceModel;
import org.softuni.exodia.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentCreateBean extends BaseBean {

    private DocumentService documentService;
    private ModelMapper modelMapper;
    private DocumentBindingModel documentBindingModel;

    public DocumentCreateBean() {
    }

    @Inject
    public DocumentCreateBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.documentBindingModel = new DocumentBindingModel();
    }

    public DocumentBindingModel getDocumentBindingModel() {
        return documentBindingModel;
    }

    public void setDocumentBindingModel(DocumentBindingModel documentBindingModel) {
        this.documentBindingModel = documentBindingModel;
    }

    public void createDocument() throws IOException {
        DocumentServiceModel dc = this.documentService.createDocument(this.modelMapper.map(
                this.documentBindingModel, DocumentServiceModel.class));

        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/view/details.jsf?id=" + dc.getId());

    }
}
