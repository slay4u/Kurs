package com.company.kurs.query;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ComandaAmountTest {

    @Test
    void getName_com() throws NoSuchFieldException, IllegalAccessException {
        final ComandaAmount comandaAmount = new ComandaAmount();
        final Field field = comandaAmount.getClass().getDeclaredField("name_com");
        field.setAccessible(true);
        field.set(comandaAmount, "magic");
        final String res = comandaAmount.getName_com();
        assertEquals("magic", res, "nice");
    }

    @Test
    void getAmount() throws NoSuchFieldException, IllegalAccessException {
        final ComandaAmount comandaAmount = new ComandaAmount();
        final Field field = comandaAmount.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        Long as = (long) 100;
        field.set(comandaAmount, as);
        final Long res = comandaAmount.getAmount();
        assertEquals(100, res, "nice");
    }

    @Test
    void setName_com() throws NoSuchFieldException, IllegalAccessException {
        final ComandaAmount comandaAmount = new ComandaAmount();
        comandaAmount.setName_com("cool");
        final Field field = comandaAmount.getClass().getDeclaredField("name_com");
        field.setAccessible(true);
        assertEquals("cool", field.get(comandaAmount), "nice");
    }

    @Test
    void setAmount() throws NoSuchFieldException, IllegalAccessException {
        final ComandaAmount comandaAmount = new ComandaAmount();
        comandaAmount.setAmount((long) 100);
        final Field field = comandaAmount.getClass().getDeclaredField("amount");
        field.setAccessible(true);
        assertEquals((long) 100, field.get(comandaAmount), "nice");
    }
}