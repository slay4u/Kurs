package com.company.kurs.query;

public class ComandaAmount {
    private String name_com;
    private Long amount;

    public ComandaAmount(String name_com, Long amount) {
        this.name_com = name_com;
        this.amount = amount;
    }

    public String getName_com() {
        return name_com;
    }

    public void setName_com(String name_com) {
        this.name_com = name_com;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
