package org.softuni.exodia.web.beans;

import org.apache.commons.codec.digest.DigestUtils;
import org.softuni.exodia.domain.models.binding.UserLoginBindingModel;
import org.softuni.exodia.domain.models.service.UserServiceModel;
import org.softuni.exodia.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private UserService userService;
    private UserLoginBindingModel userLoginBindingModel;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel user = this.userService.getUserByUsername(
                this.userLoginBindingModel.getUsername());

        String inputPassword = DigestUtils.sha256Hex(this.userLoginBindingModel.getPassword());
        if (user == null || !user.getPassword().equals(inputPassword)) {
            return;
        }

        Map<String, Object> sessionMap = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap();

        sessionMap.put("user-id", user.getId());
        sessionMap.put("username", user.getUsername());

        this.redirect("/home");
    }
}
