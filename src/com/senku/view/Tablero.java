/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.view;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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
        ficha.setCenterX(300); 
        ficha.setCenterY(180); 
//Asignamos los colores: Blanco para el centro de la bola, rojo para el anillo medio/exterior
        Stop[] stops = new Stop[] { 
         new Stop(0.0, Color.WHITE),  
         new Stop(0.3, Color.RED)
      };     
// Aqui asignamos la funcion del "foco" que llega a la bola, para mas informacion cntrol+space
      RadialGradient coloresRadial = 
         new RadialGradient(0, 0, 300, 180, 60, false, CycleMethod.NO_CYCLE, stops);
      
      ficha.setFill(coloresRadial); 
      
      Group root = new Group(ficha);
      
        gridTablero.setHgap(10);
        gridTablero.setVgap(10);
        gridTablero.add(ficha,3,0);
        
        //gridTablero.add(ficha2,4,0);
        //gridTablero.add(ficha3,5,0);
        return gridTablero;
    }
    
    
    
}
