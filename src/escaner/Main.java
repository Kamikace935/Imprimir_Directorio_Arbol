package escaner;

import java.io.File;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        File fichero;

        System.out.print("Introduce la ruta que quieres revisar: ");
        fichero = new File(teclado.nextLine());

        teclado.close();
    }

    public static String imprimeDirectorioArbol(File fichero){
        if (!fichero.isDirectory()){
            throw new IllegalArgumentException("El fichero no es un directorio");
        }
        int sangrado = 0;
        StringBuilder sb = new StringBuilder();
        imprimeDirectorioArbol(fichero, sangrado, sb);
        return sb.toString();
    }

    private static void imprimeDirectorioArbol(File fichero, int sangrado, StringBuilder sb) {
         if(!fichero.isDirectory()){
             throw new IllegalArgumentException("El fichero no es un directorio");
         }
         sb.append(sangradoString(sangrado));
         sb.append("+--");
         sb.append(fichero.getName());
         sb.append("/");
         sb.append("\n");
         File[] ficheros = fichero.listFiles();
         if (ficheros != null) {
             for (File archivo : ficheros) {
                 if (archivo.isDirectory()) {
                     imprimeDirectorioArbol(archivo, sangrado + 1, sb);
                 } else {
                     imprimirArchivo(archivo, sangrado + 1, sb);
                 }
             }
         }
    }

    private static void imprimirArchivo(File archivo, int sangrado, StringBuilder sb) {
        sb.append(sangradoString(sangrado));
        sb.append("+--");
        sb.append(archivo.getName());
        sb.append(pesoArchivo(archivo));
        //sb.append("");
        sb.append("\n");
    }

    private static String sangradoString(int sangrado) {
        return "|  ".repeat(Math.max(0, sangrado));
    }

    private static String pesoArchivo(File archivo) {
        double peso = (double)archivo.length()/1024;
        return "("+ peso + " kb)";
    }
    
}
