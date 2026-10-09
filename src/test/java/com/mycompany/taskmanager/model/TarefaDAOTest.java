package com.mycompany.taskmanager.model;

import com.mycompany.taskmanager.db.TarefaDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TarefaDAOTest {
    
    private TarefaDAO dao;
    private Connection conexaoTeste;
    
    @BeforeEach
    public void setUp() throws SQLException{
        conexaoTeste = DriverManager.getConnection("jdbc:sqlite::memory:");
        
        try (Statement stmt = conexaoTeste.createStatement()){
            String sql = """
                         CREATE TABLE IF NOT EXISTS tarefas (
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             titulo TEXT NOT NULL,
                             descricao TEXT,
                             concluida INTEGER DEFAULT 0
                         );
                         """;
            stmt.execute(sql);
        }
        dao = new TarefaDAO(conexaoTeste);
    }
    
    @AfterEach
    public void tearDown() throws SQLException { // Adicionado throws SQLException
        if(conexaoTeste != null && !conexaoTeste.isClosed()){
            conexaoTeste.close(); // Corrigido
        }
    }
    
    @Test
    public void testInserirElistaTarefa() throws SQLException {
        Tarefa novaTarefa = new Tarefa("Estudar JUnit", "Fazer os testes");
        
        dao.inserir(novaTarefa);
        
        List<Tarefa> tarefasSalvas = dao.listarTodos();
        
        assertEquals(1, tarefasSalvas.size(), "Deve haver 1 tarefa no banco");
        
        assertEquals("Estudar JUnit", tarefasSalvas.get(0).getTitulo());
        
        assertTrue(tarefasSalvas.get(0).getId() > 0, "O ID da tarefa deve ter sido gerado pelo banco");
    }
}