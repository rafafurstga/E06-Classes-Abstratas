public class Main {
    public static void main(String[] args) {
        PessoaFisica joao = PessoaFisica.criarPessoaFisica("João", "117.252.516-12", "Av. Antonio Carlos, 6627", 36, 'm');

        Conta conta1 = new Conta(1234, joao, 0, 1500);
        Conta conta2 = new Conta(12121, joao, 10000, 1500);

        conta1.depositar(3000);
        conta1.depositar(2000);
        conta1.sacar(500);

        conta2.transferir(conta1, 666);
        conta2.depositar(234);

        conta1.imprimirExtrato();
        conta2.imprimirExtrato();

        int media = Operacao.getTotalOperacoes() / Conta.getTotalContas();
        System.out.println("Média de operações por conta aberta: " + media);
    }
}