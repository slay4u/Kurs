package com.company.kurs.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Travma")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Travma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTravma;

    @Column(nullable = false, unique = false, length = 50)
    private String description;
    @Column(length = 50, nullable = false, unique = false)
    private java.sql.Date dateTravma;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_player", nullable = false)
    private Player travmaPlayer;
}
