import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class TesteDePerformance {

    public static char CaracterMaisFrequenteQuadratico(String texto) {
        char maisFrequente = texto.charAt(0);
        int ocorrenciasDoMaisFrequente = 1;
            for (int i = 0; i < texto.length(); i++) {
                char caracterDaVez = texto.charAt(i);
                int contOcorrencias = 1;
                for (int j = i + 1; j < texto.length(); j++) {
                    if (texto.charAt(j) == caracterDaVez) {
                    contOcorrencias++;
                    }
                }
                if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                    maisFrequente = caracterDaVez;
                    ocorrenciasDoMaisFrequente = contOcorrencias;
                }
        }
        return maisFrequente;
    }

    public static Integer ParComSomaDadaQuadratico(ArrayList<Integer> numeros, int somaDesejada) {
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x,y);
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> lista;

    private ArrayList<Character> lista2 = new ArrayList<>();

    String termo;

    @Before
    public void setup(){
        Random rand = new Random();
        lista = new ArrayList<>();
        for(int i=0;i<30000;i++){
            lista.add(rand.nextInt());
        }
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        for(int j=0;j<30000;j++){
            lista2.add(alfabeto.charAt(rand.nextInt(25)));
        }
        StringBuilder result = new StringBuilder(lista2.size());
        for (Character c : lista2) {
            result.append(c);
        }
        termo = result.toString();
    }

    @Test
    public void CompararEfetividadeDaSomaDoPar(){
        long inicioSomaQuadratico = System.currentTimeMillis();
        ParComSomaDadaQuadratico(lista, 53);
        long duracaoSomaQuadratico = System.currentTimeMillis() - inicioSomaQuadratico;
        System.out.println("Duração do algoritmo quadratico para encontrar a soma do par: " + duracaoSomaQuadratico);
        long inicioSoma = System.currentTimeMillis();
        SomaDoPar.encontrarParComSomaDada(lista, 53);
        long duracaoSoma = System.currentTimeMillis() - inicioSoma;
        System.out.println("Duração do algoritmo eficiente para encontrar a soma do par: " + duracaoSoma);
        assertTrue(duracaoSomaQuadratico > duracaoSoma);
    }

    @Test
    public void CompararEfetividadeCaracterMaisFrequente(){
        long inicioCaracterQuadratico = System.currentTimeMillis();
        CaracterMaisFrequenteQuadratico(termo);
        long duracaoCaracterQuadratico = System.currentTimeMillis() - inicioCaracterQuadratico;
        System.out.println("Duração do algoritmo quadratico para encontrar o caracter mais frequente: " + duracaoCaracterQuadratico);
        long inicioCaracterFrequente = System.currentTimeMillis();
        CaracterMaisFrequente.encontrarCaracterMaisFrequente(termo);
        long duracaoCaracterFrequente = System.currentTimeMillis() - inicioCaracterFrequente;
        System.out.println("Duração do algoritmo eficiente para encontrar o caracter mais frequente: " + duracaoCaracterFrequente);
        assertTrue(duracaoCaracterQuadratico > duracaoCaracterFrequente);
    }

}
