/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku;

import com.senku.model.MatrizSenku;
import com.senku.view.Tablero;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author PC15
 */
public class Senku extends Application {
    
    int paneScores = 0;
    int marcador = -1;

    @Override
    public void start(Stage primaryStage) {
     

        Tablero tablero = new Tablero();
        
//        tablero.esquinasNegro(0, 0);
        tablero.nuevaPartida();
       
        tablero.mostrarFondoTablero();
        
        
        
//        tablero.mostrarFichas();
   

// Contenedor para alinear el tablero en centro horizontalmente
        HBox hBox = new HBox(tablero.getGridTablero());
        hBox.setAlignment(Pos.CENTER);
        
// Contenedor principal será de tipo VBox para centrar tablero verticalmente
        VBox root = new VBox();
        root.getChildren().add(hBox);
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root, 1000, 500);
        
        primaryStage.setTitle("Senku");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Hola");
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   

}
