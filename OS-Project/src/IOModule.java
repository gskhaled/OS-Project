import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOModule {

	public BufferedReader in = null;
	public File f = null;

	// this method is used when reading from a file and outputing to a temp file
	// that gets deleted later on
	public IOModule(String in, String out) throws IOException {
		this.in = new BufferedReader(new FileReader(in));
		this.f = new File(out);
	}

	// this constructer is used when creating a file to write to it ONLY
	public IOModule(String out) {
		this.f = new File(out);
	}

	public IOModule() {
		// mainly used when i want an IO module for prints only
	}

	// reads a line from the BufferedReader input file
	public String readLine() throws IOException {
		return this.in.readLine();
	}

	// writes a LINE to the destination file
	public void writeLine(String line) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(this.f, true));
		out.write(line);
		out.write('\n');
		out.close();
	}

	// writes something to the destination file not followed by an enter-stroke
	public void write(String line) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(this.f, true));
		out.write(line);
		out.close();
	}

	// deletes the file that has the same name supplied, then renames the temporary
	// file to be that name supplied
	public void adjustFiles(String name) throws IOException {
		this.in.close();
		File f1 = new File(name);
		f1.delete();
		this.f.renameTo(new File(name));
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void println(String s) {
		System.out.println(s);
	}
}
