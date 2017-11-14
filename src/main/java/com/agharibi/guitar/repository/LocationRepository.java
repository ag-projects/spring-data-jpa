package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Location;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LocationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param location
     * @return
     */
    public Location create(Location location) {
        entityManager.persist(location);
        entityManager.flush();
        return location;
    }

    /**
     *
     * @param location
     * @return
     */
    public Location update(Location location) {
        Location updatedLocation = entityManager.merge(location);
        entityManager.flush();
        return updatedLocation;
    }

    /**
     *
     * @param location
     */
    public void delete(Location location) {
        entityManager.remove(location);
        entityManager.flush();
    }

    /**
     *
     * @param id
     * @return
     */
    public Location find(Long id) {
        return entityManager.find(Location.class, id);
    }

    /**
     * Custom Finder
     * @param name
     * @return
     */
    public List<Location> getLocationByStateName(String name) {
        Query query = entityManager.createQuery("select l from Location l where l.state like :state");
        query.setParameter("state", name + "%");
        return query.getResultList();
    }
}
