import java.util.Scanner;

public class MenuTerminal {
    private Scanner scanner;
    private String[] opcoes;

    public MenuTerminal(String[] opcoes) {
        this.scanner = new Scanner(System.in);
        this.opcoes = opcoes;
    }

    public void exibirMenu() {
        System.out.println("Selecione uma opção:");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + ". " + opcoes[i]);
        }
    }

    public int escolherOpcao() {
        int escolha = 0;
        do {
            try {
                System.out.print("Escolha: ");
                escolha = Integer.parseInt(scanner.nextLine());
                if (escolha < 1 || escolha > opcoes.length) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número.");
            }
        } while (escolha < 1 || escolha > opcoes.length);

        return escolha;
    }
}