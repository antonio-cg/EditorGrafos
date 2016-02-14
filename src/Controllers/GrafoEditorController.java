/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Helpers.Arista;
import Helpers.AristaGrafica;
import Helpers.Grafo;
import Helpers.Nodo;
import Helpers.NodoGrafico;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 *
 * @author antonio
 */
public class GrafoEditorController implements Initializable {

    @FXML
    private Pane contenedorGrafo;
    private Grafo grafo;
    private NodoGrafico inicio;
    private NodoGrafico Final;
    @FXML
    private TableView<Nodo> nodosTable;
    @FXML
    private TableColumn<Nodo, String> nodoColumn;
    @FXML
    private TableColumn<Nodo, String> DatosColumn;
    @FXML
    private TableView<Arista> aristaTables;
    @FXML
    private TableColumn<Arista, String> nodoInicioColumn;
    @FXML
    private TableColumn<Arista, String> nodoFinColumn;
    @FXML
    private TableColumn<Arista, Double> PesoColumn;
    @FXML
    private TableColumn<Arista, Double> heuristicaColumn;
    private ObservableList<Nodo> listaNodo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grafo = new Grafo(contenedorGrafo,false);
        
        
        //Inicializamos TableView Nodos
        nodosTable.setItems(grafo.getNodos());
        nodoColumn.setCellValueFactory( new PropertyValueFactory<Nodo, String>("Nombre"));
        DatosColumn.setCellValueFactory(new PropertyValueFactory<Nodo, String>("Datos"));
        //Incializamos TableView Aristas 
        aristaTables.setItems(grafo.getAristas());
        
        nodoInicioColumn.setCellValueFactory(new PropertyValueFactory<Arista,String>("nombreNodoInicio"));
        nodoFinColumn.setCellValueFactory(new PropertyValueFactory<Arista,String>("nombreNodoFin"));
        PesoColumn.setCellValueFactory(new PropertyValueFactory<Arista,Double>("peso"));
        heuristicaColumn.setCellValueFactory(new PropertyValueFactory<Arista,Double>("heuristica"));
    }    

    /**
     * Metodo que maneja cuando damos click dentro del pane que contiene el grafo
     * @param event evento del mouse
     */
    @FXML
    private void contenedorOnClick(MouseEvent event) {
        System.out.println(event.getButton());
        System.out.println(event.getTarget().getClass());
        if(event.getButton() == MouseButton.PRIMARY && event.isControlDown())
        {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("Please enter your name:");
            Optional<String> result = dialog.showAndWait();
            
            if (result.isPresent()){
                Nodo nod = new Nodo(result.get());
                grafo.agregaNodo(nod,event.getX(),event.getY());
               // listaNodo = FXCollections.observableArrayList(grafo.getNodos());

                }
        } 
        //Eliminamos Nodo
        if(event.getButton() == MouseButton.SECONDARY && event.getTarget().getClass().equals(NodoGrafico.class) && event.isControlDown())
        {
            System.out.println("Estamos para borrar");
            NodoGrafico nodo = (NodoGrafico)event.getTarget();
            grafo.eliminaNodoGrafico(nodo);
           // listaNodo = FXCollections.observableArrayList(grafo.getNodos());

        }
        //Agregamos Arista
        if(event.getButton() == MouseButton.SECONDARY && event.getTarget().getClass().equals(NodoGrafico.class))
        {
            if(inicio == null)//todavia no se elige un nodo de inicio
            {
                inicio = (NodoGrafico)event.getTarget(); //ponemos el nodo de inicio
            }
            else
            {
                NodoGrafico nodo = (NodoGrafico)event.getTarget();
                Arista arista = new Arista(inicio.getNodo(),nodo.getNodo());
                grafo.agregaArista(arista);
                AristaGrafica aristaGrafica = new AristaGrafica(inicio.getCenterX(),inicio.getCenterY(),nodo.getCenterX(),nodo.getCenterY(),arista);
                contenedorGrafo.getChildren().add(aristaGrafica);
                inicio = null;
            }
            
        }

    }
    
}
