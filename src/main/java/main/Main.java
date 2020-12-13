package main;

import org.moviedb.model.Movie;
import org.moviedb.service.MyHibernateService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hi!");
        List<Movie> movies = new MyHibernateService().findMovies();
        System.out.println(movies.get(0).getName());
        System.out.println(new MyHibernateService().findLastMovie().getName());

        Movie movie = new Movie();
        movie.setName("303");
        movie.setDirector("Hans Weingartner");
        movie.setImdbScore(7.5);
        movie.setYear(2018);
        movie.setDuration(145);

        new MyHibernateService().addMovie(movie);
        System.out.println(new MyHibernateService().findLastMovie().getName());
        System.out.println(new MyHibernateService().findLastMovie().getDuration());

    }

}