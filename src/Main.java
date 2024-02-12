import java.util.Scanner;

public class Main {

    Scanner input = new Scanner(System.in);

    public static String[][] vehicles = new String[100][3];
    public static String[][] clients = new String[100][4];
    public static int xv = 0;

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
            System.out.println("[5] Sortir");
            System.out.println("Sel·lecciona una opció: ");

            if (input.hasNextInt()){
                menuItem = input.nextInt();
                switch (menuItem){
                    case 1:
                        System.out.println("Has triat donar d’alta nou client....");
                        //insert code here
                        break;
                    case 2:
                        System.out.println("Has triat donar d’alta nou mecànic....");
                        //insert code here
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
                        //insert code here
                        break;
                    case 5:
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

        }while(menuItem!=5);

    }

    //insert code here
    public static void AltaVehicle() {
        //variables
        if (clients[0][3] == null) {
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
    public static void AltaClients() {
    }
}

