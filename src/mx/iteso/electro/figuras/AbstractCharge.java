package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;

import mx.iteso.electro.util.VectorR3;

public abstract class AbstractCharge 
{
	
	public static final double K = 8.999;
	public static final double infX = 100;	
	public static final double infY = 100;	
	public static final double infZ = 100;	
	
	public abstract void draw(GL gl);
	public abstract void drawField(GL gl, List<AbstractCharge> charges);
	public abstract VectorR3 calculateForce(List<AbstractCharge> carga);
	
	public double getK()
	{
		return K*Math.pow(10, 9);
	}
}
