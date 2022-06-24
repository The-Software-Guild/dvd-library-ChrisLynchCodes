package org.chris.dao;

import org.chris.dto.Actor;
import org.chris.dto.DVD;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {

    DvdLibraryDao testDao;

    public DvdLibraryDaoFileImplTest()
    {
    }

    @BeforeAll
    public static void setUpClass()
    {
    }

    @AfterAll
    public static void tearDownClass()
    {
    }

    //before every test runs, we will have created a new blank dvdLibraryTextFile.txt file using the FileWriter.
    @BeforeEach
    public void setUp() throws Exception
    {
        String testFile = "dvdLibraryTextFile.txt";
        // Use the FileWriter to quickly blank the file
        try (FileWriter fileWriter = new FileWriter(testFile)) {
            testDao = new DvdLibraryDaoFileImpl(testFile);
        }

    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void addGetDVD() throws Exception
    {
        // Create our method test inputs
        String dvdId = "1";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        dvd.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));

        // Add dvd to DAO
        testDao.addDVD(dvdId, dvd);
        DVD retrievedDvd = testDao.getDvd(dvdId);

        // Verify that the data is equal
        assertEquals(dvd.getDvdId(), retrievedDvd.getDvdId());
        assertEquals(dvd.getTitle(), retrievedDvd.getTitle());
        assertEquals(dvd.getReleaseDate(), retrievedDvd.getReleaseDate());
        assertEquals(dvd.getMpaaRating(), retrievedDvd.getMpaaRating());
        assertEquals(dvd.getDirectorsName(), retrievedDvd.getDirectorsName());
        assertEquals(dvd.getStudio(), retrievedDvd.getStudio());
        assertEquals(dvd.getUserRating(), retrievedDvd.getUserRating());

    }

    @Test
    void addGetAllDVDS() throws Exception
    {

        //create first dvd
        String dvdId = "1";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        dvd.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));
        //create second dvd
        String dvdId2 = "2";
        DVD dvd2 = new DVD(dvdId2);
        dvd2.setTitle("The Matrix Reloaded");
        dvd2.setReleaseDate("2003");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorsName("Lana Wachowski");
        dvd2.setStudio("Warner Bros.");
        dvd2.setUserRating("5");
        dvd2.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));
        //create third dvd
        String dvdId3 = "3";
        DVD dvd3 = new DVD(dvdId3);
        dvd3.setTitle("The Matrix Revolutions");
        dvd3.setReleaseDate("2003");
        dvd3.setMpaaRating("R");
        dvd3.setDirectorsName("Lana Wachowski");
        dvd3.setStudio("Warner Bros.");
        dvd3.setUserRating("5");
        dvd3.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));
        //add dvds to dao
        testDao.addDVD(dvdId, dvd);
        testDao.addDVD(dvdId2, dvd2);
        testDao.addDVD(dvdId3, dvd3);
        //get all dvds
        List<DVD> allDvds = testDao.getAllDVDS();
        //verify not null
        assertNotNull(allDvds, "The list od DVDs must not be null");
        //verify size
        assertEquals(3, allDvds.size(), "The list of DVDs must contain 3 DVDs");

        //verify dvd1
        assertEquals(dvd.getDvdId(), allDvds.get(0).getDvdId(), "The first DVD in the list must have an ID of 1");
        assertEquals(dvd.getTitle(), allDvds.get(0).getTitle(), "The first DVD in the list must have a title of The Matrix");
        assertEquals(dvd.getReleaseDate(), allDvds.get(0).getReleaseDate(), "The first DVD in the list must have a release date of 1999");
        assertEquals(dvd.getMpaaRating(), allDvds.get(0).getMpaaRating(), "The first DVD in the list must have an MPAA rating of R");
        assertEquals(dvd.getDirectorsName(), allDvds.get(0).getDirectorsName(), "The first DVD in the list must have a director's name of Lana Wachowski");
        assertEquals(dvd.getStudio(), allDvds.get(0).getStudio(), "The first DVD in the list must have a studio of Warner Bros.");
        assertEquals(dvd.getUserRating(), allDvds.get(0).getUserRating(), "The first DVD in the list must have a user rating of 5");

        //verify dvd2
        assertEquals(dvd2.getDvdId(), allDvds.get(1).getDvdId(), "The second DVD in the list must have an ID of 2");
        assertEquals(dvd2.getTitle(), allDvds.get(1).getTitle(), "The second DVD in the list must have a title of The Matrix Reloaded");
        assertEquals(dvd2.getReleaseDate(), allDvds.get(1).getReleaseDate(), "The second DVD in the list must have a release date of 2003");
        assertEquals(dvd2.getMpaaRating(), allDvds.get(1).getMpaaRating(), "The second DVD in the list must have an MPAA rating of R");
        assertEquals(dvd2.getDirectorsName(), allDvds.get(1).getDirectorsName(), "The second DVD in the list must have a director's name of Lana Wachowski");
        assertEquals(dvd2.getStudio(), allDvds.get(1).getStudio(), "The second DVD in the list must have a studio of Warner Bros.");
        assertEquals(dvd2.getUserRating(), allDvds.get(1).getUserRating(), "The second DVD in the list must have a user rating of 5");

        //verify dvd3
        assertEquals(dvd3.getDvdId(), allDvds.get(2).getDvdId(), "The third DVD in the list must have an ID of 3");
        assertEquals(dvd3.getTitle(), allDvds.get(2).getTitle(), "The third DVD in the list must have a title of The Matrix Revolutions");
        assertEquals(dvd3.getReleaseDate(), allDvds.get(2).getReleaseDate(), "The third DVD in the list must have a release date of 2003");
        assertEquals(dvd3.getMpaaRating(), allDvds.get(2).getMpaaRating(), "The third DVD in the list must have an MPAA rating of R");
        assertEquals(dvd3.getDirectorsName(), allDvds.get(2).getDirectorsName(), "The third DVD in the list must have a director's name of Lana Wachowski");
        assertEquals(dvd3.getStudio(), allDvds.get(2).getStudio(), "The third DVD in the list must have a studio of Warner Bros.");
        assertEquals(dvd3.getUserRating(), allDvds.get(2).getUserRating(), "The third DVD in the list must have a user rating of 5");


    }




    @Test
    void addRemoveDvd() throws Exception
    {
        //create first dvd
        String dvdId = "1";
        DVD dvd = new DVD(dvdId);
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        dvd.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));
        //create second dvd
        String dvdId2 = "2";
        DVD dvd2 = new DVD(dvdId2);
        dvd2.setTitle("The Matrix Reloaded");
        dvd2.setReleaseDate("2003");
        dvd2.setMpaaRating("R");
        dvd2.setDirectorsName("Lana Wachowski");
        dvd2.setStudio("Warner Bros.");
        dvd2.setUserRating("5");
        dvd2.setActors(Arrays.asList(
                new Actor("Keanu Reeves"), new Actor("Laurence Fishburne"), new Actor("Carrie-Anne Moss")));

        //Add to dao
        testDao.addDVD(dvdId, dvd);
        testDao.addDVD(dvdId2, dvd2);

        //Remove dvd
        DVD removedDvd = testDao.removeDvd(dvd.getDvdId());

        //Verify removed dvd
        assertEquals(dvd.getDvdId(), removedDvd.getDvdId(), "The removed DVD must have an ID of 1");

        //Verify dvd is no longer in dao
        List<DVD> allDvds = testDao.getAllDVDS();
        assertNotNull(allDvds, "The list of DVDs must not be null");
        assertEquals(1, allDvds.size(), "The list of DVDs must contain 1 DVD");
        assertEquals(dvd2.getDvdId(), allDvds.get(0).getDvdId(), "The first DVD in the list must have an ID of 2");
        assertFalse(allDvds.contains(dvd), "The list should not contain the removed DVD");

    }

//    @Test
//    void updateDvd()
//    {
//    }
//    @Test
//    void searchDvdByTitle()
//    {
//
//    }

}