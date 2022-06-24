package org.chris.service;

import org.chris.dao.DvdLibraryDao;
import org.chris.dao.DvdLibraryPersistenceException;
import org.chris.dto.DVD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DvdLibraryDaoStubImpl implements DvdLibraryDao {


    public DVD dvd;
    public DVD dvdTwo;

    public DvdLibraryDaoStubImpl()
    {
        dvd = new DVD("1");
        dvd.setTitle("The Matrix");
        dvd.setReleaseDate("1999");
        dvd.setMpaaRating("R");
        dvd.setDirectorsName("Lana Wachowski");
        dvd.setStudio("Warner Bros.");
        dvd.setUserRating("5");
        //second dvd
         dvdTwo = new DVD("100");
        dvdTwo.setTitle("The Matrix 2");
        dvdTwo.setReleaseDate("2000");
        dvdTwo.setMpaaRating("R");
        dvdTwo.setDirectorsName("Lana Wachowski");
        dvdTwo.setStudio("Warner Bros.");
        dvdTwo.setUserRating("5");

    }

    public DvdLibraryDaoStubImpl(DVD dvd, DVD dvdTwo)
    {
        this.dvd = dvd;
        this.dvdTwo = dvdTwo;
    }




    /**
     * Adds the given DVD to the library and associates it with the given
     * dvd id. If there is already a dvd associated with the given
     * dvd id it will return that dvd object, otherwise it will
     * return null.
     *
     * @param dVDId id with which dvd is to be associated
     * @param dVD   dvd to be added to the roster
     * @return the Dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    @Override
    public DVD addDVD(String dVDId, DVD dVD) throws DvdLibraryPersistenceException
    {
        if (dVDId.equals(dvd.getDvdId())) {
            return dvd;
        }
        return null;

    }

    /**
     * Returns a List of all dvds in the library.
     *
     * @return List containing all dvds in the library.
     */
    @Override
    public List<DVD> getAllDVDS() throws DvdLibraryPersistenceException
    {
        List<DVD> dvds = new ArrayList<DVD>();
        dvds.add(dvd);
        dvds.add(dvdTwo);
        return dvds;
    }

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such dvd exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the dvd object associated with the given dvd id,
     * null if no such dvd exists
     */
    @Override
    public DVD getDvd(String dvdId) throws DvdLibraryPersistenceException
    {
       if(dvdId.equals(dvd.getDvdId()))
       {
           return dvd;
       }
         return null;
    }

    /**
     * Returns the dvd object associated with the given dvd title.
     * Returns null if no such dvd exists
     *
     * @param title title of the dvd to retrieve
     * @return the dvd object associated with the given dvd title,
     * null if no such dvd exists
     */
    @Override
    public List<DVD> searchDvdByTitle(String title) throws DvdLibraryPersistenceException
    {
        if(title.equals(dvd.getTitle()))
        {
            List<DVD> dvds = new ArrayList<DVD>();
            dvds.add(dvd);
            return dvds;
        }
        return null;
    }

    /**
     * Removes from the library the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    @Override
    public DVD removeDvd(String dvdId) throws DvdLibraryPersistenceException
    {
        if(dvdId.equals(dvd.getDvdId()))
        {
            return dvd;
        }
        return null;
    }

    /**
     * Updates the dvd associated with the given id.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId     id of dvd to be edited
     * @param editedDvd
     * @return dvd object that was edited or null if no dvd
     * was associated with the given dvd id
     */
    @Override
    public DVD updateDvd(String dvdId, DVD editedDvd) throws DvdLibraryPersistenceException
    {
        if(dvdId.equals(dvd.getDvdId()))
        {
            return dvd;
        }
        return null;
    }
}
