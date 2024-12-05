import java.security.SecureRandom;

public class Sudoku {

    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) {
        crearTableroVacio();
        if (generaSudoku(0, 0)) {
            mostrarSudoku();
        } else {
            System.out.println("No se pudo generar el Sudoku.");
        }
    }

    public static boolean validacionNumero(int fila, int columna, int numero) {
        // Validar fila
        for (int j = 0; j < 9; j++) {
            if (sudoku[fila][j] == numero) {
                return false;
            }
        }

        // Validar columna
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][columna] == numero) {
                return false;
            }
        }

        // Validar cuadro 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[inicioFila + i][inicioColumna + j] == numero) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean generaSudoku(int fila, int columna) {
        if (fila == 9) {
            return true;
        }

        // Calcular la siguiente posición
        int siguienteFila = (columna == 8) ? fila + 1 : fila;
        int siguienteColumna = (columna == 8) ? 0 : columna + 1;

        if (sudoku[fila][columna] != 0) {
            return generaSudoku(siguienteFila, siguienteColumna);
        }

        SecureRandom random = new SecureRandom();
        for (int numero = 1; numero <= 9; numero++) {
            int aleatorio = random.nextInt(9) + 1;

            if (validacionNumero(fila, columna, aleatorio)) {
                sudoku[fila][columna] = aleatorio;

                if (generaSudoku(siguienteFila, siguienteColumna)) {
                    return true;
                }

                sudoku[fila][columna] = 0; // Backtracking
            }
        }

        return false;
    }

    public static void mostrarSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void crearTableroVacio() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
            }
        }
    }
}
