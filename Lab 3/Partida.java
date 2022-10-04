import java.util.Random;

public class Partida {

    public Jogador player1;
    public Jogador player2;

    public int resultado;

    public Partida(Jogador player1, Jogador player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public Jogador getPlayer1(){
        return this.player1;
    }
    public Jogador getPlayer2(){
        return this.player2;
    }
    public static int getResultado(){
        Random rand = new Random();
        return rand.nextInt(3);
    }
    public void setResultado(int resultado){
        this.resultado = resultado;
    }
    public int showResultado(){
        return this.resultado;
    }
}
