/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.LogicaNegocio;

import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Alex T. Rivera
 */
public class AplicarImagen {
   
    public static void ponerImagen(JLabel label, String ruta){
        ImageIcon imagen = new ImageIcon(ruta);        
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), WIDTH));
        label.setIcon(icono);
    }
}


