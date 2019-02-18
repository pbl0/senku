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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MatrizSenku matrizSenku = new MatrizSenku();
        int [][] matriz = matrizSenku.getMatrizInicial();
        matrizSenku.moverFicha(matriz,3,1,3,3);
        matrizSenku.mostrarMatriz(matriz);
        // TODO code application logic here
    }
    
}
