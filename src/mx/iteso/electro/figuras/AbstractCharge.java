package mx.iteso.electro.figuras;

import javax.media.opengl.GL;

public abstract class AbstractCharge {
	
	public static final double K = 8.999;
	public static final double infX = 500;	
	public static final double infY = 500;	
	public static final double infZ = 500;	
	
	public abstract void draw(GL gl);
	public abstract void drawField(GL gl, int divisions);

}
