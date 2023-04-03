package escaner;

import java.io.File;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        File fichero;
        System.out.print("Introduce la ruta que quieres revisar: ");
        fichero = new File(teclado.nextLine());

        System.out.print(imprimeDirectorioArbol(fichero));

        teclado.close();
    }


    public static String imprimeDirectorioArbol(File fichero){
        if (!fichero.isDirectory()){
            throw new IllegalArgumentException("El fichero no es un directorio");
        }
        int posicion = 0;
        StringBuilder sb = new StringBuilder();
        imprimeDirectorioArbol(fichero, posicion, sb);
        return sb.toString();
    }

    private static String imprimeDirectorioArbol(File fichero, int posicion, StringBuilder sb) {
         if(!fichero.isDirectory()){
             throw new IllegalArgumentException("El fichero no es un directorio");
         }
         sb.append(posicionString(posicion));
         sb.append("+--");
         sb.append(fichero.getName());
         sb.append("/");
         sb.append("\n");
         for(File archivo : fichero.listFiles()) {
             if(archivo.isDirectory()) {
                 imprimeDirectorioArbol(archivo, posicion + 1, sb);
             } else {
                 imprimirArchivo(archivo, posicion + 1, sb);
             }
         }
    }

    
}
