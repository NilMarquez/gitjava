import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    Scanner input = new Scanner(System.in);
    public static String[][] clients = new String[100][2];
    public static String[][] mecanics = new String[100][3];
    public static String[][] vehicles = new String[100][3];
    public static String[][] reparacions = new String[100][4];
    public static int numMecanics = 0;
    public static int numVehicles = 0;
    public static int numReparacions = 0;
    public static int xv = 0;
    static int totalPersonas = 0;
    public static void main(String[] args) {

        Main main = new Main();
        main.init();

    }



    public void init(){
        int menuItem = 0;
        do {
            System.out.println("TALLER DE REPARACIÓ DE VEHICLES");
            System.out.println("[1] Donar d’alta nou client");
            System.out.println("[2] Donar d’alta nou mecànic");
            System.out.println("[3] Introduir nou vehicle");
            System.out.println("[4] Crear fitxa de nova reparació");
            System.out.println("[5] Actulitzar reparació");
            System.out.println("[6] Sortir");
            System.out.println("Sel·lecciona una opció: ");

            if (input.hasNextInt()){
                menuItem = input.nextInt();
                switch (menuItem){
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
                        //insert code here
                        AltaVehicle();
                        for (int i=0; i<vehicles.length; i++) {
                            if (vehicles[i][0] != null) {
                                for (int j = 0; j < vehicles[i].length; j++) {
                                    System.out.print(vehicles[i][j] + " || ");
                                }
                                System.out.println();
                            }
                        }

                        break;
                    case 4:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        NovaReparacions();
                        break;
                    case 5:
                        System.out.println("Has triat actualitzar reparació....");
                        ActualitzarReparacions();
                        break;
                    case 6:
                        System.out.println("Sortint....");
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            }else{
                System.out.println("Opció no vàlida");
            }
            input.nextLine();
            System.out.println("");

        }while(menuItem!=6);

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
            clients[totalPersonas][0] = nombre;
            clients[totalPersonas][1] = dni;
            totalPersonas++;

            System.out.println("Persona registrada con éxito.");
        }

        public static boolean buscarDNI(String dni) {
            for (int i = 0; i < totalPersonas; i++) {
                if (clients[i][1].equals(dni)) {
                    return true; // DNI ya existe
                }
            }
            return false; // DNI no existe
        }
    //insert code here
    public static void AltaVehicle() {
        //variables
        if (clients[0][1] == null) {
            System.out.println("No hi ha ningún client i per tant no es pot associar un propietari al vehicle.");
            System.out.println("Si us plau, introdueix primer les dades de un client");
            AltaClients();
        } else {
            int opcio = 1;
            Scanner input = new Scanner(System.in);
            while (opcio == 1) {
                boolean jaexisteix;
                //Introduïr les dades
                do {
                    System.out.println("Introdueïx la matrícula del vehicle:");
                    String comprobacio = input.next();
                    jaexisteix = true;

                    //comprobació
                    for (int i = 0; i < vehicles.length; i++) {
                        if (vehicles[i][0] != null && vehicles[i][0].equals(comprobacio)) {
                            jaexisteix = false;
                            System.out.println("ERROR! AQUESTA MATRÍCULA JA EXISTEIX");
                            break;
                        }
                    }
                    if (jaexisteix) {
                        vehicles[xv][0] = comprobacio;
                        break;
                    }
                } while (!jaexisteix);
                System.out.println("Introdueïx el nom de model del vehicle");
                vehicles[xv][1] = input.next();
                System.out.println("Intrueïx el DNI del propietari");
                vehicles[xv][2] = input.next();
                xv++;
                System.out.println("Vols posar un altre vehicle?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opcio = input.nextInt();
            }
        }
    }


    public static void NovaReparacions() {
        Scanner scanner = new Scanner(System.in);

        // Mostrem les matrícules dels cotxes donats d'alta
        mostrarMatriculesCotxes();

        // Demanem a l'usuari que esculli una matrícula
        System.out.print("Escull una matrícula de cotxe: ");
        String matricula = scanner.next();

        // Validem si la matrícula existeix
        if (!matr(matricula)) {
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

    public static void ActualitzarReparacions() {
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

    public static void mostrarMatriculesCotxes() {
        System.out.println("Matrícules dels cotxes donats d'alta:");
        for (int i = 0; i < numVehicles; i++) {
            System.out.println(vehicles[i][0]);
        }
    }

    public static boolean existeixMatricula(String matricula) {
        boolean matriculaEncontrada = false;
        for (int i = 0; i < numVehicles; i++) {
            if (vehicles[i][0] == (matricula)) {
                matriculaEncontrada = true;
                break;
            }
        }
        return matriculaEncontrada;
    }

    public static String trobarMecanicLliure() {
        String mecanicLliure = "";
        for (int i = 0; i < numMecanics; i++) {
            if (mecanics[i][2] == ("lliure")) {
                mecanics[i][2] = "ocupat";
                mecanicLliure = mecanics[i][0];
                break;
            }
        }
        return mecanicLliure;
    }

    public static void mostrarMatriculesReparacionsEnCurs() {
        System.out.println("Matrícules dels cotxes amb reparacions en marxa:");
        for (int i = 0; i < numReparacions; i++) {
            if (reparacions[i][2] == ("en curs")) {
                System.out.println(reparacions[i][0]);
            }
        }
    }

    public static int trobarReparacioMatricula(String matricula) {
        int Repara = -1;
        for (int i = 0; i < numReparacions; i++) {
            if (reparacions[i][0] == (matricula) && reparacions[i][2] == ("en curs")) {
                Repara = i;
                break;
            }
        }
        return Repara;
    }

    public static void AltaEmpleats() {




        Scanner scanner = new Scanner(System.in);

        // Definir el tamaño de la matriz (por ejemplo, 10 trabajadores)
        int numTrabajadores = 10;
        String[][] trabajadores = new String[numTrabajadores][3]; // [nombre, dni, estado]

        // Alta de trabajadores
        for (int i = 0; i < numTrabajadores; i++) {
            System.out.println("Ingrese los datos del trabajador " + (i + 1) + ":");
            trabajadores[i][0] = obtenerInput("Nombre: ");
            trabajadores[i][1] = obtenerInput("DNI: ");
            trabajadores[i][2] = obtenerInput("¿Está trabajando? (Sí/No): ");
        }

        // Mostrar la información de los trabajadores
        System.out.println("\nInformación de los trabajadores:");
        for (int i = 0; i < numTrabajadores; i++) {
            System.out.println("Trabajador " + (i + 1) + ":");
            System.out.println("Nombre: " + trabajadores[i][0]);
            System.out.println("DNI: " + trabajadores[i][1]);
            System.out.println("Estado: " + trabajadores[i][2]);
            System.out.println();
        }
    }

    // Método para obtener la entrada del usuario
    public static String obtenerInput(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextLine();
    }


}


    //TODO: Modificar
/**
 * Aqui se comentarien els mètodes
 * Test primer commit
 * TEST NIL FETE
 */
