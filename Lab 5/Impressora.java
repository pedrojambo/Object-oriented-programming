public abstract class Impressora {

    public int QntFolhas;
    public int QntDocumentosImpressos;

    public Impressora(){
        this.QntFolhas = 0;
        this.QntDocumentosImpressos = 0;
    }

    public boolean imprimirDocumento(Documento documento) {
        if(documento.getQuantPaginas()>this.QntFolhas){
            return false;
        }
        for (int i = 0; i < documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.QntFolhas--;
        }
        this.QntDocumentosImpressos++;
        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {
        this.QntFolhas += numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {
        return this.QntFolhas;
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.QntDocumentosImpressos;
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}