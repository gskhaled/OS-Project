import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class ExperienceYears extends Process {

	ExperienceYears(PCB p) {
		super(p);
	}

	public static void function() {
		try {
			IOModule io = new IOModule("Employees.csv", "Employees2.csv");
			String line = io.readLine();
			while (line != null) {
				String[] parts = line.split(", ");
				String[] date = parts[2].split("/");
				Integer day = Integer.parseInt(date[0]);
				Integer month = Integer.parseInt(date[1]);
				Integer year = Integer.parseInt(date[2]);
				Date d = new Date(year - 1900, month - 1, day);
				Date dnow = new Date();
				long difference = dnow.getTime() - d.getTime();
				long days = (difference / (60 * 60 * 24 * 1000));
				io.writeLine(parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + days / 365);
				line = io.readLine();
			}
			io.adjustFiles("Employees.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	void run() {
		try {
			IOModule.print("Experience Years is being calculated.... ");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// call the function of this class
			function();
			// print DONE after
			IOModule.print(" Done! ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
