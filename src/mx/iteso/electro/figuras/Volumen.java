package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;

import mx.iteso.electro.util.VectorR3;

public class Volumen extends AbstractCharge {
	private VectorR3 pos;
	privol;
	private VectorR3 fuerza;
	private String name;

	public Volumen(double charge, VectorR3 pos, VectorR3 vol)
	{
		super.charge = charge;
		this.pos = pos;
		this.vol = vol;
		this.name = "q"+n;
		n++;
		fuerza = new VectorR3(0,0,0);
		if(vol.x == 0 )
			vol.x = 1E-5;
		if(vol.y == 0 )
			vol.y = 1E-5;
		if(vol.z == 0 )
			vol.z = 1E-5;
	}
g()
	{
		return name+" "+pos.toVector()+" F="+String.format("%1$,.3f",fuerza.magnitud());
	}

	
	@Override
	public void dra
	@Override
	public void draw(GL gl) {
		VectorR3 v = new VectorR3(vol.x, vol.y, vol.z);
		if(v.x <.12)
			v.x = .12;
		if(v.y <.12)
			v.y = .12;
		if(v.z <.12)
			v.z = .12;
		
		gl.glPushMatrix();
		if(0);
		else
			gl.glColor3d(0, 0, 1);			
		gl.glTranslated(pos.x, pos.y, pos.z);
		gl.glColor3d(1, 1, 1);
		gl.glLineWidth(3);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(fuerza.x, fuerza.y, fuerza.z);
		gl.glEnd();
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3d(-vol.x/2,vol.y/2,-vol.z/2);
		g//frente
		gl.glVertex3d(-v.x/2,v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,-v.y/2,-v.z/2);
		gl.glVertex3d(-v.x/2,-v.y/2,-v.z/2);

		//fondo
		gl.glVertex3d(-v.x/2,-v.y/2,v.z/2);
		gl.glVertex3d(v.x/2,-v.y/2,v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,v.z/2);
		gl.glVertex3d(-v.x/2,v.y/2,v.z/2);

		//abajo
		gl.glVertex3d(-v.x/2,-v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,-v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,-v.y/2,v.z/2);
		gl.glVertex3d(-v.x/2,-v.y/2,v.z/2);

		//arriba
		gl.glVertex3d(-v.x/2,v.y/2,v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,-v.z/2);
		gl.glVertex3d(-v.x/2,v.y/2,-v.z/2);

		//der
		gl.glVertex3d(v.x/2,-v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,-v.z/2);
		gl.glVertex3d(v.x/2,v.y/2,v.z/2);
		gl.glVertex3d(v.x/2,-v.y/2,v.z/2);

		//izq
		gl.glVertex3d(-v.x/2,-v.y/2,v.z/2);
		gl.glVertex3d(-v.x/2,v.y/2,v.z/2);
		gl.glVertex3d(-v.x/2,v.y/2,-v.z/2);
		gl.glVertex3d(-v.x/2,-v.y/2,-v.z/2);
verride
	public void drawField(GL gl, List<AbstractCharge> charges) {

	}

	@Override
	pVectorR3 calculateE(VectorR3 p)
	{		
		VectorR3 e1 = new VectorR3(1,1,1);
		e1 = e1.prod(getK());
		e1 = e1.prod(charge);

		double dif = .05;
		if(in == null)
		{
		in = new VectorR3(0,0,0);
		for(double x = -vol.x/2; x < vol.x/2 ; x+=dif)
			for(double y = -vol.y/2; y < vol.y/2 ; y+=dif)
				for(double z = -vol.z/2; z < vol.z/2 ; z+=dif)
				{
					VectorR3 dR = p.resta(new VectorR3(pos.x+x, pos.y+y, pos.z+z));
					VectorR3 i = new VectorR3(1,1,1);
					i = i.prod(dR);			
					i = i.div(Math.pow(dR.magnitud(),3));
					in = in.suma(i);
				}
		}
		e1 = e1.prod(in);

		return e1;a) {
		return null;
	}

}
ew VectorR3(0,0,0);
	}

	public VectorR3 getVolume() {return vol;}

}
