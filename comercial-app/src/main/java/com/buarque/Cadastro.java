package com.buarque;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Cadastro {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Valor total: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        System.out.println("Data de pagamento: ");
        LocalDate dataPagamento = LocalDate.parse(scanner.nextLine(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String dml = """
                insert into venda(
                  id,
                  nome_cliente,
                  valor_total,
                  data_pagamento
                ) values (?, ?, ?, ?)
                """;

        try (Connection conexao = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/comercial", "user", "080163180896");
             PreparedStatement comando = conexao.prepareStatement(dml)) {

            comando.setInt(1, 4);
            comando.setString(2, nome);
            comando.setBigDecimal(3, valor);
            comando.setDate(4, Date.valueOf(dataPagamento));
            comando.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro cadastrando venda");
            e.printStackTrace();
        }

    }

}
