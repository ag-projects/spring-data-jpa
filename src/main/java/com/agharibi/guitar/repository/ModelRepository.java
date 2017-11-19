package com.agharibi.guitar.repository;

import com.agharibi.guitar.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        Pageable page = new PageRequest(0,2, sort);
        return modelJpaRepository.queryByPriceRangeAAndWoodType(lowest, highest, "%" + wood + "%", page);
    }

    /**
     * NameQuery Finder
     * @param modelType
     * @return
     */
    public List<Model> getModelsByType(String modelType) {
        return modelJpaRepository.findAllModelsByType(modelType);
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
