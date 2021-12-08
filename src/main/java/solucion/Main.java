package solucion;

import java.util.Scanner;

public class Main {
    // https://www.aceptaelreto.com/problem/submission.php?id=565095
    public static void main(String[] args) {
        CuadradosEsotericos cuadradosEsotericos = new CuadradosEsotericos();
        String opcion = "";
        Scanner entrada = new Scanner(System.in);

        while (!(opcion = entrada.nextLine()).equals("0")) {
            System.out.println(cuadradosEsotericos.comprobarCuadrado(Integer.parseInt(opcion), entrada.nextLine()));
        }
    }

}
