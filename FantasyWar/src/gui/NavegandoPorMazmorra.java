package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clasesPrincipales.Monstruo;
import comunicacionConGui.Comunicacion;
import mazmorras.Mazmorras;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NavegandoPorMazmorra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int index = 0;
	private JButton siguienteBoton;
	private JLabel tituloLabel;
	private JLabel esbirro1Label;
	private JLabel esbirro2label;
	private JLabel esbirro3label;
	private JLabel esbirro4label;
	private JLabel bosslabel;
	private static Monstruo monstruoSeleccionado;
	

	/**
	 * Create the dialog.
	 */
	public NavegandoPorMazmorra() {
		setBounds(100, 100, 317, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tituloLabel = new JLabel("Monstruos");
		tituloLabel.setFont(new Font("AR DESTINE", Font.BOLD, 16));
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLabel.setBounds(10, 11, 281, 28);
		contentPanel.add(tituloLabel);
		
		inicializaVentana();
		
		esbirro1Label.setHorizontalAlignment(SwingConstants.CENTER);
		esbirro1Label.setForeground(Color.GRAY);
		esbirro1Label.setFont(new Font("Tahoma", Font.BOLD, 11));
		esbirro1Label.setBounds(10, 50, 281, 22);
		contentPanel.add(esbirro1Label);
		
		
		esbirro2label.setHorizontalAlignment(SwingConstants.CENTER);
		esbirro2label.setForeground(Color.GRAY);
		esbirro2label.setFont(new Font("Tahoma", Font.BOLD, 11));
		esbirro2label.setBounds(10, 83, 281, 22);
		contentPanel.add(esbirro2label);
		
		
		esbirro3label.setHorizontalAlignment(SwingConstants.CENTER);
		esbirro3label.setForeground(Color.GRAY);
		esbirro3label.setFont(new Font("Tahoma", Font.BOLD, 11));
		esbirro3label.setBounds(10, 116, 281, 22);
		contentPanel.add(esbirro3label);
		
		
		esbirro4label.setHorizontalAlignment(SwingConstants.CENTER);
		esbirro4label.setForeground(Color.GRAY);
		esbirro4label.setFont(new Font("Tahoma", Font.BOLD, 11));
		esbirro4label.setBounds(10, 149, 281, 22);
		contentPanel.add(esbirro4label);
		
		
		bosslabel.setHorizontalAlignment(SwingConstants.CENTER);
		bosslabel.setForeground(Color.GRAY);
		bosslabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		bosslabel.setBounds(10, 182, 281, 22);
		contentPanel.add(bosslabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				siguienteBoton = new JButton("Siguiente monstruo");
				siguienteBoton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						siguienteMonstruo();
					}
				});
				buttonPane.add(siguienteBoton);
				getRootPane().setDefaultButton(siguienteBoton);
			}
			{
				JButton salirBoton = new JButton("Salir de mazmorra");
				salirBoton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						terminarMazmorra();
					}
				});
				salirBoton.setActionCommand("Cancel");
				buttonPane.add(salirBoton);
			}
		}
	}


	protected void terminarMazmorra() {
		if(index >= 5){
			Comunicacion.getJugador().aumentarExp(2000);
			Comunicacion.getMonstruoSeleccionado().aumentarExp(4000);
			JOptionPane.showMessageDialog(null, "Has completado con éxito la mazmorra.");
		}
		Comunicacion.getMonstruoSeleccionado().reestablecerse();
		setVisible(false);
	}


	private void siguienteMonstruo() {
		if(Comunicacion.getMonstruoSeleccionado().isMuerto()){
			JOptionPane.showMessageDialog(null, "Tu monstruo a muerto, vuelve a intentarlo en otro momento.");
			System.exit(0);
		}
		Comunicacion.setMonstruoMazmorra(Comunicacion.getMazmorra().getMazmorra().get(index));
		LuchaMazmorra lucha = new LuchaMazmorra();
		actualizar();
		index++;
		lucha.setVisible(true);
		if(index >= 5)
			siguienteBoton.setEnabled(false);
		
	}
	
	private void actualizar(){
		switch (index) {
		case 0:
			esbirro1Label.setForeground(Color.RED);
			break;
		case 1:
			esbirro2label.setForeground(Color.RED);
			break;
		case 2:
			esbirro3label.setForeground(Color.RED);
			break;
		case 3:
			esbirro4label.setForeground(Color.RED);
			break;
		case 4:
			bosslabel.setForeground(Color.RED);
			break;
		default:
			break;
		}
	}
	
	private void inicializaVentana(){
		esbirro1Label = new JLabel(Comunicacion.getMazmorra().getMazmorra().get(0).getNombre()+"       Nivel: "+
				Comunicacion.getMazmorra().getMazmorra().get(0).getNivel());
		esbirro2label = new JLabel(Comunicacion.getMazmorra().getMazmorra().get(1).getNombre()+"       Nivel: "+
				Comunicacion.getMazmorra().getMazmorra().get(1).getNivel());
		esbirro3label = new JLabel(Comunicacion.getMazmorra().getMazmorra().get(2).getNombre()+"       Nivel: "+
				Comunicacion.getMazmorra().getMazmorra().get(2).getNivel());
		esbirro4label = new JLabel(Comunicacion.getMazmorra().getMazmorra().get(3).getNombre()+"       Nivel: "+
				Comunicacion.getMazmorra().getMazmorra().get(3).getNivel());
		bosslabel = new JLabel(Comunicacion.getMazmorra().getMazmorra().get(4).getNombre()+"       Nivel: "+
				Comunicacion.getMazmorra().getMazmorra().get(4).getNivel());
		setMonstruoSeleccionado(Comunicacion.getMonstruoSeleccionado());
	}


	public static Monstruo getMonstruoSeleccionado() {
		return monstruoSeleccionado;
	}


	private static void setMonstruoSeleccionado(Monstruo monstruo) {
		monstruoSeleccionado = monstruo;
	}
}
