package org.softuni.exodia.repository;

import org.softuni.exodia.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    private static final String FIND_ALL_USERS_QUERY = "SELECT u FROM User u";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT u FROM User u WHERE u.id = :id";
    private static final String FIND_USER_BY_USERNAME_QUERY = "SELECT u FROM User u WHERE u.username = :username";

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User entity) {
        return this.executeTransaction(entityManager -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    @Override
    public List<User> findAll() {
        return this.executeTransaction(entityManager -> entityManager
                .createQuery(FIND_ALL_USERS_QUERY, User.class)
                .getResultList());
    }

    @Override
    public User findById(String id) {
        return this.executeTransaction(entityManager -> entityManager
                .createQuery(FIND_USER_BY_ID_QUERY, User.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public User findByUsername(String username) {
        return this.executeTransaction(entityManager -> entityManager
                .createQuery(FIND_USER_BY_USERNAME_QUERY, User.class)
                .setParameter("username", username)
                .getSingleResult());
    }
}
