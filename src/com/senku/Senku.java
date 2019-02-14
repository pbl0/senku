/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku;

import com.senku.view.Tablero;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author PC15
 */
public class Senku extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Tablero tablero = new Tablero();
        
        StackPane root = new StackPane();
        root.getChildren().add(tablero.getGridTablero());
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Senku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
