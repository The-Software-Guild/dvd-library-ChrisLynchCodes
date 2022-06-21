package org.chris.dto;

public class DVD {

    private final String dvdId;
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studio;
    private String userRating;


    public DVD(String dvdId)
    {
        this.dvdId = dvdId;
    }

    public String getDvdId()
    {
        return dvdId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating()
    {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating)
    {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName()
    {
        return directorsName;
    }

    public void setDirectorsName(String directorsName)
    {
        this.directorsName = directorsName;
    }

    public String getStudio()
    {
        return studio;
    }

    public void setStudio(String studio)
    {
        this.studio = studio;
    }

    public String getUserRating()
    {
        return userRating;
    }

    public void setUserRating(String userRating)
    {
        this.userRating = userRating;
    }
}
