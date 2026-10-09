/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author julio
 */
public class ListaGenerica<T extends BaseTarefa>{
    
    private final List<T> elementos = new ArrayList<>();
    
    public void adcionar(T elemento){
        if(elemento != null){
            elementos.add(elemento);
        }
    }
    
    public void remover(T elemento) {
        elementos.remove(elemento);
    }
    
    public List<T> listarTodos(){
        return new ArrayList<>(elementos);
    }
    
    public void adcionarVarios(List<? extends T> novosElementos) {
        if(novosElementos != null){
            elementos.addAll(novosElementos);
        }
    }
    
    public void exportarConluidasPara(List<? super T> destino){
            for(T item : elementos){
                if(item.isConcluida()){
                destino.add(item);
            }
        }
    }
    
    public static int contarElementos(List<?> qualquerLista){
        return qualquerLista == null ? 0 : qualquerLista.size();
    }
}
