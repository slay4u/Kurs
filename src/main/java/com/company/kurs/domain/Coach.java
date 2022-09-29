package com.company.kurs.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coach {
    private String com;
    private Long am;

    public Coach(String com, Long am) {
        this.com = com;
        this.am = am;
    }
}
