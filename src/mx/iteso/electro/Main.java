package mx.iteso.electro;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import mx.iteso.electro.control.ControlPanel;
import mx.iteso.electro.figuras.AbstractCharge;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Vector<AbstractCharge> charges = new Vector<AbstractCharge>();
		Universe field = new Universe(charges);
		
		JFrame frame = new JFrame("Electro");
		ControlPanel controlPanel = new ControlPanel(charges);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				field,controlPanel);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(750);
		frame.getContentPane().add(split);

		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
