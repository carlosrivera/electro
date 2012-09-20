package mx.iteso.electro.gfx;

import java.util.*;
import javax.media.opengl.GLAutoDrawable;
import mx.iteso.electro.gfx.interfaces.*;

public class SceneGraph {
	private Hashtable<String, Renderable> _graph;
	private GLAutoDrawable _gLDrawable;
	
	public GLAutoDrawable getGLDrawable() {
		return _gLDrawable;
	}

	public void setGLDrawable(GLAutoDrawable _gLDrawable) {
		this._gLDrawable = _gLDrawable;
		
		for (Renderable item : _graph.values()) {
			if (!item.isCompiled())
				item.compile(_gLDrawable);
		}
	}

	public SceneGraph() {
		_graph = new Hashtable<String, Renderable>();
	}
	
	public SceneGraph(GLAutoDrawable gLDrawable) {
		_graph = new Hashtable<String, Renderable>();
		
		setGLDrawable(gLDrawable);
	}
	
	public void render() {
		if (_gLDrawable != null) {
			for (Renderable item : _graph.values()) {
				item.render(_gLDrawable);
			}
		}
	}
	
	public void insert(Renderable object) {
		if (_gLDrawable != null)
			object.compile(_gLDrawable);
		
		_graph.put(String.valueOf(object.hashCode()), object);
	}
	
	public void clear() {
		_graph.clear();
	}
}
