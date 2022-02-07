/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Next Design
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    
    @XmlElement(name = "idMovie")
    private int idMovie;
    private String title;
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    @XmlElement(name = "pubdate")
    private LocalDateTime pubDate;
    private String description;
    @XmlElement(name = "originaltitle")
    private String originalTitle;
    
    private String director; 
    @XmlElementWrapper
    @XmlElement(name = "director")
    private List<Director> directors;
    
    private String actor;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Actor> actors;
    
    private String duration;
    
    private String genre;
    @XmlElementWrapper
    @XmlElement(name = "genre")
    private List<Genre> genres;
    
    @XmlElement(name = "posterpath")
    private String posterPath;
    private String link;
    @XmlJavaTypeAdapter(PublishedDateAdapterShort.class)
    private LocalDate expected;

    public Movie() {}

    public Movie(String title, LocalDateTime pubDate, String description, String originalTitle, String duration, String posterPath, String link, LocalDate expected) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.expected = expected;
    }

    public Movie(int idMovie, String title, LocalDateTime pubDate, String description, String originalTitle, String duration, String posterPath, String link, LocalDate expected) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.expected = expected;
    }

    public Movie(int idMovie, String title, LocalDateTime pubDate, String description, String originalTitle, List<Director> directors, List<Actor> actors, String duration, List<Genre> genres, String posterPath, String link, LocalDate expected) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.directors = directors;
        this.actors = actors;
        this.duration = duration;
        this.genres = genres;
        this.posterPath = posterPath;
        this.link = link;
        this.expected = expected;
    }
    
    
    

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getExpected() {
        return expected;
    }

    public void setExpected(LocalDate expected) {
        this.expected = expected;
    }

    @Override
    public String toString() {
        return idMovie + " - " + title;
    }

}
