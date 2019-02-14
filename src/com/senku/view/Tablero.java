/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 *
 * @author PC15
 */
public class Tablero {
    
    GridPane gridTablero = new GridPane();
    Circle ficha = new Circle(15);
    /*Circle ficha2 = new Circle(15);
    Circle ficha3 = new Circle(15);
    */
    
    
    public GridPane getGridTablero(){
        ficha.setFill(Color.RED);
        gridTablero.setHgap(10);
        gridTablero.setVgap(10);
        gridTablero.add(ficha,3,0);
        
        //gridTablero.add(ficha2,4,0);
        //gridTablero.add(ficha3,5,0);
        return gridTablero;
    }
    
    
    
}
