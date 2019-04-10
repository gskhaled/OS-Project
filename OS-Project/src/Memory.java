import java.util.concurrent.TimeUnit;

public class Memory {
	// boolean array that represents the memory. if it's false then no process is
	// using it at the moment, if it's true then some process is utilizing it
	static boolean[] memory = new boolean[100];

	// this method checks if there exists contiguous a block of memory with a length
	// "a". returns -1 if no block was found, and an index at which the block starts
	// otherwise
	public static int Check(int a) {// "a" represents the number of blocks a process needs
		int i = 0;
		int count = 0;
		boolean series = true; // we need a contiguous block only!
		while (i < memory.length && count < a) { // break the loop the moment i have found the needed number of cells
			if (memory[i]) { // once i find a used block, i will not have a series anymore. and i will need
								// to reset the count again
				series = false;
				count = 0;
			} else { // keep series as true and increment the count
				series = true;
				count++;
			}
			i++;
		}
		// if i found the cells in sequence and the number of cells is sufficient then
		// return true
		if (series && count == a)
			return i - a;
		else
			return -1;
	}

	// takes 2 parameters, the length of the block, as well as the index from which
	// the block begins
	// uses this block from memory
	public static void Use(int length, int index) {
		while (index < length) {
			memory[index] = true;
			index++;
		}
		System.out.print(" Used " + length + " blocks of memory to - ");
	}

	// takes 2 parameters, the length of the block, as well as the index from which
	// the block begins
	// un-uses this block from memory, so another process can use it later
	public static void unUse(int length, int index) {
		while (index < length) {
			memory[index] = false;
			index++;
		}
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.print(" ... Now stopped using " + length + " blocks of memory!!" + '\n');
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
