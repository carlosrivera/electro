package mx.iteso.electro.figuras;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import mx.iteso.electro.util.VectorR3;


public class CargaPuntual extends AbstractCharge{
	private VectorR3 pos;
	private double _charge;
	private String name;
	private static GLU glu = new GLU();
	private static GLUquadric puntito = glu.gluNewQuadric();

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
	public void draw(GL gl) 
	{
//	    monky.bind();
	    glu.gluQuadricTexture(puntito, true);
	    gl.glPushMatrix();
	    gl.glTranslated(pos.x, pos.y, pos.z);
//	    gl.glRotated(90, 1, 0, 0);
	    glu.gluSphere(puntito, 0.05, 10, 10);
	    gl.glPopMatrix();	    
	}

	@Override
	public void drawField(GL gl, int divisions) 
	{
	    gl.glPushMatrix();
	    gl.glColor3d(1, 1, 1);
	    gl.glTranslated(pos.x, pos.y, pos.z);
	    gl.glBegin(GL.GL_LINES);
	    for(int x = 0 ; x <= 360; x+=360/divisions)
	    {
		    for(int y = 0 ; y <= 360; y+=360/divisions)
		    {
			    for(int z = 0 ; z <= 360; z+=360/divisions)
			    {
//			    	double a =infX*Math.cos(x);
//			    	double b =infY*Math.sin(y);
//			    	double c =infZ*Math.cos(z);
			    	gl.glVertex3d(pos.x, pos.y, pos.z);
			    	gl.glVertex3d(infX*Math.cos(x), infY*Math.sin(y), infZ*Math.cos(z));
			    }
		    }
	    }
	    gl.glEnd();
	    gl.glPopMatrix();	    
		
	}
	
}
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
