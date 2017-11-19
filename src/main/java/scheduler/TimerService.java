package scheduler;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class TimerService {
    @EJB
    private MoviesService moviesService;

    @Schedule(second="59", minute="59",hour="23", persistent=false)
    public void doWork() {
        moviesService.insertIntoDb();
    }
}
