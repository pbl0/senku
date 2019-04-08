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
public class Movimiento {
    //Propiedades Movimiento:
    public int xSel;
    public int ySel;
    public int xDes;
    public int yDes;
    public boolean vertical;
    
    /**
     * Metodo Constructor
     * @param xSel
     * @param ySel
     * @param xDes
     * @param yDes
     * @param vertical
     */
    public Movimiento(int xSel, int ySel, int xDes, int yDes, boolean vertical){
        this.xSel = xSel;
        this.ySel = ySel;
        this.xDes = xDes;
        this.yDes = yDes;
        this.vertical = vertical;     
    }    
}

