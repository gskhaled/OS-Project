import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	int days = 1;
	ArrayList<Process> processesArray = new ArrayList<Process>();
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
		// created 4 new processes in my batch system, each with a different period and
		// TTF

		// process parameters: process_id, user_id, timeToFinish, period (every how long
		// in days), memory requirement
		s.processesArray.add(new PayRoll(new PCB(1, 1, 1, 10, 10)));
		s.processesArray.add(new ExperienceYears(new PCB(2, 1, 5, 10, 20)));
		s.processesArray.add(new TuitionFeesIncrease(new PCB(3, 1, 2, 30, 30)));
		s.processesArray.add(new GPACategory(new PCB(4, 1, 10, 60, 40)));
		while (true) {
			try {
				if (s.days % 365 != 0)
					s.days++;
				else
					s.days = 1;
				// loop over processesArrayl processes every day checking if it's time for the
				// process to
				// run
				for (int i = 0; i < s.processesArray.size(); i++) {
					Process p = s.processesArray.get(i);
					if ((s.days % p.pcb.period) == 0) {
						System.out.print("We are in day " + s.days + " therefore: ");
						int index = Memory.Check(p.pcb.memorySize);
						if (index != -1) {
							Memory.Use(p.pcb.memorySize, index);
							s.Ready(p);
							s.Running();
							Memory.unUse(p.pcb.memorySize, index);
						} else
							SystemCall.read("No space in memory for this process");
					}
				}
				TimeUnit.SECONDS.sleep(1); // wait for a second to change the day
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
