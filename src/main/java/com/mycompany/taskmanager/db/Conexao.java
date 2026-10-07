/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author julio
 */
public class Conexao {
    
    private static final String URL = "jdbc:sqlite:taskmanager.db";
    
    private Conexao(){};
    
    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL);
    }
}
