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

        System.out.print(imprimeDirectorioArbol(fichero));
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
         sb.append(pesoArchivo(fichero));
         sb.append(ultimaModificacion(fichero));
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
        sb.append(ultimaModificacion(archivo));
        sb.append("\n");
    }

    private static String sangradoString(int sangrado) {
        return "|  ".repeat(Math.max(0, sangrado));
    }

    private static String pesoArchivo(File archivo) {
        double peso = (double)archivo.length()/1024;
        return "("+ peso + " kb)";
    }

    private static String ultimaModificacion(File archivo) {
        long lm = archivo.lastModified();

        Date d = new Date(lm);
        Calendar c = new GregorianCalendar();
        c.setTime(d);

        String dia, mes, anno, hora, minuto, segundo;

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        anno = Integer.toString(c.get(Calendar.YEAR));
        hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        minuto = Integer.toString(c.get(Calendar.MINUTE));
        segundo = Integer.toString(c.get(Calendar.SECOND));

        return " - "+ hora + ":" + minuto + ":" + segundo + " " + dia + "/" + mes +"/" + anno;
    }
}
