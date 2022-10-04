public class Principal {

    public static void main(String[] args) {

        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 65; numeroDeRodadas *= 2) {
            JogoMalucoComSorteadores<DadosDeGamao, DadosTriplos> jogoMaluco = new JogoMalucoComSorteadores<>(
                    "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                    numeroDeRodadas, dadosDeGamao, dadosTriplos);
            for (int i = 1; i <= 1000; i++){

                jogoMaluco.jogar();

            }
            System.out.println("Número de rodadas: "+numeroDeRodadas);
            System.out.println(String.format("Vitórias do jogador 1: %.1f",jogoMaluco.getPercentualVitoriasJogador1())+"%");
            System.out.println(String.format("Vitórias do jogador 2: %.1f",jogoMaluco.getPercentualVitoriasJogador2())+"%");
            System.out.println(String.format("Empates: %.1f",jogoMaluco.getPercentualEmpates())+"%"+"\n");
        }
    }
}
