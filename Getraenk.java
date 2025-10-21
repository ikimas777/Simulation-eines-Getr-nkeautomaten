public class Getraenk {
    private String name;
    private double preis;
    private int bestand;

    public Getraenk(String name, double preis, int bestand) {
        this.name = name;
        this.preis = preis;
        this.bestand = bestand;
    }

    public String getName() { return name; }
    public double getPreis() { return preis; }
    public int getBestand() { return bestand; }

    public void setBestand(int bestand) { this.bestand = bestand; }

    public void ausgeben() {
        if (bestand > 0) {
            System.out.println("Sie erhalten: " + name);
            bestand--;
        } else {
            System.out.println("Leider ausverkauft");
        }
    }

    public void auffuellen(int menge) {
        bestand += menge;
    }
}
