import java.util.Map;
import java.util.Random;

public class DadoGenerico<T> implements Sorteador<Integer> {

    Map<T, Integer> frequenciaByResultado;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) {
        this.frequenciaByResultado = frequenciaByResultado;
    }

    @Override
    public Integer sortear() {
        int frequenciaAcumulada = 0;
        int frequenciaMaxima = 0;
        for (int i = 1; i < frequenciaByResultado.size()+1; i++){
            frequenciaMaxima += frequenciaByResultado.get(i);
        }
        Random rand = new Random();
        int x = rand.nextInt(frequenciaMaxima+1);
        for(int i = 1; i < frequenciaByResultado.size()+1; i++){
            frequenciaAcumulada += frequenciaByResultado.get(i);
            if(x <= frequenciaAcumulada){
                return i;
            }
        }
        return null;
    }
}