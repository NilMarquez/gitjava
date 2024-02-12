/**
 * Fet per Ivan Vallejo, Nil Màrquez i Manuel Caravaca
 * Link github: https://github.com/NilMarquez/gitjava
 */

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
        /**
         * @param xv és una variable externa que he creat la qual es va sumant per afegir els vehicles nous en una fila nova
         * @param jaexisteix és una variable que he fet la qual comproba si una matrícula que s'introudeix ja existeix o no
         * @param input és simplement un escaner
         * @param opcio serveix per escollir si vols donar d'alta a un nou vehicle sense tenir que tornar al menú principal i donar-li de nou a la opció
         */
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
    private static void NovaReparacions () {
        /**
         * @param seleccion serveix per sel·leccionar una matrícula disponible a través d'un menú
         * @param matricula serveix per buscar una matrícula de la llista de vehicles
         * @param codimecanic serveix per buscar un mecànic de la llista de mecànics
         */
        Scanner scanner = new Scanner(System.in);

        // Comprobamos si hay vehículos dados de alta
        if (vehicles[0][0] == null) {
            System.out.println("No hi ha cotxes donats d'alta. Si us plau, introdueixi primer un vehicle.");
            return;
        }

        // Mostramos las matrículas de los vehículos
        System.out.println("Matrícules dels cotxes donats d'alta:");
        for (int i = 0; i < vehicles.length && vehicles[i][0] != null; i++) {
            System.out.println((i + 1) + ". " + vehicles[i][0]);
        }

        // Pedimos al usuario que seleccione una matrícula
        System.out.print("Selecciona una matrícula per a la nova reparació: ");
        int seleccion = scanner.nextInt() - 1;

        // Validamos la selección
        if (seleccion < 0 || seleccion >= vehicles.length || vehicles[seleccion][0] == null) {
            System.out.println("Selecció incorrecta. Torna a intentar-ho.");
            return;
        }

        String matricula = vehicles[seleccion][0];

        // Buscamos el primer mecánico disponible
        String codiMecanic = null;
        for (int i = 0; i < mecanics.length && mecanics[i][0] != null; i++) {
            if (mecanics[i][2].equals("lliure")) {
                codiMecanic = mecanics[i][0];
                mecanics[i][2] = "ocupat";
                break;
            }
        }

        // Si no hay mecánicos disponibles, la reparación se asigna como "oberta"
        if (codiMecanic == null) {
            codiMecanic = "null";
        }

        // Creamos la nueva ficha de reparación
        for (int i = 0; i < reparacions.length; i++) {
            if (reparacions[i][0] == null) {
                reparacions[i][0] = matricula;
                reparacions[i][1] = codiMecanic;
                reparacions[i][2] = (codiMecanic.equals("null")) ? "oberta" : "en curs";
                System.out.println("Reparació creada amb èxit.");
                break;
            }
        }
    }

    private static void ActualitzarReparacions () {
        /**
         * mètode no finalitzat
         */
        Scanner scanner = new Scanner(System.in);
        // Mostramos las matrículas de los vehículos con reparación en marcha
        System.out.println("Matrícules dels cotxes amb reparació en marxa:");
        for (int i = 0; i < reparacions.length && reparacions[i][0] != null; i++) {
            if (reparacions[i][2].equals("en curs")) {
                System.out.println((i + 1) + ". " + reparacions[i][0]);
            }
        }

        // Pedimos al usuario que seleccione una matrícula
        System.out.print("Selecciona una matrícula per a modificar l'estat de la reparació: ");
        int seleccion = scanner.nextInt() - 1;

        // Validamos la selección
        if (seleccion < 0 || seleccion >= reparacions.length || reparacions[seleccion][0] == null
                || !reparacions[seleccion][2].equals("en curs")) {
            System.out.println("Selecció incorrecta o reparació no en curs. Torna a intentar-ho.");
            return;
        }

        // Mostramos el estado actual de la reparación seleccionada
        System.out.println("Estat actual de la reparació: " + reparacions[seleccion][2]);

        // Pedimos al usuario que ingrese el nuevo estado
        System.out.print("Introdueix el nou estat de la reparació (oberta/en curs/acabada): ");
        String nouEstat = scanner.next();

        // Validamos el nuevo estado
        if (!nouEstat.equals("oberta") && !nouEstat.equals("en curs") && !nouEstat.equals("acabada")) {
            System.out.println("Estat no vàlid. Torna a intentar-ho.");
            return;
        }

        // Modificamos el estado de la reparación
        reparacions[seleccion][2] = nouEstat;
        System.out.println("Estat de la reparació modificat amb èxit.");
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