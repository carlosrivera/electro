package mx.iteso.electro.simulator;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import mx.iteso.electro.gfx.*;

public class DummyParticle extends SceneObject {

	@Override
	public void render(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		
		gl.glPushMatrix();
		
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3f(-1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		gl.glEnd();
		
		gl.glPopMatrix();
	}



}
