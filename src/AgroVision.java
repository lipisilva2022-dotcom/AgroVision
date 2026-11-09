import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.util.Scanner;

public class AgroVision {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao;

        try {

            // Estabelece a conexão com o banco de dados
            DatabaseManager db = new DatabaseManager(Conexao.conectar());

            // Loop que mantém o menu funcionando até o usuário sair
            do {

                System.out.println("\n===== AGROVISION =====");
                System.out.println("1 - Cadastrar novo relatório");
                System.out.println("2 - Consultar relatório");
                System.out.println("3 - Listar relatórios");
                System.out.println("4 - Excluir relatório");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                // Lê a opção digitada pelo usuário
                opcao = sc.nextInt();

                // Consome a quebra de linha deixada pelo nextInt()
                sc.nextLine();

                // Verifica qual opção foi escolhida
                switch (opcao) {

                    case 1:

                        // Chama o método de cadastro
                        cadastrarRelatorio(sc, db);

                        break;

                    case 2:

                        System.out.println("CONSULTAR RELATÓRIO");

                        break;

                    case 3:

                        System.out.println("LISTAR RELATÓRIOS");

                        break;

                    case 4:

                        System.out.println("EXCLUIR RELATÓRIO");

                        break;

                    case 0:

                        System.out.println("Encerrando AgroVision...");

                        break;

                    default:

                        System.out.println("Opção inválida!");

                }

            } while (opcao != 0);

            // Encerra a conexão com o banco
            db.fecharConexao();

        } catch (SQLException e) {

            // Trata possíveis erros de conexão ou operações SQL
            System.out.println("Erro no banco de dados: " + e.getMessage());

        } finally {

            // Fecha o Scanner ao finalizar o programa
            sc.close();

        }

    }


    // ==========================================
    // CADASTRAR RELATÓRIO
    // ==========================================

    public static void cadastrarRelatorio(Scanner sc, DatabaseManager db) {

        try {

            System.out.println("\n===== NOVO RELATÓRIO =====");

            // Recebe a data e converte para LocalDate
            System.out.print("Data de referência (dd/MM/yyyy): ");

            String dataTexto = sc.nextLine();

            LocalDate data = LocalDate.parse(
                    dataTexto,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );

            // Recebe o faturamento
            System.out.print("Faturamento: R$ ");

            double faturamento = Double.parseDouble(
                    sc.nextLine().replace(",", ".")
            );

            // Recebe a comissão
            System.out.print("Comissão: R$ ");

            double comissao = Double.parseDouble(
                    sc.nextLine().replace(",", ".")
            );

            // Recebe a margem mensal
            System.out.print("Margem mensal (%): ");

            double margem = Double.parseDouble(
                    sc.nextLine().replace(",", ".")
            );

            // Recebe a retirada mensal
            System.out.print("Retirada mensal: R$ ");

            double retirada = Double.parseDouble(
                    sc.nextLine().replace(",", ".")
            );

            // Recebe o saldo
            System.out.print("Saldo: R$ ");

            double saldo = Double.parseDouble(
                    sc.nextLine().replace(",", ".")
            );

            // Cria um objeto RelatorioMensal com os dados recebidos
            RelatorioMensal relatorio = new RelatorioMensal(
                    0,
                    data,
                    faturamento,
                    comissao,
                    margem,
                    retirada,
                    saldo
            );

            // Envia o objeto para o DatabaseManager cadastrar no MySQL
            if (db.cadastrarRelatorio(relatorio)) {

                System.out.println("\nRelatório cadastrado com sucesso!");

            } else {

                System.out.println("\nNão foi possível cadastrar o relatório.");

            }

        } catch (Exception e) {

            // Trata erros de preenchimento e cadastro
            System.out.println("Erro ao cadastrar relatório: " + e.getMessage());

        }

    }

}