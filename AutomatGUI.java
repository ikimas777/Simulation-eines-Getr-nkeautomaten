import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AutomatGUI extends JFrame {
    private final Getraenkeautomat automat;
    private JComboBox<String> drinkBox;
    private JTextField geldField;
    private JTextArea ausgabe;

    public AutomatGUI() {
        // Automat + drinks
        automat = new Getraenkeautomat();
        automat.fuegeGetraenkHinzu(new Getraenk("Wasser", 1.00, 5));
        automat.fuegeGetraenkHinzu(new Getraenk("Cola", 1.50, 3));
        automat.fuegeGetraenkHinzu(new Getraenk("Kaffee", 2.00, 2));

        // window
        setTitle("Getränkeautomat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // top field
        JPanel top = new JPanel();
        top.add(new JLabel("Getränk wählen:"));
        drinkBox = new JComboBox<>(new String[]{"Wasser", "Cola", "Kaffee"});
        top.add(drinkBox);
        add(top, BorderLayout.NORTH);

        // middle field
        JPanel middle = new JPanel();
        middle.add(new JLabel("Geld eingeben (€):"));
        geldField = new JTextField(5);
        middle.add(geldField);

        JButton kaufenBtn = new JButton("Kaufen");
        middle.add(kaufenBtn);
        add(middle, BorderLayout.CENTER);

        // lower field
        ausgabe = new JTextArea(6, 30);
        ausgabe.setEditable(false);
        add(new JScrollPane(ausgabe), BorderLayout.SOUTH);

        // react on pressed
        kaufenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gewaehlt = (String) drinkBox.getSelectedItem();
                String geldText = geldField.getText().replace(",", ".");
                try {
                    double betrag = Double.parseDouble(geldText);
                    automat.werfeGeldEin(betrag);
                    ausgabe.append("Eingeworfen: " + betrag + " €\n");
                    ausgabe.append("Versuch Kauf von: " + gewaehlt + "\n");
                    
                    automat.waehleGetraenk(gewaehlt);
                } catch (NumberFormatException ex) {
                    ausgabe.append("Ungültiger Betrag!\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutomatGUI gui = new AutomatGUI();
            gui.setVisible(true);
        });
    }
}
