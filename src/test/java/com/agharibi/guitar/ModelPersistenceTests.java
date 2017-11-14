package com.agharibi.guitar;

import com.agharibi.guitar.models.Model;
import com.agharibi.guitar.repository.ModelJpaRepository;
import com.agharibi.guitar.repository.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelPersistenceTests {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelJpaRepository modelJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Model model = new Model();
        model.setFrets(10);
        model.setName("Test Model");
        model.setPrice(BigDecimal.valueOf(55L));
        model.setWoodType("Maple");
        model.setYearFirstMade(new Date());
        model = modelRepository.create(model);

        entityManager.clear();

        Model otherModel = modelRepository.find(model.getId());
        assertEquals("Test Model", otherModel.getName());
        assertEquals(10, otherModel.getFrets());
        modelRepository.delete(otherModel);
    }


    @Test
    public void testGetModelsInPriceRange() throws Exception {
        List<Model> modelList = modelRepository
                .getModelsInPriceRange(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));

        assertEquals(4, modelList.size());
    }

    @Test
    public void testGetModelsByPriceRangeAndWoodType() throws Exception {
        List<Model> models = modelRepository.getModelsByPriceRangeAndWoodType(
                BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "Maple");

        assertEquals(3, models.size());
    }

    @Test
    public void testGetModelsByType() throws Exception {
        List<Model> models = modelRepository.getModelsByType("Electric");
        assertEquals(4, models.size());
    }

    @Test
    public void testGetModelsByTypes() throws Exception {
        List<String> types = Arrays.asList("Electric", "Acoustic");
        List<Model> models = modelJpaRepository.findByModelTypeNameIn(types);

        models.forEach(model -> {
            assertTrue(model.getModelType().getName().equals("Electric") ||
                    model.getModelType().getName().equals("Acoustic"));
        });
    }
}
