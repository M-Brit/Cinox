package beans;

/**
 * beans representant l'utilisateur ->  permet de passer l'utilisateur notament dans la connexion
 */
public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String motDePasse;
    private String email;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String pseudo, String motdepasse, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.motDePasse = motdepasse;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepasse() {
        return motDePasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motDePasse = motdepasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
