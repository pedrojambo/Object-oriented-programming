public class DadosDeGamao implements Sorteador {

    public int sortear(){
        Dado Dado1 = new Dado();
        Dado Dado2 = new Dado();
        int result1 = Dado1.sortear();
        int result2 = Dado2.sortear();
        int result = result1 + result2;
        if(result1==result2) {
            result *= 2;
        }
        return result;
    }
}
