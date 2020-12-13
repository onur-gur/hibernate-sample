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
@Table(name = "actors")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "name")
    private String name;

    @Column(name = "nominations")
    private Integer nominations;

    @Column(name = "oscar_nominations")
    private Integer oscarNominations;

    @Column(name = "oscars")
    private Integer oscars;

    @Column(name = "wins")
    private Integer wins;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private List<Actor> actors = new LinkedList<>();

}
