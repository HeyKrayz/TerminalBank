import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Você deseja fazer login ou criar uma nova conta? (L/C)");
        String decisao = scanner.nextLine();
        acao(decisao, scanner, random);
        scanner.close();
    }

    private static void acao(String decisao, Scanner scanner, Random random) {
        if (decisao.equalsIgnoreCase("C")) {
            System.out.println("Criando conta aguarde...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("Digite seu nome completo: ");
            String nome = scanner.nextLine();

            System.out.print("Digite seu CPF (ex: 123.456.789-99): ");
            String cpf = scanner.nextLine();

            if (cpfExiste(cpf)) {
                System.out.println("CPF já cadastrado! Faça login ou use outro CPF.");
                return;
            }

            int numeroConta = random.nextInt(1000);
            System.out.println("Número da conta gerado: " + numeroConta);

            inforClient cliente = new inforClient(nome, cpf, numeroConta);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Dados.txt", true))) {
                writer.write(cliente.guardarInfor() + cliente.Saldo + "\n");
                System.out.println("Conta salva com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar a conta.");
                e.printStackTrace();
            }

        } else if (decisao.equalsIgnoreCase("L")) {
            System.out.println("essa funçao ainda não foi feita.");
        } else {
            System.out.println("L ou C");
        }
    }

    private static boolean cpfExiste(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Dados.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                inforClient cliente = inforClient.fromString(linha);
                if (cliente != null && cliente.getcpf().equals(cpf)) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}
