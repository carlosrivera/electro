package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import mx.iteso.electro.util.VectorR3;


public class CargaPuntual extends AbstractCharge{
	private VectorR3 pos;
	private VectorR3 fuerza;
	private double _charge;
	private String name;
	private static GLU glu = new GLU();
	private static GLUquadric puntito = glu.gluNewQuadric();

	public CargaPuntual(int name, double charge, VectorR3 pos)
	{
		this._charge = charge;
		this.pos = pos;
		this.name = "q"+name;
		fuerza = new VectorR3(0,0,0);
	}
	
	public VectorR3 getPos() {
		return pos;
	}

	public void setPos(VectorR3 pos) {
		this.pos = pos;
	}

	public VectorR3 getFuerza() {
		return fuerza;
	}

	public void setFuerza(VectorR3 fuerza) {
		this.fuerza = fuerza;
	}

	public double get_charge() {
		return _charge;
	}

	public void set_charge(double _charge) {
		this._charge = _charge;
	}
	
	public VectorR3 calculateForce(List<AbstractCharge> cargas)
	{
		fuerza = new VectorR3(0,0,0);
		for(AbstractCharge carga : cargas)
			if(carga instanceof CargaPuntual)
			{
				CargaPuntual q = (CargaPuntual)carga;
				if(!pos.equals(q.pos))
				{
					fuerza.x+= (q._charge*pos.resta(q.pos).x) / Math.pow((pos.resta(q.pos).magnitud()),3);
					fuerza.y+= (q._charge*pos.resta(q.pos).y) / Math.pow((pos.resta(q.pos).magnitud()),3);
					fuerza.z+= (q._charge*pos.resta(q.pos).z) / Math.pow((pos.resta(q.pos).magnitud()),3);
				}
			}
		fuerza.x *= getK()*_charge;
		fuerza.y *= getK()*_charge;
		fuerza.z *= getK()*_charge;

		return fuerza;
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
		return name+" "+pos+" F="+String.format("%1$,.3f",fuerza.magnitud());
	}

	@Override
	public void draw(GL gl) 
	{
		double mf = fuerza.magnitud();
		//	    monky.bind();
		glu.gluQuadricTexture(puntito, true);
		gl.glPushMatrix();
		gl.glColor3d(1, 1, 1);
		gl.glTranslated(pos.x, pos.y, pos.z);
		glu.gluSphere(puntito, 0.02, 10, 10);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(fuerza.x/mf, fuerza.y/mf, fuerza.z/mf);
		gl.glVertex3d(fuerza.x/mf, fuerza.y/mf, fuerza.z/mf);		
		gl.glVertex3d(fuerza.x/mf-.05, fuerza.y/mf-.05, fuerza.z/mf-.05);		
		gl.glEnd();
		gl.glPopMatrix();	    
	}

	@Override
	public void drawField(GL gl, int divisions) 
	{
		gl.glPushMatrix();
		gl.glColor3d(1, 1, 1);
		gl.glTranslated(pos.x, pos.y, pos.z);
		gl.glBegin(GL.GL_LINES);
		for(int x = 0 ; x <= 360; x+=45)
		{
			for(int y = 0 ; y <= 360; y+=360/divisions)
			{
				gl.glVertex3d(pos.x, pos.y, pos.z);
				gl.glVertex3d(infX*Math.toDegrees(Math.cos(x)), infY*Math.toDegrees(Math.sin(x)), 0);
			}
		}
		gl.glEnd();
		gl.glPopMatrix();	    

	}

}
