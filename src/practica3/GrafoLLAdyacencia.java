/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Esneider
 */
public class GrafoLLAdyacencia {

    private final LinkedList<Nodo> nodosCabeza;
    private int a;
    static int costos [][];
    static String ruta;

    public GrafoLLAdyacencia(String ruta) throws IOException {
        nodosCabeza = new LinkedList<>();
        this.ruta = ruta; //-> Se agrega la ruta para qeu permanesca en un sttic desde el metodo
        a = contar(ruta);//->Nos tre el numero de posiciones diferentes
        costos = new int [a][a];//->se crea el vector de costos
    }

    //Metodo para insertar, aún no probado. Rezen para que funcione o se los va a llevar los negros del ataúd xD
    public boolean Insertar(Nodo partida, Nodo llegada) {

        boolean agregar = false;

        if (!nodosCabeza.isEmpty() && nodosCabeza.contains(partida)) {

            Nodo recorrido = nodosCabeza.get(nodosCabeza.indexOf(partida));
            recorrido = recorrido.getLiga();

            do {

                if (recorrido != llegada) {

                    if (recorrido.getLiga() != null) {
                        recorrido = recorrido.getLiga();
                    }

                } else {
                    return agregar;
                }

            } while (recorrido.getLiga() != null);

            recorrido.setLiga(llegada);

        } else {

            nodosCabeza.addLast(partida);
            partida.setLiga(llegada);

        }
        return agregar;
    }

//     if ( !nodosCabeza.isEmpty() && nodosCabeza.contains(partida)) {
//
//            Nodo recorrido = nodosCabeza.get(nodosCabeza.indexOf(partida));
//            
//            while (recorrido.getLiga() != null) {
//                recorrido = recorrido.getLiga();
//                if(recorrido!=llegada){
//                   recorrido = recorrido.getLiga();
//                }
//            }
//            
//            recorrido.setLiga(llegada);
//
//        }
    
    //Pase el metodo de leerArchivo para acá para poder usar el metodo insertar sin crear objetos. 
    //si se puede de otra manera sin necesidad de traerlo acá pueden hacerlo
    public void leerArchivo() throws FileNotFoundException, IOException {

        String text1;
        String text2;

        FileReader archivo = new FileReader(ruta);

        BufferedReader bufferArchivo = new BufferedReader(archivo);

        /*
        Esta parte sirve para leer el numero de lineas del archivo
        la idea es pensar si se puede usar para la matriz de costos o no
         */
        int nLineas = (int) bufferArchivo.lines().count();
        
        

        text1 = bufferArchivo.readLine();

        StringTokenizer tokenizadorDePalabras = new StringTokenizer(text1, ":");

        int cont = 0;
        Nodo partida = null;
        Nodo llegada = null;
        int cost;

        while (tokenizadorDePalabras.hasMoreTokens()) {

            text2 = tokenizadorDePalabras.nextToken();

            if (cont == 0) {
                partida = new Nodo(text2);
            }
            //gaurda el costo, aun no usado. Falta crear la matriz de costos
            if (cont == 1) {
                cost = Integer.parseInt(text2);
            }
            if (cont == 2) {
                llegada = new Nodo(text2);
            }
            cont++;

            if (!tokenizadorDePalabras.hasMoreTokens()) {
                text1 = bufferArchivo.readLine();
                cont = 0;
                Insertar(partida, llegada);

                if (text1 != null) {
                    tokenizadorDePalabras = new StringTokenizer(text1, ":");
                    cont = 0;
                }
            }

        }
        
        

    }
    
    public int contar(String ruta)throws FileNotFoundException, IOException{
        
        
        int cantidad = 0;
        String text1;
        String text2;

        // Cargamos el buffer con el contenido del archivo
        FileReader archivo = new FileReader(ruta);
        
        //pasamos el archivo buffer al bufferReader
        BufferedReader bufferArchivo = new BufferedReader(archivo);
        
        //Se crea un contenedor para el maximo de posibles ubicaciones
        //int nLineas = (int) bufferArchivo.lines().count();
        //String contenedor [] = new String[nLineas];
        LinkedList contenedor = new LinkedList();

        // Aqui se lee la primera linea del archivo, si se quiere leer otra linea se copia
        // y pega el mismo codigo debajo(supongo que se puede hacer con un For)
        text1 = bufferArchivo.readLine();

        //Recibe un String en este caso text1 y el delimitador o separador de palabras que es la coma "," 
        StringTokenizer tokenizadorDePalabras = new StringTokenizer(text1, ":");
        int cont = 0;
        String dato = "";
        
        // Ciclo para extraer las palabras de la linea separadas por ","
        while (tokenizadorDePalabras.hasMoreTokens()) {
            
            //lleva la palabra o token a la variable text2
            text2 = tokenizadorDePalabras.nextToken();
            
            //se separan la id de la perona, el nombre de la persona y el padre
            if(cont == 0){
                if(!contenedor.equals(text2)){
                    contenedor.add(text2);
                    cantidad++;
                }
                    }
            
            if(cont == 2){
              if(!contenedor.equals(text2)){
                    contenedor.add(text2);
                    cantidad++;
                }    
            }
            
            cont ++;
            
                //verifica si el tokenizador tiene palabras, de ser asi manda  la informacion en las variables 
                //y añade  las personas a el arbol 
            if (!tokenizadorDePalabras.hasMoreTokens()) {
            text1 = bufferArchivo.readLine();
            cont=0;
             //separa el texto  
                if (text1 != null) {
                    tokenizadorDePalabras = new StringTokenizer(text1, ":");
                    cont=0;
                }
            }
        
        }
        
        return cantidad;
    }
        
            
}


