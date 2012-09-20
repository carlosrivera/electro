package mx.iteso.electro.simulator;

import mx.iteso.electro.gfx.*;

public class Simulator extends Thread {
	private Renderer _renderer;
	private boolean _running = false;
	
	public Simulator (Renderer renderer) {
		_renderer = renderer;
		
		_renderer.getScene().insert(new DummyParticle());
		_renderer.getScene().insert(new Grid());
		
		super.start();
	}
	
	public void run() {		
		while(_running) {
			//do physics
		}
	}
	
	public void dispose() {
		_running = false;
	}
}
