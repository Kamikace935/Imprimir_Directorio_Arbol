package escaner;

import java.io.File;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        File fichero;
        System.out.print("Introduce la ruta que quieres revisar: ");
        fichero = new File(teclado.nextLine());

        System.out.print(printDirectoryTree(fichero));

        teclado.close();
    }


    public static String printDirectoryTree(File fichero){
        int posicion = 0;

        return "";
    }

    private static String printDirectoryTree(File fichero,int posicion, StringBuilder sb) {
        git 
    }
}
