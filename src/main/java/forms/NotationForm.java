package forms;

import beans.NoteMoyenne;
import beans.NoteUtilisateur;
import dao.NoteMoyenneImpl;
import dao.NoteUtilisateurImpl;

import java.util.List;

public class NotationForm {
    public static int getNote(int idUser, int idFilm) {
        List l = NoteUtilisateurImpl.getNote(idUser, idFilm);
        if (!l.isEmpty()) {
            return ((NoteUtilisateur)l.get(0)).getNote();
        }
        return 0;
    }

    public static void addNote(int idUser, int idFilm, int note) {
        NoteUtilisateurImpl.addNote(idUser, idFilm, note);
    }

    public static float getMoyenne(int idFilm) {
        return NoteMoyenneImpl.getMoyenne(idFilm);
    }

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
