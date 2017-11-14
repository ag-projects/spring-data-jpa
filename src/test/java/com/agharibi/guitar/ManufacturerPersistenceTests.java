package com.agharibi.guitar;

import com.agharibi.guitar.models.Manufacturer;
import com.agharibi.guitar.repository.ManufacturerJpaRepository;
import com.agharibi.guitar.repository.ManufacturerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerPersistenceTests {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ManufacturerJpaRepository manufacturerJpaRepository;

    @Test
    public void testTrueFalse() throws Exception {
        List<Manufacturer> inactiveList = manufacturerJpaRepository.findByActiveFalse();
        assertEquals(1, inactiveList.size());
        assertEquals("Gibson Guitar Corporation", inactiveList.get(0).getName());

        List<Manufacturer> activeList = manufacturerJpaRepository.findByActiveTrue();
        assertEquals(1, activeList.size());
        assertEquals("Fender Musical Instruments Corporation", activeList.get(0).getName());

    }

    @Test
    public void testGetManufacturersFoundedBeforeDate() throws Exception {
        List<Manufacturer> manufacturers = manufacturerRepository.getManufacturersFoundedBeforeDate(new Date());
        assertEquals(2, manufacturers.size());
    }

    @Test
    public void testGetManufactureByName() throws Exception {
        Manufacturer manufacturer = manufacturerRepository.getManufacturerByName("Fender");
        assertEquals("Fender Musical Instruments Corporation", manufacturer.getName());
    }

    @Test
    public void testGetManufacturersThatSellModelsOfType() throws Exception {
        List<Manufacturer> manufacturers = manufacturerRepository
                .getManufacturersThatSellModelsOfType("Semi-Hollow Body Electric");

        assertEquals(1, manufacturers.size());
    }
}
