import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GPACategory extends Process {

	GPACategory(PCB p) {
		super(p);
	}

	public static void GPA() {
		try {
			IOModule io = new IOModule("Students.csv", "Students2.csv");
			String line = io.readLine();
			while (line != null) {
				String[] parts = line.split(", ");
				double x = Double.parseDouble(parts[3]);
				String cat = "";
				if (x >= 0.7 && x <= 1.3)
					cat = "A";
				else if (x > 1.3 && x <= 2.5)
					cat = "B";
				else
					cat = "C";

				io.writeLine(
						parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + parts[4] + ", " + cat);

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

			IOModule.print("GPA category is being calculated ");
			GPA();
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			IOModule.print(" Done! ");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
