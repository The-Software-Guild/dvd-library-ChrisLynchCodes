package org.chris;

import org.chris.controller.DVDLibraryController;
import org.chris.dao.DvdLibraryAuditDao;
import org.chris.dao.DvdLibraryAuditDaoFileImpl;
import org.chris.dao.DvdLibraryDao;
import org.chris.dao.DvdLibraryDaoFileImpl;
import org.chris.service.DvdLibraryServiceLayer;
import org.chris.service.DvdLibraryServiceLayerImpl;
import org.chris.ui.DVDLibraryView;
import org.chris.ui.UserIO;
import org.chris.ui.UserIOConsoleImpl;

public class Main {
    public static void main(String[] args)
    {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryAuditDao myAuditDao = new DvdLibraryAuditDaoFileImpl();
        DvdLibraryServiceLayer service = new DvdLibraryServiceLayerImpl(myDao, myAuditDao);
        DVDLibraryController controller = new DVDLibraryController(service, myView);
        controller.run();
    }
}