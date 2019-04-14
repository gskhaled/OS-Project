import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PayRoll extends Process {

	public PayRoll(PCB p) {
		super(p);
	}

	public static void Salary() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("Employees.csv"));
			String line = in.readLine();
			int sum = 0;
			while (line != null) {
				String[] parts = line.split(", ");
				// calculating annual total salary
				int s = Integer.parseInt(parts[3]);
				sum += s;
				line = in.readLine();
			}
			in.close();
			System.out.print(" The total salaries of employees is: " + sum * 12 + " ... ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	void run() {
		try {
			SystemCall.read("Pay roll is being calculated.....");
			Salary();
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			SystemCall.read(" Done!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
