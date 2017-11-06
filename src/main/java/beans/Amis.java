package beans;

public class Amis {
    private int idUser;
    private int idAmi;

    public Amis() {

    }

    public Amis(int idUser, int idAmi) {
        idUser = idUser;
        idAmi = idAmi;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAmi() {
        return idAmi;
    }

    public void setIdAmi(int idAmi) {
        this.idAmi = idAmi;
    }
}
