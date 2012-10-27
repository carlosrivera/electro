package mx.iteso.electro.gfx;

import javax.media.opengl.GL;

public class Grid2 
{

	public void draw(GL gl, int width, int height, int padding)
	{
		gl.glColor3d(.2, .2, .2);
		gl.glBegin(GL.GL_LINES);
		for(int i = -width; i< width; i+= width*2/padding)
		{
			gl.glVertex3d(-height,0, i);
			gl.glVertex3d(height,0, i);
		}
		for(int i = -height; i< height; i+= height*2/padding)
		{
			gl.glVertex3d(i, 0, -width);
			gl.glVertex3d(i, 0, width);
		}
		gl.glEnd();
	}

	public void drawAxis(GL gl, int xLimit, int yLimit, int zLimit) {
		gl.glBegin(GL.GL_LINES);
		gl.glColor3d(.5, 0, 0);
		gl.glVertex3d(0, 0.03, -xLimit);
		gl.glVertex3d(0, 0.03, xLimit);
		gl.glColor3d(0, 0, .5);
		gl.glVertex3d(-yLimit, 0.03, 0);
		gl.glVertex3d(yLimit, 0.03, 0);
		gl.glColor3d(0, .5, 0);
		gl.glVertex3d(0,-zLimit, 0);
		gl.glVertex3d(0,zLimit, 0);
		gl.glEnd();
	}
}
