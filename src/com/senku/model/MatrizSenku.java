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
    
    //int[][] matrizSenku = new int[7][7];
    //int x = 1;
    
    public int[][] getMatrizInicial(){
        int[][] matrizSenku = {
            {2,2,1,1,1,2,2},
            {2,2,1,1,1,2,2},
            {1,1,1,1,1,1,1},
            {1,1,1,0,1,1,1},
            {1,1,1,1,1,1,1},
            {2,2,1,1,1,2,2},
            {2,2,1,1,1,2,2}
        };
        return matrizSenku;
    }
    //Sel = Seleccionada , Des = Destino
    public void moverFicha(int[][] matriz, int xSel, int ySel, int xDes, int yDes){
        
        if (matriz[xSel][ySel] == 0){
            System.out.println("En la posicion seleccionada no hay ninguna ficha");
        } else if (matriz[xSel][ySel] == 2){
            System.out.println("La posicion seleccionada está fuera del tablero");
        } else if (matriz[xDes][yDes] == 1){
            System.out.println("Ya hay una ficha en esta posición de destino");
        } else {
            if (xSel == xDes){
                int posicionMedio = (ySel + yDes)/2;
                if (Math.abs(ySel - yDes)!= 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                } else if(matriz[xSel][posicionMedio] == 0){
                    System.out.println("No hay ninguna ficha en medio");
                } else{
                    matriz[xSel][ySel] = 0;
                    matriz[xSel][posicionMedio] = 0;
                    matriz[xDes][yDes] = 1;
                    System.out.println("Ficha movida");
                }
            } else if (ySel == yDes){
                int posicionMedio = (xSel + xDes)/2;
                if (Math.abs(xSel - xDes) != 2){
                    System.out.println("No puedes mover la ficha a esta posición");
                } else if(matriz[posicionMedio][ySel] == 0){
                    System.out.println("No hay ninguna ficha en medio");
                    
                } else{
                    matriz[xSel][ySel] = 0;
                    matriz[posicionMedio][ySel] = 0;
                    matriz[xDes][yDes] = 1;
                    System.out.println("Ficha movida");
                    
                }
            }
        }
    }
    
    public void mostrarMatriz(int [][] matriz){
       for (int j=0; j<7; j++){
            for (int i = 0; i<7; i++){
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }

    }
}
