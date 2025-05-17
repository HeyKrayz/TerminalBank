class inforClient {
    String NomeDoUsuario;
    String Cpf;
    int NumeroDaConta;
    double Saldo = 0.00;

public inforClient (String nomeDoUsuario, String cpf, int numeroDaConta){
    this.NomeDoUsuario = nomeDoUsuario;
    this.Cpf = cpf;
    this.NumeroDaConta = numeroDaConta;
}

public String getnome() {
    return NomeDoUsuario;
}

public String getcpf() {
    return Cpf;
}

public int getnumconta() {
    return NumeroDaConta;
}

public String guardarInfor () {
    return NomeDoUsuario + ":" + Cpf + ":" + NumeroDaConta + ":";
}

public static inforClient fromString (String linha) {
    String[] partes = linha.split(":");
    if (partes.length != 4) return null; 
    inforClient cliente = new inforClient(partes[0], partes[1], Integer.parseInt(partes[2]));
    cliente.Saldo = Double.parseDouble(partes[3]);
    return cliente;
}
    }