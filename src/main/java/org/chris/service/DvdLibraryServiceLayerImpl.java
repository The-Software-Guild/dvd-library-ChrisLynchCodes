package org.chris.service;

import org.chris.dao.DvdLibraryAuditDao;
import org.chris.dao.DvdLibraryDao;
import org.chris.dao.DvdLibraryPersistenceException;
import org.chris.dto.DVD;

import java.util.List;

public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {

    DvdLibraryDao dao;
    DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createDvd(DVD dvd) throws DvdLibraryDuplicateIdException, DvdLibraryDataValidationException, DvdLibraryPersistenceException
    {

        if (dao.getDvd(dvd.getDvdId()) != null) {
            throw new DvdLibraryDuplicateIdException(
                    "ERROR: Could not create dvd.  dvd Id "
                            + dvd.getDvdId()
                            + " already exists");
        }


        // throw exception if any of the validation rules are violated.
        validateDvdData(dvd);

        // persist the Dvd object
        dao.addDVD(dvd.getDvdId(), dvd);
        // The dvd was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "DVD " + dvd.getDvdId() + " " + dvd.getTitle() + " CREATED.");
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryPersistenceException
    {
        return dao.getAllDVDS();
    }

    @Override
    public DVD getDvd(String dvdId) throws DvdLibraryPersistenceException
    {
        return dao.getDvd(dvdId);
    }

    @Override
    public DVD removeDvd(String dvdId) throws DvdLibraryPersistenceException
    {
        DVD removedDvd =  dao.removeDvd(dvdId);
        auditDao.writeAuditEntry(
                "DVD " + removedDvd.getDvdId() + " " + removedDvd.getTitle() + " REMOVED.");
        return removedDvd;
    }

    @Override
    public DVD updateDvd(String dvdId, DVD dvd) throws DvdLibraryPersistenceException
    {
        return dao.updateDvd(dvdId, dvd);
    }

    @Override
    public List<DVD> searchDvdByTitle(String title) throws DvdLibraryPersistenceException
    {
        return dao.searchDvdByTitle(title);
    }

    private void validateDvdData(DVD dvd) throws DvdLibraryDataValidationException
    {
        if (dvd.getDvdId() == null || dvd.getDvdId().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Dvd id is required.");
        }
        if (dvd.getTitle() == null || dvd.getTitle().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Title is required.");
        }
        if (dvd.getDirectorsName() == null || dvd.getDirectorsName().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Director is required.");
        }
        if (dvd.getReleaseDate() == null || dvd.getReleaseDate().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Release date is required.");
        }
        if (dvd.getMpaaRating() == null || dvd.getMpaaRating().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Mpaa rating is required.");
        }
        if (dvd.getStudio() == null || dvd.getStudio().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("Studio is required.");
        }
        if (dvd.getUserRating() == null || dvd.getUserRating().trim().length() == 0) {
            throw new DvdLibraryDataValidationException("User rating is required.");
        }
    }
}
