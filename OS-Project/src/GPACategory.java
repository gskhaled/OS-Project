import java.util.concurrent.TimeUnit;

public class GPACategory extends Process {

	GPACategory(PCB p) {
		super(p);
	}

	void run() {
		try {

			SystemCall.read("GPA category is being calculated");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			System.out.println(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
