import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class TuitionFeesIncrease extends Process {

	TuitionFeesIncrease(PCB p) {
		super(p);
	}

	public static void tuition() {
		try {
			// initializes "in" to be the Employees original file
			BufferedReader in = new BufferedReader(new FileReader("Students.csv"));
			// creates a new temporary file called Students2 that i copy everything to,
			// except the part that i want to change
			File f = new File("Students2.csv");
			// true means to append the file, not rewrite it
			PrintWriter out = new PrintWriter(new FileWriter(f, true));
			String line = in.readLine();
			while (line != null) {
				String[] parts = line.split(", ");
				double x = Double.parseDouble(parts[4]);
				// increasing current tuition by 10%
				x = x * 1.1;
				out.write(parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + x + ", " + parts[5]);
				out.write('\n');
				line = in.readLine();
			}
			in.close();
			out.close();
			// delete the original Employees
			File students = new File("Students.csv");
			students.delete();
			// rename Students2 to Students ;)
			f.renameTo(students);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	void run() {
		try {

			SystemCall.read("Tuition increase is being calculated.....");
			tuition();
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			SystemCall.read(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
