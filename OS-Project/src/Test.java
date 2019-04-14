import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Test {
	public static void test() {
		try {
			// second parameter of FileWriter tells java to APPEND to metadata.csv, not
			// REWRITE it completely
			PrintWriter writer = new PrintWriter(new FileWriter("Test.csv", false));
			Date d1 = new Date(100, 0, 1);
			Date d2 = new Date(101, 1, 2);
			Date d3 = new Date(102, 2, 3);
			Date d4 = new Date(103, 3, 4);
			Date d5 = new Date(104, 4, 5);
			Date d6 = new Date(105, 5, 6);
			Date d7 = new Date(106, 6, 7);
			Date d8 = new Date(107, 7, 8);
			// long x = dnow.getTime() - d.getTime();
			// long days = (x / (60 * 60 * 24 * 1000));
			writer.write("Person 1, Doctor, " + d1.toString() + ", 10000, 0" + '\n');
			writer.write("Person 2, TA, " + d2.toString() + ", 1000, 0" + '\n');
			writer.write("Person 3, Employee, " + d3.toString() + ", 9000, 0" + '\n');
			writer.write("Person 4, Doctor, " + d4.toString() + ", 1500, 0" + '\n');
			writer.write("Person 5, Accountant, " + d5.toString() + ", 4000, 0" + '\n');
			writer.write("Person 6, TA, " + d6.toString() + ", 5200, 0" + '\n');
			writer.write("Person 7, Doctor, " + d7.toString() + ", 7000, 0" + '\n');
			writer.write("Person 8, Employee, " + d8.toString() + ", 3000, 0" + '\n');
			writer.close();

			System.out.println("Done creating file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// test();
	//
	// }

}
