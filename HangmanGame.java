import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que recrea el juego Hangman
 * @author: Iker-hub
 */
public class HangmanGame {

    /**
     * Método principal de la clase
     * @param args
     */
    public static void main(String[] args) {

        // Array de palabras ha adivinar
        String[] palabras = {"PAN", "MESA", "LAPIZ", "PELOTA", "MURCIELAGO"};

        // Obtenemos una palabra aleatoriamente
        Random r = new Random();
        String palabra = palabras[r.nextInt(5)];

        // Comenzamos el juego
        juego(palabra);
    }

    /**
     * Método para la partida
     * @param palabra String palabra a adivinar
     */
    public static void juego(String palabra) {

        // Inicializamos el tablero para la palabra
        String tablero = "";

        // Contamos el número de letras de la palabra y dibujamos el tablero
        for (int i = 0; i < palabra.length(); i++) {
            tablero = tablero + "_  ";
            System.out.print("_  ");
        }

        System.out.println("\n");

        // Inicializamos el número de vidas, un ArrayList para las letras probradas y otro 
        // que contenga las posibles letras
        int vidas = 8;
        ArrayList letrasProbadas = new ArrayList<>();
        ArrayList letras = getLetras();

        // Mostramos el número de vidas iniciales
        System.out.println("\nNº de vidas: " + vidas);

        // Pedimos en un blucle cadenas hasta que la palabra se adivine o el número de vidas llegue a 0
        try (Scanner scanner = new Scanner(System.in)) {
			while (vidas > 0) {

			    String letraProbada = scanner.nextLine().toUpperCase();

                // Controlamos que sólo se pueda introducir una letra
                if (letrasProbadas.contains(letraProbada)) {
                    System.out.println("\nLetra ya probada\n");
                } else if (!letras.contains(letraProbada.toUpperCase())) {
                    System.out.println("\nSólo puedes introducir una letra\n");
                } else {

                    // En caso de que la cadena sea válida la guardaremos en el ArrayList letrasProbadas
                    letrasProbadas.add(letraProbada);

                    // Utilizamos un boolean para comprobar si la letra está contenida en la palabra
                    boolean letraIncluida = false;

                    // Recorremos la palabra y comprobamos si la letra introducida está contenida
                    for (int i = 0; i < palabra.length(); i++) {
                        
                        // Si la letra está contenida es colocada en el tablero en todas las posiciones que aparezca
                        if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase(letraProbada)) {

                            StringBuilder stringBuilder = new StringBuilder(tablero);
                            
                            stringBuilder.setCharAt(i*3, letraProbada.charAt(0));
                            tablero = String.valueOf(stringBuilder);

                            letraIncluida = true;

                        }
                        
                        // Al terminar de recorre la palabra, si la variable letraIncluida se mantiene 
                        // en false, restaremos un vida
                        if(i == palabra.length() - 1 && !letraIncluida) {
                            vidas--;
                        }

                    }

                    // Mostramos el estado del tablero y el número de vidas que quedan
                    System.out.println("\n" + tablero);
                    System.out.println("\nNº de vidas: " + vidas);

                    // Mostramos las letras que han sido probadas
                    System.out.print("\nLetras probadas: ");
                    for (int i = 0; i < letrasProbadas.size(); i++) {
                        System.out.print(letrasProbadas.get(i) + "  ");
                    }

                    System.out.println("\n");

                    // Si el tablero contiene solo letras, la palabra habrá sido adivinada
                    if (!tablero.contains("_  ")) {
                        System.out.println("¡Felicidades has acertado la palabra! Solución: " + palabra);
			System.exit(0);
                    }

                    // Si el número de vidas llega 0, la palabra no habrá sido adivinada
                    if (vidas == 0) {
                        System.out.println("Lo sentimos, no has acertado la palabra. Solución: " + palabra);
                    }

                }

			}
		}
    }

    /**
     * Método que devuelve un ArrayList con todas las letras posibles que contendrá la palabra a adivinar
     * @return ArrayList con todas las letras posibles que contendrá la palabra a adivinar
     */
    public static ArrayList getLetras() {

        ArrayList letras = new ArrayList<>();

        String[] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
                                    "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (int i = 0; i < abecedario.length; i++) {
            letras.add(abecedario[i]);
        }

        return letras;
    }

}
