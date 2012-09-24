package mx.iteso.electro.figuras;

import javax.media.opengl.GLAutoDrawable;

import mx.iteso.electro.util.VectorR3;


public class CargaPuntual extends AbstractCharge{
	private VectorR3 pos;
	private double _charge;
	private String name;
	
	public CargaPuntual(int name, double charge, VectorR3 pos)
	{
		this._charge = charge;
		this.pos = pos;
		this.name = "q"+name;
	}
	
	// Deprecated - the vector length should be always calculated from its position
	public void setMagnitud(double charge)
	{
		this._charge = charge;
	}
	
	public double fuerza(AbstractCharge carga)
	{
		if(carga instanceof CargaPuntual)
		{
			CargaPuntual q = (CargaPuntual)carga;
			if(!pos.equals(q.pos))
				return (K*_charge*q._charge) / (pos.resta(q.pos).magnitud());
		}		
		
		return 0;
	}
	
	// Renamed from mover()
	public void setPosition(VectorR3 v)
	{
		pos = v;
	}
	
	public void move(VectorR3 v) 
	{
		pos = pos.suma(v);
	}

	
	public String toString()
	{
		return name;
	}
	
	@Override
	public void render(GLAutoDrawable gLDrawable)
	{
		
	}
	
	//Deprecated, use SceneObject.render() for engine automatic handling
	@Override
	public void Dibujar() {
		// TODO Auto-generated method stub
		
	}
	
}
