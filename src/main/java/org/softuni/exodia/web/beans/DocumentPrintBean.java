package org.softuni.exodia.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.models.service.DocumentServiceModel;
import org.softuni.exodia.domain.models.view.DocumentPrintViewModel;
import org.softuni.exodia.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentPrintBean extends BaseBean {

    private DocumentService documentService;
    private DocumentPrintViewModel documentPrintViewModel;
    private ModelMapper modelMapper;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentPrintViewModel getDocumentPrintVM(String id) {
        return this.modelMapper.map(
                this.documentService.getDocumentById(id), DocumentPrintViewModel.class);
    }

    public void print() throws IOException {
        String id = ((HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest())
                .getParameter("id");

        this.documentService.delete(id);
        this.redirect("/home");
    }
}
