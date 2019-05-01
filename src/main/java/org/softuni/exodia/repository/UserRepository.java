package org.softuni.exodia.repository;

import org.softuni.exodia.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);
}
