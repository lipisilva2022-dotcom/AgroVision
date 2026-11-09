import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TesteConexao {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("           AGROVISION - JDBC");
        System.out.println("==========================================");

        String sql = """
                SELECT
                    data_referencia,
                    faturamento,
                    comissao,
                    saldo
                FROM relatorio_mensal
                WHERE faturamento IS NOT NULL
                ORDER BY data_referencia DESC
                LIMIT 4
                """;

        NumberFormat moeda = NumberFormat.getCurrencyInstance(
                Locale.forLanguageTag("pt-BR")
        );

        DateTimeFormatter formatoData =
                DateTimeFormatter.ofPattern("MM/yyyy");

        try (Connection conexao = Conexao.conectar()) {

            System.out.println("\n[CONEXAO]");
            System.out.println("Conexao estabelecida com sucesso!");
            System.out.println("Banco: AgroVision");
            System.out.println("Status: CONECTADO");

            System.out.println("\n==========================================");
            System.out.println("          ULTIMOS LANCAMENTOS");
            System.out.println("==========================================");

            System.out.printf(
                    "%-12s %-18s %-16s %-16s%n",
                    "DATA", "FATURAMENTO", "COMISSAO", "SALDO"
            );

            System.out.println(
                    "--------------------------------------------------------------"
            );

            try (PreparedStatement statement =
                         conexao.prepareStatement(sql);

                 ResultSet resultado = statement.executeQuery()) {

                int quantidade = 0;

                while (resultado.next()) {

                    String data = resultado.getDate("data_referencia")
                            .toLocalDate()
                            .format(formatoData);

                    String faturamento = moeda.format(
                            resultado.getBigDecimal("faturamento")
                    );

                    String comissao = moeda.format(
                            resultado.getBigDecimal("comissao")
                    );

                    String saldo = moeda.format(
                            resultado.getBigDecimal("saldo")
                    );

                    System.out.printf(
                            "%-12s %-18s %-16s %-16s%n",
                            data,
                            faturamento,
                            comissao,
                            saldo
                    );

                    quantidade++;
                }

                System.out.println(
                        "--------------------------------------------------------------"
                );

                System.out.println("Registros encontrados: " + quantidade);
            }

        } catch (SQLException e) {

            System.out.println("\n[ERRO]");
            System.out.println("Falha ao conectar ou consultar o banco.");
            System.out.println("Detalhes: " + e.getMessage());

        }

        System.out.println("\n==========================================");
        System.out.println("          FIM DO TESTE JDBC");
        System.out.println("==========================================");
    }
}
