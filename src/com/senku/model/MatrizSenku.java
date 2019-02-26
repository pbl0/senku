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

    public int score; //nº bolas restantes en el tablero.
    
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

    /**
     * Comrpueba si el juego se ha ganado, perdido o si continua
     * @return
     * 'c' -> continua jugando.
     * 'g' -> ha ganado. 1 bola en tablero.
     * 'p' -> ha perdido. No hay movimientos posibles.
     */
    public char estadoJuego(){
        //List lista = new ArrayList(); 
        boolean movPosible = false;
        
        
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (this.matriz[x][y] == '1' && this.matriz[x+1][y] == '1' && this.matriz[x+2][y] == '0' || 
                    this.matriz[x][y] == '0' && this.matriz[x+1][y] == '1' && this.matriz[x+2][y] == '1' || 
                    this.matriz[x][y] == '1' && this.matriz[x][y+1] == '1' && this.matriz[x][y+2] == '0' || 
                    this.matriz[x][y] == '0' && this.matriz[x][y+1] == '1' && this.matriz[x][y+2] == '1'){
                    
                    movPosible = true;
                    //lista.add(movPosible);
                } else {
                    //lista.add(movPosible);
                }
            }
        }
        if (this.score == 1){
            return 'g';
        } else if (movPosible){
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
        
        try{
            System.out.println("Sel: " + matriz[xSel][ySel]);
            System.out.println("Des: " + matriz[xDes][yDes]);
        
            if (this.matriz[xSel][ySel] == VACIA){
                System.out.println("Error: La casilla seleccionada está vacia.");

            } else if (this.matriz[xSel][ySel] == PARED || this.matriz[xDes][yDes] == PARED){
                System.out.println("Error: La casilla seleccionada/destino está fuera del tablero.");

            } else if (this.matriz[xDes][yDes] != VACIA){
                System.out.println("Error: La casilla de destino ya está ocupada.");

            } else {
                if (xSel == xDes){
                    int posicionMedio = (ySel + yDes)/2;

                    if (Math.abs(ySel - yDes) != 2){
                        System.out.println("Error: No puedes mover la ficha a esta posición");

                    } else if(this.matriz[xSel][posicionMedio] == VACIA){
                        System.out.println("Error: No hay ninguna ficha en medio");

                    } else{
                        this.matriz[xSel][ySel] = VACIA;
                        this.matriz[xSel][posicionMedio] = VACIA;
                        this.matriz[xDes][yDes] = BOLA;
                        this.score--;
                        System.out.println("Ficha movida -> " + score);

                    }
                } else if (ySel == yDes){
                    int posicionMedio = (xSel + xDes)/2;

                    if (Math.abs(xSel - xDes) != 2){
                        System.out.println("Error: No puedes mover la ficha a esta posición");

                    } else if(this.matriz[posicionMedio][ySel] == VACIA){
                        System.out.println("Error: No hay ninguna ficha en medio");

                    } else{
                        this.matriz[xSel][ySel] = VACIA;
                        this.matriz[posicionMedio][ySel] = VACIA;
                        this.matriz[xDes][yDes] = BOLA;
                        this.score--;
                        System.out.println("Ficha movida -> " + score);

                    }
                }
            }
        } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Error: La casilla seleccionada/destino no existe.");
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
                    this.matriz[x][y] = '2';
                }else if (y == 3 && x == 3){
                    this.matriz[x][y] = '1';
                }
                else{
                    this.matriz[x][y] = '0';
                }
            }   
        }
        //this.score = 1;
    }
}  