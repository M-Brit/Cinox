package forms;

import beans.NoteMoyenne;
import beans.NoteUtilisateur;
import dao.NoteMoyenneImpl;
import dao.NoteUtilisateurImpl;

import java.util.List;

public class NotationForm {
    /**
     * Permet d'obtenir une note en fonction d'un utilisateur et du film
     * @param idUser idUser permet de recuperer une note en fonction d'un utilisateur
     * @param idFilm idFilm permet de recuperer une note en fonction d'un film
     * @return
     */
    public static int getNote(int idUser, int idFilm) {
        List l = NoteUtilisateurImpl.getNote(idUser, idFilm);
        if (!l.isEmpty()) {
            return ((NoteUtilisateur)l.get(0)).getNote();
        }
        return 0;
    }

    /**
     *  Permet d'ajouter une note a un film pour un utilisateur donnée
     * @param idUser identifiant utilisateur
     * @param idFilm identifiant du film
     * @param note note du film
     */
    public static void addNote(int idUser, int idFilm, int note) {
        NoteUtilisateurImpl.addNote(idUser, idFilm, note);
    }

    /**
     * permet de recuperer la note moyenne d'un film
     * @param idFilm identifiant du film
     * @return
     */
    public static float getMoyenne(int idFilm) {
        return NoteMoyenneImpl.getMoyenne(idFilm);
    }

    /**
     * calcul la moyenne de toutes les notes donné par divers utilisateurs
     * @param idFilm identifiant du film
     */
    public static void calculMoyenne(int idFilm) {
        List l = NoteUtilisateurImpl.calculMoyenne(idFilm);
        float moyenne = 0;
        int i = 0;
        if (!l.isEmpty()) {
            for (i = 0; i < l.size(); i++) {
                moyenne += ((NoteUtilisateur)l.get(i)).getNote();
            }
        }
        moyenne /= i;
        NoteMoyenneImpl.addMoyenne(idFilm, moyenne);
    }

}
