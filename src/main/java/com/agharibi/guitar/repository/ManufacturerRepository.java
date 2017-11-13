package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class ManufacturerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param manufacturer
     * @return
     */
    public Manufacturer create(Manufacturer manufacturer) {
        entityManager.persist(manufacturer);
        entityManager.flush();
        return manufacturer;
    }

    /**
     *
     * @param manufacturer
     * @return
     */
    public Manufacturer update(Manufacturer manufacturer) {
        Manufacturer updatedManufacturer = entityManager.merge(manufacturer);
        entityManager.flush();
        return updatedManufacturer;
    }

    /**
     *
     * @param manufacturer
     */
    public void delete(Manufacturer manufacturer) {
        entityManager.remove(manufacturer);
        entityManager.flush();
    }

    /**
     *
     * @param id
     * @return
     */
    public Manufacturer find(Long id) {
        return entityManager.find(Manufacturer.class, id);
    }

    /**
     * Custom Finder
     * @param date
     * @return
     */
    public List<Manufacturer> getManufacturersFoundedBeforeDate(Date date) {
        Query query = entityManager
                .createQuery("select m from Manufacturer m where m.foundedDate < :date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    /**
     * Custom Finder
     * @param name
     * @return
     */
    public Manufacturer getManufacturerByName(String name) {
        Query query = entityManager.createQuery("select m from Manufacturer m where m.name like :name");
        query.setParameter("name", name + "%");
        return (Manufacturer) query.getSingleResult();
    }

    /**
     * Native Query Finder
     * @param modelType
     * @return
     */
    public List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType) {
        Query query = entityManager.createNamedQuery("Manufacturer.getAllThatSellAcoustics");
        query.setParameter(1, modelType);
        return query.getResultList();
    }
}
