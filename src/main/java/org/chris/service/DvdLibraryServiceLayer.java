package org.chris.service;

import org.chris.dao.DvdLibraryPersistenceException;
import org.chris.dto.DVD;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void createDvd(DVD dvd) throws DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException, DvdLibraryPersistenceException;

    List<DVD> getAllDvds() throws DvdLibraryPersistenceException;

    DVD getDvd(String dvdId) throws DvdLibraryPersistenceException;

    DVD removeDvd(String dvdId) throws DvdLibraryPersistenceException;

    DVD updateDvd(String dvdId, DVD dvd) throws DvdLibraryPersistenceException;

    List<DVD> searchDvdByTitle(String title) throws DvdLibraryPersistenceException;
}
