package com.company.kurs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Player")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"gols", "games", "travmas", "playerComanda"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlayer;

    @Column(nullable = false, unique = true, length = 50)
    private String pibPlayer;
    @Column(length = 50, nullable = false, unique = false)
    private String country2;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda playerComanda;

    @OneToMany(mappedBy = "travmaPlayer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Travma> travmas;

    @ManyToMany(mappedBy = "players", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "golPlayer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Gol> gols;

    @Override
    public String toString() {
        return this.pibPlayer;
    }
}
