
abstract public class Process {
	
	PCB pcb;
	
	public Process(PCB p){
		pcb = p;
	}
	
	abstract void run();
	
}
