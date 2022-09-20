package uz.everbest.auth.repository.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserSessionRepositoryImpl {
    private final EntityManager entityManager;

    public UserSessionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
