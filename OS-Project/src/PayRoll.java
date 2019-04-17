import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PayRoll extends Process {

	public PayRoll(PCB p) {
		super(p);
	}

//	public static String getDirectoryPath() {
//		Path currentRelativePath = Paths.get("");
//		return currentRelativePath.toAbsolutePath().toString();
//	}

	public static void Salary() {
		try {
			IOModule io = new IOModule("Employees.csv", "-");
			String line = io.readLine();
			int sum = 0;
			while (line != null) {
				String[] parts = line.split(", ");
				// calculating annual total salary the university has to pay
				int s = Integer.parseInt(parts[3]);
				sum += s;
				line = io.readLine();
			}
			IOModule.print(" The total salaries of employees is: " + sum * 12 + " ... ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	void run() {
		try {
			IOModule.print("Pay roll is being calculated..... ");
			Salary();
			// added a slight delay according to the TTF of each process
			TimeUnit.SECONDS.sleep(this.pcb.ttf);
			// print DONE after
			IOModule.print(" Done! ");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
