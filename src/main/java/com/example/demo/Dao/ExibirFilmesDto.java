package com.example.demo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExibirFilmesDto {

    public static void main(String[] args) {
        // Configurar a conexão com o banco de dados H2
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "sa";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Consulta SQL para recuperar os filmes sem duplicatas
            String query = "SELECT DISTINCT id, titulo, genero, anoLancamento FROM filmes";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Inicialização da tabela HTML
                StringBuilder htmlTable = new StringBuilder("<table id=\"/listarFilmes\">");
                htmlTable.append("<tr><th>ID</th><th>Título</th><th>Gênero</th><th>Ano de Lançamento</th></tr>");

                // Iterar sobre os resultados e adicionar à tabela HTML
                while (resultSet.next()) {
                    // Obter os valores das colunas
                    int id = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String genero = resultSet.getString("genero");
                    int ano = resultSet.getInt("anoLancamento");

                    // Adicionar uma nova linha à tabela HTML com os valores obtidos
                    htmlTable.append("<tr>");
                    htmlTable.append("<td>").append(id).append("</td>");
                    htmlTable.append("<td>").append(titulo).append("</td>");
                    htmlTable.append("<td>").append(genero).append("</td>");
                    htmlTable.append("<td>").append(ano).append("</td>");
                    htmlTable.append("</tr>");
                }

                // Finalização da tabela HTML
                htmlTable.append("</table>");

                // Exibir a tabela HTML
                System.out.println(htmlTable.toString());
            }
        } catch (SQLException e) {
            // Tratamento de exceção em caso de erro na execução do SQL
            e.printStackTrace();
        }
    }
}
