/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.model;

/**
 *
 * @author PC15
 */
public class MatrizSenku {
    
    //'2' = pared, '1' = bola, '0' = vacio
    public char[][] matriz = {
            {'2','2','1','1','1','2','2'},
            {'2','2','1','1','1','2','2'},
            {'1','1','1','1','1','1','1'},
            {'1','1','1','0','1','1','1'},
            {'1','1','1','1','1','1','1'},
            {'2','2','1','1','1','2','2'},
            {'2','2','1','1','1','2','2'}
        };
    
    public void reiniciarMatriz(){
        for (int i=0; i<7; i++){
            for (int j = 0; j<7; j++){
                if (i < 2 && j < 2 || i > 4 && j < 2 || i < 2 && j > 4 || i > 4 && j > 4){
                    matriz[i][j] = '2';
                }else if (i == 3 && j == 3){
                    matriz[i][j] = '0';
                }
                else{
                    matriz[i][j] = '1';
                }
            }   
        }
    }
    
    //Sel = Seleccionada , Des = Destino
    public void moverFicha(int xSel, int ySel, int xDes, int yDes){
        if (matriz[xSel][ySel] == '0'){
            System.out.println("La casilla seleccionada está vacia.");
            
        } else if (matriz[xSel][ySel] == '2' || matriz[xDes][yDes] == '2'){
            System.out.println("La casilla seleccionada/destino está fuera del tablero.");
            
        } else if (matriz[xDes][yDes] != '0'){
            System.out.println("La casilla de destino ya está ocupada.");
            
        } else {
            if (xSel == xDes){
                int posicionMedio = (ySel + yDes)/2;
                
                if (Math.abs(ySel - yDes)!= 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                    
                } else if(matriz[xSel][posicionMedio] == '0'){
                    System.out.println("No hay ninguna ficha en medio");
                    
                } else{
                    matriz[xSel][ySel] = '0';
                    matriz[xSel][posicionMedio] = '0';
                    matriz[xDes][yDes] = '1';
                    System.out.println("Ficha movida");
                    
                }
            } else if (ySel == yDes){
                int posicionMedio = (xSel + xDes)/2;
                
                if (Math.abs(xSel - xDes) != 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                    
                } else if(matriz[posicionMedio][ySel] == 0){
                    System.out.println("No hay ninguna ficha en medio");
                    
                } else{
                    matriz[xSel][ySel] = '0';
                    matriz[posicionMedio][ySel] = '0';
                    matriz[xDes][yDes] = '1';
                    System.out.println("Ficha movida");
                    
                }
            }
        }
    }
    
    public void mostrarMatriz(){
       for (int j=0; j<7; j++){
            for (int i = 0; i<7; i++){
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

    }
}
