public class Main {
    public static void main(String[] args) {
        DadosDeGamao sort1 = new DadosDeGamao();
        DadosTriplos sort2 = new DadosTriplos();
        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores("jogo1", "player1", sort1, "player2", sort2, 10000);
        System.out.println(jogo.jogar());
    }
}
