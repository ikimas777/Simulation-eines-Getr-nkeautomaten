import java.util.ArrayList;

public class Getraenkeautomat {
    private ArrayList<Getraenk> getraenke;
    private double kontostand;

    public Getraenkeautomat() {
        getraenke = new ArrayList<>();
        kontostand = 0.0;
    }

    public void fuegeGetraenkHinzu(Getraenk g) {
        getraenke.add(g);
    }

    public void zeigeGetraenke() {
        System.out.println("Verfügbare Getränke:");
        for (Getraenk g : getraenke) {
            String status = g.getBestand() == 0 ? "(ausverkauft)" : "";
            System.out.println("- " + g.getName() + " (" + g.getPreis() + " €) " + status);
        }
    }

    public void werfeGeldEin(double betrag) {
        kontostand += betrag;
        System.out.printf("Sie haben aktuell %.2f € eingeworfen.%n", kontostand);
    }

    public void waehleGetraenk(String name) {
        for (Getraenk g : getraenke) {
            if (g.getName().equalsIgnoreCase(name)) {
                if (g.getBestand() == 0) {
                    System.out.println("Leider ausverkauft");
                    return;
                }
                if (kontostand < g.getPreis()) {
                    System.out.printf("Bitte werfen Sie %.2f € ein.%n", g.getPreis() - kontostand);
                    return;
                }
                g.ausgeben();
                kontostand -= g.getPreis();
                gibWechselgeldAus();
                return;
            }
        }
        System.out.println("Getränk nicht gefunden!");
    }

    public void gibWechselgeldAus() {
        if (kontostand > 0) {
            System.out.printf("Wechselgeld: %.2f €%n", kontostand);
            kontostand = 0;
        }
    }
    public Getraenk findeGetraenk(String name) {
    for (Getraenk g : getraenke) {
        if (g.getName().equalsIgnoreCase(name)) {
            return g;
        }
    }
    return null;
}
}


