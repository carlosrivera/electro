package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import mx.iteso.electro.util.VectorR3;


public class CargaPuntual extends AbstractCharge{
	private VectorR3 fuerza;
	private String name;
	private static GLU glu = new GLU();
	private static GLUquadric puntito = glu.gluNewQuadric();

	public CargaPuntual(double charge, VectorR3 pos)
	{
		super.charge = charge;
		this.pos = pos;
		this.name = "q"+n;
		n++;
		fuerza = new VectorR3(0,0,0);
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

	public void set_charge(double _charge) {
		this.charge = _charge;
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

	public VectorR3 calculateForce(List<AbstractCharge> cargas)
	{
		fuerza = new VectorR3(0,0,0);
		for(AbstractCharge carga : cargas)
			if(carga instanceof CargaPuntual)
			{
				CargaPuntual q = (CargaPuntual)carga;
				if(!pos.equals(q.pos))
				{
					fuerza.x+= (q.charge*pos.resta(q.pos).x) / Math.pow((pos.resta(q.pos).magnitud()),3);
					fuerza.y+= (q.charge*pos.resta(q.pos).y) / Math.pow((pos.resta(q.pos).magnitud()),3);
					fuerza.z+= (q.charge*pos.resta(q.pos).z) / Math.pow((pos.resta(q.pos).magnitud()),3);
				}
			}
		fuerza.x *= getK()*charge;
		fuerza.y *= getK()*charge;
		fuerza.z *= getK()*charge;

		return fuerza;
	}


	public String toString()
	{
		return name+" "+pos.toVector()+" F="+String.format("%1$,.3f",fuerza.magnitud());
	}

	@Override
	public void draw(GL gl) 
	{
		double mf = fuerza.magnitud();
		//	    monky.bind();
		glu.gluQuadricTexture(puntito, true);
		gl.glPushMatrix();
		if(charge>=0)
			gl.glColor3d(1, 0, 0);
		else
			gl.glColor3d(0, 0, 1);			
		gl.glTranslated(pos.x, pos.y, pos.z);
		glu.gluSphere(puntito, 0.12, 10, 10);
		gl.glColor3d(1, 1, 1);
		gl.glLineWidth(3);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(fuerza.x/mf, fuerza.y/mf, fuerza.z/mf);
		gl.glVertex3d(fuerza.x/mf, fuerza.y/mf, fuerza.z/mf);		
		gl.glVertex3d(fuerza.x/mf-.05, fuerza.y/mf-.05, fuerza.z/mf-.05);		
		gl.glEnd();
		gl.glPopMatrix();
		gl.glLineWidth(1);
	}

	@Override
	public VectorR3 calculateE(VectorR3 p) 
	{
		VectorR3 e1 = new VectorR3(1,1,1);
		VectorR3 R = p.resta(pos);
		e1 = e1.prod(getK()*charge);
		e1 = e1.prod(R);
		e1 = e1.div(Math.pow(R.magnitud(),3));
		return e1;
	}
}
