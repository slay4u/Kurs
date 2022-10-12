package com.company.kurs.domain;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Game")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGame;

    @Column(nullable = false, unique = false, length = 50)
    private java.sql.Date dateGame;
    @Column(length = 50, nullable = true, unique = false)
    private String rah;
    @Column(nullable = false, unique = false, length = 50)
    private String timeGame;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda gameComanda1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "name_c", nullable = false)
    private Comanda gameComanda2;

    @OneToMany(mappedBy = "golGame", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Gol> gols;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "game_player",
            joinColumns = {
                    @JoinColumn(name = "id_game",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_player",
                            nullable = false, updatable = false)})
    private Set<Player> players = new HashSet<>();
}
