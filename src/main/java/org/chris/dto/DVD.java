package org.chris.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DVD {

    private final String dvdId;
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studio;
    private String userRating;



    private List<Actor> actors;


    public DVD(String dvdId)
    {
        this.dvdId = dvdId;
        actors = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DVD dvd = (DVD) o;
        return dvdId.equals(dvd.dvdId) && title.equals(dvd.title) && releaseDate.equals(dvd.releaseDate) && mpaaRating.equals(dvd.mpaaRating) && directorsName.equals(dvd.directorsName) && studio.equals(dvd.studio) && userRating.equals(dvd.userRating) && actors.equals(dvd.actors);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(dvdId, title, releaseDate, mpaaRating, directorsName, studio, userRating, actors);
    }

    @Override
    public String toString()
    {
        return "DVD{" +
                "dvdId='" + dvdId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorsName='" + directorsName + '\'' +
                ", studio='" + studio + '\'' +
                ", userRating='" + userRating + '\'' +
                ", actors=" + actors +
                '}';
    }

    public List<Actor> getActors()
    {
        return actors;
    }

    public void setActors(List<Actor> actors)
    {
        this.actors = actors;
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
