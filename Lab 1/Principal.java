import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(ArrayList<Integer> lista1, ArrayList<Integer> lista2, int tamanho) {
        ArrayList<Integer> intersecao = new ArrayList<>(); 
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){   
                if (lista1.get(i) == lista2.get(j)){
                    intersecao.add(lista1.get(i));
                }
            }
        }
        int size = intersecao.size();
        System.out.println(intersecao);
        System.out.println("Tamanho da intersecao: " + size);
        return 0;
    }

    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior - menor + 1) + menor;
    }

    
    public static void main(String[] args) {
        System.out.printf("Digite o tamanho das listas: ");
        Scanner novo_scanner = new Scanner(System.in);
        int tamanho = novo_scanner.nextInt();
        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        for (int i = 0; i < tamanho; i++){
            lista1.add(sortearInt(1, 10*tamanho));
        }
        for (int i = 0; i < tamanho; i++){
            lista2.add(sortearInt(1, 10*tamanho));
        }
        System.out.println("Lista 1: \n" + lista1);
        System.out.println("lista 2: \n" + lista2);
        System.out.println("Intersecao: ");
        obterTamanhoIntersecao(lista1, lista2, tamanho);
    }
}