package org.softuni.exodia.repository;

import org.softuni.exodia.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {

    private static final String FIND_ALL_DOCUMENTS_QUERY = "SELECT d FROM Document d";
    private static final String FIND_DOCUMENT_BY_ID_QUERY = "SELECT d FROM Document d WHERE d.id = :id";
    private static final String DELETE_DOCUMENT_BY_ID_QUERY = "DELETE FROM Document WHERE id = :id";

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document entity) {
        return this.executeTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }

    @Override
    public List<Document> findAll() {
        return this.executeTransaction(entityManager -> entityManager
                .createQuery(FIND_ALL_DOCUMENTS_QUERY, Document.class)
                .getResultList());
    }

    @Override
    public Document findById(String id) {
        return this.executeTransaction(entityManager -> entityManager
                .createQuery(FIND_DOCUMENT_BY_ID_QUERY, Document.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public void delete(String id) {
        this.executeTransaction((em) -> {
            em.createQuery(DELETE_DOCUMENT_BY_ID_QUERY)
                    .setParameter("id", id)
                    .executeUpdate();
            return null;
        });
    }
}
