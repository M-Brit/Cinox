package forms;

import beans.Amis;
import beans.Utilisateur;
import dao.UtilisateurImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ProfilForm {

    public static JSONArray rechercheAmisJSON(List list) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject json = new JSONObject();
            Amis a = (Amis) list.get(i);
            Utilisateur u = (Utilisateur) UtilisateurImpl.rechercheUtilisateurs(a.getIdAmi()).get(0);
            json.put("id", u.getId());
            json.put("pseudo", u.getPseudo());
            json.put("prenom", u.getPrenom());
            json.put("nom", u.getNom());
            json.put("status", a.getStatus());
            array.put(json);
        }
        return array;
    }
}
