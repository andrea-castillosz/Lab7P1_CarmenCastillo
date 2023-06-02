/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_carmencastillo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author casti
 */
public class Lab7P1_CarmenCastillo {

    static Scanner leer = new Scanner(System.in);
    static Random rand = new Random();
    static int score1 = 0;
    static int score2 = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean seguirmenu = true;
        while (seguirmenu) {
            System.out.println(" ");
            System.out.println("Menu");
            System.out.println("1. She shoot, she scores!");
            System.out.println("2. Pierda, papel o ...");
            System.out.println("3. Salir.");
            System.out.println("Ingrese su opcion: ");
            int opcionmenu = leer.nextInt();

            switch (opcionmenu) {
                case 1:
                    System.out.print("Ingrese la cantidad de la filas: ");
                    int filas = leer.nextInt();
                    System.out.print("Ingrese una cantidad de columnas: ");
                    int columnas = leer.nextInt();
                    int[][] num = new int[filas][columnas];
                    boolean valid = true;
                    if (filas * columnas >= 88) {
                        System.out.println("La cantidad de casillas debe ser menor a 88.");
                        valid = false;
                    } else {
                        valid = true;
                        //ingresar juego
                        //System.out.println("---Tabla para Jugar---");
                        //num = lectura(filas, columnas);
                        //printmatriz1(num);

                        System.out.println("Ingrese la cantidad de balas: ");
                        int balas1 = leer.nextInt();
                        while (balas1 >= (filas * columnas) / 2) {
                            System.out.println("Numero de balas invalido. Reingrese su cantidad.");
                            balas1 = leer.nextInt();
                        }
                        int balas2 = balas1;
                        
                        System.out.println("---Tabla para Jugar---");
                        num = lectura(filas, columnas);
                        while (balas2 > 0 && balas1 > 0) {

                            printmatriz1(num);
                            System.out.println("Elige que numero disparar Jugador 1!");
                            int tiro1 = leer.nextInt();
                            tactualizar(num, tiro1, 1);
                            balas1--;
                            System.out.println("Le quedan: " + balas1);

                            System.out.println("Elige que numero disparar Jugador 2!");
                            int tiro2 = leer.nextInt();
                            tactualizar(num, tiro2, 2);
                            balas2--;
                            System.out.println("Le quedan: " + balas2);

                        }

                        if (score1 > score2) {
                            System.out.println("Jugador 1 gana!");
                        } else if (score1 < score2) {
                            System.out.println("Jugador 2 gana!");
                        } else if (score1 == score2) {
                            System.out.println("Es un empate!");
                        }

                        break;
                    }

                case 2:
                    int [][] matriz = new int [][] {{0,1,0,1,0}, {0,0,1,1,0}, {1,0,0,0,1}, {0,0,1,0,1}, {1,1,0,0,0}};
                    System.out.println("Elija que quiere usar [1. Piedra/2. Tijera/3. Papel/4. Lizard/5. Spock]: ");
                    int opcion = leer.nextInt();
                    opcion--;
                    int value = rand.nextInt(6-1) + 1;
                    value--;
                    System.out.println("La maquina eligio: " + value);
                    int respuesta = matriz[opcion][value];
                    if (respuesta == 1) {
                        System.out.println("¡Jugador Gana!");
                    }else if (respuesta == 0){
                        System.out.println("¡Maquina Gana!");
                    }else{
                        System.out.println("¡Empate!");
                    }

                    break;

                default:
                    seguirmenu = false;

            }

        }

    }

    public static int[][] lectura(int fila, int colum) {
        int[][] temporal = new int[fila][colum];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < colum; j++) {
                temporal[i][j] = rand.nextInt(fila * colum) + 1;
                while (repeticion(temporal[i][j], temporal, i, j)) {
                    temporal[i][j] = rand.nextInt(fila * colum) + 1;
                }
            }//fin de columnas
        }//fin de las filas

        return temporal;

    }

    public static void printmatriz1(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "   ");
            }
            System.out.println(" ");
        }

    }

    public static boolean repeticion(int valor, int[][] matriz, int ii, int jj) {
        boolean valido = false;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i != ii || j != jj) {
                    if (matriz[i][j] == valor) {
                        valido = true;
                    }
                }

            }
        }
        return valido;
    }

    public static int[][] tactualizar(int[][] matriz, int numero, int jugador) {
        boolean miss = true;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == numero) {
                    if (jugador == 1) {
                        matriz[i][j] = 88;
                    } else {
                        matriz[i][j] = 99;
                    }
                    miss = true;
                    break;
                } else {

                    miss = false;
                }
            }
            if (miss) {
                break;
            }
        }
        if (miss) {
            System.out.println("Tiro acertado!");
        } else {
            System.out.println("Tiro fallido :(");
        }
        if(jugador==1 && miss){
            score1+=numero;
        }else if(jugador==2 && miss){
            score2+=numero;
        }
        return matriz;
    }
    
    

}
