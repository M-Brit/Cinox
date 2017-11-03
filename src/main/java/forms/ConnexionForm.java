package forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurImpl;
import exceptions.ConnexionException;
import exceptions.InscriptionException;
import org.mindrot.jbcrypt.BCrypt;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_BD = "bd";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();


    public Utilisateur connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        //String email = getValeurChamp(request, CHAMP_EMAIL);
        String pseudo = getValeurChamp(request, CHAMP_PSEUDO);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);

        Utilisateur utilisateur = new Utilisateur();


        /* Validation du champ email. */
       /* try {
            validationEmail(email);
        } catch (ConnexionException e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);*/
        
        /* Validation du champ pseudo. */
         try {
         validationPseudo( pseudo );
         } catch ( ConnexionException e ) {
         setErreur( CHAMP_PSEUDO, e.getMessage() );
         }
         utilisateur.setPseudo( pseudo );
        
        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(motDePasse);
        } catch (ConnexionException e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }
        utilisateur.setMotDePasse(motDePasse);

        try {
            this.testConnexionInBd(pseudo, motDePasse);
        } catch (ConnexionException e) {
            setErreur(CHAMP_BD, e.getMessage());
        }


        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     *
     * @param email email à vérifier pour effectuer la connexion.
     * @throws ConnexionException
     */
    private void validationEmail(String email) throws ConnexionException {
        if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new ConnexionException("Merci de saisir une adresse mail valide.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     *
     * @param motDePasse motDePasse à vérifier pour effectuer la connexion.
     * @throws ConnexionException
     */
    private void validationMotDePasse(String motDePasse) throws ConnexionException {
        if (motDePasse != null && motDePasse.trim().length() != 0) {
            if (motDePasse.trim().length() < 3) {
                throw new ConnexionException("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new ConnexionException("Merci de saisir votre mot de passe.");
        }
    }

    /**
     * Valide le pseudo saisi.
     *
     * @param pseudo pseudo à vérifier pour effectuer la connexion.
     * @throws ConnexionException
     */
    private void validationPseudo(String pseudo) throws ConnexionException {
        if (pseudo != null && pseudo.trim().length() != 0) {
            if (pseudo.trim().length() < 3) {
                throw new ConnexionException("Le pseudo doit contenir au moins 3 caractères.");
            }
        } else {
            throw new ConnexionException("Merci de saisir votre pseudo.");
        }
    }


    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     *
     * @param champ
     * @param message
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }


    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon. -> permet d'éviter les espaces en trop et les valeur null ou vide.
     *
     * @param request
     * @param nomChamp
     * @return
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

    /**
     * Test si l'utilisateur est bien inscrit, pour vaider la connexion
     * @param pseudo
     * @param mdp
     * @return
     * @throws ConnexionException
     */
    private void testConnexionInBd(String pseudo,String mdp) throws ConnexionException{
        List<Utilisateur> users = UtilisateurImpl.rechercheUtilisateurs(pseudo);
        if (users.isEmpty()) {
            throw new ConnexionException("Le pseudo est déjà utilisé.");
        }
        else{
            if(!BCrypt.checkpw(mdp, users.get(0).getMotdepasse())) {
                throw new ConnexionException("Le mot de passe n'est pas le bon.");
            }
        }
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
}