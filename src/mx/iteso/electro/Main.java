package mx.iteso.electro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import mx.iteso.electro.gfx.*;
import mx.iteso.electro.simulator.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		final Renderer renderer = new Renderer();
		GLCanvas glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(renderer);
		
		glcanvas.setSize(800, 600);
		
		JFrame frame = new JFrame("Electro");
		frame.getContentPane().add(glcanvas);

		
		final Simulator simulator = new Simulator(renderer);

		simulator.run();
		
			
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				simulator.dispose();
				System.exit(0);
			}
		});

		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
	}
}
