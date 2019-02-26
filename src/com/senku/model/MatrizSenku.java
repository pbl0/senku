/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC15
 */
public class MatrizSenku {
    
    public char[][] matriz; //matriz del tablero

    int score; //nº bolas en tablero
    
    public MatrizSenku() {
        //'2' = pared, '1' = bola, '0' = vacio
        this.matriz = new char[][]{
            {'2','2','1','1','1','2','2'},
            {'2','2','1','1','1','2','2'},
            {'1','1','1','1','1','1','1'}, 
            {'1','1','1','0','1','1','1'}, 
            {'1','1','1','1','1','1','1'}, 
            {'2','2','1','1','1','2','2'}, 
            {'2','2','1','1','1','2','2'}
        };
        this.score = 32;
        
    }
    //gana si solo queda 1 bola en el tablero.
    public boolean ganar(){
        return this.score == 1;
    }
    

    /**
     * Comrpueba si el juego se ha ganado, perdido o si continua
     * @return
     * 'c' -> continua jugando.
     * 'g' -> ha ganado.
     * 'p' -> ha perdido.
     */
    public char fin(){
        List lista = new ArrayList(); 
        boolean movPosible;
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (this.matriz[x][y] == '1'&& this.matriz[x+1][y] == '1'&& this.matriz[x+2][y] == '0' || this.matriz[x][y] == '0'&& this.matriz[x+1][y] == '1'&& this.matriz[x+2][y] == '1' || this.matriz[x][y] == '1'&& this.matriz[x][y+1] == '1'&& this.matriz[x][y+2] == '0' || this.matriz[x][y] == '0'&& this.matriz[x][y+1] == '1'&& this.matriz[x][y+2] == '1'){
                    movPosible = true;
                    lista.add(movPosible);
                } else {
                    movPosible = false;
                    lista.add(movPosible);
                }
            }
        }
        if (ganar()){
            return 'g';
        } else if (lista.contains(true)){
            return 'c';
        } else {
            return 'p';
        }
    }
    
    /**
     * Metodo mover ficha
     * Sel = Seleccionada , Des = Destino
     * @param xSel
     * @param ySel
     * @param xDes
     * @param yDes
     */
    public void moverFicha(int xSel, int ySel, int xDes, int yDes){
        final char VACIA = '0';
        final char PARED = '2';
        final char BOLA = '1';
        
        
        
        if (this.matriz[xSel][ySel] == VACIA){
            System.out.println("La casilla seleccionada está vacia.");
            
        } else if (this.matriz[xSel][ySel] == PARED || this.matriz[xDes][yDes] == PARED){
            System.out.println("La casilla seleccionada/destino está fuera del tablero.");
            
        } else if (this.matriz[xDes][yDes] != VACIA){
            System.out.println("La casilla de destino ya está ocupada.");
            
        } else {
            if (xSel == xDes){
                int posicionMedio = (ySel + yDes)/2;
                
                if (Math.abs(ySel - yDes) != 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                    
                } else if(this.matriz[xSel][posicionMedio] == VACIA){
                    System.out.println("No hay ninguna ficha en medio");
                    
                } else{
                    this.matriz[xSel][ySel] = VACIA;
                    this.matriz[xSel][posicionMedio] = VACIA;
                    this.matriz[xDes][yDes] = BOLA;
                    System.out.println("Ficha movida");
                    this.score--;
                     
                }
            } else if (ySel == yDes){
                int posicionMedio = (xSel + xDes)/2;
                
                if (Math.abs(xSel - xDes) != 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                    
                } else if(this.matriz[posicionMedio][ySel] == VACIA){
                    System.out.println("No hay ninguna ficha en medio");
                    
                } else{
                    this.matriz[xSel][ySel] = VACIA;
                    this.matriz[posicionMedio][ySel] = VACIA;
                    this.matriz[xDes][yDes] = BOLA;
                    System.out.println("Ficha movida");
                    this.score--;
                        
                }
            }
        }
    }
    
    //muestra la matriz en consola
    public void mostrarMatriz(){
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                System.out.print(this.matriz[x][y]);
            }
            System.out.println();
        }
    }
    
    //metodo de prueba:
    public void vaciarMatriz(){
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                if (y < 2 && x < 2 || y > 4 && x < 2 || y < 2 && x > 4 || y > 4 && x > 4){
                    matriz[x][y] = '2';
                }else if (y == 3 && x == 3){
                    matriz[x][y] = '1';
                }
                else{
                    matriz[x][y] = '0';
                }
            }   
        }
        //this.score = 1;
    }
}  
