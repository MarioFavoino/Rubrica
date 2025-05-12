package Model;

import java.util.Objects;

public class Contatto {

    private String nome, cognome, email, telefono;

    public Contatto(String nome, String cognome, String numeroTelefono, String email) {
        this.nome = nome;
        this.cognome = cognome;
        telefono = numeroTelefono;
        if (email.equals("") || email.equals("null")) {
            this.email = "N/D";
        } else {
            this.email = email;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Calcola l'hash code del contatto, basato su nome e cognome.
     *
     * @return Hash code del contatto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cognome);
        return hash;
    }

    /**
     * Confronta questo contatto con un altro oggetto.
     * <p>
     * Due contatti sono considerati uguali se hanno lo stesso nome e cognome.
     * </p>
     *
     * @param obj Oggetto da confrontare.
     * @return true se i contatti sono uguali, false altrimenti.
     */
   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contatto other = (Contatto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.cognome, other.cognome);
    }

    @Override
    public String toString() {
        return "Contatto{" + "nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", email=" + email + '}';
    }

    public Object[] toArray() {

        Object[] a = new Object[]{
            nome,
            cognome,
            telefono,
            email

        };
        return a;
    }

    
    
}
