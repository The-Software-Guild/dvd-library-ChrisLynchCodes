package org.chris.dao;

public interface DvdLibraryAuditDao {

     void writeAuditEntry(String entry) throws DvdLibraryPersistenceException;
}
