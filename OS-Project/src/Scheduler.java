import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	int days = 1;
	ArrayList<Process> al = new ArrayList<Process>();
	Queue<Process> readyQueue = new LinkedList<>();

	void Ready(Process p) {
		readyQueue.add(p);
	}

	void Running() {
		readyQueue.remove().run();
	}

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		//created 4 new processes, each with a different period and TTF
		//TTF is the 3rd variable
		s.al.add(new PayRoll(new PCB(1, 1, 1, 10))); 
		s.al.add(new ExperienceYears(new PCB(2, 1, 5, 10)));
		s.al.add(new TuitionFeesIncrease(new PCB(3, 1, 2, 30)));
		s.al.add(new GPACategory(new PCB(4, 1, 10, 60)));
		while (true) {
			try {
				if (s.days % 365 != 0)
					s.days++;
				else
					s.days = 1;

				//loop over all processes every day checking if it's time for the process to run
				for (int i = 0; i < s.al.size(); i++) {
					Process x = s.al.get(i);
					if ((s.days % x.pcb.period) == 0) {
						System.out.print("We are in day " + s.days + " therefore: ");
						s.Ready(x);
						s.Running();
					}
				}
				TimeUnit.SECONDS.sleep(1); // wait for a second to change the day
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
