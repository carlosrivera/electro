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
		if(_charge>=0)
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
	public void drawField(GL gl, List<AbstractCharge> charges) 
	{
		double maxchar = _charge;
		for(AbstractCharge a : charges)
		{
			CargaPuntual c = (CargaPuntual)a;
			if(maxchar < c._charge)
				maxchar = c._charge;
		}
		double color = 1;
		double cantLineas = Math.PI/(Math.abs(_charge*8/maxchar));
		gl.glPushMatrix();
		gl.glColor3d(1, 1, 1);
		gl.glBegin(GL.GL_LINES);
		double xi = pos.x;
		double yi = pos.y;
		double zi = pos.z;// infZ*Math.cos(x);
		VectorR3[] cirq = new VectorR3[(int) (Math.PI*2/cantLineas+1)];
		for(int i = 0; i < (int) (Math.PI*2/cantLineas+1); i++)
			cirq[i] = new VectorR3(xi,yi,zi);

		for(double z = 0; z < Math.PI*2; z+=cantLineas)
		{
			if(z==0) continue;
			for(double x = 0 ; x <= Math.PI*2; x+=cantLineas)
			{
				if(_charge>=0)
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
						VectorR3 e1 = new VectorR3(1,1,1);
						CargaPuntual c = (CargaPuntual)a;
						VectorR3 R = p.resta(c.pos);
						e1 = e1.prod(getK()*c._charge);
						e1 = e1.prod(R);
						e1 = e1.div(Math.pow(R.magnitud(),3));
						e = e.suma(e1);
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
