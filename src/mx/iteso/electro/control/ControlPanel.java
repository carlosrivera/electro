package mx.iteso.electro.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import mx.iteso.electro.figuras.AbstractCharge;
import mx.iteso.electro.figuras.CargaPuntual;
import mx.iteso.electro.figuras.Volumen;
import mx.iteso.electro.util.VectorR3;

public class ControlPanel extends JPanel implements ActionListener,MouseListener{

	static int count = 0;
	CargaPuntual prueba;
	Vector <AbstractCharge> cargas;
	JLabel name;
	JTextField cargatxt;
	JTextField posXtxt;
	JTextField posYtxt;
	JTextField posZtxt;
	JList<AbstractCharge> list;
	JTextField cargaQtxt;
	JTextField posQXtxt;
	JTextField posQYtxt;
	JTextField posQZtxt;
	JTextField widthtxt;
	JTextField heightxt;
	JTextField depthtxt;
	JTextField fuerzaQtxt;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlPanel(Vector<AbstractCharge> charges)
	{
		super();

		prueba = new CargaPuntual(0,1, new VectorR3(0,0,0));
		cargas = charges;

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.white);

		JLabel nameQ = new JLabel("Carga de prueba Q");
		nameQ.setFont(new Font(nameQ.getFont().getName(),Font.BOLD, nameQ.getFont().getSize()+5));
		JLabel cargaQ = new JLabel("Q=");
		cargaQtxt = new JTextField("0");
		JLabel posQX = new JLabel("X=");
		posQXtxt = new JTextField("0");
		JLabel posQY = new JLabel("Y=");
		posQYtxt = new JTextField("0");
		JLabel posQZ = new JLabel("Z=");
		posQZtxt = new JTextField("0");
		JLabel fuerzaQ = new JLabel("F(total)=");
		fuerzaQtxt = new JTextField("0");

		list = new JList<AbstractCharge>(cargas);
		list.addMouseListener(this);
		JScrollPane cargasPanel = new JScrollPane(list);
		JButton agregar = new JButton("Agregar carga puntual");
		agregar.setActionCommand("agregar");
		agregar.addActionListener(this);
		agregar.setBackground(Color.black);
		agregar.setForeground(Color.white);
		JButton agregarF = new JButton("Agregar distribucion de carga");
		agregarF.setActionCommand("agregarF");
		agregarF.addActionListener(this);
		agregarF.setBackground(Color.black);
		agregarF.setForeground(Color.white);
		JButton quitar = new JButton("Quitar carga");
		quitar.setActionCommand("quitar");
		quitar.addActionListener(this);
		quitar.setBackground(Color.black);
		quitar.setForeground(Color.white);

		name = new JLabel("Carga q");
		name.setFont(new Font(name.getFont().getName(),Font.BOLD, name.getFont().getSize()+5));
		JLabel carga = new JLabel("Q=");
		cargatxt = new JTextField("0");
		JLabel posX = new JLabel("X=");
		posXtxt = new JTextField("0");
		JLabel posY = new JLabel("Y=");
		posYtxt = new JTextField("0");
		JLabel posZ = new JLabel("Z=");
		posZtxt = new JTextField("0");
		JLabel width = new JLabel("Width=");
		widthtxt = new JTextField("0");
		JLabel heigh = new JLabel("Heigh=");
		heightxt = new JTextField("0");
		JLabel depth = new JLabel("Depth=");
		depthtxt = new JTextField("0");

		JButton calcular = new JButton("Calcular fuerza");
		calcular.setActionCommand("calcular");
		calcular.addActionListener(this);
		calcular.setBackground(Color.black);
		calcular.setForeground(Color.white);

		this.add(nameQ);
		this.add(cargaQ);
		this.add(cargaQtxt);
		this.add(posQX);
		this.add(posQXtxt);
		this.add(posQY);
		this.add(posQYtxt);
		this.add(posQZ);
		this.add(posQZtxt);
		this.add(fuerzaQ);
		this.add(fuerzaQtxt);

		this.add(calcular);

		this.add(name);
		this.add(cargasPanel);

