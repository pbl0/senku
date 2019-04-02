/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.model;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 *
 * @author PC15
 */
public class MatrizSenku {
    
    public char[][] matriz; //matriz del tablero

    public int score; //nº bolas restantes en el tablero.
    
    private List<Movimiento> mov_anteriores = new ArrayList();
    
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
    final char VACIA = '0';
    final char PARED = '2';
    final char BOLA = '1';
    
    /**
     * Comrpueba si el juego se ha ganado, perdido o si continua
     * @return
     * 'c' -> continua jugando.
     * 'g' -> ha ganado; 1 bola en tablero.
     * 'p' -> ha perdido; No hay movimientos posibles.
     */
    public char estadoJuego(){
        //List lista = new ArrayList(); 
        boolean movPosible = false;
        
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (this.matriz[x][y] == this.BOLA && this.matriz[x+1][y] == this.BOLA && this.matriz[x+2][y] == this.VACIA || 
                    this.matriz[x][y] == this.VACIA && this.matriz[x+1][y] == this.BOLA && this.matriz[x+2][y] == this.BOLA || 
                    this.matriz[x][y] == this.BOLA && this.matriz[x][y+1] == this.BOLA && this.matriz[x][y+2] == this.VACIA || 
                    this.matriz[x][y] == this.VACIA && this.matriz[x][y+1] == this.BOLA && this.matriz[x][y+2] == this.BOLA){
                    
                    movPosible = true;
                    
                } else {
                    
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
     * @return
     * 0 -> Sin errores, ficha movida.
     * 1 -> Error: La casilla seleccionada está vacia.
     * 2 -> Error: La casilla seleccionada/destino está fuera del tablero.
     * 3 -> Error: La casilla de destino ya está ocupada.
     * 4 -> Error: No puedes mover la ficha a esta posición.
     * 5 -> Error: No hay ninguna ficha en medio.
     * 6 -> Error: La casilla seleccionada/destino no existe.
     */
    public char moverFicha(int xSel, int ySel, int xDes, int yDes){
        try{
            System.out.println("Sel: " + this.matriz[xSel][ySel]);
            System.out.println("Des: " + this.matriz[xDes][yDes]);
        
            if (this.matriz[xSel][ySel] == this.VACIA){
                System.out.println("Error: La casilla seleccionada está vacia.");
                return '1';

            } else if (this.matriz[xSel][ySel] == this.PARED || this.matriz[xDes][yDes] == this.PARED){
                System.out.println("Error: La casilla seleccionada/destino está fuera del tablero.");
                return '2';

            } else if (this.matriz[xDes][yDes] != this.VACIA){
                System.out.println("Error: La casilla de destino ya está ocupada.");
                return '3';

            } else {
                if (xSel == xDes){
                    int posicionMedio = (ySel + yDes)/2;

                    if (Math.abs(ySel - yDes) != 2){
                        System.out.println("Error: No puedes mover la ficha a esta posición");
                        return '4';

                    } else if(this.matriz[xSel][posicionMedio] == this.VACIA){
                        System.out.println("Error: No hay ninguna ficha en medio");
                        return '5';

                    } else{
                        this.matriz[xSel][ySel] = this.VACIA;
                        this.matriz[xSel][posicionMedio] = this.VACIA;
                        this.matriz[xDes][yDes] = this.BOLA;
                        this.score--;
                        System.out.println("Ficha movida -> " + score);
                        
                        Movimiento mov = new Movimiento();
                        mov.xSel = xSel;
                        mov.ySel = ySel;
                        mov.xDes = xDes;
                        mov.yDes = yDes;
                        this.mov_anteriores.add(mov);
                        return '0';

                    }
                } else if (ySel == yDes){
                    int posicionMedio = (xSel + xDes)/2;

                    if (Math.abs(xSel - xDes) != 2){
                        System.out.println("Error: No puedes mover la ficha a esta posición.");
                        return '4';

                    } else if(this.matriz[posicionMedio][ySel] == VACIA){
                        System.out.println("Error: No hay ninguna ficha en medio");
                        return '5';

                    } else{
                        this.matriz[xSel][ySel] = this.VACIA;
                        this.matriz[posicionMedio][ySel] = this.VACIA;
                        this.matriz[xDes][yDes] = this.BOLA;
                        this.score--;
                        System.out.println("Ficha movida -> " + this.score);
                        
                        Movimiento mov = new Movimiento();
                        mov.xSel = xSel;
                        mov.ySel = ySel;
                        mov.xDes = xDes;
                        mov.yDes = yDes;
                        this.mov_anteriores.add(mov);
                       
                        return '0';

                    }
                } else{
                    return '4';
                }
            }
        } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Error: La casilla seleccionada/destino no existe.");
                return '6';
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
    
    public void volverJugada(){
        
        Movimiento ultMov = mov_anteriores.get(mov_anteriores.size()-1);
        
        int xSel = ultMov.xSel;
        int ySel = ultMov.ySel;
        int xDes = ultMov.xDes;
        int yDes = ultMov.yDes;
        
        if (xSel == xDes){
            int posicionMedio = (ySel + yDes)/2;
            this.matriz[xSel][posicionMedio] = this.BOLA;
        
        } else if(ySel == yDes){
            int posicionMedio = (xSel + xDes)/2;
            this.matriz[xSel][posicionMedio] = this.BOLA;
            
        }
        this.matriz[xSel][ySel] = this.BOLA;
        this.matriz[xDes][yDes] = this.VACIA;
        mov_anteriores.remove(ultMov);
        this.score++; 
    }
    
    
    //metodo de prueba:
    public void vaciarMatriz(){
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                if (y < 2 && x < 2 || y > 4 && x < 2 || y < 2 && x > 4 || y > 4 && x > 4){
                    this.matriz[x][y] = this.PARED;
                }else if (y == 3 && x == 3){
                    this.matriz[x][y] = this.BOLA;
                }
                else{
                    this.matriz[x][y] = this.VACIA;
                }
            }   
        }
        //this.score = 1;
    }
}  