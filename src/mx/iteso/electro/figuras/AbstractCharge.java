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

	static int n = 0;

	double charge;
	VectorR3 pos;
	VectorR3 in;

	public void resetE(){in=null;}
	public double getCharge(){return charge;}
	public VectorR3 getPosition(){return pos;}

	public abstract void draw(GL gl);
	public abstract VectorR3 calculateForce(List<AbstractCharge> carga);
	public abstract VectorR3 calculateE(VectorR3 p);

	public double getK()
	{
		return K*Math.pow(10, 9);
	}

	public void drawField(GL gl, List<AbstractCharge> charges) 

	{
		double maxchar = charge;
		for(AbstractCharge a : charges)
		{

			if(maxchar < a.charge)
				maxchar = a.charge;
		}
		double color = 1;
		double cantLineas = Math.PI/(Math.abs(charge*8/maxchar));
		gl.glPushMatrix();
		gl.glColor3d(1, 1, 1);
		gl.glBegin(GL.GL_LINES);
		double xi = pos.x;
		double yi = pos.y;
		double zi = pos.z;// infZ*Math.cos(x);
		VectorR3[] cirq = new VectorR3[(int)(Math.PI*4/cantLineas+1)];
		for(int i = 0; i < (int) (Math.PI*2/cantLineas+1);i++)
			cirq[i] = new VectorR3(xi,yi,zi);

		for(double z = 0; z < Math.PI*2; z+=cantLineas)
		{
			if(z==0) continue;
			for(double x = 0 ; x <= Math.PI*2; x+=cantLineas)
			{
				if(charge>=0)
					gl.glColor3d(color, 0,0);
				else
					gl.glColor3d(0,0,color);

				color=1-z/Math.PI*2/3;
				zi =  z*Math.cos(x);
				int i = 0;
				for(double y = 0 ; y <= Math.PI*2; y+=cantLineas)
				{
					VectorR3 p = new VectorR3(pos.x+cirq[i].x, pos.y+cirq[i].y, pos.z+cirq[i].z);

					gl.glVertex3d(p.x, p.y, p.z);
					VectorR3 e = new VectorR3(0,0,0);

					for(AbstractCharge a : charges)
					{
						e = e.suma(a.calculateE(p));
					}
					e = e.unit();
					e = e.prod(.2);

					gl.glVertex3d(pos.x+cirq[i].x+e.x, pos.y+cirq[i].y+e.y, pos.z+cirq[i].z+e.z);
					xi = z*Math.cos(y);
					yi = z*Math.sin(y);

					cirq[i] = new VectorR3(xi,yi,zi);
					i++;
				}
			}
		}
		gl.glEnd();
		gl.glPopMatrix();
	}
}
