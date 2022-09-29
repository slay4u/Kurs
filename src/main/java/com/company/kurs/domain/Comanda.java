package com.company.kurs.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Comanda")
@Getter
@Setter
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComanda;
    @Column(nullable = false, unique = true, length = 50)
    private String name_c;
    @Column(length = 50, nullable = false, unique = true)
    private String logo;
    @Column(length = 50, nullable = true)
    private String country1;

    @OneToMany(mappedBy = "playerComanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Player> players;

    @OneToMany(mappedBy = "trenerComanda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trener> treners;

    @OneToMany(mappedBy = "gameComanda1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Game> games1;

    @OneToMany(mappedBy = "gameComanda2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Game> games2;
}
