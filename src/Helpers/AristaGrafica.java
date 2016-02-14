/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.scene.shape.Line;

/**
 *
 * @author antonio
 */
public class AristaGrafica extends Line{

    private Arista arista;
    public AristaGrafica(double x1, double y1,double x2, double y2, Arista arista) {
        this.setStartX(x1);
        this.setStartY(y1);
        this.setEndX(x2);
        this.setEndY(y2);
        this.arista = arista;
        //cambiamos de color
        this.setOnMouseEntered(e -> {
            this.setStyle("-fx-stroke-width: 3px;");
        });
        
        this.setOnMouseExited(e -> {
            this.setStyle("-fx-stroke-width: 1px;");
        });
        
        
        
    
    }
    
    
    
}
