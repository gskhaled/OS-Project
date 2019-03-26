import java.util.concurrent.TimeUnit;

public class TuitionFeesIncrease extends Process {

	TuitionFeesIncrease(PCB p) {
		super(p);
	}

	void run() {
		try {

			SystemCall.read("Tuition increase is being calculated.....");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			System.out.println(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
