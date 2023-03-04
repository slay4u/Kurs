package com.company.kurs.service;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Player;
import com.company.kurs.repository.PlayerRep;
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
import java.io.ByteArrayInputStream;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;
    @MockBean(name = "playerRep")
    PlayerRep playerRep = Mockito.mock(PlayerRep.class);

    @Test
    void listAll() {
        Player player1 = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        Player player2 = Player.builder().idPlayer(2).pibPlayer("Valentin Shulga").country2("UK").build();
        Player player3 = Player.builder().idPlayer(3).pibPlayer("Dmytro Vovk").country2("DN").build();
        List<Player> list = new ArrayList<>(Arrays.asList(player1, player2, player3));
        Mockito.doAnswer(invocation -> list).when(playerRep).findAll();
        List<Player> listResult = (List<Player>) playerRep.findAll();
        assertThat(listResult).isSameAs(list);
        verify(playerRep).findAll();
    }

    @Test
    void save() {
        Player player = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        when(playerRep.save(ArgumentMatchers.any(Player.class))).thenReturn(player);
        Player createdPlayer = playerRep.save(player);
        assertThat(createdPlayer.getIdPlayer()).isSameAs(player.getIdPlayer());
        verify(playerRep).save(player);
    }

    @Test
    void get() {
        Player player = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        when(playerRep.findById(player.getIdPlayer())).thenReturn(Optional.of(player));
        Optional<Player> createdPlayer = playerRep.findById(player.getIdPlayer());
        assertNotNull(createdPlayer);
        verify(playerRep).findById(player.getIdPlayer());
    }

    @Test
    void delete() {
        Player player = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        given(playerRep.countByIdPlayer(anyInt())).willReturn(null);
        assertThrows(EntityNotFoundException.class, () -> playerService.delete(player.getIdPlayer()));
    }

    @Test
    void makeCollection() {
        Player player1 = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        Player player2 = Player.builder().idPlayer(2).pibPlayer("Valentin Shulga").country2("UK").build();
        Player player3 = Player.builder().idPlayer(3).pibPlayer("Dmytro Vovk").country2("DN").build();
        List<Player> list = new ArrayList<>(Arrays.asList(player1, player2, player3));
        List<Player> list1 = PlayerService.makeCollection(list);
        assertThrows(AssertionError.class, () -> assertThat(list1).isSameAs(list));
    }

    @Test
    void getPlayerByComanda() {
        Comanda comanda1 = Comanda.builder().idComanda(1).name_c("Zoria").build();
        Comanda comanda2 = Comanda.builder().idComanda(2).name_c("Dinamo").build();
        Player player1 = Player.builder().idPlayer(1).playerComanda(comanda1).pibPlayer("Victor Krylosov").country2("UA").build();
        Player player2 = Player.builder().idPlayer(2).playerComanda(comanda1).pibPlayer("Valentin Shulga").country2("UK").build();
        Player player3 = Player.builder().idPlayer(3).playerComanda(comanda2).pibPlayer("Dmytro Vovk").country2("DN").build();
        List<Player> list = new ArrayList<>(Arrays.asList(player1, player2, player3));
        List<Player> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(player -> {
                if(player.getPlayerComanda().getName_c().equals("Zoria")) {
                    list1.add(player);
                }
            });
            return list1;
        }).when(playerRep).findPlayerByComanda("Zoria");
        List<Player> expectedList = playerRep.findPlayerByComanda("Zoria");
        assertThat(expectedList).isSameAs(list1);
        verify(playerRep).findPlayerByComanda("Zoria");
    }

    @Test
    void getPlayerByLetter() {
        Player player1 = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").country2("UA").build();
        Player player2 = Player.builder().idPlayer(2).pibPlayer("Valentin Shulga").country2("UK").build();
        Player player3 = Player.builder().idPlayer(3).pibPlayer("Dmytro Vovk").country2("DN").build();
        List<Player> list = new ArrayList<>(Arrays.asList(player1, player2, player3));
        List<Player> list1 = new ArrayList<>();
        Mockito.doAnswer(invocation -> {
            list.forEach(player -> {
                if(player.getPibPlayer().startsWith("V")) {
                    list1.add(player);
                }
            });
            return list1;
        }).when(playerRep).findPlayerByLetter("V");
        List<Player> expectedList = playerRep.findPlayerByLetter("V");
        assertThat(expectedList).isSameAs(list1);
        verify(playerRep).findPlayerByLetter("V");
    }

    @Test
    void getPlayerByAge() {
        Player player1 = Player.builder().idPlayer(1).pibPlayer("Victor Krylosov").agePlayer((double) 19).build();
        Player player2 = Player.builder().idPlayer(2).pibPlayer("Valentin Shulga").agePlayer((double) 36).build();
        Player player3 = Player.builder().idPlayer(3).pibPlayer("Dmytro Vovk").agePlayer((double) 55).build();
        List<Player> list = new ArrayList<>(Arrays.asList(player1, player2, player3));
        List<Player> list1 = new ArrayList<>();
        String data = "10" + "\n100.1S";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        Double age1 = Double.parseDouble(input1);
        Double age2 = Double.parseDouble(input2);
        if(age1 < 0){
            throw new IllegalArgumentException("Invalid input data before border 0: " + age1);
        }
        if(age2 > 100){
            throw new IllegalArgumentException("Invalid input data after border 100: " + age2);
        }
        Mockito.doAnswer(invocation -> {
            list.forEach(player -> {
                if(player.getAgePlayer() > age1 && player.getAgePlayer() < age2) {
                    list1.add(player);
                }
            });
            if (age1 == 0){
                System.out.println("Minimal border " + age1);
            }
            if (age2 == 100){
                System.out.println("Maximal border " + age2);
            }
            System.out.println("Test was executed with input values: " + age1 + "; " + age2);
            return list1;
        }).when(playerRep).findPlayerByAge(age1, age2);
        List<Player> expected = playerRep.findPlayerByAge(age1, age2);
        assertThat(expected).isSameAs(list1);
        verify(playerRep).findPlayerByAge(age1, age2);
    }

}