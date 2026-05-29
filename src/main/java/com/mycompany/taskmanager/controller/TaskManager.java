/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;
import com.mycompany.taskmanager.model.BaseTarefa;
import java.util.ArrayList;
/**
 *
 * @author arsen
 */
public class TaskManager {
    private ArrayList<BaseTarefa> listaDeTarefas;
    
    
    public TaskManager(){
        this.listaDeTarefas = new ArrayList<>();
    }
    
    public void adicionarTarefa (BaseTarefa novaTarefa){
        this.listaDeTarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }
    
    public void listarTarefas(){
    if(this.listaDeTarefas.isEmpty()){
        System.out.println("Nenhuma tarefa cadastrada até o momento!");
        return;
    }
    
    System.out.println("--LISTA DE TAREFAS--");
    for(int i = 0; i < this.listaDeTarefas.size(); i++){
        BaseTarefa tarefaAtual = this.listaDeTarefas.get(i);
        System.out.println((i + 1) + ". " + tarefaAtual);
    }    
  }
   public void concluirTarefa(int index){
        if(index >= 0 && index < this.listaDeTarefas.size()){
            BaseTarefa tarefa = this.listaDeTarefas.get(index);
            tarefa.setConcluida(true);
            
            System.out.println("Tarefa '" + tarefa.getTitulo() + "' concluida com sucesso!");
        }else{
            System.out.println("Erro: posição invalida!");
        }
        
    }
   
   public void removerTarefa(int index){
       if(index >= 0 && index < this.listaDeTarefas.size()){
       BaseTarefa tarefa = this.listaDeTarefas.remove(index);
           System.out.println("Tarefa removida: " + tarefa.getTitulo());
   }else{
           System.out.println("Erro: posição invalida!");
    }
   }
}
