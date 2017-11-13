package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.ModelType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ModelTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create
     * @param modelType
     * @return
     */
    public ModelType create(ModelType modelType) {
        entityManager.persist(modelType);
        entityManager.flush();
        return modelType;
    }

    /**
     * Update
     * @param modelType
     * @return
     */
    public ModelType update(ModelType modelType) {
        ModelType updatedModelType = entityManager.merge(modelType);
        entityManager.flush();
        return updatedModelType;
    }

    /**
     * Delete
     * @param modelType
     */
    public void delete(ModelType modelType) {
        entityManager.remove(modelType);
        entityManager.flush();
    }

    /**
     * Find
     * @param id
     * @return
     */
    public ModelType find(Long id) {
        return entityManager.find(ModelType.class, id);
    }
}
