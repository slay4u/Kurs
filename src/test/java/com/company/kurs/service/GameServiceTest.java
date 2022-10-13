package com.company.kurs.service;

import com.company.kurs.domain.Game;
import com.company.kurs.repository.GameRep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;
    @MockBean(name = "gameRep")
    GameRep gameRep = Mockito.mock(GameRep.class);

    @Test
    void listAll() {
        Game game1 = Game.builder().idGame(1).timeGame("12:30").rah("1-0").build();
        Game game2 = Game.builder().idGame(2).timeGame("15:30").rah("4-1").build();
        Game game3 = Game.builder().idGame(3).timeGame("17:00").rah("0-7").build();
        List<Game> list = new ArrayList<>(Arrays.asList(game1, game2, game3));
        Mockito.doAnswer(invocation -> list).when(gameRep).findAll();
        List<Game> listResult = (List<Game>) gameRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(gameRep).findAll();
    }

    @Test
    void save() {
        Game game = Game.builder().idGame(1).timeGame("12:30").rah("1-0").build();
        when(gameRep.save(ArgumentMatchers.any(Game.class))).thenReturn(game);
        Game createdGame = gameRep.save(game);
        assertThat(createdGame.getIdGame()).isSameAs(game.getIdGame());
        verify(gameRep).save(game);
    }

    @Test
    void get() {
        Game game = Game.builder().idGame(1).timeGame("12:30").rah("1-0").build();
        when(gameRep.findById(game.getIdGame())).thenReturn(Optional.of(game));
        Optional<Game> createdGame = gameRep.findById(game.getIdGame());
        assertNotNull(createdGame);
        verify(gameRep).findById(game.getIdGame());
    }

    @Test
    void delete() {
        Game game = Game.builder().idGame(1).timeGame("12:30").rah("1-0").build();
        given(gameRep.countByIdGame(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> gameService.delete(game.getIdGame()));
    }

    @Test
    void makeCollection() {
        Game game1 = Game.builder().idGame(1).timeGame("12:30").rah("1-0").build();
        Game game2 = Game.builder().idGame(2).timeGame("15:30").rah("4-1").build();
        Game game3 = Game.builder().idGame(3).timeGame("17:00").rah("0-7").build();
        List<Game> list = new ArrayList<>(Arrays.asList(game1, game2, game3));
        List<Game> list1 = GameService.makeCollection(list);
        assertThrows(AssertionError.class, () -> assertThat(list1).isSameAs(list));
    }

    @Test
    void getGameByDate() {
        Game game1 = Game.builder().idGame(1).timeGame("12:30").rah("1-0").dateGame(Date.valueOf("2022-01-01")).build();
        Game game2 = Game.builder().idGame(2).timeGame("15:30").rah("4-1").dateGame(Date.valueOf("2021-02-09")).build();
        Game game3 = Game.builder().idGame(3).timeGame("17:00").rah("0-7").dateGame(Date.valueOf("2023-11-21")).build();
        List<Game> list = new ArrayList<>(Arrays.asList(game1, game2, game3));
        Date date = Date.valueOf("2023-10-10");
        Date date1 = Date.valueOf("2021-01-01");
        List<Game> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(game -> {
                if((game.getDateGame().toLocalDate().compareTo(date.toLocalDate()) < 0) &&
                        (game.getDateGame().toLocalDate().compareTo(date1.toLocalDate()) > 0)) {
                    list1.add(game);
                }
            });
            return list1;
        }).when(gameRep).findGameByDate(date1, date);
        List<Game> expect = gameRep.findGameByDate(date1, date);
        assertThat(expect).isSameAs(list1);
    }

    @Test
    void getCount() {
        Game game1 = Game.builder().idGame(1).timeGame("12:30").rah("1-0").dateGame(Date.valueOf("2022-01-01")).build();
        Game game2 = Game.builder().idGame(2).timeGame("15:30").rah("4-1").dateGame(Date.valueOf("2022-02-09")).build();
        Game game3 = Game.builder().idGame(3).timeGame("17:00").rah("0-7").dateGame(Date.valueOf("2023-11-21")).build();
        List<Game> list = new ArrayList<>(Arrays.asList(game1, game2, game3));
        Date date = Date.valueOf("2022-02-28");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        AtomicReference<Double> amount = new AtomicReference<>((double) 0);
        Mockito.doAnswer(invocation -> {
            list.forEach(game -> {
                Calendar clGame = Calendar.getInstance();
                clGame.setTime(game.getDateGame());
                if(clGame.get(Calendar.MONTH) == cl.get(Calendar.MONTH)) {
                    amount.getAndSet(amount.get() + 1);
                }
            });
            return amount.get();
        }).when(gameRep).findCount();
        Double result = gameRep.findCount();
        assertThrows(AssertionError.class, () -> assertThat(result).isSameAs(amount.get()));
    }

    @Test
    void getLast() {
        Game game1 = Game.builder().idGame(1).timeGame("12:30").rah("1-0").dateGame(Date.valueOf("2022-01-01")).build();
        Game game2 = Game.builder().idGame(2).timeGame("15:30").rah("4-1").dateGame(Date.valueOf("2022-02-09")).build();
        Game game3 = Game.builder().idGame(3).timeGame("17:00").rah("0-7").dateGame(Date.valueOf("2023-11-21")).build();
        List<Game> list = new ArrayList<>(Arrays.asList(game1, game2, game3));
        AtomicReference<Date> date = new AtomicReference<>(Date.valueOf("2000-01-01"));
        List<Game> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            date.set(list.get(0).getDateGame());
            for (Game game : list) {
                if (game.getDateGame().compareTo(date.get()) > 0) {
                    date.set(game.getDateGame());
                }
            }
            list.forEach(game -> {
                if (game.getDateGame().toLocalDate().equals(date.get().toLocalDate())) {
                    list1.add(game);
                }
            });
            return list1;
        }).when(gameRep).findLast();
        List<Game> exp = gameRep.findLast();
        assertThat(exp).isSameAs(list1);
        verify(gameRep).findLast();
    }
}