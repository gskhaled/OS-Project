
public class PCB {

	int process_id;
	int user_id;
	int ttf;
	int period;

	int memorySize;

	public PCB(int process_id, int user_id, int ttf, int period, int memorySize) {

		this.process_id = process_id;
		this.user_id = user_id;
		this.ttf = ttf;
		this.period = period;
		this.memorySize = memorySize;

	}
}
