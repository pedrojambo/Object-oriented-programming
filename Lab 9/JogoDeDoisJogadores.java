import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {

    String nomeJogo;
    String nomeJogador1;
    String nomeJogador2;
    int numeroDeRodadas;
    ArrayList<Integer> historicoResultados = new ArrayList<>();

    public JogoDeDoisJogadores(String nome, String p1, String p2, int rodadas){
        this.nomeJogo = nome;
        this.nomeJogador1 = p1;
        this.nomeJogador2 = p2;
        this.numeroDeRodadas = rodadas;
    }

    public String getNomeJogo(){
        return this.nomeJogo;
    }

    public String getNomeJogador1(){
        return this.nomeJogador1;
    }

    public String getNomeJogador2(){
        return this.nomeJogador2;
    }

    public int getNumeroDeRodadas(){
        return this.numeroDeRodadas;
    }

    public String jogar(){
        int p1 = 0;
        int p2 = 0;
        for(int i = 0; i < numeroDeRodadas; i++){
            int result = executarRodadaDoJogo();
            historicoResultados.add(result);
            if(result == 1){
                p1++;
            }
            if(result == 2){
                p2++;
            }
        }
        if(p1>p2){
            return "O jogador " + nomeJogador1 + " venceu o jogo por " + p1 + " a " + p2 + ".";
        }
        if(p2>p1){
            return "O jogador " + nomeJogador2 + " venceu o jogo por " + p2 + " a " + p1 + ".";
        }
        return "O jogo terminou em empate após " + numeroDeRodadas + "rodadas.";
    }

    protected abstract int executarRodadaDoJogo();


}
