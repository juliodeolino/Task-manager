/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.app;
import java.util.Scanner;
import com.mycompany.taskmanager.controller.TaskManager;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import com.mycompany.taskmanager.model.BaseTarefa;

/**
 *
 * @author arsen
 */
public class Main {
    
    public static void main(String[] args){
    
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do{
            System.out.println("===== GERENCIADOR DE TAREFAS =====");
            System.out.println("Digite 1 para listar tarefas");
            System.out.println("Digite 2 para adicionar tarefas");
            System.out.println("Digite 3 para adicionar tarefas com prioridade");
            System.out.println("Digite 4 para marcar tarefa como concluida");
            System.out.println("Digite 5 para excluir uma tarefa");
            System.out.println("Digite 6 para sair");
            
            try{
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch(opcao){
                case 1:
                    manager.listarTarefas();
                    break;
                case 2:
                    System.out.println("Digite o titulo da tarefa: ");
                    String titulo = sc.nextLine();
                    
                    System.out.println("Digite a descrição da Tarefa: ");
                    String  descricao = sc.nextLine();
                    
                    Tarefa novaTarefa = new Tarefa(titulo, descricao);
                    manager.adicionarTarefa(novaTarefa);
                    break;
                case 3:
                    System.out.println("Digite o titulo da tarefa: ");
                    titulo = sc.nextLine();
                    
                    System.out.println("Digite a descrição da Tarefa: ");
                    descricao = sc.nextLine();
                    
                    System.out.println("Digite a prioridade da Tarefa: High/Mid/Low");
                    String prioridade = sc.nextLine();
                                        
                    if(!prioridade.equalsIgnoreCase("High") && !prioridade.equalsIgnoreCase("Mid") && !prioridade.equalsIgnoreCase("Low")){
                        System.out.println("Prioridade invalida!");
                        break;
                    }
                    
                    TarefaPrioritaria novaTarefaPrio = new TarefaPrioritaria(titulo, descricao, prioridade);
                    manager.adicionarTarefa(novaTarefaPrio);
                    break;
                case 4:
                   try{
                        System.out.println("Digite o número da tarefa que deseja concluir:");
                        int numero = sc.nextInt();
                        sc.nextLine();
                        manager.concluirTarefa(numero - 1);
                   }catch(java.util.InputMismatchException e){
                        System.out.println("Erro: Você deve digitar um numero inteiro!");
                        sc.nextLine();
                   }
                   break;
                case 5:
                    try{
                        System.out.println("Digite o número da tarefa que deseja excluir:");
                        int numero1 = sc.nextInt();
                        sc.nextLine();
                        manager.removerTarefa(numero1 - 1);
                    }catch(java.util.InputMismatchException e){
                        System.out.println("Erro: Você deve digitar um numero inteiro!");
                        sc.nextLine();
                   }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            }catch(java.util.InputMismatchException e){
                System.out.println("Erro: Você deve digitar um numero inteiro!");
                sc.nextLine();
                opcao = 0;
            }
        }while(opcao != 6);
        
        sc.close();
      
    }    
}
