package org.chris.controller;

import org.chris.dao.DvdLibraryDao;
import org.chris.dao.DvdLibraryPersistenceException;
import org.chris.dto.DVD;
import org.chris.service.DvdLibraryDataValidationException;
import org.chris.service.DvdLibraryDuplicateIdException;
import org.chris.service.DvdLibraryServiceLayer;
import org.chris.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private final DVDLibraryView view;
    //    private final DvdLibraryDao dao;
    private final DvdLibraryServiceLayer service;

    public DVDLibraryController(DvdLibraryServiceLayer service, DVDLibraryView view)
    {
        this.view = view;
        this.service = service;
    }

    public void run()
    {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {


                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1 -> listDvds();
                    case 2 -> createDvd();
                    case 3 -> viewDvd();
                    case 4 -> searchDvd();
                    case 5 -> editDvd();
                    case 6 -> removeDvd();
                    case 7 -> keepGoing = false;
                    default -> unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage()
    {
        view.displayExitBanner();
    }

    private int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }

    private void searchDvd() throws DvdLibraryPersistenceException
    {
        List<DVD> dvds = service.searchDvdByTitle(view.getDvdIdTitle());
        view.displayDvdList(dvds);
    }

    private void editDvd() throws DvdLibraryPersistenceException
    {
        view.displayEditDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD dvdToEdit = service.getDvd(dvdId);
        DVD editedDvd = view.getEditDetails(dvdToEdit);
        service.updateDvd(dvdId, editedDvd);
        view.displayEditSuccessBanner();

    }

    private void removeDvd() throws DvdLibraryPersistenceException
    {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD removedDvd = service.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void viewDvd() throws DvdLibraryPersistenceException
    {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD dvd = service.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void listDvds() throws DvdLibraryPersistenceException
    {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void createDvd() throws DvdLibraryPersistenceException
    {
        view.displayCreateDvdBanner();
        boolean hasErrors = false;
        do {
            DVD newDvd = view.getNewDvdInfo();
            try {
                service.createDvd(newDvd);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DvdLibraryDuplicateIdException | DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);


    }

}
