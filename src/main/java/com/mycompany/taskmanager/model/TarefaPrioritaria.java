/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;
import com.mycompany.taskmanager.model.BaseTarefa;

/**
 *
 * @author arsen
 */
public class TarefaPrioritaria extends Tarefa {
    private String prioridade;
    
    
    public TarefaPrioritaria(String titulo, String descricao, String prioridade){
        super(titulo, descricao);
        this.prioridade = prioridade;     
    }
    public TarefaPrioritaria(int id, String titulo, String descricao, String prioridade){
        super(id, titulo, descricao);
        this.prioridade = prioridade;     
    }
    
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    
    @Override
    public void exibirDetalhes(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
         return super.toString() + "[Prioridade: " + this.prioridade + "]";
    } 
}
