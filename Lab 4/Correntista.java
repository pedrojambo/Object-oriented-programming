import java.util.ArrayList;

public class Correntista extends PessoaFisica {

    private static final int LIMITE_DEFAULT = 100;

    private float limiteChequeEspecial;
    private Conta conta;
    private ArrayList<ContaInvestimento> investimentos;

    public Correntista(String nome, long cpf) {
        super(nome, cpf);
        this.conta = null;
        this.investimentos = new ArrayList<>();
        this.limiteChequeEspecial = LIMITE_DEFAULT;
    }

    public float getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public float getTotalInvestido() {
        return 0;  // ToDo IMPLEMENT ME!!
    }

    public Conta getConta() {
        return conta;
    }
}