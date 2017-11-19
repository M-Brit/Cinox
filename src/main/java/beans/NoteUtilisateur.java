package beans;

import java.io.Serializable;

/**
 * Bean representant la note d'un film pariculier par un utilisateur partculier.
 */
public class NoteUtilisateur implements Serializable {
    private int idUser;
    private int idFilm;
    private int note;

    public NoteUtilisateur() {}

    /**
     *
     * @param idUser
     * @param idFilm identifiant du film que l'on note et dont on gère la note moyenne
     * @param note
     */
    public NoteUtilisateur(int idUser, int idFilm, int note) {
        this.idFilm = idFilm;
        this.idUser = idUser;
        this.note = note;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
