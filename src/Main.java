// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {

    public static String[][] personas = new String[100][2]; // Una matriz para nombres y DNIs
    static int totalPersonas = 0;
    public static void main(String[] args) {
        //TODO: Main
        AltaClients();
        for (int i=0; i<personas.length; i++) {
            if (personas[i][0] != null) {
                for (int j = 0; j < personas[i].length; j++) {
                    System.out.print(personas[i][j] + " || ");
                }
                System.out.println();
            }
        }
    }


    public static void AltaClients() {

            agregarPersona(new Scanner(System.in));
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 3; i++) { // Puedes cambiar el número de personas a registrar
                System.out.println("Registro de Persona " + (i + 1));
                agregarPersona(scanner);
            }
        }

        public static void  agregarPersona(Scanner scanner) {
            // Ingresar DNI
            System.out.print("Ingrese el DNI: ");
            String dni = scanner.nextLine();

            // Verificar si el DNI ya existe
            if (buscarDNI(dni)) {
                System.out.println("¡Error! El DNI ya existe.");
                return;
            }

            // Ingresar nombre
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();

            // Verificar que el nombre no esté en blanco
            if (nombre.trim().isEmpty()) {
                System.out.println("¡Error! El nombre no puede estar en blanco.");
                return;
            }

            // Agregar la persona al registro
            personas[totalPersonas][0] = nombre;
            personas[totalPersonas][1] = dni;
            totalPersonas++;

            System.out.println("Persona registrada con éxito.");
        }

        public static boolean buscarDNI(String dni) {
            for (int i = 0; i < totalPersonas; i++) {
                if (personas[i][1].equals(dni)) {
                    return true; // DNI ya existe
                }
            }
            return false; // DNI no existe
        }



    public static void AltaVehicles() {
        //TODO: AltaVehicles
    }

    public static void AltaEmpleats() {
        //TODO: AltaEmpleats
    }

    public static void Reparacions() {
        //TODO: Reparacions
    }

    public static void NovaReparacions() {
        //TODO: NovaReparacions
    }

    //TODO: Modificar
}
/**
 * Aqui se comentarien els mètodes
 * Test primer commit
 */
/*hola manu pruebaaaa*/