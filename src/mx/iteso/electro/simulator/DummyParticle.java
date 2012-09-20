package mx.iteso.electro.simulator;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import mx.iteso.electro.gfx.*;

public class DummyParticle extends SceneObject {

	@Override
	public void render(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(-1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		gl.glEnd();
		
		gl.glPopMatrix();
	}



}
