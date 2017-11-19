package scheduler;

import javax.ejb.Stateless;

@Stateless
public class MoviesService {

    public void insertIntoDb() {
        System.out.println(System.currentTimeMillis() + " : Insertion into data base");
        //TODO insertion
    }
}
