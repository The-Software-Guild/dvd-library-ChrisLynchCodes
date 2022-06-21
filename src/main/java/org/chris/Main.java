package org.chris;

import org.chris.controller.DVDLibraryController;
import org.chris.dao.DVDLibraryDao;
import org.chris.dao.DVDLibraryDaoFileImpl;
import org.chris.ui.DVDLibraryView;
import org.chris.ui.UserIO;
import org.chris.ui.UserIOConsoleImpl;

public class Main {
    public static void main(String[] args)
    {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}