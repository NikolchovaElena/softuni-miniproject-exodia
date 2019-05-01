package org.softuni.exodia.web.beans;

import javax.faces.context.FacesContext;
import java.io.IOException;

public abstract class BaseBean {

    protected void redirect(String url) throws IOException {

        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/view" + url + ".jsf");
    }
}
