/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author julio
 */
public class Conexao {
    
    private static String url = "jdbc:sqlite:taskmanager.db";
    
    private Conexao(){};
    
    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(url);
    }
    
    public static void setUrlParaTestes(){
        url = "jdbc:sqlite::memory:";
    }
    
    public static void restaurarUrlPadrao(){
        url = "jdbc:sqlite:taskmanager.db";
    }
    
    
    public static void criarTabelas(){
        String SQL = """
                     CREATE TABLE IF NOT EXISTS tarefas (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo TEXT NOT NULL,
                        descricao TEXT,
                        concluida INTEGER DEFAULT 0
                     );
                     """;
        
        try (Connection conn = conectar();
        Statement stmt = conn.createStatement()){
            
            stmt.execute(SQL);
        } catch (SQLException e){
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
