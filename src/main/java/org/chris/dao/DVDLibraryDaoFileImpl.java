package org.chris.dao;

import org.chris.dto.Actor;
import org.chris.dto.DVD;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private final Map<String, DVD> dvds = new HashMap<>();
    public static final String DVD_LIBRARY_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    private void writeLibrary() throws DVDLibraryDaoException
    {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save dvd data.", e);
        }


        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDS();
        for (DVD currentDvd : dvdList) {
            // turn a dvd into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    //Data Structure to File
    private String marshallDvd(DVD aDvd)
    {
        StringBuilder actors = new StringBuilder();
        for (Actor actor : aDvd.getActors()) {
            actors.append(actor.getName());
            actors.append(",");
        }
        //handle comma for last actor
        actors.deleteCharAt(actors.lastIndexOf(","));
        return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                aDvd.getDvdId(),
                DELIMITER,
                aDvd.getTitle(),
                DELIMITER,
                aDvd.getReleaseDate(),
                DELIMITER,
                aDvd.getMpaaRating(),
                DELIMITER,
                aDvd.getDirectorsName(),
                DELIMITER,
                aDvd.getStudio(),
                DELIMITER,
                aDvd.getUserRating(),
                DELIMITER,
                actors

        );
    }


    private void loadLibrary() throws DVDLibraryDaoException
    {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            //catch the FileNotFoundException and translate it into a ClassRosterDaoException
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        DVD currentDvd;
        // Go through DVD_LIBRARY_FILE line by line, decoding each line into a
        // DVD object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the dvd id as the map key for our dvd object.
            // Put currentDvd into the map using dvd id as the key
            dvds.put(currentDvd.getDvdId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    //File to Data Structure
    private DVD unmarshallDvd(String dvdAsText)
    {

        // Given the pattern "0001::Die Hard::1989/12/21::18::John McTiernan::Universal::A+::actor/s"
        String[] dvdTokens = dvdAsText.split(DELIMITER);


        String dvdId = dvdTokens[0];

        // Index 0 - ID
        DVD dvdFromFile = new DVD(dvdId);

        // Index 1 - title
        dvdFromFile.setTitle(dvdTokens[1]);

        // Index 2 - release date
        dvdFromFile.setReleaseDate(dvdTokens[2]);

        // Index 3 - mpaa rating
        dvdFromFile.setMpaaRating(dvdTokens[3]);

        // Index 4 - directors name
        dvdFromFile.setDirectorsName(dvdTokens[4]);

        // Index 5 - studio
        dvdFromFile.setStudio(dvdTokens[5]);

        // Index 6 - user rating
        dvdFromFile.setUserRating(dvdTokens[6]);

        //actors
        String[] actorTokens = dvdTokens[7].split(",");
        for (String actor : actorTokens) {
            dvdFromFile.getActors().add(new Actor(actor));
        }

        return dvdFromFile;
    }

    @Override
    public DVD addDVD(String dVDId, DVD dVD) throws DVDLibraryDaoException
    {
        loadLibrary();
        DVD newDvd = dvds.put(dVDId, dVD);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<DVD> getAllDVDS() throws DVDLibraryDaoException
    {
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD getDvd(String dvdId) throws DVDLibraryDaoException
    {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public List<DVD> searchDvdByTitle(String title) throws DVDLibraryDaoException
    {
        loadLibrary();
        return dvds.values().stream()
                .filter(dvd -> dvd.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public DVD removeDvd(String dvdId) throws DVDLibraryDaoException
    {
        loadLibrary();
        DVD removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }


    @Override
    public DVD updateDvd(String dvdId, DVD editedDvd) throws DVDLibraryDaoException
    {
        loadLibrary();
        DVD updatedDvd = dvds.put(dvdId, editedDvd);
        writeLibrary();
        return updatedDvd;
    }
}
