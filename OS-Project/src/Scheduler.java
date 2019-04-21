import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	public int days = 0;
	ArrayList<Process> processesArray = new ArrayList<Process>(); // the array of processes
	static Queue<Process> readyQueue = new LinkedList<>(); // the ready queue
	static Process runningProcess; // the currently running process

	public Scheduler() {

	}

	void Ready(Process p) {
		readyQueue.add(p);
	}

	void Running() {
		readyQueue.remove().run();
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		Scheduler s = new Scheduler();
		IOModule io = new IOModule("Graph file CPU.csv");
		IOModule log = new IOModule("Log file.txt");
		// created 4 new processes in my batch system, each with a different period and
		// TTF
		// process parameters: process_id, user_id, timeToFinish, period (every how long
		// in days), memory requirement
		s.processesArray.add(new PayRoll(new PCB(1, 1, 5, 100, 100)));
		s.processesArray.add(new ExperienceYears(new PCB(2, 1, 3, 10, 30)));
		s.processesArray.add(new TuitionFeesIncrease(new PCB(3, 1, 4, 50, 40)));
		s.processesArray.add(new GPACategory(new PCB(4, 1, 2, 20, 20)));
		while (true) {
			try {
				if (s.days % 365 != 0)
					s.days++;
				else
					s.days = 1;
				// loop over processesArrayl processes every day checking if it's time for the
				// process to run
				boolean worked = false; // boolean that checks if a process did indeed work today or not
				for (int i = 0; i < s.processesArray.size(); i++) {
					gui.update(s, false);
					Process p = s.processesArray.get(i);
					if ((s.days % p.pcb.period) == 0) {
						log.write("Day:" + s.days + " ");
						IOModule.print("We are in day " + s.days + " therefore: ");
						int index = Memory.Check(p.pcb.memorySize);
						// check if memory has space for this process to be able to run
						if (index != -1) {
							Memory.Use(p.pcb.memorySize, index);
							// put it in the ready queue
							s.Ready(p);
							// set the variable to the process that's next in like to be ran
							runningProcess = readyQueue.peek();
							// change the process name on the label in GUI to be the current running process
							// name
							GUI.setProcess(runningProcess);
							gui.update(s, true);
							// run the process
							s.Running();
							// unuse the memory blocks once the process has finished executing
							Memory.unUse(p.pcb.memorySize, index);
						} else
							IOModule.print(" No space in memory for this process ");
						if (!worked)
							log.write(" Process " + p.getClass().toString().substring(5) + " worked for "
									+ runningProcess.pcb.ttf + " minutes and used: " + runningProcess.pcb.memorySize
									+ " blocks of memory ");
						else
							log.write(" Process " + p.getClass().toString().substring(5)
									+ " was blocked, then worked for " + runningProcess.pcb.ttf + " minutes and used: "
									+ runningProcess.pcb.memorySize + " blocks of memory ");
						worked = true; // flip the flag as a process did indeed work
					}
				}

				if (worked) {
					io.writeLine("Day " + s.days + ", " + 100);
					log.write("\n");
				} else
					io.writeLine("Day " + s.days + ", " + 0);

				gui.update(s, false);
				TimeUnit.MILLISECONDS.sleep(250); // wait for a second to change the day
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
