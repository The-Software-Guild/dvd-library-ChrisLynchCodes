package org.chris.ui;

import org.chris.dto.DVD;

import java.util.List;
import java.util.UUID;

public class DVDLibraryView {
    private final UserIO io;

    public DVDLibraryView(UserIO io)
    {
        this.io = io;
    }


    public void displayErrorMessage(String errorMsg)
    {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }


    public int printMenuAndGetSelection()
    {
        io.print("Main Menu");
        io.print("1. List DVDS");
        io.print("2. Add DVD to library");
        io.print("3. View a specific DVD");
        io.print("4. Edit a DVD");
        io.print("5. Remove a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public DVD getEditDetails(DVD dvd)
    {
        //Write ternary
        dvd.setTitle(io.readString("Please enter new DVD title"));
        dvd.setMpaaRating(io.readString("Please enter new MPAA Rating"));
        dvd.setDirectorsName(io.readString("Please enter new directors name"));
        dvd.setStudio(io.readString("Please enter new studio name"));
        dvd.setUserRating(io.readString("Please enter new user rating"));
        return dvd;

    }

    public DVD getNewDvdInfo()
    {
        String title = io.readString("Please enter DVD title");
        String mPAARating = io.readString("Please enter MPAA Rating");
        String directorsName = io.readString("Please enter directors name");
        String studio = io.readString("Please enter studio name");
        String userRating = io.readString("Please enter user rating");
        DVD currentDVD = new DVD(UUID.randomUUID().toString());
        currentDVD.setTitle(title);
        currentDVD.setMpaaRating("18");
        currentDVD.setDirectorsName("Steve Spielberg");
        currentDVD.setStudio("WB");
        currentDVD.setUserRating("Good Movie");
        currentDVD.setReleaseDate("22/06/23");
        return currentDVD;
    }

    public void displayDvdList(List<DVD> dvdList)
    {
        for (DVD currentDvd : dvdList) {
            String dvdInfo = String.format("ID-%s%n%s : %s %s : %s -- %s - %s%n",
                    currentDvd.getDvdId(),
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorsName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDvdIdChoice()
    {
        return io.readString("Please enter the dvd ID.");
    }

    public void displayDvd(DVD dvd)
    {
        if (dvd != null) {
            io.print(dvd.getDvdId());
            io.print(dvd.getTitle() + " " + dvd.getDirectorsName());
            io.print(dvd.getReleaseDate());
            io.print("");
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveResult(DVD dvdRecord)
    {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }


    //////////Banners/////////
    public void displayExitBanner()
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner()
    {
        io.print("Unknown Command!!!");
    }

    public void displayRemoveDvdBanner()
    {
        io.print("=== Remove DVD ===");
    }

    public void displayDisplayDvdBanner()
    {
        io.print("=== Display DVD ===");
    }

    public void displayDisplayAllBanner()
    {
        io.print("=== Display All DVDs ===");
    }

    public void displayCreateDvdBanner()
    {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner()
    {
        io.readString("DVD successfully added to library.  Please hit enter to continue");
    }

    public void displayEditDvdBanner()
    {
        io.print("=== Edit DVD ===");
    }

    public void displayEditSuccessBanner()
    {
        io.readString("DVD successfully edited & changes saved to library. Please hit enter to continue");
    }
}
