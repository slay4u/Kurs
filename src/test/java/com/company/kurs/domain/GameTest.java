package com.company.kurs.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getIdGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("idGame");
        field.setAccessible(true);
        field.set(game, 1);
        final Integer res = game.getIdGame();
        assertEquals(1, res, "nice");
    }

    @Test
    void getDateGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("dateGame");
        field.setAccessible(true);
        field.set(game, Date.valueOf("2000-01-01"));
        final Date res = game.getDateGame();
        assertEquals(Date.valueOf("2000-01-01"), res, "nice");
    }

    @Test
    void getRah() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("rah");
        field.setAccessible(true);
        field.set(game, "2-0");
        final String res = game.getRah();
        assertEquals("2-0", res, "nice");
    }

    @Test
    void getTimeGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("timeGame");
        field.setAccessible(true);
        field.set(game, "13:00");
        final String res = game.getTimeGame();
        assertEquals("13:00", res, "nice");
    }

    @Test
    void getGameComanda1() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("gameComanda1");
        Comanda as = new Comanda();
        field.setAccessible(true);
        field.set(game, as);
        final Comanda res = game.getGameComanda1();
        assertEquals(as, res, "nice");
    }

    @Test
    void getGameComanda2() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("gameComanda2");
        Comanda as = new Comanda();
        field.setAccessible(true);
        field.set(game, as);
        final Comanda res = game.getGameComanda2();
        assertEquals(as, res, "nice");
    }

    @Test
    void getGols() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("gols");
        Gol gol = new Gol();
        Set<Gol> gl = new HashSet<>();
        gl.add(gol);
        field.setAccessible(true);
        field.set(game, gl);
        final Set<Gol> res = game.getGols();
        res.add(gol);
        assertEquals(gl, res, "nice");
    }

    @Test
    void getPlayers() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        final Field field = game.getClass().getDeclaredField("players");
        Player player = new Player();
        Set<Player> gl = new HashSet<>();
        gl.add(player);
        field.setAccessible(true);
        field.set(game, gl);
        final Set<Player> res = game.getPlayers();
        res.add(player);
        assertEquals(gl, res, "nice");
    }

    @Test
    void setIdGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        game.setIdGame(1);
        final Field field = game.getClass().getDeclaredField("idGame");
        field.setAccessible(true);
        assertEquals(1, field.get(game), "nice");
    }

    @Test
    void setDateGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        game.setDateGame(Date.valueOf("2000-01-01"));
        final Field field = game.getClass().getDeclaredField("dateGame");
        field.setAccessible(true);
        assertEquals(Date.valueOf("2000-01-01"), field.get(game), "nice");
    }

    @Test
    void setRah() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        game.setRah("2-0");
        final Field field = game.getClass().getDeclaredField("rah");
        field.setAccessible(true);
        assertEquals("2-0", field.get(game), "nice");
    }

    @Test
    void setTimeGame() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        game.setTimeGame("13:00");
        final Field field = game.getClass().getDeclaredField("timeGame");
        field.setAccessible(true);
        assertEquals("13:00", field.get(game), "nice");
    }

    @Test
    void setGameComanda1() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        Comanda comanda = new Comanda();
        game.setGameComanda1(comanda);
        final Field field = game.getClass().getDeclaredField("gameComanda1");
        field.setAccessible(true);
        assertEquals(comanda, field.get(game), "nice");
    }

    @Test
    void setGameComanda2() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        Comanda comanda = new Comanda();
        game.setGameComanda2(comanda);
        final Field field = game.getClass().getDeclaredField("gameComanda2");
        field.setAccessible(true);
        assertEquals(comanda, field.get(game), "nice");
    }

    @Test
    void setGols() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        Gol gol = new Gol();
        Set<Gol> gols = new HashSet<>();
        gols.add(gol);
        game.setGols(gols);
        final Field field = game.getClass().getDeclaredField("gols");
        field.setAccessible(true);
        assertEquals(gols, field.get(game), "nice");
    }

    @Test
    void setPlayers() throws NoSuchFieldException, IllegalAccessException {
        final Game game = new Game();
        Player player = new Player();
        Set<Player> players = new HashSet<>();
        players.add(player);
        game.setPlayers(players);
        final Field field = game.getClass().getDeclaredField("players");
        field.setAccessible(true);
        assertEquals(players, field.get(game), "nice");
    }
}