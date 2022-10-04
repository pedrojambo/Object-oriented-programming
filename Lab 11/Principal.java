import java.util.HashMap;
import java.util.Map;


public class Principal {

    private static final int CONT_REPETICOES_POR_SIMULACAO = 1_000;

    private static DadoComum dadoHonesto = new DadoComum();
    private static DadoGenerico dadoViciado;
    private static SorteadorViaTrio trio = new SorteadorViaTrio(dadoHonesto);

    private static void percentuaisParesVSTrio(int numeroDeRodadasPorPartida) {

        SorteadorViaDoisParesConsecutivos paresConsecutivos = new SorteadorViaDoisParesConsecutivos(dadoHonesto);
        JogoMalucoComSorteadores<SorteadorViaDoisParesConsecutivos, SorteadorViaTrio> jogoHonesto;

        jogoHonesto = new JogoMalucoComSorteadores<>(
                "JogadorComParesConsecutivos", "JogadorComTrio",
                numeroDeRodadasPorPartida, paresConsecutivos, trio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++) {
            jogoHonesto.jogar();
        }

        System.out.println(String.format("\n" +
                        "      Para partidas com %d rodada(s):\n" +
                        "      Vitórias do Jogador 1: %f%%\n" +
                        "      Vitórias do Jogador 2: %f%%\n" +
                        "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogoHonesto.getPercentualVitoriasJogador1(),
                jogoHonesto.getPercentualVitoriasJogador2(),
                jogoHonesto.getPercentualEmpates()));
    }

    private static void percentuaisParesViciadosVSTrio(int numeroDeRodadasPorPartida) {

        Map<Integer, Integer> mapa = new HashMap<>();
        mapa.put(1, 1);
        mapa.put(2, 1);
        mapa.put(3, 1);
        mapa.put(4, 1);
        mapa.put(5, 1);
        mapa.put(6, 9);

        dadoViciado = new DadoGenerico(mapa);
        SorteadorViaDoisParesConsecutivos paresConsecutivosViciados = new SorteadorViaDoisParesConsecutivos(dadoViciado);

        JogoMalucoComSorteadores<SorteadorViaDoisParesConsecutivos, SorteadorViaTrio> jogoViciado;

        jogoViciado = new JogoMalucoComSorteadores<>(
                "JogadorComParesConsecutivosViciado", "JogadorComTrio",
                numeroDeRodadasPorPartida, paresConsecutivosViciados, trio);

        for (int i = 0; i < CONT_REPETICOES_POR_SIMULACAO; i++) {
            jogoViciado.jogar();
        }

        System.out.println(String.format("\n\n" +
                        "      Para partidas com %d rodada(s):\n" +
                        "      Vitórias do Jogador 1: %f%%\n" +
                        "      Vitórias do Jogador 2: %f%%\n" +
                        "      Empates: %f%%",
                numeroDeRodadasPorPartida,
                jogoViciado.getPercentualVitoriasJogador1(),
                jogoViciado.getPercentualVitoriasJogador2(),
                jogoViciado.getPercentualEmpates()));
    }

    public static void main(String[] args) {
        System.out.println("-------------------------Jogo Honesto-------------------------\n");
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 513; numeroDeRodadas*=2) {
            percentuaisParesVSTrio(numeroDeRodadas);
        }
        System.out.println("\n \n \n");
        System.out.println("-------------------------Jogo Viciado-------------------------\n");
        for (int numeroDeRodadas = 1; numeroDeRodadas <= 513; numeroDeRodadas*=2) {
            percentuaisParesViciadosVSTrio(numeroDeRodadas);
        }
    }
}
