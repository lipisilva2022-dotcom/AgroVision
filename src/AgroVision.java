import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

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
                System.out.println("4 - Atualizar relatório");
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

                        buscarRelatorioPorMes(sc, db);

                        break;

                    case 3:

                        listarRelatorios(db);

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

    // ==========================================
    // CONSULTA DE RELATÓRIO
    // ==========================================
    private static void buscarRelatorioPorMes(Scanner sc, DatabaseManager db) {

        System.out.println("===== BUSCAR RELATÓRIO =====\n");
        System.out.println("");

        System.out.print("Informe o ano desejado: ");
        int ano = sc.nextInt();

        System.out.print("Informe o més desejado: ");
        int mes = sc.nextInt();


        //VALIDAÇÃO DE ENTRADA
        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido! Digite um mês entre 1 e 12.");
            return;
        }

        // Consulta o relatório no banco de dados
        RelatorioMensal relatorio = null;

        try {
            relatorio = db.buscarRelatorioPorMes(mes, ano);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar relatório: " + e.getMessage());
            return;
        }

        if (relatorio == null) {
            System.out.println("Nenhum relatório encontrado para o período informado.");
            return;
        }

        // Apresenta os dados do relatório
        System.out.println("\n===== RELATÓRIO ENCONTRADO =====");

        System.out.println("Data de referência: " + relatorio.getDataReferencia());
        System.out.println("Faturamento: R$ " + relatorio.getFaturamento());
        System.out.println("Comissão: R$ " + relatorio.getComissao());
        System.out.println("Margem mensal: " + relatorio.getMargemMensal() + "%");
        System.out.println("Retirada mensal: R$ " + relatorio.getRetiradaMensal());
        System.out.println("Saldo: R$ " + relatorio.getSaldo());
    }

    // ==========================================
    // LISTAR TODOS OS RELATÓRIOS
    // ==========================================
    private static void listarRelatorios(DatabaseManager db) {

        try {

            // Busca todos os relatórios cadastrados no banco
            List<RelatorioMensal> relatorios = db.listarRelatorios();

            // Verifica se existem relatórios cadastrados
            if (relatorios.isEmpty()) {
                System.out.println("Nenhum relatório cadastrado.");
                return;
            }

            // Cabeçalho da listagem
            System.out.println("\n========== RELATÓRIOS CADASTRADOS ==========\n");

            System.out.printf("%-5s | %-12s | %-16s | %-14s%n",
                    "ID", "DATA", "FATURAMENTO", "COMISSÃO");

            System.out.println("-------------------------------------------------------------");

            // Percorre a lista e apresenta cada relatório
            for (RelatorioMensal relatorio : relatorios) {

                System.out.printf("%-5d | %-12s | R$ %-13.2f | R$ %-11.2f%n",
                        relatorio.getId(),
                        relatorio.getDataReferencia().format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        relatorio.getFaturamento(),
                        relatorio.getComissao());
            }

            System.out.println("=============================================================");

        } catch (SQLException e) {

            System.out.println("Erro ao listar relatórios: " + e.getMessage());

        }
    }
}