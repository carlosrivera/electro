package mx.iteso.electro.gfx;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Renderer implements GLEventListener {
	private GLU glu = new GLU();
	
	private SceneGraph _scene = new SceneGraph();
	
	
	public SceneGraph getScene() {
		return _scene;
	}

	public void display(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		// ToDo: Insert camera here!
		gl.glLoadIdentity();
		glu.gluLookAt(0, 5, -10, 0, 0, 0, 0, 1, 0);
		
		if (_scene != null)
			_scene.render();
		
		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
			boolean deviceChanged) {
		System.out.println("displayChanged called");
	}

	public void init(GLAutoDrawable gLDrawable) {
		
		GL gl = gLDrawable.getGL();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_SMOOTH);
		
		_scene.setGLDrawable(gLDrawable);
		
		//ToDo: code for testing, move it
		
	}

	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {

		final GL gl = gLDrawable.getGL();
		
		GLU glu = new GLU();
		
		if (height <= 0) 
		{
			height = 1;
		}

		final float h = (float) width / (float) height;

		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 1.0, 200.0);
		
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity(); 
	}

	public void dispose(GLAutoDrawable arg0) {
		
	}
}