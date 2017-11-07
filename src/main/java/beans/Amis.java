package beans;

import java.io.Serializable;

public class Amis implements Serializable {
    private int idUser;
    private int idAmi;
    private int status;

    public Amis() {

    }

    public Amis(int idUser, int idAmi, int status) {
        this.idUser = idUser;
        this.idAmi = idAmi;
        this.status = status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
