package mx.iteso.electro.gfx.interfaces;

import javax.media.opengl.GLAutoDrawable;

public interface Renderable {
	void render(GLAutoDrawable gLDrawable);
	void compile(GLAutoDrawable gLDrawable);
	boolean isCompiled();
}
