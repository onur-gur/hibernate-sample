package org.moviedb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "director")
    private String director;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "imdb_score")
    private Double imdbScore;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Actor> actor = new LinkedList<>();

}
