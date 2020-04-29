
package practica3;

import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;


public class Practica3 {

    
    public static void main(String[] args) throws IOException {
        String ruta = "";
        ruta = "I:\\Programacion Java\\Practica2Logica3\\Archivo.txt";
        GrafoLLAdyacencia prueba = new GrafoLLAdyacencia(ruta);
        
        System.out.println(prueba.contar(ruta));
                
        
        
    }
    
}
