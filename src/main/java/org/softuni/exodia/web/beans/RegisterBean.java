package org.softuni.exodia.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.models.binding.UserRegisterBindingModel;
import org.softuni.exodia.domain.models.service.UserServiceModel;
import org.softuni.exodia.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {

    private UserService userService;
    private ModelMapper modelMapper;
    private UserRegisterBindingModel userRegisterBindingModel;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (!this.userRegisterBindingModel.getPassword().equals(
                this.userRegisterBindingModel.getConfirmPassword())) {
            return;
        }

        this.userService.createUser(
                this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));

        this.redirect("/login");
    }
}

