import java.util.concurrent.TimeUnit;

public class ExperienceYears extends Process {

	ExperienceYears(PCB p) {
		super(p);
	}

	@Override
	void run() {
		try {

			SystemCall.read("Experience Years is being calculated....");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			SystemCall.read(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
