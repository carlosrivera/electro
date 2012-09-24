package mx.iteso.electro.figuras;

import mx.iteso.electro.gfx.SceneObject;

// Changed to abstract, it represents an object, not an action
public abstract class AbstractCharge extends SceneObject  {
	
	public static final double K = 8.999;
	
	// Deprecated
	public abstract void Dibujar();

}
