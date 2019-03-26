import java.util.concurrent.TimeUnit;

public class PayRoll extends Process {

	public PayRoll(PCB p) {
		super(p);
	}

	@Override
	void run() {
		try {

			SystemCall.read("Pay roll is being calculated.....");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			SystemCall.read(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
