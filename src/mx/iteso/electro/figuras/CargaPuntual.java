package mx.iteso.electro.figuras;

import mx.iteso.electro.util.VectorR3;

public class CargaPuntual implements Carga{
	private VectorR3 pos;
	private double magnitud;
	private String name;
	
	public CargaPuntual(int name, double magnitud, VectorR3 pos)
	{
		this.magnitud = magnitud;
		this.pos = pos;
		this.name = "q"+name;
	}
	
	public void setMagnitud(double magnitud)
	{
		this.magnitud = magnitud;
	}
	
	public double fuerza(Carga carga)
	{
		if(carga instanceof CargaPuntual)
		{
			CargaPuntual q = (CargaPuntual)carga;
			if(!pos.equals(q.pos))
		  return (K*magnitud*q.magnitud) / (pos.resta(q.pos).magnitud());
		}
		return 0;
	}
	
	public void mover(VectorR3 v)
	{
		pos = v;
	}
	
	public String toString()
	{
		return name;
	}
	
	@Override
	public void Dibujar() {
		// TODO Auto-generated method stub
		
	}
	
}
