public class Correntista{

    private final long cpf;
    private String nome;
    public Correntista(String NomeCt, long CpfCt){
        this.nome = NomeCt;
        this.cpf  = CpfCt;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public long getCpf(){
        return this.cpf;
    }
}
