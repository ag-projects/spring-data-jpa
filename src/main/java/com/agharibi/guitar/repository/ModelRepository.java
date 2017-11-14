package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ModelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelJpaRepository modelJpaRepository;

    /**
     *
     * @param model
     * @return
     */
    public Model create(Model model) {
        return modelJpaRepository.saveAndFlush(model);
    }

    /**
     *
     * @param model
     * @return
     */
    public Model update(Model model) {
        return modelJpaRepository.saveAndFlush(model);
    }

    /**
     *
     * @param model
     */
    public void delete(Model model) {
        modelJpaRepository.delete(model);
    }

    /**
     *
     * @param id
     * @return
     */
    public Model find(Long id) {
        return modelJpaRepository.findOne(id);
    }

    /**
     * Custom Finder
     * @param lowest
     * @param highest
     * @return
     */
    public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest) {
        return modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(lowest, highest);
    }

    /**
     * Custom Finder
     * @param lowest
     * @param highest
     * @param wood
     * @return
     */
    public List<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
        Query query = entityManager
                .createQuery("select m from Model m where m.price >= :lowest " +
                        "and m.price <= :highest and m.woodType like :wood");

        query.setParameter("lowest", lowest);
        query.setParameter("highest", highest);
        query.setParameter("wood", "%" + wood + "%");

        return query.getResultList();
    }

    /**
     * NameQuery Finder
     * @param modelType
     * @return
     */
    public List<Model> getModelsByType(String modelType) {
        Query namedQuery = entityManager.createNamedQuery("Model.findAllModelsByType");
        namedQuery.setParameter("name", modelType);
        return namedQuery.getResultList();
    }

    /**
     * Count
     * @return
     */
    public Long getModelCount() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

        criteriaQuery.select(builder.count(criteriaQuery.from(Model.class)));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
