public class JogoMalucoComSorteadores extends JogoDeDoisJogadores{

    Sorteador sorteador1;
    Sorteador sorteador2;

    public JogoMalucoComSorteadores(String nome, String p1, Sorteador sorteador1, String p2, Sorteador sorteador2, int rodadas) {
        super(nome, p1, p2, rodadas);
        this.sorteador1=sorteador1;
        this.sorteador2=sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {
        int p1 = sorteador1.sortear();
        int p2 = sorteador2.sortear();
        if(p1 > p2){
            return 1;
        }
        if(p1 < p2){
            return 2;
        }
        return 0;
    }
}