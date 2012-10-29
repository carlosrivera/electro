package mx.iteso.electro.util;

public class VectorR3 {
	public double x;
	public double y;
	public double z;

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
	
	public VectorR3 div(double s)
	{
		if(s!=0)
		return new VectorR3(x/s,y/s,z/s);
		return this;
	}	
	
	public VectorR3 prod(double s)
	{
		return new VectorR3(x*s,y*s,z*s);
	}
	
	public VectorR3 prod(VectorR3 v)
	{
		return new VectorR3(x*v.x,y*v.y,z*v.z);
	}
	
	public VectorR3 unit()
	{
	return new VectorR3(x/magnitud(),y/magnitud(),z/magnitud());	
	}	

	public String toString()
	{
		return String.format("%1$,.1f",x)+","+String.format("%1$,.1f",y)+","+String.format("%1$,.1f",z);
	}
	public String toVector()
	{
		return String.format("%1$,.3f",x)+"i+"+String.format("%1$,.3f",y)+"j+"+String.format("%1$,.3f",z)+"k";
	}

	public boolean equals(VectorR3 v)
	{
		return x==v.x && y == v.y && z == v.z;
	}

	public boolean closeEnough(VectorR3 pos){
		double e = 1.1;
		if(pos.x - x < e && pos.z - z < e)
			return true;
		return false;
	}
}
