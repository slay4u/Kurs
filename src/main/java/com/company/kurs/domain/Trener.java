package com.company.kurs.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Trener")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrener;

    @Column(nullable = false, unique = true, length = 50)
    private String pibtrener;
    @Column(length = 50, nullable = true, unique = false)
    private String result;
    @Column(length = 50, nullable = true)
    private String presence;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda trenerComanda;
}
