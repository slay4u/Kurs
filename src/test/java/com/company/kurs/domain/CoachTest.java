package com.company.kurs.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {

    @Test
    void getCom() throws NoSuchFieldException, IllegalAccessException {
        final Coach coach = new Coach();
        final Field field = coach.getClass().getDeclaredField("com");
        field.setAccessible(true);
        field.set(coach, "magic");
        final String res = coach.getCom();
        assertEquals("magic", res, "nice");
    }

    @Test
    void getAm() throws NoSuchFieldException, IllegalAccessException {
        final Coach coach = new Coach();
        final Field field = coach.getClass().getDeclaredField("am");
        field.setAccessible(true);
        Long as = (long) 100;
        field.set(coach, as);
        final Long res = coach.getAm();
        assertEquals(100, res, "nice");
    }

    @Test
    void setCom() throws NoSuchFieldException, IllegalAccessException {
        final Coach coach = new Coach();
        coach.setCom("cool");
        final Field field = coach.getClass().getDeclaredField("com");
        field.setAccessible(true);
        assertEquals("cool", field.get(coach), "nice");
    }

    @Test
    void setAm() throws NoSuchFieldException, IllegalAccessException {
        final Coach coach = new Coach();
        coach.setAm((long) 100);
        final Field field = coach.getClass().getDeclaredField("am");
        field.setAccessible(true);
        assertEquals((long) 100, field.get(coach), "nice");
    }
}