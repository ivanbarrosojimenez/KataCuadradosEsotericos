package solucion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuadradosEsotericosTest {

    /** The environment. */
    CuadradosEsotericos cuadradosEsotericos = new CuadradosEsotericos();

    @Test
    void no_cuando_la_entrada_no_es_valida() {
        // cuadradosEsotericos.imprimirCuadrado();
        Assertions.assertEquals("NO", cuadradosEsotericos.comprobarCuadrado(3, "1 2 3 4 5 6 7 8"));
        Assertions.assertEquals("NO", cuadradosEsotericos.comprobarCuadrado(2, "1 2 3 4 5"));
        Assertions.assertEquals("NO", cuadradosEsotericos.comprobarCuadrado(5, "1 2"));
        Assertions.assertEquals("NO", cuadradosEsotericos.comprobarCuadrado(3, "1 A 2"));
    }

    @Test
    void no_cuando_error_al_validar_constante_magica_impar() {
        Assertions.assertEquals("NO", cuadradosEsotericos.comprobarCuadrado(3, "2 8 1 6 3 5 7 4 9"));
    }

    @Test
    void diabolico_cuando_suma_lo_mismo_columnas_filas_diagonales() {
        Assertions.assertEquals("DIABOLICO", cuadradosEsotericos.comprobarCuadrado(3, "28 21 26 23 25 27 24 29 22"));
    }

    @Test
    void esoterico_cuando_tiene_secuencia_todos_los_numeros_de_n_a_tamanio_e_impar() {
        Assertions.assertEquals("EXOTERICO", cuadradosEsotericos.comprobarCuadrado(3, "4 9 2 3 5 7 8 1 6"));
    }

    @Test
    void esoterico_ConstanteMagica2_impar() {
        Assertions.assertEquals("EXOTERICO",
                cuadradosEsotericos.comprobarCuadrado(7,
                        "22 47 16 41 10 35 4 5 23 48 17 42 11 29 30 6 24 49 18 36 12 13 31 7 25 43 19 37 38 14 32 1 26 44 20 21 39 8 33 2 27 45 46 15 40 9 34 3 28"));
    }

    @Test
    void esoterico_ConstanteMagica2_par() {
        Assertions.assertEquals("EXOTERICO",
                cuadradosEsotericos.comprobarCuadrado(8,
                        "1 63 62 4 5 59 58 8 56 10 11 53 52 14 15 49 48 18 19 45 44 22 23 41 25 39 38 28 29 35 34 32 33 31 30 36 37 27 26 40 24 42 43 21 20 46 47 17 16 50 51 13 12 54 55 9 57 7 6 60 61 3 2 64"));
    }
}
