package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;

import mx.iteso.electro.util.VectorR3;

public abstract class AbstractCharge 
{
	
	public static final double K = 8.999;
	public static final double infX = 500;	
	public static final double infY = 500;	
	public static final double infZ = 500;	
	
	public abstract void draw(GL gl);
	public abstract void drawField(GL gl, int divisions);
	public abstract VectorR3 fuerza(List<AbstractCharge> carga);
	
	public double getK()
	{
		return K*Math.pow(10, 9);
	}

}
