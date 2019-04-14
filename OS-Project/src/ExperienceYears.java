import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class ExperienceYears extends Process {

	ExperienceYears(PCB p) {
		super(p);
	}

	public static void function() {
		try {
			// initializes "in" to be the Employees original file
			BufferedReader in = new BufferedReader(new FileReader("Employees.csv"));
			// creates a new temporary file called Employees2 that i copy everything to,
			// except the part that i want to change
			File f = new File("Employees2.csv");
			// true means to append the file, not rewrite it
			PrintWriter out = new PrintWriter(new FileWriter(f, true));
			String line = in.readLine();
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
				if (days > 365)
					out.write(parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", "
							+ (Integer.parseInt(parts[4]) + 1));
				else
					out.write(line);
				out.write('\n');
				line = in.readLine();
			}
			in.close();
			out.close();
			// delete the original Employees
			File employees = new File("Employees.csv");
			employees.delete();
			// rename Employees2 to Employees ;)
			f.renameTo(employees);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	void run() {
		try {
			SystemCall.read("Experience Years is being calculated....");
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// call the function of this class
			function();
			// print DONE after
			SystemCall.read(" Done!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
