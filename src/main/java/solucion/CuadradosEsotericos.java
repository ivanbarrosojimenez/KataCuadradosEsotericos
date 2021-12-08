package solucion;

import java.util.Scanner;

public class CuadradosEsotericos {

    private int tamanio;
    private String numeros;
    private int[][] cuadrado;

    private int constanteMagica2 = 0;

    public static void main(String[] args) {
        CuadradosEsotericos cuadradosEsotericos = new CuadradosEsotericos();
        String opcion = "";
        Scanner entrada = new Scanner(System.in);

        while (!(opcion = entrada.nextLine()).equals("0")) {
            System.out.println(cuadradosEsotericos.comprobarCuadrado(Integer.parseInt(opcion), entrada.nextLine()));
        }
    }

    public String comprobarCuadrado(int tamanio, String numeros) {
        this.tamanio = tamanio;
        this.numeros = numeros;
        if (!validarEntrada() || !obtenerConstanteMagicaDiabolico()) {
            return "NO";
        }
        if (obtenerConstanteMagicaExoterico()) {
            return "EXOTERICO";
        }
        return "DIABOLICO";
    }

    private boolean obtenerConstanteMagicaExoterico() {
        return comprobarSerieNumeros() && esquinasYLadosSumanLoMismo();
    }

    private boolean esquinasYLadosSumanLoMismo() {
        int sumaLaterales = -1;
        int sumaEsquinas = cuadrado[0][0] + cuadrado[0][tamanio - 1] + cuadrado[tamanio - 1][0]
                + cuadrado[tamanio - 1][tamanio - 1];

        if (tamanio % 2 != 0) {
            constanteMagica2 = cuadrado[tamanio / 2][tamanio / 2] * 4;
            sumaLaterales = cuadrado[0][tamanio / 2] + cuadrado[tamanio / 2][0] + cuadrado[tamanio - 1][tamanio / 2]
                    + cuadrado[tamanio / 2][tamanio - 1];
        } else {
            constanteMagica2 = cuadrado[tamanio / 2][tamanio / 2] +
                    cuadrado[tamanio / 2 - 1][tamanio / 2 - 1] +
                    cuadrado[tamanio / 2][tamanio / 2 - 1] +
                    cuadrado[tamanio / 2 - 1][tamanio / 2];
            sumaLaterales = (cuadrado[0][tamanio / 2] +
                    cuadrado[tamanio / 2][0] +
                    cuadrado[tamanio - 1][tamanio / 2] +
                    cuadrado[tamanio / 2][tamanio - 1] +
                    cuadrado[0][tamanio / 2 - 1] +
                    cuadrado[tamanio / 2 - 1][0] +
                    cuadrado[tamanio - 1][tamanio / 2 - 1] +
                    cuadrado[tamanio / 2 - 1][tamanio - 1]) / 2;

        }

        return sumaEsquinas == constanteMagica2 && constanteMagica2 == sumaLaterales;
    }

    private boolean comprobarSerieNumeros() {
        try {
            boolean[] numeros = new boolean[tamanio * tamanio];
            for (int posicionNumero = 0, indiceX = 0,
                    indiceY = 0; posicionNumero < numeros.length; posicionNumero++, indiceY++) {
                if (indiceY > tamanio - 1) {
                    indiceX++;
                    indiceY = 0;
                }
                numeros[cuadrado[indiceX][indiceY] - 1] = true;
            }
            return contieneLoMismo(numeros);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarEntrada() {
        return convertirEnCuadrado();

    }

    private boolean convertirEnCuadrado() {
        try {

            cuadrado = new int[tamanio][tamanio];
            String[] sNumeros = numeros.split(" ");
            if (sNumeros.length != tamanio * tamanio) {
                return false;
            }
            int indiceX = 0;
            int indiceY = 0;
            for (int posicionNumero = 0; posicionNumero < sNumeros.length; posicionNumero++, indiceY++) {
                if (indiceY > tamanio - 1) {
                    indiceX++;
                    indiceY = 0;
                }
                cuadrado[indiceX][indiceY] = Integer.parseInt(sNumeros[posicionNumero]);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void imprimirCuadrado() {
        for (int x = 0; x < cuadrado.length; x++) {
            for (int y = 0; y < cuadrado[x].length; y++) {
                System.out.print(cuadrado[x][y] + " ");

            }
            System.out.println();

        }
    }

    public boolean obtenerConstanteMagicaDiabolico() {
        int[] constanteMagicaDiagonales = obtenerSumaDiagonales();
        if (!sumanLoMismo(constanteMagicaDiagonales)) {
            return false;
        }

        int[] constanteMagicaColumnas = obtenerSumaColumnas();

        if (!sumanLoMismo(constanteMagicaColumnas)) {
            return false;
        }

        int[] constanteMagicaFilas = obtenerSumaFilas();
        if (!sumanLoMismo(constanteMagicaFilas)) {
            return false;
        }

        return constanteMagicaFilas[0] == constanteMagicaDiagonales[0]
                && constanteMagicaColumnas[0] == constanteMagicaFilas[0];
    }

    private int[] obtenerSumaDiagonales() {
        int[] sumaDiagonal = new int[2];
        sumaDiagonal[0] = obtenerSumaDiagonal1();
        sumaDiagonal[1] = obtenerSumaDiagonal2();

        return sumaDiagonal;
    }

    private int obtenerSumaDiagonal2() {
        int sumaDiagonal = 0;
        for (int x = cuadrado.length - 1; x >= 0; x--) {
            sumaDiagonal += cuadrado[x][x];
        }
        return sumaDiagonal;
    }

    private int obtenerSumaDiagonal1() {
        int sumaDiagonal = 0;
        for (int x = 0; x < cuadrado.length; x++) {
            sumaDiagonal += cuadrado[x][x];
        }
        return sumaDiagonal;
    }

    private int[] obtenerSumaFilas() {
        int[] sumaFila = new int[tamanio];
        for (int x = 0; x < cuadrado.length; x++) {
            for (int y = 0; y < cuadrado[x].length; y++) {
                sumaFila[x] += cuadrado[y][x];
            }
        }
        return sumaFila;
    }

    private int[] obtenerSumaColumnas() {
        int[] sumaColuma = new int[tamanio];
        for (int x = 0; x < cuadrado.length; x++) {
            for (int y = 0; y < cuadrado[x].length; y++) {
                sumaColuma[x] += cuadrado[x][y];
            }
        }
        return sumaColuma;
    }

    private boolean sumanLoMismo(int[] array) {
        int numeroReferencia = array[0];
        for (int i = 1; i < array.length; i++) {
            if (numeroReferencia != array[i])
                return false;
        }
        return true;
    }

    private boolean contieneLoMismo(boolean[] array) {
        boolean numeroReferencia = true;
        for (int i = 1; i < array.length; i++) {
            if (numeroReferencia != array[i])
                return false;
        }
        return true;
    }
}
