/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejosDeDatoExternos;

import java.text.*;

public class Herramientas {

    public String recortarNumero(double numeroLargo) {

        if (!String.valueOf(numeroLargo).isEmpty()) {
            DecimalFormat df = new DecimalFormat("#.######");
            return df.format(numeroLargo);
        }
        else{
            System.out.println("no puedes mandar valores vacios.");
            return "0";
        }
    }
}
