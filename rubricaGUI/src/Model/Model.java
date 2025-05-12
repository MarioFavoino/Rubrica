package Model;

import Model.Contatto;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Model {  //contiene i contatti nell'array list

    private ArrayList<Contatto> rubrica = new ArrayList<>();

    public boolean nuovoContatto(Contatto contatto) {

        if (rubrica.contains(contatto)) {
            return false; // Il contatto esiste già, non aggiungere
        }
        rubrica.add(contatto); // Aggiunge il contatto se non esiste
        isModified = true;  // Segna come modificato
        return true;
    }

    public boolean modificaContatto(int indice, Contatto c) {
        // Verifica se esiste già un contatto con lo stesso nome e cognome (escluso il contatto che stiamo modificando)
        for (int i = 0; i < rubrica.size(); i++) {
            Contatto contattoEsistente = rubrica.get(i);

            // Controlla se il contatto esistente ha lo stesso nome e cognome ma non è lo stesso contatto
            if (i != indice && contattoEsistente.getNome().equals(c.getNome()) && contattoEsistente.getCognome().equals(c.getCognome())) {
                System.out.println("Modifica non consentita: contatto con lo stesso nome e cognome esiste già");
                return false;  // Non è possibile modificare il contatto con lo stesso nome e cognome
            }
        }

        // Se il controllo passa, modifica il contatto
        rubrica.set(indice, c);
        isModified = true;
        return true;
    }


    /*public boolean isConfermato(Contatto c) {
    for (int i = 0; i < rubrica.size(); i++) {
        if (rubrica.get(i).getNome().equalsIgnoreCase(c.getNome())) {
            if (rubrica.get(i).getCognome().equalsIgnoreCase(c.getCognome())) { // ERRORE QUI
                return false;
            }
        }
    }
    return true;
}*/
    public boolean eliminaContatto(String nome, String cognome) {
        for (int i = 0; i < rubrica.size(); i++) {
            if (rubrica.get(i).getNome().equalsIgnoreCase(nome)) {
                if (rubrica.get(i).getCognome().equalsIgnoreCase(cognome)) {
                    rubrica.remove(i);
                    isModified = true;  // Segna come modificato
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < rubrica.size(); i++) {
            s += rubrica.get(i).toString() + "\n";
        }

        return s;
    }

    public int size() {
        return rubrica.size();
    }

    //----------------------------------------------SALVATAGGIO SU FILE----------------------------------------------
    private final String fileRubrica = "rubrica.txt";
    private boolean isModified = false;  // Indica se i dati sono stati modificati

    /**
     * Salva un contatto sul file della rubrica.
     *
     * @param contatto Il contatto da salvare.
     * @return true se il salvataggio è avvenuto con successo, false altrimenti.
     */
    public boolean salvaContattoSuFile(Contatto contatto) {
        try {
            FileWriter w = new FileWriter(fileRubrica, true);
            PrintWriter fout = new PrintWriter(w);

            fout.println(contatto.getNome() + ";" + contatto.getCognome() + ";" + contatto.getTelefono() + ";" + contatto.getEmail());
            fout.flush();
            w.close();
            return true;

        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Esporta tutti i contatti della rubrica su file.
     *
     * @return true se l'esportazione è avvenuta con successo, false altrimenti.
     */
    public boolean esportaContatti() {
        try {
            // Scrittura dei contatti su file
            FileWriter w = new FileWriter(fileRubrica, false);
            PrintWriter fout = new PrintWriter(w);

            if (rubrica.isEmpty()) {
                w.close();
            } else {
                // Scrittura dei contatti nel file
                for (int i = 0; i < rubrica.size(); i++) {
                    fout.println(rubrica.get(i).getNome() + ";" + rubrica.get(i).getCognome() + ";" + rubrica.get(i).getTelefono() + ";" + rubrica.get(i).getEmail());
                }
                fout.flush();
                w.close();
            }

            // Dopo il salvataggio, imposta isModified a false
            isModified = false;  // Indica che i dati sono stati salvati

            return true;
        } catch (IOException ex) {
            // Gestione dell'errore
            return false;
        }
    }

    public boolean isModified() {
        return isModified;  // Restituisce se i dati sono stati modificati
    }

    /**
     * Importa i contatti dal file della rubrica nell'ArrayList.
     *
     * @return true se l'importazione è avvenuta con successo, false altrimenti.
     */
    public void importaContatti() {
        try {
            FileReader r = new FileReader(fileRubrica);
            BufferedReader fin = new BufferedReader(r);
            StringTokenizer stringa;
            String riga = fin.readLine();

            while (riga != null) {
                stringa = new StringTokenizer(riga, ";");
                Contatto contatto = new Contatto(
                        stringa.nextToken(), // Nome
                        stringa.nextToken(), // Cognome
                        stringa.nextToken(), // Telefono
                        stringa.nextToken() // Email
                );
                rubrica.add(contatto);  // Aggiungi il contatto alla lista
                riga = fin.readLine();
            }
            fin.close();
        } catch (IOException ex) {
            ex.printStackTrace();  // Per il debug, stampa dello stack trace
        }
    }

    //Metodo per ottenere il riferimento alla rubrica (ArrayList)
    public ArrayList<Contatto> getRubrica() {
        return rubrica;
    }

}
