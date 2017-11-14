package com.agharibi.guitar;

import com.agharibi.guitar.models.ModelType;
import com.agharibi.guitar.repository.ModelTypeJpaRepository;
import com.agharibi.guitar.repository.ModelTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTypePersistenceTests {

    @Autowired
    private ModelTypeJpaRepository modelTypeJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        ModelType modelType  = new ModelType();
        modelType.setName("Test Model Type");
        modelType = modelTypeJpaRepository.save(modelType);

        entityManager.clear();

        ModelType otherModelType = modelTypeJpaRepository.findOne(modelType.getId());
        assertEquals("Test Model Type", otherModelType.getName());

        modelTypeJpaRepository.delete(modelType);
    }

    @Test
    public void testFind() throws Exception {
        ModelType modelType = modelTypeJpaRepository.findOne(1L);
        assertEquals("Dreadnought Acoustic", modelType.getName());
    }
}
