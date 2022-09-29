package com.company.kurs.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComandaAmount {
    private String name_com;
    private Long amount;

    public ComandaAmount(String name_com, Long amount) {
        this.name_com = name_com;
        this.amount = amount;
    }
}
