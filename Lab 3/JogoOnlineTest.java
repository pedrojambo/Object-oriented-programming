import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {   //Visto que os métodos da classe JogoOnline são static, os testes só funcionarão se forem feitos individualmente

    Jogador jogador1 = new Jogador("user1", "111");
    Jogador jogador2 = new Jogador("user2", "222");
    Jogador jogador3 = new Jogador("user3", "333");
    Jogador jogador4 = new Jogador("user4", "444");

    @Before
    public void setUp() {
        JogoOnline.jogadores.add(jogador1);
        JogoOnline.jogadores.add(jogador2);
        JogoOnline.jogadores.add(jogador3);
        JogoOnline.jogadores.add(jogador4);
    }

    @Test
    public void testeCadastro() { //Testa o metodo de cadastro e a checagem de usernames repetidos
        JogoOnline.Cadastrar("joaozinho", "acucar");
        JogoOnline.Cadastrar("joaninha", "tempero");
        JogoOnline.Cadastrar("fernandinho", "canela");
        JogoOnline.Cadastrar("joaozinho", "123");       //Deve falhar, pois joaozinho ja existe
        assertEquals(7, JogoOnline.jogadores.size());    //Deve conter os jogadores do "setUp" mais os 3 cadastros do teste
    }

    @Test
    public void testeLogin() { //Testa o metodo login
        JogoOnline.login("user1", "111");
        JogoOnline.login("user2", "222");
        JogoOnline.login("user3", "senha errada");
        JogoOnline.login("user2", "222");
        assertEquals("", true, jogador1.getStatus());   //Após o login, os jogadores devem estar online
        assertEquals("", true, jogador2.getStatus());
        assertEquals("Se uma senha errada" +
                "for inserida, o login deve falhar", false, jogador3.getStatus());
        assertEquals("Se o login nao for efetuado," +
                "o usuario deve estar como offline", false, jogador4.getStatus());
    }

    @Test
    public void testeLogout() { //Testa o metodo logout
        JogoOnline.login("user1", "111");
        JogoOnline.login("user2", "222");
        JogoOnline.logout("user1");         //deve funcionar
        JogoOnline.logout("user2");         //deve funcionar
        JogoOnline.logout("user3");         //não funciona, pois o jogador ja esta offline
        assertEquals("", false, jogador1.getStatus());
        assertEquals("", false, jogador2.getStatus());
        assertEquals("", false, jogador3.getStatus());
        assertEquals("", false, jogador4.getStatus());
    }

    @Test
    public void testeIniciarPartida() { //Testa o metodo iniciar partida
        JogoOnline.login("user1", "111");
        JogoOnline.login("user2", "222");
        JogoOnline.login("user3", "333");
        JogoOnline.iniciarPartida(jogador1, jogador3);    //deve funcionar
        JogoOnline.iniciarPartida(jogador2, jogador4);    //não deve funcionar, pois jogador 4 offline
        JogoOnline.iniciarPartida(jogador1, jogador2);    //não deve funcionar, pois jogador 1 em partida
        Assert.assertTrue(jogador1.getAtividade());       //jogador 1 e 3 devem estar em atividade
        Assert.assertFalse(jogador2.getAtividade());      //jogador 2 e 4 devem estar fora de atividade
        Assert.assertTrue(jogador3.getAtividade());
        Assert.assertFalse(jogador4.getAtividade());
    }

    @Test
    public void testeEscolherAdversario() { //Testa o metodo escolherAdversário. Nesse caso, deve retornar o unico jogador disponivel: jogador4 (user4)
        JogoOnline.login("user1", "111");
        JogoOnline.login("user2", "222");
        JogoOnline.login("user3", "333");
        JogoOnline.login("user4", "444");
        JogoOnline.iniciarPartida(jogador2, jogador3);   //jogador 2 e 3 em partida, logo, ao chamar o metodo como jogador 1, o jogador 4 é a única opção de oponente
        assertEquals("", jogador4, JogoOnline.escolherAdversario(jogador1));
        JogoOnline.Cadastrar("joaozinho", "acucar");
        JogoOnline.Cadastrar("fernandinho", "canela");
        JogoOnline.Cadastrar("joaninha", "tempero");
        JogoOnline.login("joaozinho", "acucar");
        JogoOnline.login("fernandinho", "canela");
        JogoOnline.login("joaninha", "tempero");       //Caso mais de um usuario esteja disponivel, o escolhido é aleatorio
        assertTrue(JogoOnline.escolherAdversario(jogador1).getStatus());      //Está online
        assertFalse(JogoOnline.escolherAdversario(jogador1).getAtividade());  //Não está em partida
    }

    @Test
    public void testeEncerrarPartida() { //Testa o metodo encerrarPartida
        JogoOnline.login("user1", "111");
        JogoOnline.login("user2", "222");
        JogoOnline.login("user3", "333");
        JogoOnline.login("user4", "444");
        JogoOnline.encerrarPartida(JogoOnline.iniciarPartida(jogador1, jogador2));//Vamos encerrar uma partida entre user1 e user4
        assertFalse(jogador1.getAtividade());       //Ambos devem estar fora de partida
        assertFalse(jogador2.getAtividade());
        assertEquals(jogador1.getHistórico(), jogador2.getHistórico());            //Verifica se a mesma partida está no histórico de ambos
        switch (jogador1.histórico.get(0).resultado) {                   //Já que o resultado é aleatório no meu código, precisamos testar a pontuação dos jogadores em cada possível resultado
            case 0:
                assertEquals(1000, jogador1.getPontuação());         //Acessamos o resultado através do atributo "resultado" no objeto Partida criado ao encerrar a partida
                assertEquals(1000, jogador1.getPontuação());
                break;
            case 1:
                assertEquals(1001, jogador1.getPontuação());
                assertEquals(999, jogador2.getPontuação());
                break;
            case 2:
                assertEquals(999, jogador1.getPontuação());
                assertEquals(1001, jogador2.getPontuação());
                break;
        }


    }
}