/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author antonio
 */
public class Grafo {
    private ObservableList<Nodo> listaNodo = FXCollections.observableArrayList();
    private ObservableList<Arista> listaArista = FXCollections.observableArrayList(); 
    private boolean dirigido = false; 
    private Pane panel;
   

    public Grafo(Pane panel,boolean esDirigido) {
        this.panel = panel;
        this.dirigido = esDirigido;
    }
    
    /**
     * Revisa si Existe el nodo dentro de la lista
     * @param nodo Nodo que se va a revisar
     * @return True Existe el nodo False: No existe 
     */
    public boolean existeNodo(Nodo nodo)
    {
        for(Nodo n : listaNodo)
        {
            if(nodo.getNombre().equals(n.getNombre()))
            {
                return true;
            }
        }
        
        return false;   
    
    }
    public void eliminaNodoGrafico(NodoGrafico nodo)
    {
        System.out.println(panel.getChildren().contains(nodo));
        panel.getChildren().remove(nodo);
        panel.getChildren().remove(nodo.getTexto());
        listaNodo.remove(nodo.getNodo());
    }
    
    public void eliminaNodo(Nodo nodo)
    {
        listaNodo.remove(nodo);
    }
    public void dibujaNodo(double x,double y,Nodo nodo,Pane pane)
    {
         NodoGrafico nod = new NodoGrafico(x,y,nodo);
         pane.getChildren().addAll(nod,nod.getTexto());
    }
    
    /**
     * Revisa si existe la arista dentro del grafo
     * @param arista
     * @return 
     */
    public boolean existeArista(Arista arista)
    {
        for(Arista a : listaArista)
        {
            if(arista.imprimeRelacion().equals(a.imprimeRelacion()))
            {
                return true;
            }
        }
        
        return false;
    }
    /**
     * Agrega la opcion al grafo sobre si es dirigido o no, si no es dirigido cuando se agrega una arista se agregara tambien su contraparte
     * por ejemplo, a->b tambien se agregara b->a, y si dirigido solo se agrega solo el nodo
     * @param opc 
     */
    public void setDirigido(boolean opc)
    {
        dirigido = opc;
    }
    /**
     * Agregas un nuevo objeto nodo a la lista
     * @param nodo 
     */
    public void agregaNodo(Nodo nodo,double x, double y)
    {
        if(!this.existeNodo(nodo))
        {
            listaNodo.add(nodo);
            dibujaNodo(x,y,nodo, panel);
        }
        else
        {
            System.out.println("El nodo que intentas agregar ya existe!!");
        }
        
    }
    
    public void agregaArista(Arista arista)
    {
        if(!existeArista(arista))
        {
            
            //Si el grafo no es dirigido agregamos la contraparte ejemplo: si la arista es a->b entonces agregamos b->a
            if(!dirigido)
            {
               
                listaArista.add(arista);
                if(!existeArista(arista.obtenerContraparte()))
                {
                   listaArista.add(arista.obtenerContraparte());
                }
            }
            else
            {
                listaArista.add(arista);
            }
           
 
        }
        else
        {
            System.out.println("Error la arista ya existe en el grafo");
        }
    }
    
    public void imprimeRelaciones()
    {
        for(Arista a : listaArista)
        {
            System.out.println(a.imprimeRelacion());
        }
    }
    
    
    /**
     * Imprime una lista de adyacencia
     */
    public void imprimeListaAdyacencia()
    {
        for(Nodo nodo : listaNodo)
        {
            System.out.print(nodo.getNombre() +" -> ");
            for(Arista arista : listaArista)
            {
                
                if(nodo.equals(arista.getInicio()))
                {
                    System.out.print(arista.getFin().getNombre() + " -> ");
                }
            }
            System.out.println(" ");
        }
    }
    
    /**
     * Enlaza cada nodo con el adyacnete
     * @return 
     */
    public List<Nodo> getListaAdyacencia()
    {
        List<Nodo> lista = new ArrayList<>();
        Nodo inicial;//Con que nodo iniciamos
        Nodo actual; //el nodo actual
        for(Nodo n : listaNodo) //Recorremos todos los nodos
        {
            actual = n;
            inicial = n;
            for(Arista arista : listaArista)
            {
                if(n.equals(arista.getInicio()))
                {
                    Nodo siguiente = Nodo.nuevaInstancia(arista.getFin());
                    siguiente.setPeso(arista.getPeso());
                    siguiente.setHeuristica(arista.getHeuristica());
                    actual.setSiguiente(siguiente);
                    actual = siguiente;
                }
            }
            actual.setSiguiente(null);
            lista.add(inicial);
        }
       return lista;
        
    }
    
    public ObservableList<Nodo> getNodos()
    {
        return listaNodo;
    }
    
    public int getNumeroNodos()
    {
        return listaNodo.size();
    }
    public ObservableList<Arista> getAristas()
    {
        return listaArista;
    }
    
    public int getNumeroAristas()
    {
        return listaArista.size();
    }
}
