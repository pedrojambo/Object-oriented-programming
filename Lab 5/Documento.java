import java.util.ArrayList;

public class Documento {

    public ArrayList<String> Paginas;
    public boolean Cor;


    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.Cor = emCores;
        Paginas = paginas;
    }

    public boolean isEmCores() {
        return this.Cor;
    }

    public int getQuantPaginas() {
        return Paginas.size();
    }

    public String getPagina(int numeroDaPagina) {
        return Paginas.get(numeroDaPagina);
    }
}