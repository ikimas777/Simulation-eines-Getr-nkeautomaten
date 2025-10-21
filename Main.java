import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Getraenkeautomat automat = new Getraenkeautomat();

        automat.fuegeGetraenkHinzu(new Getraenk("Wasser", 1.00, 5));
        automat.fuegeGetraenkHinzu(new Getraenk("Cola", 1.50, 3));
        automat.fuegeGetraenkHinzu(new Getraenk("Kaffee", 2.00, 2));

        while (true) {
            System.out.println("\n=== Getränkeautomat ===");
            System.out.println("Herzlich Willkommen!");
            automat.zeigeGetraenke();

            System.out.print("Wählen Sie ein Getränk (oder 'exit'): ");
            String wahl = sc.nextLine();
            if (wahl.equalsIgnoreCase("exit")) break;

            Getraenk ausgewaehlt = automat.findeGetraenk(wahl);
            if (ausgewaehlt == null) {
                System.out.println("Getränk nicht gefunden!");
                continue;
            }
            if (ausgewaehlt.getBestand() == 0) {
                System.out.println("Leider ausverkauft!");
                continue;
            }

            double preis = ausgewaehlt.getPreis();
            double eingeworfen = 0.0;

            System.out.printf("Sie haben aktuell %.2f € eingeworfen.%n", eingeworfen);
            while (eingeworfen < preis) {
                System.out.printf("Bitte werfen Sie %.2f € ein.%n", preis - eingeworfen);
                System.out.print("Eingabe: ");
                String input = sc.nextLine().replace(",", ".");
                double betrag;
                try {
                    betrag = Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültiger Betrag!");
                    continue;
                }
                eingeworfen += betrag;
                System.out.printf("Sie haben aktuell %.2f € eingeworfen.%n", eingeworfen);
            }

            // Gdy wystarczy pieniędzy
            double wechselgeld = eingeworfen - preis;
            ausgewaehlt.ausgeben();
            if (wechselgeld > 0) {
                System.out.printf("Wechselgeld: %.2f €%n", wechselgeld);
            }
     
           
        }

        sc.close();
        System.out.println("Automat wird beendet. Auf Wiedersehen!");
    }
}