		this.add(carga);
		this.add(cargatxt);
		this.add(posX);
		this.add(posXtxt);
		this.add(posY);
		this.add(posYtxt);
		this.add(posZ);
		this.add(posZtxt);
		this.add(width);
		this.add(widthtxt);
		this.add(heigh);
		this.add(heightxt);
		this.add(depth);
		this.add(depthtxt);

		this.add(agregar);
		this.add(agregarF);
		this.add(quitar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("agregar"))
		{
			double carga = Double.parseDouble(cargatxt.getText());
			double x = Double.parseDouble(posXtxt.getText());
			double y = Double.parseDouble(posYtxt.getText());
			double z = Double.parseDouble(posZtxt.getText());
			cargas.add(new CargaPuntual(count, carga,new VectorR3(x,y,z)));
			DefaultListModel<AbstractCharge> mod = new DefaultListModel<AbstractCharge>();
			count++;
			for(AbstractCharge obj : cargas)
			{
				mod.addElement(obj);
			}
			list.setModel(mod);
			cargatxt.setText("0");
			posXtxt.setText("0");
			posYtxt.setText("0");
			posZtxt.setText("0");
		}
		if(e.getActionCommand().equals("agregarF"))
		{
			double carga = Double.parseDouble(cargatxt.getText());
			double x = Double.parseDouble(posXtxt.getText());
			double y = Double.parseDouble(posYtxt.getText());
			double z = Double.parseDouble(posZtxt.getText());
			double w = Double.parseDouble(widthtxt.getText());
			double h = Double.parseDouble(heightxt.getText());
			double d = Double.parseDouble(depthtxt.getText());
			cargas.add(new Volumen(count, carga,new VectorR3(x,y,z), new VectorR3(w,h,d)));
			DefaultListModel<AbstractCharge> mod = new DefaultListModel<AbstractCharge>();
			for(AbstractCharge obj : cargas)
			{
				mod.addElement(obj);
			}
			list.setModel(mod);
			cargatxt.setText("0");
			posXtxt.setText("0");
			posYtxt.setText("0");
			posZtxt.setText("0");
			widthtxt.setText("0");
			heightxt.setText("0");
			depthtxt.setText("0");
		}
		if(e.getActionCommand().equals("quitar"))
		{
			cargas.remove(list.getSelectedValue());
			DefaultListModel<AbstractCharge> mod = new DefaultListModel<AbstractCharge>();
			for(AbstractCharge obj : cargas)
			{
				mod.addElement(obj);
			}
			list.setModel(mod);
			cargatxt.setText("0");
			posXtxt.setText("0");
			posYtxt.setText("0");
			posZtxt.setText("0");
		}
		if(e.getActionCommand().equals("calcular"))
		{
			double carga = Double.parseDouble(cargaQtxt.getText());
			double x = Double.parseDouble(posQXtxt.getText());
			double y = Double.parseDouble(posQYtxt.getText());
			double z = Double.parseDouble(posQZtxt.getText());
			prueba.setPosition(new VectorR3(x,y,z));
			prueba.set_charge(carga);
			fuerzaQtxt.setText(prueba.calculateForce(cargas).toString());

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1)
		{
			int index = list.locationToIndex(e.getPoint());
			ListModel<AbstractCharge> dlm = list.getModel();
			if(dlm.getSize()>index && index>=0)
			{
				CargaPuntual item = (CargaPuntual) dlm.getElementAt(index);;
				list.ensureIndexIsVisible(index);
				cargatxt.setText(""+item.get_charge());
				 posXtxt.setText(""+item.getPos().x);
				 posYtxt.setText(""+item.getPos().y);
				 posZtxt.setText(""+item.getPos().z);
			}
		}
		if(e.getClickCount() == 2)
		{
			int index = list.locationToIndex(e.getPoint());
			ListModel<AbstractCharge> dlm = list.getModel();
			if(dlm.getSize()>index && index>=0)
			{
				CargaPuntual item = (CargaPuntual) dlm.getElementAt(index);;
				list.ensureIndexIsVisible(index);
				cargaQtxt.setText(""+item.get_charge());
				 posQXtxt.setText(""+item.getPos().x);
				 posQYtxt.setText(""+item.getPos().y);
				 posQZtxt.setText(""+item.getPos().z);
				 fuerzaQtxt.setText(""+item.getFuerza());
				 prueba = item;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}
