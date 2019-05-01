package org.softuni.exodia.service;


import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.exodia.domain.entities.User;
import org.softuni.exodia.domain.models.service.UserServiceModel;
import org.softuni.exodia.repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
        }
        return null;
    }

    @Override
    public UserServiceModel createUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map(
                this.userRepository.save(user), UserServiceModel.class);
    }

}
