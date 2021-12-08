package solucion;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int dim, num, aux, aux2, aux3, aux4, aux5, cm2;
        boolean diabolical, esoteric;

        do {
            dim = sc.nextInt();

            if (dim != 0) {
                diabolical = true;
                esoteric = true;

                int[][] matrix = new int[dim][dim];
                Set<Integer> nums = new HashSet<>();

                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        matrix[i][j] = sc.nextInt();
                        nums.add(matrix[i][j]);
                    }
                }

                aux = 0;
                for (int i = 0; i < dim; i++) {
                    aux += matrix[0][i];
                }

                aux4 = 0;
                aux5 = 0;
                for (int i = 0; i < dim; i++) {
                    aux4 += matrix[i][i];
                    aux5 += matrix[i][dim - 1 - i];
                    aux2 = 0;
                    aux3 = 0;
                    for (int j = 0; j < dim; j++) {
                        aux2 += matrix[i][j];
                        aux3 += matrix[j][i];
                    }
                    if (aux != aux2 || aux != aux3) {
                        diabolical = false;
                        break;
                    }
                }

                if (!diabolical || aux != aux4 || aux != aux5) {
                    System.out.println("NO");
                } else {
                    cm2 = 4 * aux / dim;
                    if (Collections.max(nums, null) > dim * dim ||
                            nums.size() != dim * dim ||
                            matrix[0][0] + matrix[dim - 1][0] +
                                    matrix[dim - 1][dim - 1] + matrix[0][dim - 1] != cm2) {
                        esoteric = false;
                    }

                    if (esoteric) {
                        if (dim % 2 != 0) {
                            if (matrix[dim / 2][dim / 2] *
                                    4 != cm2 ||
                                    matrix[0][dim / 2] +
                                            matrix[dim / 2][0] +
                                            matrix[dim / 2][dim - 1] +
                                            matrix[dim - 1][dim / 2] != cm2) {
                                esoteric = false;
                            }
                        } else {
                            if (matrix[dim / 2 - 1][dim / 2 - 1] +
                                    matrix[dim / 2 - 1][dim / 2] +
                                    matrix[dim / 2][dim / 2 - 1] +
                                    matrix[dim / 2][dim / 2] != cm2 ||
                                    matrix[0][dim / 2 - 1] +
                                            matrix[0][dim / 2] +
                                            matrix[dim / 2 - 1][0] +
                                            matrix[dim / 2][0] +
                                            matrix[dim / 2 - 1][dim - 1] +
                                            matrix[dim / 2][dim - 1] +
                                            matrix[dim - 1][dim / 2 - 1] +
                                            matrix[dim - 1][dim / 2] != 2 * cm2) {
                                esoteric = false;
                            }
                        }
                    }

                    if (esoteric) {
                        System.out.println("ESOTERICO");
                    } else {
                        System.out.println("DIABOLICO");
                    }
                }
            }
        } while (dim != 0);
    }
}