package com.company.kurs.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ComandaTest {

    @Test
    void getIdComanda() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("idComanda");
        field.setAccessible(true);
        field.set(comanda, 1);
        final Integer res = comanda.getIdComanda();
        assertEquals(1, res, "nice");
    }

    @Test
    void getName_c() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("name_c");
        field.setAccessible(true);
        field.set(comanda, "cool");
        final String res = comanda.getName_c();
        assertEquals("cool", res, "nice");
    }

    @Test
    void getLogo() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("logo");
        field.setAccessible(true);
        field.set(comanda, "cool");
        final String res = comanda.getLogo();
        assertEquals("cool", res, "nice");
    }

    @Test
    void getCountry1() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("country1");
        field.setAccessible(true);
        field.set(comanda, "cool");
        final String res = comanda.getCountry1();
        assertEquals("cool", res, "nice");
    }

    @Test
    void getPlayers() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("players");
        Player as = new Player();
        Set<Player> pl = new HashSet<>();
        pl.add(as);
        field.setAccessible(true);
        field.set(comanda, pl);
        final Set<Player> res = comanda.getPlayers();
        res.add(as);
        assertEquals(pl, res, "nice");
    }

    @Test
    void getTreners() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("treners");
        Trener at = new Trener();
        Set<Trener> tl = new HashSet<>();
        tl.add(at);
        field.setAccessible(true);
        field.set(comanda, tl);
        final Set<Trener> res = comanda.getTreners();
        res.add(at);
        assertEquals(tl, res, "nice");
    }

    @Test
    void getGames1() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("games1");
        Game g1 = new Game();
        Set<Game> gl = new HashSet<>();
        gl.add(g1);
        field.setAccessible(true);
        field.set(comanda, gl);
        final Set<Game> res = comanda.getGames1();
        res.add(g1);
        assertEquals(gl, res, "nice");
    }

    @Test
    void getGames2() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        final Field field = comanda.getClass().getDeclaredField("games2");
        Game g2 = new Game();
        Set<Game> gl = new HashSet<>();
        gl.add(g2);
        field.setAccessible(true);
        field.set(comanda, gl);
        final Set<Game> res = comanda.getGames2();
        res.add(g2);
        assertEquals(gl, res, "nice");
    }

    @Test
    void setIdComanda() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        comanda.setIdComanda(1);
        final Field field = comanda.getClass().getDeclaredField("idComanda");
        field.setAccessible(true);
        assertEquals(1, field.get(comanda), "nice");
    }

    @Test
    void setName_c() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        comanda.setName_c("cool");
        final Field field = comanda.getClass().getDeclaredField("name_c");
        field.setAccessible(true);
        assertEquals("cool", field.get(comanda), "nice");
    }

    @Test
    void setLogo() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        comanda.setLogo("cool");
        final Field field = comanda.getClass().getDeclaredField("logo");
        field.setAccessible(true);
        assertEquals("cool", field.get(comanda), "nice");
    }

    @Test
    void setCountry1() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        comanda.setCountry1("cool");
        final Field field = comanda.getClass().getDeclaredField("country1");
        field.setAccessible(true);
        assertEquals("cool", field.get(comanda), "nice");
    }

    @Test
    void setPlayers() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        Player pl = new Player();
        Set<Player> players = new HashSet<>();
        players.add(pl);
        comanda.setPlayers(players);
        final Field field = comanda.getClass().getDeclaredField("players");
        field.setAccessible(true);
        assertEquals(players, field.get(comanda), "nice");
    }

    @Test
    void setTreners() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        Trener tl = new Trener();
        Set<Trener> treners = new HashSet<>();
        treners.add(tl);
        comanda.setTreners(treners);
        final Field field = comanda.getClass().getDeclaredField("treners");
        field.setAccessible(true);
        assertEquals(treners, field.get(comanda), "nice");
    }

    @Test
    void setGames1() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        Game gl = new Game();
        Set<Game> games = new HashSet<>();
        games.add(gl);
        comanda.setGames1(games);
        final Field field = comanda.getClass().getDeclaredField("games1");
        field.setAccessible(true);
        assertEquals(games, field.get(comanda), "nice");
    }

    @Test
    void setGames2() throws NoSuchFieldException, IllegalAccessException {
        final Comanda comanda = new Comanda();
        Game gl = new Game();
        Set<Game> games = new HashSet<>();
        games.add(gl);
        comanda.setGames2(games);
        final Field field = comanda.getClass().getDeclaredField("games2");
        field.setAccessible(true);
        assertEquals(games, field.get(comanda), "nice");
    }
}