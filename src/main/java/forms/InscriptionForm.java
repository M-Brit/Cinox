package forms;

import dao.UtilisateurImpl;
import exceptions.InscriptionException;

import java.util.HashMap;
import java.util.Map;

public class InscriptionForm {

    // Validations.
    public static Map<String, String> validation(String pseudo, String email, String motDePasse, String confirmation) {
        Map<String, String> erreurs = new HashMap<>();

        try {
            validationEmail(email);
        } catch (InscriptionException e) {
            erreurs.put("email", e.getMessage());
        }

        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (InscriptionException e) {
            erreurs.put("motdepasse", e.getMessage());
        }

        try {
            validationPseudo(pseudo);
        } catch (InscriptionException e) {
            erreurs.put("pseudo", e.getMessage());
        }

        return erreurs;
    }

    // Valide l'adresse mail saisie.
    private static void validationEmail(String email) throws InscriptionException {
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new InscriptionException("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new InscriptionException("Merci de saisir une adresse mail.");
        }
    }

    // Valide les mots de passe saisis.
    private static void validationMotsDePasse(String motDePasse, String confirmation) throws InscriptionException {
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new InscriptionException("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new InscriptionException("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new InscriptionException("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    // Valide le pseudo saisi.
    private static void validationPseudo(String nom) throws InscriptionException {
        if (nom != null && nom.trim().length() < 3) {
            throw new InscriptionException("Le pseudo doit contenir au moins 3 caractères.");
        }
        if (!UtilisateurImpl.rechercheUtilisateurs(nom).isEmpty()) {
            throw new InscriptionException("Le pseudo est déjà utilisé.");
        }
    }
}
