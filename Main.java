
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] opcoes = { "Listar pessoas", "Adicionar pessoa", "Remover pessoa", "Atualizar pessoa", "Sair" };
        MenuTerminal menu = new MenuTerminal(opcoes);
        CRUDManager<Usuario> manager = new CRUDManager<>("myitems.txt");
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = 0;
            do {
                menu.exibirMenu();
                choice = menu.escolherOpcao();
                switch (choice) {
                    case 1:
                        System.out.println("Lista de pessoas:");
                        List<Usuario> allItems = manager.read();
                        System.out.println("Current items: " + allItems);
                        break;
                    case 2:
                        System.out.println("Adicionar pessoa:");
                        System.out.print("Nome: ");
                        String nome = scanner.next();
                        System.out.print("Email: ");
                        String email = scanner.next();
                        System.out.print("Senha: ");
                        String senha = scanner.next();
                        manager.create(new Usuario(nome, email, senha));
                        break;
                    case 3:
                        System.out.println("Remover pessoa:");
                        System.out.print("Pesquisar pessoa para remover:");
                        String nomeToRemove = scanner.next();
                        int resultado = manager
                                .buscarIndexPorCriterio(Usuario -> Usuario.getNome().equals(nomeToRemove));
                        if (resultado != -1) {
                            manager.delete(resultado);
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                        break;
                    case 4:
                        System.out.println("Atualizar pessoa:");
                        System.out.print("Pesquisar pessoa para atualizar:");
                        String nomeToUpdate = scanner.next();
                        int updateResult = manager
                                .buscarIndexPorCriterio(Usuario -> Usuario.getNome().equals(nomeToUpdate));
                        if (updateResult != -1) {
                            System.out.print("Novo nome: ");
                            String novoNome = scanner.next();
                            System.out.print("Novo email: ");
                            String novoEmail = scanner.next();
                            System.out.print("Nova senha: ");
                            String novaSenha = scanner.next();
                            manager.update(updateResult, new Usuario(novoNome, novoEmail, novaSenha));
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (choice != 5);
        }

    }
}