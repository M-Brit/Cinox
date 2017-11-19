package beans;

/**
 * Bean representant la note moyenne d'un film particulier .
 */
public class NoteMoyenne {

    private int idFilm;
    private float Note;

    /** Un bean a besoin d'n constructeur vide. **/
    public NoteMoyenne() {}

    /**
     * Permet d'initialiser notre bean represantant la note moyenne d'un film
     * @param idFilm identifiant du film que l'on note et dont on gère la note moyenne
     * @param note note du film permet de calculer la moyenne des notes.
     */
    public NoteMoyenne(int idFilm, float note) {
        this.idFilm = idFilm;
        Note = note;
    }

            /** GETTERS AND SETTERS **/

    public float getNote() {
        return Note;
    }

    public void setNote(float note) {
        Note = note;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }
}
