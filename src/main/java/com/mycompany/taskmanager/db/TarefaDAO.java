package com.mycompany.taskmanager.db;

import com.mycompany.taskmanager.model.Tarefa;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    
    private Connection conexaoInjetada = null;

    public TarefaDAO() {
    }
    
    public TarefaDAO(Connection conexao) {
        this.conexaoInjetada = conexao;
    }
        
    private Connection obterConexao() throws SQLException {
        if(conexaoInjetada != null && !conexaoInjetada.isClosed()){
            return conexaoInjetada;
        }
        return Conexao.conectar();
    }
    
    private void fecharConexaoSenaoForDeTeste(Connection conn) throws SQLException{
        if(conexaoInjetada == null && conn != null && !conn.isClosed()){
            conn.close();
        }
    }
    
    public void inserir(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefas(titulo, descricao, concluida) VALUES (?, ?, ?)";
        
        Connection conn = obterConexao(); 
        try {
            conn.setAutoCommit(false);  
            
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, tarefa.getTitulo());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setBoolean(3, tarefa.isConcluida()); 
            
                stmt.executeUpdate();
                    
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        tarefa.setId(rs.getInt(1));
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } finally {
            fecharConexaoSenaoForDeTeste(conn);
        }
    }
    
    public List<Tarefa> listarTodos() throws SQLException { 
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, concluida FROM tarefas";
        
        // AQUI ESTAVA O ERRO! Ajustado para não usar try-with-resources na conexão
        Connection conn = obterConexao();
        try {
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                    
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String descricao = rs.getString("descricao");
                    boolean concluida = rs.getBoolean("concluida"); 
                    
                    Tarefa tarefa = new Tarefa(id, titulo, descricao, concluida);
                    tarefas.add(tarefa);
                }
            }
        } finally {
            fecharConexaoSenaoForDeTeste(conn);
        }
        return tarefas;
    }
    
    public void atualizar(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, concluida = ? WHERE id = ?";
        
        // AQUI TAMBÉM!
        Connection conn = obterConexao();
        try { 
            conn.setAutoCommit(false);
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, tarefa.getTitulo());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setBoolean(3, tarefa.isConcluida());
                stmt.setInt(4, tarefa.getId());
                
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) { 
                conn.rollback(); 
                throw e;
            }
        } finally {
            fecharConexaoSenaoForDeTeste(conn);
        }
    }
    
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        
        // AQUI TAMBÉM!
        Connection conn = obterConexao();
        try {
            conn.setAutoCommit(false);
            
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e){
                conn.rollback();
                throw e;
            }
        } finally {
            fecharConexaoSenaoForDeTeste(conn);
        }
    }
}