import java.util.ArrayList;
import java.util.HashSet;

public class SomaDoPar {
    public static Integer encontrarParComSomaDada(ArrayList<Integer> numeros, int somaDesejada) {
        HashSet<Integer> elementos = new HashSet<Integer>();
        for(int i : numeros){
            elementos.add(i);
        }
        for(int j : elementos){
            int x = somaDesejada - j;
            if(elementos.contains(x)){
                return Math.min(j, x);
            }
        }
        return null;
    }
}