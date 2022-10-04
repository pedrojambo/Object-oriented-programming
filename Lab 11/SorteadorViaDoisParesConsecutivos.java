public class SorteadorViaDoisParesConsecutivos implements Sorteador<Integer>{
    Sorteador Dado;

    public SorteadorViaDoisParesConsecutivos(Sorteador dado){
        this.Dado = dado;
    }

    public Integer sortear(){
        for(int i = 0; i < 2; i++) {
            int X = (int) Dado.sortear();
            int Y = (int) Dado.sortear();
            if (X != Y) {
                return 0;
            }
        }
        return 1;
    }
}
