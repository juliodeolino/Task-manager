package com.mycompany.taskmanager.app;

import com.mycompany.taskmanager.db.Conexao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Conexao.criarTabelas();
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/taskmanager/view/Main.fxml"));
        
        Scene scene = new Scene(root, 650, 450);

        primaryStage.setTitle("TaskManager - iRede");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args); 
    }
}