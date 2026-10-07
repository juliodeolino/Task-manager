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
public class Tarefa extends BaseTarefa{

   
    public Tarefa(String titulo, String descricao){
       super(titulo, descricao);
    }
    
    public Tarefa(int id, String titulo, String descricao){
       super(id, titulo, descricao);
    }
    
    public Tarefa(int id, String titulo,String descricao, boolean concluida){
       super(id, titulo, descricao, concluida);
    }
    
    @Override
    public void exibirDetalhes(){
        System.out.println(this.toString());
    }
}
