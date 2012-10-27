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
//		GLProfile profile = GLProfile.get(GLProfile.GL2);
//		GLCapabilities capabilities = new GLCapabilities();
		

//		final Renderer renderer = new Renderer();
//		GLCanvas glcanvas = new GLCanvas();
//		glcanvas.addGLEventListener(renderer);
		
//		glcanvas.setSize(800, 600);
		
		Vector<AbstractCharge> charges = new Vector<AbstractCharge>();
		Universe field = new Universe(charges);
		
		JFrame frame = new JFrame("Electro");
		ControlPanel controlPanel = new ControlPanel(charges);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				field,controlPanel);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(800);
		frame.getContentPane().add(split);

		
//		final Simulator simulator = new Simulator(renderer);
//
//		simulator.run();

//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent ev) {
//				simulator.dispose();
//				System.exit(0);
//			}
//		});

		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
