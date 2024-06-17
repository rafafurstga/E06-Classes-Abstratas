public class Conta {

    protected int numero;

    protected Cliente dono;

    protected double saldo;

    protected double limite;

    protected Operacao[] operacoes;

    protected int proximaOperacao;

    protected static int totalContas = 0;


    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new Operacao[10];
        this.proximaOperacao = 0;
        Conta.totalContas++;
    }

    Operacao[] fazOperacaoS(double valor){
        if(this.operacoes.length<=proximaOperacao){
            Operacao[] copy = this.operacoes;
            this.operacoes = new Operacao[proximaOperacao*2];
            for(int i = 0; i < proximaOperacao; i++){
                this.operacoes[i] = copy[i];
            }
        }
        this.operacoes[proximaOperacao] = new OperacaoSaque(valor);
        this.proximaOperacao++;

        return this.operacoes;
    }
    Operacao[] fazOperacaoD(double valor){
        if(this.operacoes.length<=proximaOperacao){
            Operacao[] copy = this.operacoes;
            this.operacoes = new Operacao[proximaOperacao*2];
            for(int i = 0; i < proximaOperacao; i++){
                this.operacoes[i] = copy[i];
            }
        }
        this.operacoes[proximaOperacao] = new OperacaoDeposito(valor);
        this.proximaOperacao++;

        return this.operacoes;
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;
            fazOperacaoS(valor);
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        fazOperacaoD(valor);
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    public String toString() {
        String string = "===== Conta " + this.numero + " =====\n" + "Dono: " + this.dono.getNome() + "\n" + "Saldo: " + this.saldo + "\n" + "Limite: " + this.limite + "\n====================";
        return string;
    }

    public void imprimirExtrato() {
        System.out.println("======= Extrato Conta " + this.numero + "======");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.printf(atual.toString());
            }
        }
        System.out.println("====================");
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setLimite(double limite) {
        if (limite < 0)
            limite = 0;

        this.limite = limite;
    }

    public boolean equals(Conta conta){
        if(this.numero == conta.numero){
            return true;
        }else{
            return false;
        }
    }
}
