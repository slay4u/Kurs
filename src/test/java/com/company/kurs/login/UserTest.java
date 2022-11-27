package com.company.kurs.login;

import com.company.kurs.domain.Comanda;
import com.company.kurs.domain.Player;
import com.company.kurs.repository.PlayerRep;
import com.company.kurs.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class UserTest {
    @InjectMocks
    @MockBean
    User userB = Mockito.mock(User.class);

    @MockBean(name = "playerRep")
    PlayerRep playerRep = Mockito.mock(PlayerRep.class);

    private final String string = "Zoria";

    @Test
    void isEnabled() {
        User user = User.builder().id(1).username("userLogin").password("userPassword").build();
        Mockito.doAnswer(invocation -> user.getUsername().equals("userLogin") && user.getPassword().equals("userPassword")).when(userB).isEnable();
        boolean pass = userB.isEnable();
        assertTrue(pass, "Такого користувача не існує.");
        trueTable(Player.class);
    }

    @Test
    void trueTable(Class clas) {
        boolean cls = clas.equals(Player.class);
        assertTrue(cls, "Невірно обраний розділ.");
        trueName(string);
    }

    @Test
    void trueName(String str) {
        List<String> str1 = new ArrayList<>();
        str1.add("Zoria");
        str1.add("Dinamo");
        boolean bool = false;
        for (String s : str1) {
            if (str.equals(s)) {
                bool = true;
                break;
            }
        }
        assertTrue(bool, "Назва команди не знайдена.");
        getPlayerByComanda();
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
                if(player.getPlayerComanda().getName_c().equals(string)) {
                    list1.add(player);
                }
            });
            return list1;
        }).when(playerRep).findPlayerByComanda(string);
        List<Player> expectedList = playerRep.findPlayerByComanda(string);
        AssertionsForClassTypes.assertThat(expectedList).isSameAs(list1);
        verify(playerRep).findPlayerByComanda(string);
        for (Player p : expectedList) {
            System.out.println(p.getPibPlayer());
        }
    }
}