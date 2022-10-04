public class ContaInvestimento extends Conta{

    private String tipoInvestimento;

    private float taxaJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros) {
        super (numeroDaConta, correntista);
        this.tipoInvestimento = tipoInvestimento;
        this.taxaJuros = taxaJuros;
        for (Conta conta: Conta.Contas){
            if(conta.getCorrentista() == correntista && conta != this){
                return;
            }
        }
        throw new RuntimeException("Correntista sem conta corrente!");
    }

    public void aplicarJuros() {
        float valorAntigo = getSaldoEmReais();
        float valorNovo = valorAntigo * taxaJuros;
        this.receberDepositoEmDinheiro(valorNovo);
        this.sacar(valorAntigo);
    }

    public void resgatarTotal(Conta contaDoInvestidor) {
        float total = this.getSaldoEmReais();
        if (this.getCorrentista() == contaDoInvestidor.getCorrentista())
            this.efetuarTransferecia(contaDoInvestidor, total);
    }

}
