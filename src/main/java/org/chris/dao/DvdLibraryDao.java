package org.chris.dao;

import org.chris.dto.DVD;

import java.util.List;

public interface DvdLibraryDao {

    /**
     * Adds the given DVD to the library and associates it with the given
     * dvd id. If there is already a dvd associated with the given
     * dvd id it will return that dvd object, otherwise it will
     * return null.
     *
     * @param dVDId id with which dvd is to be associated
     * @param dVD dvd to be added to the roster
     * @return the Dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    DVD addDVD(String dVDId, DVD dVD)throws DvdLibraryPersistenceException;

    /**
     * Returns a List of all dvds in the library.
     *
     * @return List containing all dvds in the library.
     */
    List<DVD> getAllDVDS()throws DvdLibraryPersistenceException;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such dvd exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the dvd object associated with the given dvd id,
     * null if no such dvd exists
     */
    DVD getDvd(String dvdId)throws DvdLibraryPersistenceException;

    /**
     * Returns the dvd object associated with the given dvd title.
     * Returns null if no such dvd exists
     *
     * @param title title of the dvd to retrieve
     * @return the dvd object associated with the given dvd title,
     * null if no such dvd exists
     */
    List<DVD> searchDvdByTitle(String title)throws DvdLibraryPersistenceException;
    /**
     * Removes from the library the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    DVD removeDvd(String dvdId)throws DvdLibraryPersistenceException;

    /**
     * Updates the dvd associated with the given id.
     * Returns the dvd object that is being edited or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be edited
     * @return dvd object that was edited or null if no dvd
     * was associated with the given dvd id
     */
    DVD updateDvd(String dvdId, DVD editedDvd)throws DvdLibraryPersistenceException;
}
