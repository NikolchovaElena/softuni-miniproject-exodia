package org.softuni.exodia.service;

import org.softuni.exodia.domain.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel getUserByUsername(String username);

    UserServiceModel createUser(UserServiceModel userServiceModel);


}
