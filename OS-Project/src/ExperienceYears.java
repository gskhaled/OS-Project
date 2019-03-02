import java.util.concurrent.TimeUnit;

public class ExperienceYears extends Process {

	ExperienceYears(PCB p) {
		super(p);
	}

	void run() {
		try {

			System.out.print("Experience Years is being calculated....");
			//added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			//print DONE after
			System.out.println(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
