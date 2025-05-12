package Controller;

import Model.Contatto;
import Model.Model;
import View.ContattoDialog;
import View.contattoFrame;
import View.ContattoListener;
import javax.swing.JOptionPane;
//import io.github.PaoloRiccardoGrasso.View.MainFrm;

public class ContattoController {  //cointrtoller del contatto frame (frame principale con tabella)

    private Model model; // questo è da fare costruttore probabilmente
    private contattoFrame view;
    private ContattoDialog dialog;

    public ContattoController(contattoFrame view, Model model) {

        this.view = view;
        this.model = model;
        importaContattiDalFile();
    }

    //Metodo utile per la creazione di un nuovo contatto con interfaccia grafica
    public void nuovoContatto() {
        dialog = new ContattoDialog(view, true);
        dialog.setTitle("Nuovo Contatto");
        dialog.setLocationRelativeTo(view);
        dialog.addContattoListener(new ContattoListener() {
            @Override
            public void confermaActionPerformed() {
                Contatto c = dialog.getContatto();
                if (c == null) {
                    System.out.println("Nessun contatto fornito.");
                    return;
                }

                System.out.println(model.toString());
                boolean isAggiunto = model.nuovoContatto(c);

                if (isAggiunto) {  //aggiunge il contatto se non esiste
                    JOptionPane.showMessageDialog(dialog, "Contatto aggiunto!", "Contatto", JOptionPane.INFORMATION_MESSAGE);
                    view.aggiungiContattoTabella(c);
                    if (model.size() == 0) {
                        view.getContattiPresLbl().setText("Non è presente nessun contatto");
                    } else {
                        view.getContattiPresLbl().setText("Contatti presenti nella rubrica: " + model.size()+" ");
                    }

                    System.out.println(model.size());
                    System.out.println(model.toString());
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Contatto già esistente!", "Errore", JOptionPane.ERROR_MESSAGE);
                    //comparire finestra di errore che il nome o il cognome 7
                }
            }
        });
        dialog.setVisible(true);

    }

    /**
     * 
     * Modifica il contatto all'interno dell'array list, comunicando attraverso il model.
     * 
     * @return True - Se la modifica è stata effettuata con successo.
     * <br>False - Se la modifica non è stata fatta per errori generali.
     *
     * 
     * 
     */
    public boolean modificaContatto(int indice, Contatto contatto) {
        if (model.modificaContatto(indice, contatto)) {
            return true;

        } else {
            return false;
        }

    }

    
    //Metodo utile per l'eliminazione del contatto attraverso interfacia grafica
    public void eliminaContatto(String nome, String cognome) {
        if (model.eliminaContatto(nome, cognome)) {
            JOptionPane.showMessageDialog(view, "Contatto eliminato con successo", "Avviso", JOptionPane.INFORMATION_MESSAGE);
            view.togliElementi();

            if (model.size() == 0) {
                view.getContattiPresLbl().setText("Non è presente nessun contatto ");
            } else {
                view.getContattiPresLbl().setText("Contatti presenti nella rubrica: " + model.size()+" ");
            }

        } else {

            JOptionPane.showMessageDialog(view, "Contatto non trovato", "Avviso", JOptionPane.WARNING_MESSAGE);

        }
    }

    //Importa i contatti all'interno dell'arraylist attraverso il file di testo.
    public void importaContattiDalFile() {
        // Carica i contatti nel modello
        model.importaContatti();

        // Aggiorna la tabella nella vista con i contatti appena caricati
        for (Contatto contatto : model.getRubrica()) {
            view.aggiungiContattoTabella(contatto);  // Aggiungi il contatto alla tabella della vista
        }

    }

    //Metodo utile per esportare i contatti dall'arraylist (Attraverso il model) nel file.
    public void salvaSuFile() {
        model.esportaContatti();
    }

}
