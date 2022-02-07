/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Next Design
 */

public interface MovieRepository {
    
    int checkIfUserExists(String userName) throws Exception;
    int createUser(String username, String password, int role) throws Exception;
    //Optional<User> selectUser(int id) throws Exception;
    Optional<User> selectUserByName(String userName) throws Exception;
    
    int createMovie(Movie movie) throws Exception;
    //void createMovie(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    void eraseAllMoviesFromDatabase() throws Exception;
    
    
    
    int createGenre(int id, Genre genre) throws Exception;
    void createNewGenre(String genreName) throws Exception;
    void createGenres(List<Genre> genres) throws Exception;
    void updateGenre(int idGenre, String genreName) throws Exception;
    void deleteGenre(int id) throws Exception;
    Optional<Genre> selectGenre(int id) throws Exception;
    Optional<Genre> selectGenreByName(Genre genre) throws Exception;
    List<Genre> selectGenres(int selectedMovieId) throws Exception;
    List<Genre> getAllGenres() throws Exception;
    
    int createActor(int id, Actor actor) throws Exception;
    void createNewActor(String actorName) throws Exception;
    void createActors(List<Actor> actors) throws Exception;
    void updateActor(int idActor, String ActorName) throws Exception;
    void deleteActor(int id) throws Exception;
    Optional<Actor> selectActor(int id) throws Exception;
    Optional<Actor> selectActorByName(Actor actor) throws Exception;
    List<Actor> selectActors(int selectedMovieId) throws Exception;
    List<Actor> getAllActors() throws Exception;
    
    int createDirector(int id, Director director) throws Exception;
    void createNewDirector(String directorName) throws Exception;
    void createDirectors(List<Director> directors) throws Exception;
    void updateDirector(int idDirector, String directorName) throws Exception;
    void deleteDirector(int id) throws Exception;
    Optional<Director> selectDirector(int id) throws Exception;
    Optional<Director> selectDirectorByName(Director director) throws Exception;
    List<Director> selectDirectors(int selectedMovieId) throws Exception;
    List<Director> getAllDirectors() throws Exception;

    void populateMovieDirection(int idMovie, int idDirector) throws Exception;
    void populateMovieActor(int idMovie, int idActor) throws Exception;
    void populateMovieGenre(int idMovie, int idGenre) throws Exception;

    void updateMovieDirection(int idMovie, int idDirector) throws Exception;
    void updateMovieActor(int idMovie, int idActor) throws Exception;
    void updateMovieGenre(int idMovie, int idGenre) throws Exception;

    void removeActorsFromMovieCastByIdMovie(int idMovie) throws Exception;
    void removeDirectorsFromMovieCastByIdMovie(int idMovie) throws Exception;
    void removeGenresFromMovieCastByIdMovie(int idMovie) throws Exception;

    void deleteSelectedMovie(int idMovie) throws Exception;

    int checkIfActorRelatesToAMovie(int idActor) throws Exception;
    int checkIfDirectorRelatesToAMovie(int idDirector) throws Exception;
    int checkIfGenreRelatesToAMovie(int idGenre) throws Exception;



    



    

    

    

    



    


   
    
    
}
