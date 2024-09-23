package com.buarque;

import java.math.BigDecimal;
import java.sql.*;

public class Consulta {

    public static void main(String[] args) {
        try (Connection conexao = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/comercial", "user", "080163180896");
             Statement comando = conexao.createStatement();
             ResultSet resultado = comando.executeQuery("select *  from venda")){

            while (resultado.next()){

                Long id = resultado.getLong("id");
                String nome = resultado.getString("nome_cliente");
                BigDecimal valor = resultado.getBigDecimal("valor_total");
                String data = resultado.getString("data_pagamento");

                System.out.printf("%d - %s - R$ %.2f - %s%n",id, nome, valor, data);
            }

        } catch (SQLException e) {
            System.out.println("Erro de banco de dados");
            e.printStackTrace();
        }

    }

}
