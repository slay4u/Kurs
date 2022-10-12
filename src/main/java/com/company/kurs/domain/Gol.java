package com.company.kurs.domain;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Gol")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGol;

    @Column(nullable = false, unique = false, length = 50)
    private String time1;

    @Column(nullable = false, unique = false, length = 50)
    private String timeGol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_game", nullable = false)
    private Game golGame;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_player", nullable = false)
    private Player golPlayer;
}
