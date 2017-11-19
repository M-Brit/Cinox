package beans;

/**
 * beans represantant un commentaire
 */
public class Messages {

    private String message;
    private String idUser;
    private String idFilm;
    private String date;

    public Messages() {}

    public Messages (String message, String idUser, String idFilm, String Date ) {
        this.message = message;
        this.idUser = idUser;
        this.idFilm = idFilm;
        this.date = date;
    }

    /*---------------- GETTERS ----------------*/

    public String getMessage() {
        return message;
    }
    public String getIdUser() { return idUser; }
    public String getIdFilm() {
        return idFilm;
    }
    public String getDate() { return date; }


    /*---------------- SETTERS ----------------*/

    public void setMessage(String message) {
        this.message = message;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }
    public void setDate(String date) { this.date = date; }
}
