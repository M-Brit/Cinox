package beans;

public class NoteMoyenne {
    private int idFilm;
    private float Note;

    public NoteMoyenne() {

    }

    public NoteMoyenne(int idFilm, float note) {
        this.idFilm = idFilm;
        Note = note;
    }

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
