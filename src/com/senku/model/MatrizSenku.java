/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senku.model;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase MatrizSenku
 * Parte Lógica
 * @author Pablo
 */
public class MatrizSenku {
    //Propiedades MatrizSenku:
    public char[][] matriz; //matriz del tablero
    public int score; //nº bolas restantes en el tablero.
    private List<Movimiento> mov_anteriores = new ArrayList(); //Lista que guarda los movimientos realizados.
    Instant tiempoInicio; //variable para guardar el tiempo de inicio.
    
    final char VACIA = '0';
    final char PARED = '2';
    final char BOLA = '1';

    /**
     * Metodo constructor.
     * Tipo de tablero:
     * @param t
     * 1 -> Cruz
     * 2 -> Octágono
     * 3 -> Todo bolas
     * 4 -> Cruz pequeña
     * 5 -> Paredes alrededor
     * 
     */
    public MatrizSenku(int t) {
        //'2' = pared, '1' = bola, '0' = vacio
        
        switch (t) {
            case 1:
                //Cruz
                this.matriz = new char[][]{
                    {'2','2','1','1','1','2','2'},
                    {'2','2','1','1','1','2','2'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','0','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'2','2','1','1','1','2','2'},
                    {'2','2','1','1','1','2','2'}
                };  
                break;
            case 2:
                //Octágono
                this.matriz = new char[][]{
                    {'2','2','1','1','1','2','2'},
                    {'2','1','1','1','1','1','2'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','0','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'2','1','1','1','1','1','2'},
                    {'2','2','1','1','1','2','2'}
                };  
                break;
            case 3:
                //Todo bolas.
                this.matriz = new char[][]{
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','0','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','1','1','1','1'},
                    {'1','1','1','1','1','1','1'}
                };  
                break;
            case 4:
                //Cruz pequeña.
                this.matriz = new char[][]{
                    {'2','2','2','2','2','2','2'},
                    {'2','2','1','1','1','2','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','1','1','0','1','1','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','2','1','1','1','2','2'},
                    {'2','2','2','2','2','2','2'}
                };  
                break;
            case 5:
                //Paredes alrededor.
                this.matriz = new char[][]{
                    {'2','2','2','2','2','2','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','1','1','0','1','1','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','1','1','1','1','1','2'},
                    {'2','2','2','2','2','2','2'}
                };  
                break;
            default:
                System.out.println("Error: Selecciona un tablero valido.");
                System.exit(0);
                break;
        }
        
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                if(this.matriz[x][y] == this.BOLA){
                    this.score++;
                }
            }
        }
        System.out.println("Num de bolas iniciales: "+ this.score);
        this.mov_anteriores.clear(); //Limpia la lista
        this.tiempoInicio = Instant.now(); //Coge tiempo de inicio.
        
    }
    
    /**
     * Metodo que comrpueba si el juego se ha ganado, perdido o si continua.
     * @return char
     * 'c' -> continua jugando.
     * 'g' -> ha ganado; 1 bola en tablero.
     * 'p' -> ha perdido; No hay movimientos posibles.
     */
    public char estadoJuego(){ 
        boolean movPosible = false;
        
        //comprueba si aun hay movimientos posibles.
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                if (this.matriz[x][y] == this.BOLA && this.matriz[x+1][y] == this.BOLA && this.matriz[x+2][y] == this.VACIA || 
                    this.matriz[x][y] == this.VACIA && this.matriz[x+1][y] == this.BOLA && this.matriz[x+2][y] == this.BOLA || 
                    this.matriz[x][y] == this.BOLA && this.matriz[x][y+1] == this.BOLA && this.matriz[x][y+2] == this.VACIA || 
                    this.matriz[x][y] == this.VACIA && this.matriz[x][y+1] == this.BOLA && this.matriz[x][y+2] == this.BOLA){
                    
                    movPosible = true; //Hay al menos un movimiento posible.
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
     * Metodo que calcula el tiempo transcurrido desde que se ha iniciado el juego.
     * @return tiempo de juego en segundos
     */
    public long tiempo(){
        long tiempoTranscurrido = Duration.between(tiempoInicio, Instant.now()).getSeconds();
        //System.out.println(tiempoTranscurrido);
        return tiempoTranscurrido;
    }
    
    /**
     * Metodo con el que movemos la ficha. Retorna codigos de error
     * @param xSel Coordenada x seleccionada
     * @param ySel Coordenada y seleccionada
     * @param xDes Coordenada x de destino
     * @param yDes Coordenada y de destino
     * @return char
     * 0 -> Sin errores, se mueve la ficha.
     * 1 -> Error: La casilla seleccionada está vacia.
     * 2 -> Error: La casilla seleccionada/destino está fuera del tablero (Pared).
     * 3 -> Error: La casilla de destino ya está ocupada.
     * 4 -> Error: No puedes mover la ficha a esta posición.
     * 5 -> Error: No hay ninguna ficha en medio.
     * 6 -> Error: La casilla seleccionada/destino está fuera de la matriz.
     */
    public char moverFicha(int xSel, int ySel, int xDes, int yDes){
        try{
            System.out.println("Sel: " + this.matriz[xSel][ySel]);
            System.out.println("Des: " + this.matriz[xDes][yDes]);
            
            if (this.matriz[xSel][ySel] == this.VACIA){
                //Si la casilla seleccionada está vacia
                System.out.println("Error: La casilla seleccionada está vacia.");
                return  '1';
            
            } else if (this.matriz[xSel][ySel] == this.PARED || this.matriz[xDes][yDes] == this.PARED){
                //Si la casilla seleccionada es una pared
                System.out.println("Error: La casilla seleccionada/destino está fuera del tablero.");
                return '2';

            } else if (this.matriz[xDes][yDes] != this.VACIA){
                //Si la casilla de destino no está vacia
                System.out.println("Error: La casilla de destino ya está ocupada.");
                return '3';

            } else {
                if (xSel == xDes){
                    //Si el movimiento es vertical
                    int posicionMedio = (ySel + yDes)/2;

                    if (Math.abs(ySel - yDes) != 2){
                        //si el movimiento no es de dos casillas
                        System.out.println("Error: No puedes mover la ficha a esta posición");
                        return '4';

                    } else if(this.matriz[xSel][posicionMedio] == this.VACIA){
                        //Si la posicion del medio no está vacia
                        System.out.println("Error: No hay ninguna ficha en medio");
                        return '5';

                    } else{
                        //Movemos la ficha
                        this.matriz[xSel][ySel] = this.VACIA;
                        this.matriz[xSel][posicionMedio] = this.VACIA;
                        this.matriz[xDes][yDes] = this.BOLA;
                        
                        //Objeto de la clase Movimiento:
                        Movimiento mov = new Movimiento(xSel, ySel, xDes, yDes, true);
                        
                        //Almacenamos el movimiento en la lista:
                        this.mov_anteriores.add(mov);
                        
                        //Disminuye el numero de bolas
                        this.score--;
                        
                        System.out.println("Ficha movida -> " + score);
                        
                        return '0';

                    }
                } else if (ySel == yDes){
                    //Si el movimiento es horizontal
                    int posicionMedio = (xSel + xDes)/2;

                    if (Math.abs(xSel - xDes) != 2){
                        //Si el movimiento no es de dos casillas
                        System.out.println("Error: No puedes mover la ficha a esta posición.");
                        return '4';

                    } else if(this.matriz[posicionMedio][ySel] == VACIA){
                        //Si la posicion del medio no está vacia
                        System.out.println("Error: No hay ninguna ficha en medio");
                        return '5';

                    } else{
                        //Movemos la ficha
                        this.matriz[xSel][ySel] = this.VACIA;
                        this.matriz[posicionMedio][ySel] = this.VACIA;
                        this.matriz[xDes][yDes] = this.BOLA;
                        
                        //Objeto de la clase Movimiento:
                        Movimiento mov = new Movimiento(xSel, ySel, xDes, yDes, false);
                        
                        //Almacenamos el movimiento en la lista:
                        this.mov_anteriores.add(mov);
                        
                        //Disminuye el numero de bolas
                        this.score--;
                        
                        System.out.println("Ficha movida -> " + this.score);
                       
                        return '0';

                    }
                } else{
                    System.out.println("Error: No puedes mover la ficha a esta posición");
                    return '4';
                }
            }
        } catch(ArrayIndexOutOfBoundsException e){
            //Si la casilla seleccionada/destino está fuera de la matriz.
                System.out.println("Error: La casilla seleccionada/destino no existe.");
                return '6';
        }
    }
    
    /**
     * Metodo que muestra la matriz en consola.
     */
    public void mostrarMatriz(){
        
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                System.out.print(this.matriz[x][y]);
            }
            System.out.println();
        }
    }
    

    /**
     * Metodo que cancela una jugada.
     */
    public void volverJugada(){
        
        if (mov_anteriores.isEmpty()){
            //Si no hay movimientos anteriores.
            System.out.println("No hay jugadas anteriores.");
        } else {
            //ultimo movimiento
            
            Movimiento ultMov = mov_anteriores.get(mov_anteriores.size()-1); 

            int xSel = ultMov.xSel;
            int ySel = ultMov.ySel;
            int xDes = ultMov.xDes;
            int yDes = ultMov.yDes;
            boolean vertical = ultMov.vertical;

            if (vertical) {
                int posicionMedio = (ySel + yDes)/2;
                this.matriz[xSel][posicionMedio] = this.BOLA;

            } else {
                int posicionMedio = (xSel + xDes)/2;
                this.matriz[xSel][posicionMedio] = this.BOLA;

            }
            this.matriz[xSel][ySel] = this.BOLA;
            this.matriz[xDes][yDes] = this.VACIA;
            mov_anteriores.remove(ultMov);
            this.score++;
            System.out.println("Jugada cancelada");
        }    
    }
    
    /**
     * Metodo de prueba que vacia la matriz simulando una victoria.
     */
    public void vaciarMatriz(){ 
        for (int y = 0; y < 7; y++){
            for (int x = 0; x < 7; x++){
                if(this.matriz[x][y] == this.BOLA){
                    this.matriz[x][y] = this.VACIA;
                    this.score--;
                }
            }
        }
        this.matriz[3][3] = this.BOLA;
        this.score++;
    }
}
