package beans;

import java.io.Serializable;

/**
 * Class represantant les amis
 * un amis est un utilisateur il possède donc un identifiant d'utilisateur, un identifiant amis et un statu
 */
public class Amis implements Serializable {
    private int idUser;
    private int idAmi;
    private int status;

    public Amis() {

    }

    /**
     * Permet de cree l'instance d'un bean represantant un ami.
     * @param idUser identifiant utilisateur
     * @param idAmi identifiant amis
     * @param status TODO
     */
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
