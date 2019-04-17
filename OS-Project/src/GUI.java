import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings({ "serial", "static-access" })
public class GUI extends JFrame {
	static JPanel memory;
	static JButton days;
	static JButton process;
	static JButton cpu;

	public GUI() {
		this.setBounds(0, 0, 1000, 800);
		this.setTitle("GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(4, 1));

		days = new JButton(0 + "");
		days.setFont(new Font("Arial", Font.BOLD, 45));
		this.getContentPane().add(days);

		process = new JButton("");
		process.setFont(new Font("Arial", Font.BOLD, 45));
		this.getContentPane().add(process);

		memory = new JPanel();
		memory.setLayout(new GridLayout(1, 10));
		this.getContentPane().add(memory);

		cpu = new JButton();
		cpu.setBackground(Color.RED);
		cpu.setFont(new Font("Arial", Font.BOLD, 45));
		cpu.setText("Idle -_-");
		this.getContentPane().add(cpu);
		this.setVisible(true);
	}

	public static void setProcess(Process p) {
		String text = p.getClass().toString().substring(5);
		process.setText(text);
	}

	public void updateMemory(int memoryUsed) {

	}

	public void update(Scheduler s, boolean on) {
		days.setText(s.days + "");

		if (!on) {
			cpu.setBackground(Color.RED);
			cpu.setText("Idle -_-");
			process.setText("");
			JPanel n = new JPanel();
			n.setLayout(new GridLayout(1, 10));
			int i = 0;
			for (; i < 10; i++) {
				JButton b = new JButton();
				b.setBackground(Color.WHITE);
				n.add(b);
			}
			this.getContentPane().remove(memory);
			memory = n;
			this.getContentPane().add(memory);
			this.setVisible(true);
			return;
		}

		if (s.processRunning != null) {
			String text = s.processRunning.getClass().toString().substring(5);
			process.setText(text);

			JPanel n = new JPanel();
			n.setLayout(new GridLayout(1, 10));
			int memoryUsed = s.processRunning.pcb.memorySize;
			int i = 0;
			for (; i < 10; i++) {
				JButton b = new JButton();
				if (memoryUsed > 0) {
					b.setBackground(Color.BLUE);
					memoryUsed -= 10;
				} else
					b.setBackground(Color.WHITE);
				n.add(b);
			}
			this.getContentPane().remove(memory);
			memory = n;
			this.getContentPane().add(memory);

			cpu.setBackground(Color.GREEN);
			cpu.setText("Active ;)");
			this.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new GUI();
	}
}
