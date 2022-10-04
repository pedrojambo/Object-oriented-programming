public class ImpressoraJatoDeTinta extends Impressora {

    public int NivelTintaPreta;
    public int NivelTintaAmarela;
    public int NivelTintaMagenta;
    public int NivelTintaCiano;

    @Override
    public void executarRotinaLimpeza() {
        System.out.println("Limpando bicos de impressão e verificando tintas...");
    }

    @Override
    public void executarImpressaoPagina(String pagina) {
        System.out.println("Imprimindo utilizando jatos de tinta...");
    }

    public int getNivelTintaPreta() {
        return NivelTintaPreta;
    }

    public int getNivelTintaAmarela() {
        return NivelTintaAmarela;
    }

    public int getNivelTintaMagenta() {
        return NivelTintaMagenta;
    }

    public int getNivelTintaCiano() {
        return NivelTintaCiano;
    }
}