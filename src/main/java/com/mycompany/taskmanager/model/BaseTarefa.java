/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

/**
 *
 * @author arsen
 */
public abstract class BaseTarefa {
    protected String titulo;
    protected String descricao;
    protected boolean concluida;
    
    public BaseTarefa(){
    super();
    }
    
    public BaseTarefa(String titulo, String descricao, boolean concluida){
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
    }
    
        public BaseTarefa(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
    }
        
    public abstract void exibirDetalhes();
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public boolean isConcluida(){
        return concluida;
    }
    
    public void setConcluida (boolean concluida){
        this.concluida = concluida;
    }
    
    @Override
    public String toString(){
        String status = this.concluida ? "[X]" : "[ ]";
        return "Tarefa{"
                + status
                + "Titulo: " + this.titulo + ", "
                + "Descricão: " + this.descricao + "}";
                
    }
}
