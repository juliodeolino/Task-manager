package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.db.TarefaDAO;
import com.mycompany.taskmanager.model.Tarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Optional;

public class MainController {

    @FXML private TableView<Tarefa> tabelaTarefas;
    @FXML private TableColumn<Tarefa, Integer> colId;
    @FXML private TableColumn<Tarefa, String> colTitulo;
    @FXML private TableColumn<Tarefa, String> colDescricao;
    @FXML private TableColumn<Tarefa, Boolean> colStatus;

    @FXML private TextField txtTitulo;
    @FXML private TextArea txtDescricao;
    @FXML private CheckBox chkConcluida;

    private final TarefaDAO dao = new TarefaDAO();
    private final ObservableList<Tarefa> dadosTabela = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("concluida"));

        carregarTarefas();

        tabelaTarefas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                txtTitulo.setText(novo.getTitulo());
                txtDescricao.setText(novo.getDescricao());
                chkConcluida.setSelected(novo.isConcluida());
            }
        });
    }

    private void carregarTarefas() {
        try {
            dadosTabela.clear();
            dadosTabela.addAll(dao.listarTodos());
            tabelaTarefas.setItems(dadosTabela);
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro de Banco", "Não foi possível carregar as tarefas: " + e.getMessage());
        }
    }

    @FXML
    public void adicionarTarefa() {
        String titulo = txtTitulo.getText().trim();
        String descricao = txtDescricao.getText().trim();

        if (titulo.isEmpty()) {
            exibirAlerta(Alert.AlertType.WARNING, "Validação", "O título não pode estar vazio.");
            return;
        }

        try {
            Tarefa selecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                selecionada.setTitulo(titulo);
                selecionada.setDescricao(descricao);
                selecionada.setConcluida(chkConcluida.isSelected());
                dao.atualizar(selecionada);
            } else {
                Tarefa nova = new Tarefa(titulo, descricao);
                nova.setConcluida(chkConcluida.isSelected());
                dao.inserir(nova);
            }

            limparCampos();
            carregarTarefas();
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Banco", "Falha ao salvar a tarefa: " + e.getMessage());
        }
    }

    @FXML
    public void marcarConcluida() {
        Tarefa selecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            exibirAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione uma tarefa na tabela.");
            return;
        }

        try {
            selecionada.setConcluida(true);
            dao.atualizar(selecionada);
            carregarTarefas();
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Banco", "Falha ao atualizar status: " + e.getMessage());
        }
    }

    @FXML
    public void removerTarefa() {
        Tarefa selecionada = tabelaTarefas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            exibirAlerta(Alert.AlertType.WARNING, "Atenção", "Selecione uma tarefa para remover.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação de Exclusão");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja realmente excluir a tarefa: \"" + selecionada.getTitulo() + "\"?");

        Optional<ButtonType> resposta = confirmacao.showAndWait();
        if (resposta.isPresent() && resposta.get() == ButtonType.OK) {
            try {
                dao.excluir(selecionada.getId());
                limparCampos();
                carregarTarefas();
            } catch (SQLException e) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro no Banco", "Falha ao excluir tarefa: " + e.getMessage());
            }
        }
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtDescricao.clear();
        chkConcluida.setSelected(false);
        tabelaTarefas.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}