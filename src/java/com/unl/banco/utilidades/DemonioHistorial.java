/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unl.banco.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KRADAC
 */
public class DemonioHistorial extends Thread{

    @Override
    public void run() {
        while (true) { 
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bvecologica", "bvecologicarest", "bvecologicarest");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("call demonio_historial()");
                conexion.close();
                sleep(172800000);
            } catch (SQLException ex) {
                Logger.getLogger(DemonioHistorial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemonioHistorial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
