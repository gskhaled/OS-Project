import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	int days = 1;
	ArrayList<Process> al = new ArrayList<Process>();
	Queue<Process> readyQueue = new LinkedList<>();
	static Queue<Process> blockedQueue = new LinkedList<>();

	void Ready(Process p) {
		// if(blockedQueue.isEmpty())
		readyQueue.add(p);
//		else {
//			readyQueue.add(blockedQueue.remove());
//		}

	}

	void Running() {
		readyQueue.remove().run();
	}

	void enqueueBlocked(Process p) {
		blockedQueue.add(p);
	}

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		// created 4 new processes, each with a different period and TTF
		// TTF is the 3rd variable
		s.al.add(new PayRoll(new PCB(1, 1, 1, 10, 10)));
		s.al.add(new ExperienceYears(new PCB(2, 1, 5, 10, 20)));
		s.al.add(new TuitionFeesIncrease(new PCB(3, 1, 2, 30, 30)));
		s.al.add(new GPACategory(new PCB(4, 1, 10, 60, 40)));
		while (true) {
			try {
				if (s.days % 365 != 0)
					s.days++;
				else
					s.days = 1;

				// loop over all processes every day checking if it's time for the process to
				// run
				for (int i = 0; i < s.al.size(); i++) {
					Process x = s.al.get(i);
					if ((s.days % x.pcb.period) == 0) {
						System.out.print("We are in day " + s.days + " therefore: ");
						if (Memory.Check(x.pcb.memorySize))
							Memory.Use(x.pcb.memorySize);
						else
							s.enqueueBlocked(x);
						s.Ready(x);
						s.Running();
						Memory.unUse(x.pcb.memorySize);
					}
				}
				TimeUnit.SECONDS.sleep(1); // wait for a second to change the day
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
