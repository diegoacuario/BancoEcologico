/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.utilidades;

/**
 *
 * @author KRADAC
 */
public class Principal {
    public static void main(String[] args) {
        Demonio d = new Demonio();
        d.start();
        
        DemonioHistorial dh = new DemonioHistorial();
        dh.start();
    }
}
