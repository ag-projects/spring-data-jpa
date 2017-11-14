package com.agharibi.guitar;


import com.agharibi.guitar.models.Location;
import com.agharibi.guitar.repository.LocationJpaRepository;
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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

@ContextConfiguration(locations = {"classpath:com/agharibi/guitar/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationPersistenceTests {

    @Autowired
    private LocationJpaRepository locationJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testFindJpaNot() throws Exception {
        List<Location> locations = locationJpaRepository.findByStateNot("Utah");
        assertNotSame("Utah", locations.get(0).getState());
    }

    @Test
    public void testFindJpaOr() throws Exception {
        List<Location> locations = locationJpaRepository.findByStateOrCountry("Alabama", "Utah");
        assertEquals("Alabama", locations.get(0).getState());
    }

    @Test
    public void testFindJpaAnd() throws Exception {
        List<Location> locations = locationJpaRepository.findByStateAndCountry("Alabama", "United States");
        assertEquals("Alabama", locations.get(0).getState());
    }

    @Test
    public void testFindJpaLocation() throws Exception {
        List<Location> locations = locationJpaRepository.findAll();
        assertNotNull(locations);
    }

    @Test
    @Transactional
    public void test_save_Get_delete() throws Exception {
        Location location = new Location();
        location.setCountry("Canada");
        location.setState("British Columbia");
        location = locationJpaRepository.saveAndFlush(location);

        entityManager.clear();

        Location otherLocation = locationJpaRepository.findOne(location.getId());
        assertEquals("Canada", otherLocation.getCountry());
        assertEquals("British Columbia", otherLocation.getState());

        locationJpaRepository.delete(otherLocation);
    }

    @Test
    public void testFindWithLike() throws Exception {
        List<Location> locationList = locationJpaRepository.findByStateLike("New%");
        assertEquals(4, locationList.size());

        List<Location> locations = locationJpaRepository.findByStateNotLike("New%");
        assertEquals(46, locations.size());

        List<Location> locationsStartingWithNew = locationJpaRepository.findByStateStartingWith("New");
        assertEquals(4, locationsStartingWithNew.size());
    }

    @Test
    @Transactional
    public void testFindWithChildern() throws Exception {
        Location arizona = locationJpaRepository.findOne(3L);
        assertEquals("United States", arizona.getCountry());
        assertEquals("Arizona", arizona.getState());

        assertEquals(1, arizona.getManufacturers().size());
        assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
    }
}
