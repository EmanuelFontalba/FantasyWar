package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Monstruos.Guerrero;
import Monstruos.Mago;
import Monstruos.Monstruo;
import Monstruos.Sacerdote;
import comunicacionConGui.Comunicacion;
import clasesPrincipales.Combate;
import enumeraciones.Ataques;
import enumeraciones.Clase;
import enumeraciones.Razas;
import enumeraciones.TipoDeDanno;
import excepciones.ErrorEnElTurnoException;
import excepciones.FeInsuficienteException;
import excepciones.IraInsuficienteException;
import excepciones.ManaInsuficienteException;
import excepciones.MonstruoNoExisteException;
import excepciones.NombreInvalidoException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Luchar extends JDialog {

	private Component contentPane;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSeparator;
	private JTextField textFieldSeparator2;
	private JTextField textFieldEnergia;


	private Mago magoCPU;
	private Guerrero guerreroCPU;
	private Sacerdote sacerdoteCPU;
	private Mago magoJugador;
	private Guerrero guerreroJugador;
	private Sacerdote sacerdoteJugador;

	private Monstruo monstruoCPU;
	private JTextField textFieldSalud;
	private JTextField textFieldSaludCPU;
	private JTextPane textPaneEstadisticas;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Luchar dialog = new Luchar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public Luchar() {
		setTitle("Lucha");
		try {
			monstruoAleatorio();
		} catch (NombreInvalidoException e2) {
			JOptionPane.showMessageDialog(contentPane, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		setBounds(100, 100, 436, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(143, 188, 143));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTu = new JLabel("Tu:");
			lblTu.setFont(lblTu.getFont().deriveFont(lblTu.getFont().getStyle() | Font.BOLD));
			lblTu.setForeground(new Color(255, 255, 255));
			lblTu.setBounds(87, 11, 46, 14);
			contentPanel.add(lblTu);
		}
		{
			textFieldSeparator = new JTextField();
			textFieldSeparator.setEnabled(false);
			textFieldSeparator.setEditable(false);
			textFieldSeparator.setBounds(210, 0, 4, 101);
			contentPanel.add(textFieldSeparator);
			textFieldSeparator.setColumns(10);
		}
		{
			JLabel lblCpu = new JLabel("CPU:");
			lblCpu.setFont(lblCpu.getFont().deriveFont(lblCpu.getFont().getStyle() | Font.BOLD));
			lblCpu.setForeground(Color.WHITE);
			lblCpu.setBounds(304, 11, 46, 14);
			contentPanel.add(lblCpu);
		}
		{
			JLabel lblSalud = new JLabel("Salud:");
			lblSalud.setFont(lblSalud.getFont().deriveFont(lblSalud.getFont().getStyle() | Font.BOLD));
			lblSalud.setForeground(new Color(255, 255, 255));
			lblSalud.setBounds(22, 43, 46, 14);
			contentPanel.add(lblSalud);
		}
		{
			textFieldSeparator2 = new JTextField();
			textFieldSeparator2.setEditable(false);
			textFieldSeparator2.setBounds(0, 98, 419, 4);
			contentPanel.add(textFieldSeparator2);
			textFieldSeparator2.setColumns(10);
		}


		final JComboBox comboBoxHabilidad = new JComboBox();
		comboBoxHabilidad.setBounds(235, 159, 161, 20);
		contentPanel.add(comboBoxHabilidad);


		JLabel lblHabilidades = new JLabel("Habilidades:");
		lblHabilidades.setFont(lblHabilidades.getFont().deriveFont(lblHabilidades.getFont().getStyle() | Font.BOLD));
		lblHabilidades.setForeground(new Color(255, 255, 255));
		lblHabilidades.setBounds(235, 134, 161, 14);
		contentPanel.add(lblHabilidades);

		JLabel lblTusEstadisticas = new JLabel("Tus estadisticas:");
		lblTusEstadisticas.setFont(lblTusEstadisticas.getFont().deriveFont(lblTusEstadisticas.getFont().getStyle() | Font.BOLD));
		lblTusEstadisticas.setForeground(new Color(255, 255, 255));
		lblTusEstadisticas.setBounds(23, 109, 191, 14);
		contentPanel.add(lblTusEstadisticas);

		{
			textFieldEnergia = new JTextField();
			textFieldEnergia.setEditable(false);
			textFieldEnergia.setBounds(114, 71, 86, 20);
			contentPanel.add(textFieldEnergia);
			textFieldEnergia.setColumns(10);
			
		}

		comboBoxHabilidad.setModel(new DefaultComboBoxModel(Ataques.getArray(Comunicacion.monstruoSeleccionado.getClass())));
		JButton btnNewButton = new JButton("Atacar");
		btnNewButton.setBounds(235, 244, 161, 66);
		contentPanel.add(btnNewButton);
		
		textFieldSalud = new JTextField();
		textFieldSalud.setEditable(false);
		textFieldSalud.setBounds(114, 40, 86, 20);
		contentPanel.add(textFieldSalud);
		textFieldSalud.setColumns(10);
		
		textFieldSaludCPU = new JTextField();
		textFieldSaludCPU.setEditable(false);
		textFieldSaludCPU.setBounds(310, 67, 86, 20);
		contentPanel.add(textFieldSaludCPU);
		textFieldSaludCPU.setColumns(10);
		
		JLabel lblNewLabelRazaCPU = new JLabel(monstruoCPU.getRaza().toString());
		lblNewLabelRazaCPU.setFont(lblNewLabelRazaCPU.getFont().deriveFont(lblNewLabelRazaCPU.getFont().getStyle() | Font.BOLD));
		lblNewLabelRazaCPU.setForeground(Color.WHITE);
		lblNewLabelRazaCPU.setBounds(234, 27, 162, 14);
		contentPanel.add(lblNewLabelRazaCPU);
		
		JLabel lblNewLabelClaseCPU= new JLabel();
		lblNewLabelClaseCPU.setFont(lblNewLabelClaseCPU.getFont().deriveFont(lblNewLabelClaseCPU.getFont().getStyle() | Font.BOLD));
		lblNewLabelClaseCPU.setForeground(Color.WHITE);
		lblNewLabelClaseCPU.setBounds(235, 43, 161, 14);
		contentPanel.add(lblNewLabelClaseCPU);
		if(monstruoCPU.getClass() == Guerrero.class)
			lblNewLabelClaseCPU.setText(Clase.GUERRERO.toString());
		if(monstruoCPU.getClass() == Mago.class)
			lblNewLabelClaseCPU.setText(Clase.MAGO.toString());
		if(monstruoCPU.getClass() == Sacerdote.class)
			lblNewLabelClaseCPU.setText(Clase.SACERDOTE.toString());
		
		{
			textPaneEstadisticas = new JTextPane();
			textPaneEstadisticas.setOpaque(false);
			textPaneEstadisticas.setForeground(new Color(255, 255, 255));
			textPaneEstadisticas.setFont(textPaneEstadisticas.getFont().deriveFont(textPaneEstadisticas.getFont().getStyle() | Font.BOLD));
			textPaneEstadisticas.setEditable(false);
			textPaneEstadisticas.setBounds(10, 134, 215, 176);
			contentPanel.add(textPaneEstadisticas);
		}
		{
			JLabel labelEnergia = new JLabel(nombreEnergia());
			labelEnergia.setFont(labelEnergia.getFont().deriveFont(labelEnergia.getFont().getStyle() | Font.BOLD));
			labelEnergia.setForeground(Color.WHITE);
			labelEnergia.setBounds(22, 73, 46, 14);
			contentPanel.add(labelEnergia);
		}
		
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("src\\imagenes\\fondoLucha.jpg"));
			label.setBounds(-38, -55, 677, 506);
			contentPanel.add(label);
		}
		{
			JLabel lblSalud_1 = new JLabel("Salud: ");
			lblSalud_1.setFont(lblSalud_1.getFont().deriveFont(lblSalud_1.getFont().getStyle() | Font.BOLD));
			lblSalud_1.setForeground(new Color(255, 255, 255));
			lblSalud_1.setBounds(245, 71, 46, 14);
			contentPanel.add(lblSalud_1);
		}
		

		actualizarVista();
		btnNewButton.addActionListener(new ActionListener() {
			private Component parentComponent;

			public void actionPerformed(ActionEvent e) {


			
					//Selección de el ataque del turno
					int danno=0;
					try {
						Comunicacion.monstruoSeleccionado.luchar((Ataques)comboBoxHabilidad.getSelectedItem(), monstruoCPU);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					if(monstruoCPU.isMuerto()){
						setVisible(false);
						try {
							Comunicacion.jugador.getColeccionMonstruos().get(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
						} catch (MonstruoNoExisteException
								| NombreInvalidoException e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						Comunicacion.jugador.aumentarExp(1000);
						Comunicacion.monstruoSeleccionado.reestablecerse();
						Principal.actualizar();
						JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
						return;
					}

					try {
						monstruoCPU.luchar(ataqueAleatorioCPU(), Comunicacion.monstruoSeleccionado);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					if(Comunicacion.monstruoSeleccionado.isMuerto()){
						setVisible(false);
						try {
							Comunicacion.jugador.getColeccionMonstruos().get
							(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
						} catch (MonstruoNoExisteException
								| NombreInvalidoException e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						Comunicacion.jugador.aumentarExp(50);
						Comunicacion.monstruoSeleccionado.reestablecerse();
						Principal.actualizar();
						JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
						return;
					}
				
				actualizarVista();
			}

			private Ataques ataqueAleatorioCPU() {
				//Ataque aleatorio de la cpu
				int dados=(int) (Math.random()*4);

				if(monstruoCPU.getClass() == Mago.class){
					switch(dados){
					case 0:
						return Ataques.BOLA_DE_FUEGO;
					case 1:
						return Ataques.ESCUDO_DE_ESCARCHA;
					case 2:
						return Ataques.LLUVIA_DE_METEOROS;
					case 3:
						return Ataques.MEDITACION;
					}
				}
				if(monstruoCPU.getClass() == Sacerdote.class){
					switch(dados){
					case 0:
						return Ataques.ENERGIA_DIVINA;
					case 1:
						return Ataques.CURACION_ANCESTRAL;
					case 2:
						return Ataques.ESCUDO_DIVINO;
					case 3:
						return Ataques.CURACION_ABSOLUTA;
					}
				}
				if(monstruoCPU.getClass() == Guerrero.class){
					switch(dados){
					case 0:
						return Ataques.RAJAR;
					case 1:
						return Ataques.EJECUTAR;
					case 2:
						return Ataques.PROTECCION;
					case 3:
						return Ataques.FILOTORMENTA;
					}
				}
				return null;
			}


		});

	}

	/**
	 * Actualiza la vista de la ventana.
	 */
	private void actualizarVista() {
		textFieldSaludCPU.setText((new Integer(monstruoCPU.getSaludActual())).toString());
		textFieldSalud.setText((new Integer(Comunicacion.monstruoSeleccionado.getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(Comunicacion.monstruoSeleccionado.getPotenciador())).toString());
		textPaneEstadisticas.setText("Salud máxima: "+Comunicacion.monstruoSeleccionado.getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.monstruoSeleccionado.getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.monstruoSeleccionado.getPoderHabilidad()+
				"\rArmadura: "+(Comunicacion.monstruoSeleccionado.getArmadura()+Comunicacion.monstruoSeleccionado.getArmaduraProvisional())+
				"\rResistencia mágica: "+(Comunicacion.monstruoSeleccionado.getResistenciaMagica()+Comunicacion.monstruoSeleccionado.getResistenciaMagicaProvisional())+
				"\rProbabilidad de impacto crítico: "+Comunicacion.monstruoSeleccionado.getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.monstruoSeleccionado.getProbabilidadEsquivar());
	}


	/**
	 * Genera un monstruo aleatorio.
	 * @throws NombreInvalidoException
	 * 				Si el nombre no empieza por mayuscula
	 */
	private void monstruoAleatorio() throws NombreInvalidoException{
		Razas razaSeleccionada = Razas.values()[(int) (Math.random()*6)];
		Clase claseSeleccionada = Clase.values()[(int) (Math.random()*3)];
		switch(claseSeleccionada){
		case GUERRERO:
			monstruoCPU = new Guerrero("Monstruo", razaSeleccionada);
			break;
		case MAGO:
			monstruoCPU = new Mago("Monstruo", razaSeleccionada);
			break;
		case SACERDOTE:
			monstruoCPU = new Sacerdote("Monstruo", razaSeleccionada);
			break;
		}

	}	
	
	private String nombreEnergia(){
		if(Comunicacion.monstruoSeleccionado.getClass() == Mago.class)
			return "Mana";
		if(Comunicacion.monstruoSeleccionado.getClass() == Guerrero.class)
			return "Ira";
		if(Comunicacion.monstruoSeleccionado.getClass() == Sacerdote.class)
			return "Fe";
		return "¿?";
	}
}
