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
    
    int[][] matrizSenku = new int[7][7];
    
    public void empezar(){
        for (int i=0; i<7; i++){
            for (int j = 0; j<7; j++){
                if (i < 2 && j < 2 || i > 4 && j < 2 || i < 2 && j > 4 || i > 4 && j > 4 || i == 3 && j == 3){
                    matrizSenku[i][j] = 0;

                } else{
                    matrizSenku[i][j] = 1;
                }
            }   
        }
    }
    
   public void mostrarMatriz(){
       for (int i=0; i<7; i++){
            for (int j = 0; j<7; j++){
                System.out.print(matrizSenku[i][j]);
            }
            System.out.println();
        }

    }
}
