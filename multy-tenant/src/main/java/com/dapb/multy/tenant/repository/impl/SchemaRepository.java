package com.dapb.multy.tenant.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SchemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createSchema(String schemaName) {
        String sql = "CREATE SCHEMA IF NOT EXISTS " + schemaName;
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
