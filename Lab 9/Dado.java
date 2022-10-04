import java.util.Random;

public class Dado implements Sorteador {

    public int sortear() {
        Random rand = new Random();
        return rand.nextInt(7);
    }
}
