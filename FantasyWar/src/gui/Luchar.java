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

public class Luchar extends JDialog {

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
		try {
			monstruoAleatorio();
		} catch (NombreInvalidoException e2) {

		}
		setBounds(100, 100, 435, 392);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(143, 188, 143));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTu = new JLabel("Tu:");
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
			lblCpu.setBounds(304, 11, 46, 14);
			contentPanel.add(lblCpu);
		}
		{
			JLabel lblSalud = new JLabel("Salud:");
			lblSalud.setBounds(10, 50, 46, 14);
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
		lblHabilidades.setBounds(235, 134, 161, 14);
		contentPanel.add(lblHabilidades);

		JLabel lblTusEstadisticas = new JLabel("Tus estadisticas:");
		lblTusEstadisticas.setBounds(10, 113, 191, 14);
		contentPanel.add(lblTusEstadisticas);

		{
			textFieldEnergia = new JTextField();
			textFieldEnergia.setEditable(false);
			textFieldEnergia.setBounds(101, 72, 86, 20);
			contentPanel.add(textFieldEnergia);
			textFieldEnergia.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		comboBoxHabilidad.setModel(new DefaultComboBoxModel(Ataques.getArray(Comunicacion.monstruoSeleccionado.getClass())));
		JButton btnNewButton = new JButton("Atacar");
		btnNewButton.setBounds(235, 244, 161, 66);
		contentPanel.add(btnNewButton);
		
		textFieldSalud = new JTextField();
		textFieldSalud.setEditable(false);
		textFieldSalud.setBounds(101, 47, 86, 20);
		contentPanel.add(textFieldSalud);
		textFieldSalud.setColumns(10);
		
		textFieldSaludCPU = new JTextField();
		textFieldSaludCPU.setEditable(false);
		textFieldSaludCPU.setBounds(273, 67, 86, 20);
		contentPanel.add(textFieldSaludCPU);
		textFieldSaludCPU.setColumns(10);
		
		JLabel lblNewLabelRazaCPU = new JLabel("New label");
		lblNewLabelRazaCPU.setBounds(234, 27, 46, 14);
		contentPanel.add(lblNewLabelRazaCPU);
		
		JLabel lblNewLabelClaseCPU = new JLabel("New label");
		lblNewLabelClaseCPU.setBounds(350, 27, 46, 14);
		contentPanel.add(lblNewLabelClaseCPU);
		{
			textPaneEstadisticas = new JTextPane();
			textPaneEstadisticas.setEditable(false);
			textPaneEstadisticas.setBounds(10, 134, 215, 176);
			contentPanel.add(textPaneEstadisticas);
		}
		actualizarVista();
		btnNewButton.addActionListener(new ActionListener() {
			private Component parentComponent;

			public void actionPerformed(ActionEvent e) {

				ataqueAleatorioCPU();
			
					//Selección de el ataque del turno
					int danno=0;
					try {
						Comunicacion.monstruoSeleccionado.luchar((Ataques)comboBoxHabilidad.getSelectedItem(), monstruoCPU);
					} catch (Exception e1) {
						// capturar
					}
					if(monstruoCPU.isMuerto()){
						setVisible(false);
						try {
							Comunicacion.jugador.getColeccionMonstruos().get(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
						} catch (MonstruoNoExisteException
								| NombreInvalidoException e1) {
							// capturar
						}
						Comunicacion.jugador.aumentarExp(1000);
						Comunicacion.monstruoSeleccionado.reestablecerse();
						JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
					}

					try {
						monstruoCPU.luchar(ataqueAleatorioCPU(), Comunicacion.monstruoSeleccionado);
					} catch (Exception e1) {
						// capturar
					}
					if(Comunicacion.monstruoSeleccionado.isMuerto()){
						setVisible(false);
						try {
							Comunicacion.jugador.getColeccionMonstruos().get
							(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
						} catch (MonstruoNoExisteException
								| NombreInvalidoException e1) {
							// capturar
						}
						Comunicacion.jugador.aumentarExp(50);
						Comunicacion.monstruoSeleccionado.reestablecerse();
						JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
					}
				
				actualizarVista();
			}

			private Ataques ataqueAleatorioCPU() {
				//Ataque aleatorio de la cpu
				int dados=(int) (Math.random()*(0-3)+3);

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
					default:
						break;
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
					default: 
						break;
					}
				}
				if(guerreroCPU != null){
					switch(dados){
					case 0:
						return Ataques.RAJAR;
					case 1:
						return Ataques.EJECUTAR;
					case 2:
						return Ataques.PROTECCION;
					case 3:
						return Ataques.FILOTORMENTA;
					default:
						break;
					}
				}
				return Ataques.BOLA_DE_FUEGO;
			}


		});

	}

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



	private void monstruoAleatorio() throws NombreInvalidoException{
		Razas razaSeleccionada = Razas.values()[(int) (Math.random()*(0-5)+5)];
		Clase claseSeleccionada = Clase.values()[(int) (Math.random()*(0-3)+3)];
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
}
