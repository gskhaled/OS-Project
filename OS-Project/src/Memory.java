public class Memory {
	static boolean[] memory = new boolean[100];

	public static boolean Check(int a) {
		int count = 0;
		boolean series = true;
		while (count < a && series) {
			if (memory[a])
				series = false;
			count++;
		}
		if (!series)
			return false;
		else
			return true;
	}

	public static void Use(int a) {
		System.out.print(" Used " + a + " blocks of memory!!!!!!");
		int i = 0;
		while (i < a) {
			if (!memory[i])
				memory[i] = true;
			i++;
		}
	}

	public static void unUse(int a) {
		System.out.println(" Stopped using " + a + " blocks of memory!!!!!!");
		int i = 0;
		while (i < a) {
			if (memory[i])
				memory[i] = false;
			i++;
		}
	}
}
