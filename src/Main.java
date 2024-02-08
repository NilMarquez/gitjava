// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {

    public String[][] client = new String[100][2];
    public static void main(String[] args) {
        //TODO: Main
        AltaClients();

    }

    public static void AltaClients() {



            Scanner scanner = new Scanner(System.in);
        String[][] clients = new String[100][2];
        int totalClients = 0;
            // Introduir dades del client
            System.out.print("Introdueixi el DNI del nou client: ");
            String nouDNI = scanner.next();

            System.out.print("Introdueixi el nom del nou client: ");
            String nouNom = scanner.next();

            // Verificar que el DNI no existeixi prèviament
            boolean dniExiste = false;
            for (int i = 0; i < totalClients; i++) {
                if (nouDNI.equals(clients[i][0])) {
                    dniExiste = true;
                    break;

    }}}



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