import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TuitionFeesIncrease extends Process {

	TuitionFeesIncrease(PCB p) {
		super(p);
	}

	public static void tuition() {
		try {
			IOModule io = new IOModule("Students.csv", "Students2.csv");
			String line = io.readLine();
			while (line != null) {
				String[] parts = line.split(", ");
				double fees = Double.parseDouble(parts[4]);
				// increasing current tuition by 10%
				fees = fees * 1.1;
				io.writeLine(
						parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + fees + ", " + parts[5]);
				line = io.readLine();

			}
			io.adjustFiles("Students.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	void run() {
		try {

			IOModule.print("Tuition increase is being calculated..... ");
			tuition();
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			IOModule.print(" Done! ");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
