import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private ContaCorrente contaDoJoao;
    private Correntista joao;
    private float saldoInicial;
    private Correntista maria;
    private ContaCorrente contaDaMaria;

    @Before
    public void setUp() {
        joao = new Correntista("joao", 123123123);
        contaDoJoao = new ContaCorrente(1, joao);
        saldoInicial = contaDoJoao.getSaldoEmReais();
        maria = new Correntista("maria", 234234234);
        contaDaMaria = new ContaCorrente(2, maria);
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0,
                saldoInicial,
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);

    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 2, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicial, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferencia() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("transferencia deve ser no máximo" +
                        "igual ao saldo disponivel", saldoInicial + 3, contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        assertEquals("valor transferido deve" +
                "ser descontado da conta que " +
                "efetuou a transferência", saldoInicial - 3, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Tentou transferir um valor maior " +
                "que o saldo", saldoInicial, contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        assertEquals("Transferencias negativas " +
                "devem ser ignoradas", saldoInicial, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("verifica se o cpf do contista é " +
                "devidamente anexado",123123123, joao.getCpf(), FLOAT_DELTA);
    }

    @Test
    public void getQuantidadeDeTransacoesDeTodasAsContas(){

        contaDaMaria.receberDepositoEmDinheiro(100);
        contaDaMaria.receberDepositoEmDinheiro(1000);
        contaDaMaria.sacar(100);
        contaDoJoao.receberDepositoEmDinheiro(2000);
        contaDoJoao.receberDepositoEmDinheiro(1000);
        contaDoJoao.sacar(2000);
        contaDaMaria.efetuarTransferecia(contaDoJoao, 1000000);
        assertEquals("verifica se a quantidade de transações é devidamente apontada" +
                "pelo teste",6,ContaCorrente.getQuantidadeDeTransacoesDeTodasAsContas(),FLOAT_DELTA);

    }

}