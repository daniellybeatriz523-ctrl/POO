package CrudNuvem;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Produto {

    public static void main(String[] args) {
    	String host = "kodama.proxy.rlwy.net";
    	String porta = "37282";
    	String database = "railway";
    	String usuariobanco = "root";
    	String bancosenha = "dFQdfBCDGnqTSbezkPjgonRycoUywLQq";

        String url = "jdbc:mysql://" + host + ":" + porta + "/" + database
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try (Scanner sc = new Scanner(System.in);
             Connection conexao = DriverManager.getConnection(url, usuariobanco, bancosenha)) {

            System.out.println("Conectado ao banco com sucesso!");

            String criarTabela = "CREATE TABLE IF NOT EXISTS PRODUTO ("
                    + "CODIGO INT PRIMARY KEY, "
                    + "NOME VARCHAR(100) NOT NULL, "
                    + "PRECO DECIMAL(10,2) NOT NULL)";

            try (Statement criar = conexao.createStatement()) {
                criar.executeUpdate(criarTabela);
            }

            int opcao = -1;

            while (opcao != 0) {
                System.out.println("\n===== MENU PRODUTOS =====");
                System.out.println("1 - Inserir");
                System.out.println("2 - Listar");
                System.out.println("3 - Atualizar");
                System.out.println("4 - Deletar");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opcao: ");

                try {
                    String linha = sc.nextLine();
                    if (linha == null || linha.trim().isEmpty()) {
                        System.out.println("Entrada vazia. Tente novamente.");
                        continue;
                    }
                    opcao = Integer.parseInt(linha.trim());

                    switch (opcao) {
                        case 1:
                            inserirProduto(conexao, sc);
                            break;
                        case 2:
                            listarProdutos(conexao);
                            break;
                        case 3:
                            atualizarProduto(conexao, sc);
                            break;
                        case 4:
                            deletarProduto(conexao, sc);
                            break;
                        case 0:
                            System.out.println("Saindo do sistema...");
                            break;
                        default:
                            System.out.println("Opcao invalida!");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Erro: digite apenas numeros inteiros para a opcao.");
                } catch (SQLException e) {
                    System.out.println("Erro no banco: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro de conexao: " + e.getMessage());
            System.out.println("Verifique URL, usuario, senha e se o banco esta acessivel.");
        }
    }

    // ======== METODOS AUXILIARES ========

    private static void inserirProduto(Connection conexao, Scanner sc) throws SQLException {
        System.out.print("Codigo do produto: ");
        int codigo = lerInteiro(sc);

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome nao pode ser vazio.");
            return;
        }

        System.out.print("Preco do produto: ");
        BigDecimal preco = lerBigDecimal(sc);

        String sql = "INSERT INTO PRODUTO (CODIGO, NOME, PRECO) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, nome);
            stmt.setBigDecimal(3, preco);
            stmt.executeUpdate();
            System.out.println("Inserido com sucesso!");
        }
    }

    private static void listarProdutos(Connection conexao) throws SQLException {
        String sql = "SELECT * FROM PRODUTO ORDER BY CODIGO";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== LISTA DE PRODUTOS =====");
            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                System.out.printf("Codigo: %d | Nome: %s | Preco: R$ %s%n",
                        rs.getInt("CODIGO"),
                        rs.getString("NOME"),
                        rs.getBigDecimal("PRECO"));
            }

            if (!encontrou) {
                System.out.println("Nenhum produto cadastrado.");
            }
        }
    }

    private static void atualizarProduto(Connection conexao, Scanner sc) throws SQLException {
        System.out.print("Codigo do produto que deseja atualizar: ");
        int codigo = lerInteiro(sc);

        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine().trim();
        if (novoNome.isEmpty()) {
            System.out.println("Nome nao pode ser vazio.");
            return;
        }

        System.out.print("Novo preco: ");
        BigDecimal novoPreco = lerBigDecimal(sc);

        String sql = "UPDATE PRODUTO SET NOME = ?, PRECO = ? WHERE CODIGO = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setBigDecimal(2, novoPreco);
            stmt.setInt(3, codigo);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto nao encontrado!");
            }
        }
    }

    private static void deletarProduto(Connection conexao, Scanner sc) throws SQLException {
        System.out.print("Codigo do produto que deseja deletar: ");
        int codigo = lerInteiro(sc);

        String sql = "DELETE FROM PRODUTO WHERE CODIGO = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Produto nao encontrado!");
            }
        }
    }

    // Helpers de leitura segura
    private static int lerInteiro(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero inteiro: ");
            }
        }
    }

    private static BigDecimal lerBigDecimal(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim().replace(",", ".");
                return new BigDecimal(s);
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um preco (ex: 19.90): ");
            }
        }
    }
}