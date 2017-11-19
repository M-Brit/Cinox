package beans;

public class Event {

    private int idEvent;
    private String movie;
    private String cinema;
    private String adress;
    private String date;
    private String time;
    private int idUser;
    private int status;

    public Event(String movie, String cinema, String adress, String date, String time, int idUser, int status) {
        this.movie = movie;
        this.cinema = cinema;
        this.adress = adress;
        this.date = date;
        this.time = time;
        this.idUser = idUser;
        this.status = status;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int event) {
        //do nothing
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
