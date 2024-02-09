package com.company;

import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);

    public static String[][] clients = new String[100][2];
    public static String[][] mecanics = new String[100][3];
    public static String[][] vehicles = new String[100][3];
    public static String[][] reparacions = new String[100][4];
    public static int numMecanics = 0;
    public static int numVehicles = 0;
    public static int numReparacions = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    public void init() {
        int menuItem = 0;
        do {
            System.out.println("TALLER DE REPARACIÓ DE VEHICLES");
            System.out.println("[1] Donar d’alta nou client");
            System.out.println("[2] Donar d’alta nou mecànic");
            System.out.println("[3] Introduir nou vehicle");
            System.out.println("[4] Crear fitxa de nova reparació");
            System.out.println("[5] Sortir");
            System.out.println("Sel·lecciona una opció: ");

            if (input.hasNextInt()) {
                menuItem = input.nextInt();
                switch (menuItem) {
                    case 1:
                        System.out.println("Has triat donar d’alta nou client....");
                        AltaClients();
                        break;
                    case 2:
                        System.out.println("Has triat donar d’alta nou mecànic....");
                        AltaEmpleats();
                        break;
                    case 3:
                        System.out.println("Has triat introduir nou vehicle....");
                        AltaVehicles();
                        break;
                    case 4:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        NovaReparacions();
                        break;
                    case 5:
                        System.out.println("Has triat actualitzar les dades de les reparacions....");
                        actualitzarReparacions();
                        break;
                    case 6:
                        System.out.println("Sortint....");
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            } else {
                System.out.println("Opció no vàlida");
            }
            input.nextLine();
            System.out.println("");
        } while (menuItem != 6);
    }

    public static void AltaClients() {
        //TODO: AltaClients
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
        Scanner scanner = new Scanner(System.in);

        // Mostrem les matrícules dels cotxes donats d'alta
        mostrarMatriculesCotxes();

        // Demanem a l'usuari que esculli una matrícula
        System.out.print("Escull una matrícula de cotxe: ");
        String matricula = scanner.next();

        // Validem si la matrícula existeix
        if (!existeixMatricula(matricula)) {
            System.out.println("La matrícula no existeix. Insereix primer el cotxe.");
            return;
        }

        // Trobem un mecànic lliure
        String codiMecanic = trobarMecanicLliure();

        // Validem si hi ha mecànics disponibles
        if (codiMecanic == ("")) {
            System.out.println("No hi ha mecànics disponibles. La reparació serà assignada com a 'oberta'.");
        } else {
            System.out.println("La reparació serà assignada com a 'en curs'.");
        }

        // Afegim la nova reparació
        reparacions[numReparacions][0] = matricula; // Assignem la matrícula a la nova reparació
        reparacions[numReparacions][1] = codiMecanic; // Assignem el codi del mecànic a la nova reparació

        // Verifiquem l'estat de la reparació en funció del codi del mecànic
        if (codiMecanic.isEmpty()) {
            // Si el codi del mecànic està buit, l'estat de la reparació és "oberta"
            reparacions[numReparacions][2] = "oberta";
        } else {
            // Si el codi del mecànic no està buit, l'estat de la reparació és "en curs"
            reparacions[numReparacions][2] = "en curs";
        }

        // Incrementem el comptador de reparacions realitzades
        numReparacions++;
    }

    public static void actualitzarReparacions() {
        Scanner scanner = new Scanner(System.in);

        // Mostrem les matrícules dels cotxes amb reparacions en marxa
        mostrarMatriculesReparacionsEnCurs();

        // Demanem a l'usuari que esculli una matrícula
        System.out.print("Escull una matrícula de cotxe amb reparació en marxa: ");
        String matricula = scanner.next();

        // Validem si la matrícula té una reparació en marxa
        int inReparacio = trobarReparacioMatricula(matricula);

        if (inReparacio == -1) {
            System.out.println("No s'ha trobat cap reparació en marxa per a aquesta matrícula.");
            return;
        }

        // Mostrem les opcions d'estat i demanem a l'usuari que esculli una
        System.out.println("Els estats de la reparació són: oberta, en curs, acabada");
        System.out.print("Escull un nou estat per a la reparació: ");
        String nouEstat = scanner.next();

        // Validem l'estat inserit
        if (nouEstat == ("oberta") || nouEstat == ("en curs") || nouEstat == ("acabada")) {
            reparacions[inReparacio][2] = nouEstat;
            System.out.println("Estat de la reparació actualitzat amb èxit.");
        } else {
            System.out.println("Estat no vàlid. No s'ha actualitzat cap dada.");
        }
    }

    //TODO: Modificar
}
/**
 * Aqui se comentarien els mètodes
 * Test primer commit
 * TEST NIL FETE
 */
