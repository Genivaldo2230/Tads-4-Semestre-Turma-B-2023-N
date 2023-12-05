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
            // Consulta SQL para recuperar os filmes
            String query = "SELECT id, titulo, genero, anoLancamento FROM series";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Obter a referência da tabela HTML
                StringBuilder htmlTable = new StringBuilder("<table id=\"/listarSeries\">");
                htmlTable.append("<tr><th>Título</th><th>Gênero</th><th>anoLancamento</th></tr>");

                // Iterar sobre os resultados e adicionar à tabela HTML
                while (resultSet.next()) {
                    String titulo = resultSet.getString("titulo");
                    String genero = resultSet.getString("genero");
                    int ano = resultSet.getInt("ano");

                    htmlTable.append("<tr>");
                    htmlTable.append("<td>").append(titulo).append("</td>");
                    htmlTable.append("<td>").append(genero).append("</td>");
                    htmlTable.append("<td>").append(ano).append("</td>");
                    htmlTable.append("</tr>");
                }

                htmlTable.append("</table>");

                // Exibir a tabela HTML
                System.out.println(htmlTable.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
