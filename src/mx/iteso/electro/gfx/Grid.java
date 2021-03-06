package mx.iteso.electro.gfx;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

public class Grid extends SceneObject {

	@Override
	public void render(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		
		if (_compiled) {
			gl.glPushMatrix();
			
			gl.glCallList(_displayList);
	
			gl.glPopMatrix();
		}
	}

	@Override
	public void compile(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		
		_displayList = gl.glGenLists(1);
		gl.glNewList(_displayList, GL.GL_COMPILE);
		gl.glColor3f(.3f, .3f, .3f);


		gl.glBegin(GL.GL_LINES);
		
		for (int i = -10; i <= 10; i++) {
			if (i == 0) {
				gl.glColor3f(.8f, .3f, .3f);
			} else {
				gl.glColor3f(.25f, .8f, .25f);
			}

			gl.glVertex3f(i, 0, -10);
			gl.glVertex3f(i, 0, 10);

			if (i == 0) {
				gl.glColor3f(.3f, .3f, .8f);
			} else {
				gl.glColor3f(.25f, .8f, .25f);
			}
			
			gl.glVertex3f(-10, 0, i);
			gl.glVertex3f(10, 0, i);
		}
		
		gl.glEnd();
		
		gl.glEndList();
		
		_compiled = true;
	}
}