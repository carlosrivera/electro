package mx.iteso.electro.util;

public class VectorR3 {
	private double x;
	private double y;
	private double z;
	
	public VectorR3(double x,double y,double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double magnitud()
	{
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
	}
	
	public VectorR3 suma(VectorR3 v)
	{
		return new VectorR3(x+v.x,y+v.y,z+v.z);
	}
	
	public VectorR3 resta(VectorR3 v)
	{
		return new VectorR3(x-v.x,y-v.y,z-v.z);
	}
	
	public String toString()
	{
		return "("+x+","+y+","+z+")";
	}
	
	public boolean equals(VectorR3 v)
	{
		return x==v.x && y == v.y && z == v.z;
	}

}
