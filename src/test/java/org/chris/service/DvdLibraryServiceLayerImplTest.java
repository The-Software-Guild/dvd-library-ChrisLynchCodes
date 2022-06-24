package org.chris.service;

import org.chris.dao.DvdLibraryAuditDao;
import org.chris.dao.DvdLibraryDao;
import org.chris.dao.DvdLibraryPersistenceException;
import org.chris.dto.DVD;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryServiceLayerImplTest {

    private DvdLibraryServiceLayer service;

    public DvdLibraryServiceLayerImplTest()
    {
        DvdLibraryDao dao = new DvdLibraryDaoStubImpl();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStubImpl();
        service = new DvdLibraryServiceLayerImpl(dao, auditDao);
    }

    @BeforeEach
    void setUp()
    {
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void createValidDvd()
    {
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("2");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        //assertDoesNotThrow(() -> service.createDvd(dvd));
        try {
            service.createDvd(dvd);
        } catch (DvdLibraryDuplicateIdException
                 | DvdLibraryDataValidationException
                 | DvdLibraryPersistenceException e) {
            fail("DVD was valid no exception should have been thrown");
        }

    }

    @Test
    void createDuplicateIdDvd()
    {
        // ARRANGE
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("1");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");

        try {
            service.createDvd(dvd);
            //If the call executes and no exception is thrown
            fail("Expected DupeId Exception was not thrown.");
        } catch (DvdLibraryDataValidationException
                 | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDuplicateIdException e) {
            assertEquals("ERROR: Could not create dvd.  dvd Id 1 already exists", e.getMessage());
        }
    }

    @Test
    void createInvalidDvd()
    {
        // ARRANGE
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("2");
        //title is invalid
        dvd.setTitle("");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");

        try {
            service.createDvd(dvd);
            //If the call executes and no exception is thrown
            fail("Expected Validation Exception was not thrown.");
        } catch (DvdLibraryDuplicateIdException
                 | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDataValidationException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void getAllDvds() throws Exception
    {
        // ARRANGE
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("1");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        //DVD with id 100 already exists in the library via dao test stub
        DVD dvd2 = new DVD("100");
        dvd2.setTitle("The Matrix 2");
        dvd2.setReleaseDate("2000");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorsName("Lana Wachowski");
        dvd2.setStudio("Warner Bros.");
        dvd2.setUserRating("5");
        List<DVD> dvds = service.getAllDvds();

        // ACT & ASSERT
        assertEquals(2, service.getAllDvds().size(), "Incorrect number of dvds returned.");
        assertTrue(service.getAllDvds().contains(dvd), "Dvd not found in list.");
        assertTrue(service.getAllDvds().contains(dvd2), "Dvd not found in list.");

    }

    @Test
    void getDvd() throws Exception
    {
        // ARRANGE
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("1");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        // ACT & ASSERT
        assertNotNull(service.getDvd("1"), "Dvd not found.");
        assertEquals(dvd, service.getDvd("1"), "Incorrect dvd returned.");
        assertNull(service.getDvd("-1"), "Dvd found when it should not have been.");

    }

    @Test
    void removeDvd() throws Exception
    {
        // ARRANGE
        //DVD with id 1 already exists in the library via dao test stub
        DVD dvd = new DVD("1");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");

        // ACT & ASSERT
        DVD removedDvd = service.removeDvd("1");
        assertNotNull(removedDvd, "Dvd not found.");
        assertEquals(dvd, removedDvd, "Incorrect dvd returned.");
        assertNull(service.getDvd("-1"), "Dvd found when it should not have been.");
    }

    @Test
    void updateDvd()
    {
    }

    @Test
    void searchDvdByTitle()
    {
    }
}