package mx.iteso.electro.gfx;

import javax.media.opengl.GLAutoDrawable;

import mx.iteso.electro.gfx.interfaces.Renderable;

public class SceneObject implements Renderable {

	protected int _displayList;
	protected boolean _compiled;
	
	
	public boolean isCompiled() {
		return _compiled;
	}

	@Override
	public void render(GLAutoDrawable gLDrawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void compile(GLAutoDrawable gLDrawable) {
		// TODO Auto-generated method stub

	}

}
