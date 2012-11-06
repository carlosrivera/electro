package mx.iteso.electro;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static javax.media.opengl.GL.GL_MODULATE;
import static javax.media.opengl.GL.GL_NEAREST;
import static javax.media.opengl.GL.GL_REPEAT;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import static javax.media.opengl.GL.GL_TEXTURE_ENV;
import static javax.media.opengl.GL.GL_TEXTURE_ENV_MODE;
import static javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static javax.media.opengl.GL.GL_TEXTURE_WRAP_S;
import static javax.media.opengl.GL.GL_TEXTURE_WRAP_T;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import mx.iteso.electro.figuras.AbstractCharge;
import mx.iteso.electro.gfx.Grid2;

import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Universe extends GLCanvas implements GLEventListener, KeyListener, MouseListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FPSAnimator animator;
	private GLU glu = new GLU();
	private double observerX = 0, observerY = 1, observerZ = 2.5;
	private double observerAngle = 0;
	private double observerSpeed = 0.3;
	private Status currentStatus = Status.IDLE;
	private Grid2 grid;
	private List<AbstractCharge> charges;
	private Point clicke = new Point();

	private int xLimit = 100;
	private int yLimit = 100;
	private int zLimit = 100;
	
	enum Status 
	{
		IDLE, MOVE_FORWARD, MOVE_BACKWARDS, 
		ROTATE_RIGHT, ROTATE_LEFT, MOVE_UPWARD, MOVE_DOWNWARD
	};

	public Universe(List<AbstractCharge> charges)
	{
		// Crear una animacion de 20 cuadros por segundo
		animator = new FPSAnimator(this, 20);
		animator.start();
		// Los eventos basicos de OpenGL (init, display, reshape)
		// los manejara esta misma clase
		addGLEventListener(this);
		addKeyListener(this);
		addMouseListener(this);
		
		grid = new Grid2();
		this.charges = charges;

	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,boolean deviceChanged) {}

	//Este metodo se invocara antes del primer dibujado de la ventana
	public void init(GLAutoDrawable drawable) {
		// Obtener acceso a las funciones de OpenGL
		GL gl = drawable.getGL();
		// Establecer el color de fondo
		gl.glClearColor(0f, 0f, 0f, 1f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		gl.glEnable(GL.GL_LIGHT0);


		float ambient_light[] = new float[]{.7f, .7f, .7f, 1};
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambient_light , 0);

		float amb_light0[] = new float[]{ .3f, .3f, .1f, 1};
		float dif_light0[] = new float[]{ .4f, .4f, .6f, 1};
		float pos_light0[] = new float[]{ 0, 0, 2, 1};

		gl.glLightModelfv(GL.GL_LIGHT0 | GL.GL_AMBIENT, amb_light0, 0);
		gl.glLightModelfv(GL.GL_LIGHT0 | GL.GL_DIFFUSE, dif_light0, 0);
		gl.glLightModelfv(GL.GL_LIGHT0 | GL.GL_POSITION, pos_light0, 0);

		try {
			initTextures(gl);
		} catch(IOException e) {
			System.out.println(e);
		}

		gl.glEnable(GL.GL_NORMALIZE);

	}

	//Este metodo se invocara cada vez que se repinte la ventana
	public void display(GLAutoDrawable drawable) {
		update();			
		// Obtener acceso a las funciones de OpenGL
		GL gl = drawable.getGL();
		// Limpiar el bufer de color (detalles mas adelante)
		// con el color de fondo establecido en init()
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glRotated(-observerAngle, 0, 1, 0);
		gl.glTranslated(-observerX, -observerY, -observerZ);
		
		grid.draw(gl, xLimit, yLimit,20);
		grid.drawAxis(gl, xLimit, yLimit, zLimit);
		
		for(AbstractCharge c : charges)
		{
			c.draw(gl);
			c.drawField(gl, charges);
		}

	}

	//Este metodo se invocara cada vez que la ventana cambie de tamaño
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		// Obtener acceso a las funciones de OpenGL
		GL gl = drawable.getGL();
		// Establecer el puerto de despliegue
		// El escenario generado cubrira todo el area de la ventana
		// reservado para el contexto grafico de OpenGL
		gl.glViewport(0, 0, w, h);
		// Calcular la relación de aspecto del contexto grafico
		double aspect = (double) w / h;
		// Se modificara la matriz de Proyección
		gl.glMatrixMode(GL.GL_PROJECTION);
		// Reiniciar la matriz de Proyección
		gl.glLoadIdentity();
		// Establecer proyección en perspectiva
		glu.gluPerspective(50, aspect, .1, 500);
		gl.glMatrixMode(GL.GL_MODELVIEW);
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP    : currentStatus = Status.MOVE_FORWARD;
		break;
		case KeyEvent.VK_DOWN  : currentStatus = Status.MOVE_BACKWARDS;
		break;	
		case KeyEvent.VK_RIGHT : currentStatus = Status.ROTATE_RIGHT;
		break;
		case KeyEvent.VK_LEFT  : currentStatus = Status.ROTATE_LEFT;
		break;
		case KeyEvent.VK_PAGE_UP : currentStatus = Status.MOVE_UPWARD;
		break;
		case KeyEvent.VK_PAGE_DOWN : currentStatus = Status.MOVE_DOWNWARD;
		break;
		}

	}


	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
			currentStatus = Status.IDLE;
	}



	void moveForward() {
		double radians = toRadians(observerAngle);
		if (observerZ >= -zLimit && observerZ <= zLimit){
			observerZ -= observerSpeed * cos(radians);
		}
		else{
			if (observerZ > -zLimit){
				observerZ = zLimit;
			}
			else{
				observerZ = -zLimit;
			}
		}
		if (observerX >= -xLimit && observerX <= xLimit){
			observerX -= observerSpeed * sin(radians);
		}
		else{
			if (observerX > -xLimit){
				observerX = xLimit;
			}
			else{
				observerX = -xLimit;
			}
		}
	}


	void moveBackwards() {
		double radians = toRadians(observerAngle);
		if (observerZ >= -zLimit && observerZ <= zLimit)
			observerZ += observerSpeed * cos(radians);
		else{
			if (observerZ > -zLimit){
				observerZ = zLimit;
			}
			else{
				observerZ = -zLimit;
			}
		}
		if (observerX >= -xLimit && observerX <= xLimit){
			observerX += observerSpeed * sin(radians);
		}
		else{
			if (observerX > -xLimit){
				observerX = xLimit;
			}
			else{
				observerX = -xLimit;
			}
		}
	}


	void rotateRight(int step) {
		observerAngle -= observerSpeed*step;
	}


	void rotateLeft(int step) {
		observerAngle += observerSpeed*step;
	}

	void moveDownward(int step) {
		observerY -= observerSpeed*step;
	}


	void moveUpward(int step) {
		observerY += observerSpeed*step;
	}

	void update() {
		switch(currentStatus) {
		case IDLE			: break;
		case MOVE_FORWARD   : moveForward(); break;
		case MOVE_BACKWARDS : moveBackwards(); break;
		case ROTATE_RIGHT   : rotateRight(10); break;
		case ROTATE_LEFT    : rotateLeft(10); break;
		case MOVE_DOWNWARD  : moveDownward(1); break;
		case MOVE_UPWARD	: moveUpward(1); break;
		}

	}




	void initTextures(GL gl) throws IOException {
		gl.glEnable(GL_TEXTURE_2D);
//		floorTex   = initTexture(gl, "floor.jpg");
//		for (int i = 0; i < ingredTex.length; i++){
//			ingredTex[i] = initTexture(gl, "ingrediente"+i+".png");
//		}

		gl.glEnable(GL.GL_TEXTURE_2D);
	}

	Texture initTexture(GL gl, String filename) throws IOException {
		Texture texture = TextureIO.newTexture(new File("textures"+File.separator+ filename), false);
		texture.bind();
		gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		return texture;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicke = e.getLocationOnScreen();
//		System.out.println(clicke);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int moveX = clicke.x-e.getLocationOnScreen().x;
		int moveY = clicke.y-e.getLocationOnScreen().y;
		if(Math.abs(moveX) > 20)
		{
			if(moveX > 0)
			{
				rotateLeft(moveX/10);
			}
			else
			{
				rotateRight(-moveX/10);
			}
		}
		if(Math.abs(moveY) > 20)
		{
			if(moveY > 0)
			{
				moveUpward(moveY/10);
			}
			else
			{
				moveDownward(-moveY/10);
			}
		}
//		System.out.println(clicke.x-e.getLocationOnScreen().x);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}



}
