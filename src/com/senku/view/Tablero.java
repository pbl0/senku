/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.view;


import com.senku.model.MatrizSenku;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.LIGHTGRAY;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 *
 * @author PC15
 */

public class Tablero {
    //Llamamos a la matriz de MatrizSenku para poder usarla mas tarde
    private MatrizSenku senku;
    
    GridPane gridTablero = new GridPane();
    
    int contadorClick = 0;
    int columnas = 7;
    
    int xSel = 0;
    int ySel = 0;
    int xDes = 0;
    int yDes = 0;
    

    
    ArrayList<Circle> listaBolas =new ArrayList();
    
    public GridPane getGridTablero(){
  
            gridTablero.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                
                double matchX = Math.floor(mouseEvent.getX() / 60);   
                double matchY = Math.floor(mouseEvent.getY() / 60);
                contadorClick++;
                
                
                if(contadorClick == 1){
                    xSel = (int) matchX;
                    ySel = (int) matchY;   
                }else if(contadorClick ==2){
                    xDes = (int) matchX;
                    yDes = (int) matchY;
                    
                    for(int i=0; i<listaBolas.size(); i++){
                     Circle listF = listaBolas.get(i);
                     gridTablero.getChildren().remove(listF);   
                     }
                    
                    senku.moverFicha(xSel, ySel, xDes, yDes);
                    mostrarFondoTablero();
                    
                    contadorClick = 0;
                }
 
                System.out.println("Numero de clicks:" +
                        contadorClick);
                System.out.println(" X:" + 
                        matchX + " Y:"+ matchY);
            
            }
        });
        //Este paso nos separa las casillas del tablero
        gridTablero.setHgap(10);
        gridTablero.setVgap(10);
        return gridTablero;
    }
    
    public void crearBola(int x, int y){
        Circle ficha = new Circle(25);
            
        ficha.setCenterX(300); 
        ficha.setCenterY(180); 
    //Asignamos los colores: Blanco para 
    //el centro de la bola, rojo para el anillo medio/exterior
        Stop[] stops = new Stop[] { 
         new Stop(0.0, Color.WHITE),  
         new Stop(0.3, Color.RED)
      };     
    // Aqui asignamos la funcion del "foco" 
    //que llega a la bola, para mas informacion cntrol+space
    
      RadialGradient coloresRadial = 
              //Aqui damos la posicion de la luz blanca en nuestra ficha (centrada en este caso)
        new RadialGradient(0, 0, 300, 180, 80, false, CycleMethod.NO_CYCLE, stops);
      
      ficha.setFill(coloresRadial);
      
      Group root = new Group(ficha); 
      gridTablero.add(ficha,x,y);
      

      listaBolas.add(ficha);
       
    }
      
    // Modificamos el tablero que vamos a utilizar 
    public void nuevaPartida() {
        this.senku = new MatrizSenku(1);
        senku.mostrarMatriz();     
        this.mostrarFondoTablero();
//        this.mostrarFichas();
    }
    //For que nos recorre la matriz para asi poder diferenciar los 2,1 y 0 que encontramos en dicha matriz
    public void mostrarFondoTablero(){ 
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){  
               esquinasNegro(x,y);       
            }
        }
    }

    //En este paso, dibujaremos los recuadros que contendran las fichas
    //y los añadimos al grid. Despues, con un switch le decimos que
    //si al recorrer la matriz, encuentra un 1, nos rellene de color gris claro
    //el cuadrado del tablero, con otro case, le decimos que si encuentra un 2
    //cambie el color de los cuadros del tablero a negro, añadiendo en cada caso
    //un evento que hara que con cada click en el tablero, dependiendo del color
    //del cuadrado, nos contestara una cosa u otra
    
    public void transfor(int x, int y){
        int posicion = ((y*columnas)+x);
        
    }
    
    public void esquinasNegro(int x, int y){
        Rectangle r = new Rectangle(50, 50);
        r.setStroke(Color.BLACK);
  
        gridTablero.add(r, x, y);
        
        char esquina = senku.getEsquina(x, y);
        switch (esquina) {
            
            case MatrizSenku.BOLA:
                
                r.setFill(Color.LIGHTGRAY); 
                this.crearBola(x, y);
     
            break;
                
            case MatrizSenku.PARED:
                r.setFill(Color.BLACK);
            break;
            
            case MatrizSenku.VACIA:
                r.setFill(Color.LIGHTGRAY);
            break;
                
        }
        
    }
    
    
}
