/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author antonio
 */


    public class NodoGrafico extends Circle  {
    
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private boolean isDrag = false;
    
    private Text texto = new Text();
    private final Nodo nodo;
   
    
    public Text getTexto() {
        return texto;
    }
    
    
    public NodoGrafico(double x, double y,Nodo nodo){
        
        this.nodo = nodo;
        texto.setFont(new Font(20));
         
        texto.setTextAlignment(TextAlignment.JUSTIFY);
        texto.setText(nodo.getNombre());
        texto.setVisible(true);
        texto.setX(x);
        texto.setY(y);
        
        
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(20);
        this.setOpacity(1);
        this.setFill(Color.AQUA);
        this.setStroke(Color.BLACK);
        
        this.setOnMouseClicked(e -> {
            
           
        });
        
        
        this.setOnMouseClicked(e -> {
           //Editar referencias
           if(e.isShiftDown())
           {
               try 
               {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/newNodoWindow.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));  
                    stage.show();
               } 
               catch(Exception er) 
               {
                    er.printStackTrace();
               }
           }
           if(e.isAltDown() && e.isControlDown())
           {
               //Borramos 
               
               System.out.println("Borramos");
           }
           
        
        });
        
        //Cambiar el color cuando el mouse pasa sobre el circulo
        this.setOnMouseEntered(e -> {
            this.setFill(Color.AZURE);
        });
        
        //cambiar el color cuando el mouse sale del area del circulo
        this.setOnMouseExited(e -> {
            this.setFill(Color.AQUA);
        });
        
        this.setOnMousePressed(e->{
            isDrag = true;
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((Circle)(e.getSource())).getTranslateX();
            orgTranslateY = ((Circle)(e.getSource())).getTranslateY();
        });
        
        this.setOnMouseDragged(t->{
            
            if(isDrag && t.isAltDown())
            {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                texto.setTranslateX(newTranslateX);
                texto.setTranslateY(newTranslateY);
                
            }
        });
        
        setOnMouseReleased(e->{
            isDrag = false;
        });
       
        
      
   }

    public Nodo getNodo() {
        return nodo;
    }
   
   
            
    
}
    
    

