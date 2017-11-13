package com.agharibi.guitar;


import com.agharibi.guitar.models.Location;
import com.agharibi.guitar.repository.LocationRepository;
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

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationPersistenceTests {

    @Autowired
    private LocationRepository locationRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    public void test_save_Get_delete() throws Exception {
        Location location = new Location();
        location.setCountry("Canada");
        location.setState("British Columbia");
        location = locationRepository.create(location);

        entityManager.clear();

        Location otherLocation = locationRepository.find(location.getId());
        assertEquals("Canada", otherLocation.getCountry());
        assertEquals("British Columbia", otherLocation.getState());

        locationRepository.delete(otherLocation);
    }

    @Test
    public void testFindWithLike() throws Exception {
        List<Location> locationList = locationRepository.getLocationByStateName("New");
        assertEquals(4, locationList.size());
    }

    @Test
    @Transactional
    public void testFindWithChildern() throws Exception {
        Location arizona = locationRepository.find(3L);
        assertEquals("United States", arizona.getCountry());
        assertEquals("Arizona", arizona.getState());

        assertEquals(1, arizona.getManufacturers().size());
        assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
    }
}