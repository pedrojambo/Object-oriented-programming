import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class JogoOnline {

    public static ArrayList<Jogador> jogadores = new ArrayList<>();


    public static void Cadastrar(String user, String senha){
        for (int i=0;i<jogadores.size();i++) {
            Jogador jogador = JogoOnline.jogadores.get(i);
            if (jogador.getUsername() == user) {
                System.out.println("Esse usuário já existe.");
                return;
            }
        }
        if(user != null && senha != null) {
            Jogador jogador = new Jogador(user, senha);
            JogoOnline.jogadores.add(jogador);
            System.out.println("Parabéns " +user+ ", sua conta foi criada com sucesso!");
            return;
        }
        System.out.println("Por favor, insira um usuário e/ou senha válido(s)");
    }

    public static void login(String user, String senha){
        for (int i=0;i<jogadores.size();i++){
            Jogador jogador = JogoOnline.jogadores.get(i);
            if(jogador.getSenha() == senha && jogador.getUsername() == user){
                if(jogador.getStatus() == true){
                    System.out.println("Usuário já está logado!");
                    return;
                }
                jogador.setStatus(true);
                System.out.println("Login efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Senha e/ou usuário incorreto(s).");
    }

    public static void logout(String user){
        for (int i=0;i<jogadores.size();i++){
            Jogador jogador = JogoOnline.jogadores.get(i);
            if(jogador.getUsername() == user){
                if(jogador.getStatus() == false){
                    System.out.println("O usuário já está offline!");
                    return;
                }
                jogador.setStatus(false);
                System.out.println("Logout efetuado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: usuário não encontrado.");
    }

    public static Jogador escolherAdversario(Jogador solicitante){
        int contador = 0;
        ArrayList<Jogador> jogadoresDisponiveis = new ArrayList<>();
        for (int i=0;i<jogadores.size();i++){
            Jogador jogador = JogoOnline.jogadores.get(i);
            if(jogador.getStatus() == true && jogador.getAtividade() == false && jogador != solicitante){
                jogadoresDisponiveis.add(jogador);
                contador++;
            }
        }
        if(contador != 0) {
            Random rand = new Random();
            int i = rand.nextInt(jogadoresDisponiveis.size());
            return jogadoresDisponiveis.get(i);
        }
        System.out.println("Nenhum jogador disponivel");
        return null;
    }

    public static Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {
        if (jogador1.getStatus() == false) {
            System.out.println("Erro: Um ou mais jogadores offline.");
            return null;
        }
        if (jogador2.getStatus() == false) {
            System.out.println("Erro: Um ou mais jogadores offline.");
            return null;
        }
        if (jogador1.getAtividade() == true) {
            System.out.println("Erro: Um ou mais jogadores em partida.");
            return null;
        }
        if (jogador2.getAtividade() == true) {
            System.out.println("Erro: Um ou mais jogadores em partida.");
            return null;
        }
        Partida partida = new Partida(jogador1, jogador2);
        jogador1.setAtividade(true);
        jogador2.setAtividade(true);
        return partida;
    }

    public static void encerrarPartida(Partida partida){
        int resultado = partida.getResultado();
        partida.setResultado(resultado);
        partida.getPlayer1().addHistórico(partida);
        partida.getPlayer2().addHistórico(partida);
        partida.getPlayer1().setAtividade(false);
        partida.getPlayer2().setAtividade(false);
        partida.setResultado(resultado);
        if(resultado == 0){
            return;
        }
        if(resultado == 1){
            partida.getPlayer1().setPontuação("win");
            partida.getPlayer2().setPontuação("lose");
            return;
        }
        if(resultado == 2){
            partida.getPlayer2().setPontuação("win");
            partida.getPlayer1().setPontuação("lose");
        }
    }

}