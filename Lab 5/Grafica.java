import java.util.ArrayList;

public class Grafica {

    public float PrecoSemCores;
    public float PrecoEmCores;
    public ArrayList<Impressora> Impressoras = new ArrayList<>();
    int i = 0;

    public void imprimirDocumento(Documento documento) {
        Impressoras.get(i).imprimirDocumento(documento);
        if (i == Impressoras.size() - 1){
            i = 0;
            return;
        }
        i++;
    }

    public float orcarImpressao(Documento documento) {
        if(documento.isEmCores()){
            return documento.getQuantPaginas() * this.PrecoEmCores;
        }
        return documento.getQuantPaginas() * this.PrecoSemCores;
    }

    public void adicionarImpressora(Impressora impressora) {
        Impressoras.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if(!emCores){
            this.PrecoSemCores = precoPorPagina;
        }
        this.PrecoEmCores = precoPorPagina;
    }
}