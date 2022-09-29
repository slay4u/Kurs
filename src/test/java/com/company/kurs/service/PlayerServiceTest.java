package com.company.kurs.service;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    }

    @Test
    void getPlayerByLetter() {
    }
}