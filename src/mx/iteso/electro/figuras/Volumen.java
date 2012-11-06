package mx.iteso.electro.figuras;

import java.util.List;

import javax.media.opengl.GL;

import mx.iteso.electro.util.VectorR3;

public class Volumen extends AbstractCharge {
	private VectorR3 pos;
	private VectorR3 vol;
	private VectorR3 fuerza;
	private double _charge;
	private String name;

	public Volumen(int name, double charge, VectorR3 pos, VectorR3 vol)
	{
		this._charge = charge;
		this.pos = pos;
		this.vol = vol;
		this.name = "q"+name;
		fuerza = new VectorR3(0,0,0);
	}
	
	public String toString()
	{
		return name+" "+pos.toVector()+" F="+String.format("%1$,.3f",fuerza.magnitud());
	}

	
	@Override
	public void draw(GL gl) {
		gl.glPushMatrix();
		if(_charge>=0)
			gl.glColor3d(1, 0, 0);
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
		gl.glVertex3d(vol.x/2,vol.y/2,-vol.z/2);
		gl.glVertex3d(vol.x/2,-vol.y/2,-vol.z/2);
		gl.glVertex3d(-vol.x/2,-vol.y/2,-vol.z/2);
		
//		gl.glVertex3d(vol.x/2,-vol.y/2,vol.z/2);
//		gl.glVertex3d(-vol.x/2,-vol.y/2,vol.z/2);
//		gl.glVertex3d(-vol.x/2,-vol.y/2,-vol.z/2);
//		gl.glVertex3d(vol.x/2,-vol.y/2,-vol.z/2);
//		
//		gl.glVertex3d(-vol.x/2,-vol.y/2,-vol.z/2);
//		gl.glVertex3d(vol.x/2,-vol.y/2,-vol.z/2);
//		gl.glVertex3d(vol.x/2,vol.y/2,-vol.z/2);
//		gl.glVertex3d(-vol.x/2,vol.y/2,-vol.z/2);
//		
//		gl.glVertex3d(vol.x/2,vol.y/2,vol.z/2);
//		gl.glVertex3d(-vol.x/2,vol.y/2,vol.z/2);
//		gl.glVertex3d(vol.x/2,vol.y/2,vol.z/2);
//		gl.glVertex3d(-vol.x/2,vol.y/2,vol.z/2);
		
		gl.glEnd();
		gl.glPopMatrix();
		gl.glLineWidth(1);

	}

	@Override
	public void drawField(GL gl, List<AbstractCharge> charges) {

	}

	@Override
	public VectorR3 calculateForce(List<AbstractCharge> carga) {
		return null;
	}

}
