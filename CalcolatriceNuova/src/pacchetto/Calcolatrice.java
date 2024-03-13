package pacchetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calcolatrice {
    private JFrame finestra; // La finestra principale della calcolatrice
    private JTextField schermo; // Lo schermo per visualizzare i numeri e i risultati
    private double numero1, numero2; // I numeri utilizzati per le operazioni
    private String operatore; // L'operatore selezionato
    private JPanel pannelloBottoni; // Il pannello contenente i pulsanti numerici e operatori

    public static void main(String[] args) {
        // Metodo principale per avviare l'applicazione
        SwingUtilities.invokeLater(() -> {
            // Creazione di un'istanza della calcolatrice e inizializzazione dell'interfaccia utente
            Calcolatrice calcolatrice = new Calcolatrice();
            calcolatrice.inizializzaInterfaccia();
        });
    }

    public Calcolatrice() {
        // Costruttore della classe Calcolatrice, inizializza la finestra e il pannello dei bottoni
        finestra = new JFrame("Calcolatrice");
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setSize(300, 400);

        schermo = new JTextField(); // Creazione dello schermo
        schermo.setEditable(false); // Lo schermo non è modificabile dall'utente
        schermo.setHorizontalAlignment(JTextField.RIGHT); // Allineamento a destra del testo
        finestra.add(schermo, BorderLayout.NORTH); // Aggiunta dello schermo alla parte superiore della finestra

        pannelloBottoni = new JPanel(); // Creazione del pannello dei bottoni
        pannelloBottoni.setLayout(new GridLayout(4, 4)); // Layout a griglia 4x4 per i bottoni

        // Aggiunta dei bottoni numerici e degli operatori al pannello
        aggiungiBottone("1");
        aggiungiBottone("2");
        aggiungiBottone("3");
        aggiungiBottone("+");

        aggiungiBottone("4");
        aggiungiBottone("5");
        aggiungiBottone("6");
        aggiungiBottone("-");

        aggiungiBottone("7");
        aggiungiBottone("8");
        aggiungiBottone("9");
        aggiungiBottone("*");

        aggiungiBottone("0");
        aggiungiBottone("=");
        aggiungiBottone("/");

        finestra.add(pannelloBottoni, BorderLayout.CENTER); // Aggiunta del pannello dei bottoni al centro della finestra
    }

    private void aggiungiBottone(String etichetta) {
        // Metodo per aggiungere un bottone al pannello dei bottoni
        JButton bottone = new JButton(etichetta); // Creazione del bottone con l'etichetta specificata
        bottone.addActionListener(new GestoreBottone(etichetta)); // Aggiunta di un listener per gestire l'azione del bottone
        pannelloBottoni.add(bottone); // Aggiunta del bottone al pannello dei bottoni
    }

    private void inizializzaInterfaccia() {
        // Metodo per rendere visibile la finestra della calcolatrice
        finestra.setVisible(true);
    }

    private class GestoreBottone implements ActionListener {
        // Classe interna per gestire l'azione dei bottoni
        private String etichettaBottone; // L'etichetta del bottone associato

        public GestoreBottone(String etichetta) {
            // Costruttore della classe GestoreBottone, memorizza l'etichetta del bottone
            this.etichettaBottone = etichetta;
        }

        public void actionPerformed(ActionEvent e) {
            // Metodo chiamato quando viene premuto un bottone
            if (etichettaBottone.matches("[0-9]")) {
                // Se il bottone è un numero, aggiunge il numero allo schermo
                schermo.setText(schermo.getText() + etichettaBottone);
            } else if (etichettaBottone.equals("=")) {
                // Se il bottone è "=", esegue il calcolo
                numero2 = Double.parseDouble(schermo.getText());
                calcolaRisultato();
            } else {
                // Se il bottone è un operatore, memorizza il numero corrente e l'operatore
                numero1 = Double.parseDouble(schermo.getText());
                operatore = etichettaBottone;
                schermo.setText(""); // Cancella lo schermo per l'inserimento del secondo numero
            }
        }

        private void calcolaRisultato() {
            // Metodo per calcolare il risultato dell'operazione
            switch (operatore) {
                case "+":
                    schermo.setText(String.valueOf(numero1 + numero2));
                    break;
                case "-":
                    schermo.setText(String.valueOf(numero1 - numero2));
                    break;
                case "*":
                    schermo.setText(String.valueOf(numero1 * numero2));
                    break;
                case "/":
                    schermo.setText(String.valueOf(numero1 / numero2));
                    break;
            }
        }
    }
}

