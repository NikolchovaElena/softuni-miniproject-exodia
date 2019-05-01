package org.softuni.exodia.repository;

import org.softuni.exodia.domain.entities.Document;

public interface DocumentRepository extends GenericRepository<Document, String> {

    void delete(String id);
}
