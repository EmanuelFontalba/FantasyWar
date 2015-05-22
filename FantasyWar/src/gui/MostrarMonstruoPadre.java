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

import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Sacerdote;
import comunicacionConGui.Comunicacion;
import enumeraciones.Clase;
import enumeraciones.Razas;
import excepciones.MonstruoNoExisteException;
import excepciones.NombreInvalidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;


import java.awt.Color;

@SuppressWarnings("serial")
public class MostrarMonstruoPadre extends JDialog {

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarMonstruoPadre dialog = new MostrarMonstruoPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarMonstruoPadre() {
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
			textFieldNombre.setText(Comunicacion.monstruoEncontrado.getNombre());
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
			if (Comunicacion.monstruoEncontrado != null)
				textFieldRaza.setText(Comunicacion.monstruoEncontrado.getRaza().toString());
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
			if (Comunicacion.monstruoEncontrado != null){
				if(Comunicacion.monstruoEncontrado.getClass()== Guerrero.class)
					textFieldClase.setText(Clase.GUERRERO.toString());
				if(Comunicacion.monstruoEncontrado.getClass()== Mago.class)
					textFieldClase.setText(Clase.MAGO.toString());
				if(Comunicacion.monstruoEncontrado.getClass()== Sacerdote.class)
					textFieldClase.setText(Clase.SACERDOTE.toString());
			}
		}
		
		txtEstadisticas = new JTextPane();
		txtEstadisticas.setOpaque(false);
		txtEstadisticas.setBackground(Color.WHITE);
		if (Comunicacion.monstruoEncontrado != null)
			txtEstadisticas.setText("Salud máxima: "+Comunicacion.monstruoEncontrado.getSaludMaxima()+
					"\rAtaque básico: "+Comunicacion.monstruoEncontrado.getAtaqueBasico()+
					"\rPoder de habilidad: "+Comunicacion.monstruoEncontrado.getPoderHabilidad()+
					"\rArmadura: "+Comunicacion.monstruoEncontrado.getArmadura()+
					"\rResistencia mágica: "+Comunicacion.monstruoEncontrado.getResistenciaMagica()+
					"\rProbabilidad de impacto crítico: "+Comunicacion.monstruoEncontrado.getProbabilidadCritico()+
					"\rProbabilidad de esquivar: "+Comunicacion.monstruoEncontrado.getProbabilidadEsquivar());
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
		textFieldNivel.setText(""+Comunicacion.monstruoEncontrado.getNivel());
		
		textFieldExp = new JTextField();
		textFieldExp.setEditable(false);
		textFieldExp.setBounds(104, 224, 86, 20);
		contentPanel.add(textFieldExp);
		textFieldExp.setText(""+Comunicacion.monstruoEncontrado.getExp());
		textFieldExp.setColumns(10);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			int indexPrimero=0;
			if(Comunicacion.monstruosEncontrados !=null)
				indexPrimero = Comunicacion.monstruosEncontrados.getColeccion().indexOf(Comunicacion.monstruoEncontrado);
			buttonPrevious = new JButton("<");
			if(indexPrimero == 0)
				buttonPrevious.setEnabled(false);
			buttonPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = Comunicacion.monstruosEncontrados.getColeccion().indexOf(Comunicacion.monstruoEncontrado);
					if(index == 1){
						buttonPrevious.setEnabled(false); 
					}else
						buttonPrevious.setEnabled(true);
					if(0<=--index){
						Comunicacion.monstruoEncontrado = Comunicacion.monstruosEncontrados.get(index);
						buttonNext.setEnabled(true);
					}
					actualizar();

				}
			});
			
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				private Component contentPane;
				private Component parentComponent;

				public void actionPerformed(ActionEvent e) {
					try {
						Comunicacion.jugador.getColeccionMonstruos().remove(Comunicacion.monstruoEncontrado.getNombre());
						setVisible(false);
						JOptionPane.showMessageDialog(parentComponent, "Monstruo eliminado con éxito");
					} catch (NombreInvalidoException
							| MonstruoNoExisteException e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			buttonPane.add(btnEliminar);
			buttonPane.add(buttonPrevious);
			btnEliminar.setVisible(false);
			
			buttonNext = new JButton(">");
			if(Comunicacion.monstruosEncontrados != null)
				if(indexPrimero == Comunicacion.monstruosEncontrados.size()-1)
					buttonNext.setEnabled(false);
			buttonNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = Comunicacion.monstruosEncontrados.getColeccion().indexOf(Comunicacion.monstruoEncontrado);
					if(index == Comunicacion.monstruosEncontrados.size()-2){
						buttonNext.setEnabled(false); 
					}else
						buttonNext.setEnabled(true);
					if(Comunicacion.monstruosEncontrados.size()>=++index){
						Comunicacion.monstruoEncontrado = Comunicacion.monstruosEncontrados.get(index);
						buttonPrevious.setEnabled(true);
					}
					actualizar();
				}
			});
			buttonPane.add(buttonNext);
			{
				btnSeleccionar = new JButton("Seleccionar");
				buttonPane.add(btnSeleccionar);
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
							Comunicacion.monstruoSeleccionado = Comunicacion.monstruoEncontrado;
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
						Comunicacion.monstruoEncontrado = null;
						Comunicacion.monstruosEncontrados = null;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			labelImg = new JLabel("");
			labelImg.setIcon(new ImageIcon(Comunicacion.monstruoEncontrado.getRutaImg()));
			labelImg.setBounds(214, 139, 210, 144);
			contentPanel.add(labelImg);
			
			actualizar();
		}
		
		
	}
	
	private void actualizar(){
		labelImg.setIcon(new ImageIcon(Comunicacion.monstruoEncontrado.getRutaImg()));
		txtEstadisticas.setText("Salud máxima: "+Comunicacion.monstruoEncontrado.getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.monstruoEncontrado.getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.monstruoEncontrado.getPoderHabilidad()+
				"\rArmadura: "+Comunicacion.monstruoEncontrado.getArmadura()+
				"\rResistencia mágica: "+Comunicacion.monstruoEncontrado.getResistenciaMagica()+
				"\rProbabilidad de impacto crítico: "+Comunicacion.monstruoEncontrado.getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.monstruoEncontrado.getProbabilidadEsquivar());
		textFieldNombre.setText(Comunicacion.monstruoEncontrado.getNombre());
		textFieldRaza.setText(Comunicacion.monstruoEncontrado.getRaza().toString());
		if(Comunicacion.monstruoEncontrado.getClass()== Guerrero.class)
			textFieldClase.setText(Clase.GUERRERO.toString());
		if(Comunicacion.monstruoEncontrado.getClass()== Mago.class)
			textFieldClase.setText(Clase.MAGO.toString());
		if(Comunicacion.monstruoEncontrado.getClass()== Sacerdote.class)
			textFieldClase.setText(Clase.SACERDOTE.toString());
		textFieldNivel.setText(""+Comunicacion.monstruoEncontrado.getNivel());
		textFieldExp.setText(""+Comunicacion.monstruoEncontrado.getExp());
	}
}
