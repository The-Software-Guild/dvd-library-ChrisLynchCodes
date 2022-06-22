package org.chris.ui;

import org.chris.dto.Actor;
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
        io.print("3. View a specific DVD by Id");
        io.print("4. View a specific DVD by Title");
        io.print("5. Edit a DVD");
        io.print("6. Remove a DVD");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public DVD getEditDetails(DVD dvd)
    {
        dvd.setTitle(io.readString("Please enter new DVD title"));
        dvd.setReleaseDate(io.readString("Please enter new DVD release date"));
        dvd.setMpaaRating(io.readString("Please enter new MPAA Rating"));
        dvd.setDirectorsName(io.readString("Please enter new directors name"));
        dvd.setStudio(io.readString("Please enter new studio name"));
        dvd.setUserRating(io.readString("Please enter new user rating"));
        List<Actor> actors = dvd.getActors();
        actors.clear();
        String[] actorTokens = io.readString("Please enter new actors. Split by ','").split(",");
        for (String actor : actorTokens) {
            actors.add(new Actor(actor));
        }
        return dvd;

    }

    public DVD getNewDvdInfo()
    {
        DVD currentDVD = new DVD(UUID.randomUUID().toString());
        currentDVD.setTitle(io.readString("Please enter DVD title"));
        currentDVD.setMpaaRating(io.readString("Please enter MPAA Rating"));
        currentDVD.setDirectorsName(io.readString("Please enter directors name"));
        currentDVD.setStudio(io.readString("Please enter studio name"));
        currentDVD.setUserRating(io.readString("Please enter user rating"));
        currentDVD.setReleaseDate(io.readString("Please enter release date"));


        boolean continueAddingActors = true;
        while (continueAddingActors) {
            currentDVD.getActors().add(new Actor(io.readString("Enter an actor name")));

            //if they do not enter Y then stop asking
            if (!io.readString("Enter another actor? [Y]/[N]").equalsIgnoreCase("Y"))
                continueAddingActors = false;
        }
        return currentDVD;
    }

    public void displayDvdList(List<DVD> dvdList)
    {
        for (DVD currentDvd : dvdList) {

            //using string.format
            String initialDvdInfo = String.format("\nID-%s%nTitle:%s - Release Date:%s - Rating:%s\nDirector:%s - Studio:%s - User Rating:%s%nActors:",
                    currentDvd.getDvdId(),
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getMpaaRating(),
                    currentDvd.getDirectorsName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());


            List<Actor> actors = currentDvd.getActors();
            StringBuilder dvdInfo = new StringBuilder(initialDvdInfo);

            for (Actor actor : actors) {
                dvdInfo.append("-");
                dvdInfo.append(actor.getName());
            }
            io.print(dvdInfo.toString());
        }
        io.readString("Please hit enter to continue.");
    }

    public String getDvdIdChoice()
    {
        return io.readString("Please enter the dvd ID.");
    }
    public String getDvdIdTitle()
    {
        return io.readString("Please enter the dvd Title.");
    }

    public void displayDvd(DVD dvd)
    {
        if (dvd != null) {
            io.print(String.format("\nID-%s%nTitle:%s - Release Date:%s - Rating:%s\nDirector:%s - Studio:%s - User Rating:%s",
                    dvd.getDvdId(),
                    dvd.getTitle(),
                    dvd.getReleaseDate(),
                    dvd.getMpaaRating(),
                    dvd.getDirectorsName(),
                    dvd.getStudio(),
                    dvd.getUserRating()));


            List<Actor> actors = dvd.getActors();
            for (int i = 0; i < actors.size(); i++) {
                Actor actor = actors.get(i);
                io.print("Actor " + (i + 1) + " " + actor.getName());
            }
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
