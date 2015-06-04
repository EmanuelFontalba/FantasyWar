package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clasesPrincipales.Clase;
import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.MonstruoNoExisteException;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Sacerdote;
import comunicacionConGui.Comunicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;









import java.awt.Color;

public class MostrarMonstruoPadre extends JDialog {
	private static final long serialVersionUID = 5894950866252302225L;
	private static final Component parentComponent = null;
	private static final Component contentPane = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldRaza;
	private JTextField textFieldClase;
	JButton buttonPrevious ;	
	JButton buttonNext ;
	private JTextField textFieldNivel;
	private JTextField textFieldExp;
	private JTextPane txtEstadisticas;
	JButton btnEliminar;
	JButton btnSeleccionar;
	JButton okButton;
	JLabel labelImg;

	/**
	 * Create the dialog.
	 */
	public MostrarMonstruoPadre() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(10, 11, 76, 14);
			contentPanel.add(lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setEditable(false);
			textFieldNombre.setBounds(104, 8, 86, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			textFieldNombre.setText(Comunicacion.getMonstruoEncontrado().getNombre());
		}
		{
			JLabel lblRaza = new JLabel("Raza:");
			lblRaza.setBounds(10, 61, 46, 14);
			contentPanel.add(lblRaza);
		}
		{
			textFieldRaza = new JTextField();
			textFieldRaza.setEditable(false);
			textFieldRaza.setBounds(104, 58, 86, 20);
			contentPanel.add(textFieldRaza);
			textFieldRaza.setColumns(10);
			if (Comunicacion.getMonstruoEncontrado() != null)
				textFieldRaza.setText(Comunicacion.getMonstruoEncontrado().getRaza().toString());
		}
		{
			JLabel lblClase = new JLabel("Clase:");
			lblClase.setBounds(10, 114, 46, 14);
			contentPanel.add(lblClase);
		}
		{
			textFieldClase = new JTextField();
			textFieldClase.setEditable(false);
			textFieldClase.setBounds(104, 111, 86, 20);
			contentPanel.add(textFieldClase);
			textFieldClase.setColumns(10);
			if (Comunicacion.getMonstruoEncontrado() != null){
				if(Comunicacion.getMonstruoEncontrado().getClass()== Guerrero.class)
					textFieldClase.setText(Clase.GUERRERO.toString());
				if(Comunicacion.getMonstruoEncontrado().getClass()== Mago.class)
					textFieldClase.setText(Clase.MAGO.toString());
				if(Comunicacion.getMonstruoEncontrado().getClass()== Sacerdote.class)
					textFieldClase.setText(Clase.SACERDOTE.toString());
			}
		}
		
		txtEstadisticas = new JTextPane();
		txtEstadisticas.setOpaque(false);
		txtEstadisticas.setBackground(Color.WHITE);
		if (Comunicacion.getMonstruoEncontrado() != null)
			txtEstadisticas.setText("Salud máxima: "+Comunicacion.getMonstruoEncontrado().getSaludMaxima()+
					"\rAtaque básico: "+Comunicacion.getMonstruoEncontrado().getAtaqueBasico()+
					"\rPoder de habilidad: "+Comunicacion.getMonstruoEncontrado().getPoderHabilidad()+
					"\rArmadura: "+Comunicacion.getMonstruoEncontrado().getArmadura()+
					"\rResistencia mágica: "+Comunicacion.getMonstruoEncontrado().getResistenciaMagica()+
					"\rProbabilidad de impacto crítico: "+Comunicacion.getMonstruoEncontrado().getProbabilidadCritico()+
					"\rProbabilidad de esquivar: "+Comunicacion.getMonstruoEncontrado().getProbabilidadEsquivar());
		txtEstadisticas.setEditable(false);
		txtEstadisticas.setBounds(214, 11, 210, 117);
		contentPanel.add(txtEstadisticas);
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(10, 174, 46, 14);
		contentPanel.add(lblNivel);
		
		JLabel lblExp = new JLabel("Exp:");
		lblExp.setBounds(10, 227, 46, 14);
		contentPanel.add(lblExp);
		
		textFieldNivel = new JTextField();
		textFieldNivel.setEditable(false);
		textFieldNivel.setBounds(104, 171, 86, 20);
		contentPanel.add(textFieldNivel);
		textFieldNivel.setColumns(10);
		textFieldNivel.setText(""+Comunicacion.getMonstruoEncontrado().getNivel());
		
		textFieldExp = new JTextField();
		textFieldExp.setEditable(false);
		textFieldExp.setBounds(104, 224, 86, 20);
		contentPanel.add(textFieldExp);
		textFieldExp.setText(""+Comunicacion.getMonstruoEncontrado().getExp());
		textFieldExp.setColumns(10);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			int indexPrimero=0;
			if(Comunicacion.getMonstruosEncontrados() !=null)
				indexPrimero = Comunicacion.getMonstruosEncontrados().getColeccion().indexOf(Comunicacion.getMonstruoEncontrado());
			buttonPrevious = new JButton("<");
			if(indexPrimero == 0)
				buttonPrevious.setEnabled(false);
			buttonPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					monstruoAnterior();

				}
			});
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarMonstruo();
				}
			});
			buttonPane.add(btnEliminar);
			buttonPane.add(buttonPrevious);
			btnEliminar.setVisible(false);
			
			buttonNext = new JButton(">");
			if(Comunicacion.getMonstruosEncontrados() != null)
				if(indexPrimero == Comunicacion.getMonstruosEncontrados().size()-1)
					buttonNext.setEnabled(false);
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					monstruoSiguiente();
				}
			});
			buttonPane.add(buttonNext);
			{
				btnSeleccionar = new JButton("Seleccionar");
				buttonPane.add(btnSeleccionar);
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
							Comunicacion.setMonstruoSeleccionado(Comunicacion.getMonstruoEncontrado());
							Principal.actualizar();
							setVisible(false);
					}
				});
			}
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						Comunicacion.setMonstruoEncontrado(null);
						Comunicacion.setMonstruosEncontrados(null);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			labelImg = new JLabel("");
			labelImg.setIcon(new ImageIcon(Comunicacion.getMonstruoEncontrado().getRutaImg()));
			labelImg.setBounds(214, 139, 210, 144);
			contentPanel.add(labelImg);
			
			actualizar();
		}
		
		
	}
	
	/**
	 * Actualiza la página.
	 */
	private void actualizar(){
		labelImg.setIcon(new ImageIcon(Comunicacion.getMonstruoEncontrado().getRutaImg()));
		txtEstadisticas.setText("Salud máxima: "+Comunicacion.getMonstruoEncontrado().getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.getMonstruoEncontrado().getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.getMonstruoEncontrado().getPoderHabilidad()+
				"\rArmadura: "+Comunicacion.getMonstruoEncontrado().getArmadura()+
				"\rResistencia mágica: "+Comunicacion.getMonstruoEncontrado().getResistenciaMagica()+
				"\rProbabilidad de impacto crítico: "+Comunicacion.getMonstruoEncontrado().getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.getMonstruoEncontrado().getProbabilidadEsquivar());
		textFieldNombre.setText(Comunicacion.getMonstruoEncontrado().getNombre());
		textFieldRaza.setText(Comunicacion.getMonstruoEncontrado().getRaza().toString());
		if(Comunicacion.getMonstruoEncontrado().getClass()== Guerrero.class)
			textFieldClase.setText(Clase.GUERRERO.toString());
		if(Comunicacion.getMonstruoEncontrado().getClass()== Mago.class)
			textFieldClase.setText(Clase.MAGO.toString());
		if(Comunicacion.getMonstruoEncontrado().getClass()== Sacerdote.class)
			textFieldClase.setText(Clase.SACERDOTE.toString());
		textFieldNivel.setText(""+Comunicacion.getMonstruoEncontrado().getNivel());
		textFieldExp.setText(""+Comunicacion.getMonstruoEncontrado().getExp());
	}
	
	/**
	 * Elimina un monstruo de la colección.
	 */
	private void eliminarMonstruo() {
		try {
			Comunicacion.getJugador().getColeccionMonstruos().remove(Comunicacion.getMonstruoEncontrado().getNombre());
			setVisible(false);
			JOptionPane.showMessageDialog(parentComponent, "Monstruo eliminado con éxito");
		} catch (NombreInvalidoException
				| MonstruoNoExisteException e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Muestra el monstruo anterior.
	 */
	private void monstruoAnterior() {
		int index = Comunicacion.getMonstruosEncontrados().getColeccion().indexOf(Comunicacion.getMonstruoEncontrado());
		if(index == 1){
			buttonPrevious.setEnabled(false); 
		}else
			buttonPrevious.setEnabled(true);
		if(0<=--index){
			Comunicacion.setMonstruoEncontrado(Comunicacion.getMonstruosEncontrados().get(index));
			buttonNext.setEnabled(true);
		}
		actualizar();
	}
	
	/**
	 * Muestra el siguiente monstruo.
	 */
	private void monstruoSiguiente() {
		int index = Comunicacion.getMonstruosEncontrados().getColeccion().indexOf(Comunicacion.getMonstruoEncontrado());
		if(index == Comunicacion.getMonstruosEncontrados().size()-2){
			buttonNext.setEnabled(false); 
		}else
			buttonNext.setEnabled(true);
		if(Comunicacion.getMonstruosEncontrados().size()>=++index){
			Comunicacion.setMonstruoEncontrado(Comunicacion.getMonstruosEncontrados().get(index));
			buttonPrevious.setEnabled(true);
		}
		actualizar();
	}
}
