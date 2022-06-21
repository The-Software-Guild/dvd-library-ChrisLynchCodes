package org.chris.controller;

import org.chris.dao.DVDLibraryDao;
import org.chris.dao.DVDLibraryDaoException;
import org.chris.dto.DVD;
import org.chris.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private final DVDLibraryView view;
    private final DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view)
    {
        this.view = view;
        this.dao = dao;
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
                    case 4 -> editDvd();
                    case 5 -> removeDvd();
                    case 6 -> keepGoing = false;
                    default -> unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void editDvd()throws DVDLibraryDaoException
    {
        view.displayEditDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD dvdToEdit = dao.getDvd(dvdId);
        DVD editedDvd = view.getEditDetails(dvdToEdit);
        dao.updateDvd(dvdId, editedDvd);
        view.displayEditSuccessBanner();

    }


    private void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage()
    {
        view.displayExitBanner();
    }


    private void removeDvd() throws DVDLibraryDaoException
    {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }


    private void viewDvd() throws DVDLibraryDaoException
    {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        DVD dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }

    private void listDvds() throws DVDLibraryDaoException
    {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDS();
        view.displayDvdList(dvdList);
    }

    private void createDvd() throws DVDLibraryDaoException
    {
        view.displayCreateDvdBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDVD(newDvd.getDvdId(), newDvd);
        view.displayCreateSuccessBanner();
    }

}
