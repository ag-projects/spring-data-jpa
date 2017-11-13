package com.agharibi.guitar;

import com.agharibi.guitar.models.ModelType;
import com.agharibi.guitar.repository.ModelTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static junit.framework.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTypePersistenceTests {

    @Autowired
    private ModelTypeRepository modelTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        ModelType modelType  = new ModelType();
        modelType.setName("Test Model Type");
        modelType = modelTypeRepository.create(modelType);

        entityManager.clear();

        ModelType otherModelType = modelTypeRepository.find(modelType.getId());
        assertEquals("Test Model Type", otherModelType.getName());

       // modelTypeRepository.delete(modelType);
    }

    @Test
    public void testFind() throws Exception {
        ModelType modelType = modelTypeRepository.find(1L);
        assertEquals("Dreadnought Acoustic", modelType.getName());
    }
}
