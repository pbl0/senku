/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.model;

/**
 *
 * @author Pablo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MatrizSenku matrizSenku = new MatrizSenku(1);
        //matrizSenku = new MatrizSenku();
        
        //matrizSenku.mostrarMatriz();
        //matrizSenku.moverFicha();
        char j;
        j = matrizSenku.moverFicha(5,3,3,3);
        
        matrizSenku.moverFicha(2,3,4,3);
        
        matrizSenku.moverFicha(1,3,3,3);
        
        matrizSenku.moverFicha(3,1,3,3);
        
        matrizSenku.moverFicha(4,3,2,3);
        
        matrizSenku.moverFicha(3,5,3,3);
        
        matrizSenku.moverFicha(2,3,4,3);
        
        matrizSenku.moverFicha(7,3,4,3);
        
        
        matrizSenku.vaciarMatriz();
        
        
        
        
        
        
        char resultado = matrizSenku.estadoJuego();
        long tiempo;
        if (resultado == 'g'){
            tiempo = matrizSenku.tiempo();
        }
        
        matrizSenku.mostrarMatriz();
        System.out.println("Bolas restantes => "+ matrizSenku.score);
        System.out.println("Fin => " + resultado);
        //matrizSenku.volverJugada();
    }    
}
