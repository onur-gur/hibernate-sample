package org.moviedb.service;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.moviedb.model.Movie;
import org.moviedb.util.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

public class MyHibernateService {
    /*
        private Metadata metadata;

        public MyHibernateService() {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            metadata = new MetadataSources(ssr)
                    .getMetadataBuilder()
                    .build();
        }

        public  void addMovie(Movie movie){
           try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()){
                try(Session session = sessionFactory.openSession()){
                    final Transaction transaction = session.beginTransaction();

                    session.saveOrUpdate(movie);

                    transaction.commit();
                }
            }
        }
    */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Movie> findMovies() {
        /*try(SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()){
            try(Session session = sessionFactory.openSession()){
                List<Movie> results = session.createQuery("from Movie m where m.imdbScore > :score",Movie.class)
                        .setParameter("score",8.0)
                        .getResultList();
                return results;
            }
        }*/

        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Movie m where m.imdbScore > :score", Movie.class).setParameter("score", 8.0);
        List<Movie> movies = query.getResultList();
        session.close();
        return movies;
    }

    public void addMovie(Movie movie) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.saveOrUpdate(movie);
            transaction.commit();
            System.out.println("Record inserted successfully");

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int updateMovie(Movie movie) {
        if (movie.getId() <= 0)
            return 0;

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Movie set  director=:director, duration=:duration, imdbScore=:imdbScore, name=:name, year=:year where id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", movie.getId());
        query.setParameter("name", movie.getName());
        query.setParameter("duration", movie.getDuration());
        query.setParameter("imdbScore", movie.getImdbScore());
        query.setParameter("director", movie.getDirector());
        query.setParameter("year",movie.getYear());

        int rowCount = query.executeUpdate();

        System.out.println("Rows affected: " + rowCount);
        transaction.commit();
        session.close();
        return rowCount;
    }

    public int deleteMovie(int id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "delete from Movie where id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        transaction.commit();
        session.close();
        return rowCount;
    }

    public Movie findLastMovie() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Movie m order by id desc", Movie.class);
        List<Movie> movie = query.getResultList();
        return movie.get(0);
    }
}
