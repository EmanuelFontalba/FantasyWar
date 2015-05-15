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
import enumeraciones.AtaquesGuerrero;
import enumeraciones.AtaquesMago;
import enumeraciones.AtaquesSacerdote;
import enumeraciones.Clase;
import enumeraciones.Razas;
import enumeraciones.TipoDeDanno;
import excepciones.ErrorEnElTurnoException;
import excepciones.FeInsuficienteException;
import excepciones.IraInsuficienteException;
import excepciones.ManaInsuficienteException;
import excepciones.NombreInvalidoException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Luchar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSeparator;
	private JTextField textFieldVidaJugador;
	private JTextField textFieldVidaCPU;
	private JTextField textFieldSeparator2;
	private JTextField txtEstadisticas;
	private JTextField textFieldEnergia;
	
	
	private Mago magoCPU;
	private Guerrero guerreroCPU;
	private Sacerdote sacerdoteCPU;
	private Mago magoJugador;
	private Guerrero guerreroJugador;
	private Sacerdote sacerdoteJugador;
	

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
			textFieldVidaJugador = new JTextField();
			textFieldVidaJugador.setText("sald");
			textFieldVidaJugador.setEditable(false);
			textFieldVidaJugador.setBounds(101, 47, 86, 20);
			contentPanel.add(textFieldVidaJugador);
			textFieldVidaJugador.setColumns(10);
		}
		{
			textFieldVidaCPU = new JTextField();
			textFieldVidaCPU.setText("salud enemiga");
			textFieldVidaCPU.setEditable(false);
			textFieldVidaCPU.setBounds(235, 47, 86, 20);
			contentPanel.add(textFieldVidaCPU);
			textFieldVidaCPU.setColumns(10);
			if(magoCPU != null)
				textFieldVidaCPU.setText((new Integer (magoCPU.getSaludActual())).toString());
			if(sacerdoteCPU != null)
				textFieldVidaCPU.setText((new Integer (sacerdoteCPU.getSaludActual())).toString());
			if(guerreroCPU != null)
				textFieldVidaCPU.setText((new Integer (guerreroCPU.getSaludActual())).toString());
			
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
		{
			txtEstadisticas = new JTextField();
			txtEstadisticas.setEditable(false);
			txtEstadisticas.setText("estadisticas");
			txtEstadisticas.setBounds(10, 132, 191, 178);
			contentPanel.add(txtEstadisticas);
			txtEstadisticas.setColumns(10);
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
		
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.GUERRERO){
			guerreroJugador = (Guerrero) Comunicacion.monstruoSeleccionado;
			comboBoxHabilidad.setModel(new DefaultComboBoxModel(AtaquesGuerrero.values()));
			actualizarVistaGuerrero();
			{
				JLabel lblEnergia = new JLabel("Ira:");
				lblEnergia.setBounds(10, 75, 46, 14);
				contentPanel.add(lblEnergia);
			}
		}
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.MAGO){
			magoJugador = (Mago) Comunicacion.monstruoSeleccionado;
			comboBoxHabilidad.setModel(new DefaultComboBoxModel(AtaquesMago.values()));
			actualizarVistaMago();
			{
				JLabel lblEnergia = new JLabel("Maná:");
				lblEnergia.setBounds(10, 75, 46, 14);
				contentPanel.add(lblEnergia);
			}
		}
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.SACERDOTE){
			sacerdoteJugador = (Sacerdote) Comunicacion.monstruoSeleccionado;
			comboBoxHabilidad.setModel(new DefaultComboBoxModel(AtaquesSacerdote.values()));
			actualizarVistaSacerdote();
			{
				JLabel lblEnergia = new JLabel("Fé:");
				lblEnergia.setBounds(10, 75, 46, 14);
				contentPanel.add(lblEnergia);
			}
		}
		
		JButton btnNewButton = new JButton("Atacar");
		btnNewButton.setBounds(235, 244, 161, 66);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			private Component parentComponent;

			public void actionPerformed(ActionEvent e) {
				//Selección de el ataque del turno
				int danno=0;
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.GUERRERO)
					try {
						switch(comboBoxHabilidad.getSelectedItem().toString()){
						case "RAJAR":
							danno=guerreroJugador.rajar();
							break;
						case "EJECUTAR":
							danno=guerreroJugador.ejecutar();
							break;
						case "PROTECCION":
							danno=0;
							guerreroJugador.proteccion();
							break;
						case "FILOTORMENTA":
							danno=guerreroJugador.filoTormenta();
							break;
						}
					} catch (IraInsuficienteException e2) {
						// capturar
					}
						
					
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.MAGO){
					try {
						switch(comboBoxHabilidad.getSelectedItem().toString()){
						case "BOLA_DE_FUEGO":
							danno=magoJugador.bolaDeFuego();
							break;
						case "ESCUDO_DE_ESCARCHA":
							danno=0;
							magoJugador.escudoDeEscarcha();
							break;
						case "MEDITACION":
							danno=0;
							magoJugador.meditacion();
							break;
						case "LLUVIA_DE_METEOROS":
							danno=magoJugador.lluviaDeMeteoros();
							break;
						}
					} catch (ManaInsuficienteException e2) {
						// capturar
					}
				}
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.SACERDOTE){
					try {
						switch(comboBoxHabilidad.getSelectedItem().toString()){
						case "ENERGIA_DIVINA":
							danno=sacerdoteJugador.energiaDivina();
							break;
						case "CURACION_ANCESTRAL":
							danno=0;
							sacerdoteJugador.curacionAncestral();
							break;
						case "ESCUDO_DIVINO":
							danno=0;
							sacerdoteJugador.escudoDivino();
							break;
						case "CURACION_ABSOLUTA":
							danno=0;
							sacerdoteJugador.curacionAbsoluta();
							break;
						}
					} catch (FeInsuficienteException e2) {
						// capturar
					}
				}
				
				//Ataque aleatorio de la cpu
				int dannoCPU=0;
				int dados=(int) Math.random()*(0-3)+3;
				
				if(magoCPU != null){
					try {
						switch(dados){
						case 0:
							dannoCPU=magoCPU.bolaDeFuego();
							break;
						case 1:
							dannoCPU=0;
							magoCPU.escudoDeEscarcha();
							break;
						case 2:
							dannoCPU=0;
							magoCPU.meditacion();
							break;
						case 3:
							dannoCPU=magoCPU.lluviaDeMeteoros();
							break;
						}
					} catch (ManaInsuficienteException e2) {
						// capturar
					}
				}
				if(sacerdoteCPU != null){
					try {
						switch(dados){
						case 0:
							dannoCPU=sacerdoteCPU.energiaDivina();
							break;
						case 1:
							dannoCPU=0;
							sacerdoteCPU.curacionAncestral();
							break;
						case 2:
							dannoCPU=0;
							sacerdoteCPU.escudoDivino();
							break;
						case 3:
							dannoCPU=0;
							sacerdoteCPU.curacionAbsoluta();
							break;
						}
					} catch (FeInsuficienteException e2) {
						// capturar
					}
				}
					
				if(guerreroCPU != null){
					try {
						switch(dados){
						case 0:
							dannoCPU=guerreroCPU.rajar();
							break;
						case 1:
							dannoCPU=guerreroCPU.ejecutar();
							break;
						case 2:
							dannoCPU=0;
							guerreroCPU.proteccion();
							break;
						case 3:
							dannoCPU=guerreroCPU.filoTormenta();
							break;
						}
					} catch (IraInsuficienteException e2) {
						// capturar
					}
				}
					
				//Comienzo del turno
				
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.GUERRERO){
					try {
						if(magoCPU != null){
							turnoJugador(danno);
							actualizarVistaGuerrero();
							if(magoCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUMago();
							if(guerreroJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							magoCPU.regeneracionMana();
							guerreroJugador.regeneracionDeIra(dannoCPU);
						}
						if(sacerdoteCPU != null){
							turnoJugador(danno);
							actualizarVistaGuerrero();
							if(sacerdoteCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUSacerdote();
							if(guerreroJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							guerreroJugador.regeneracionDeIra(dannoCPU);
							sacerdoteCPU.regeneracionFe();
						}
								
						if(guerreroCPU != null){
							
							turnoJugador(danno);
							actualizarVistaGuerrero();
							if(guerreroCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUGuerrero();
							if(guerreroJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							guerreroCPU.regeneracionDeIra(danno);
							guerreroJugador.regeneracionDeIra(dannoCPU);
						}
					} catch (Exception e1) {
						// CAPTURAR
					}
					
				}						
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.MAGO){
					try {
						if(magoCPU != null){
							turnoJugador(danno);
							actualizarVistaMago();
							if(magoCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUMago();
							if(magoJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							magoJugador.regeneracionMana();
							magoCPU.regeneracionMana();
						}
						if(sacerdoteCPU != null){
							turnoJugador(danno);
							actualizarVistaMago();
							if(sacerdoteCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUSacerdote();
							if(magoJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							magoJugador.regeneracionMana();
							sacerdoteCPU.regeneracionFe();
						}
								
						if(guerreroCPU != null){
							
							turnoJugador(danno);
							actualizarVistaMago();
							if(guerreroCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							turnoCPU(dannoCPU);
							actualizarVistaCPUGuerrero();
							if(magoJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							magoJugador.regeneracionMana();
							guerreroCPU.regeneracionDeIra(danno);
						}
					} catch (Exception e1) {
						// CAPTURAR
					}
					
				}
				if(Comunicacion.monstruoSeleccionado.CLASE == Clase.SACERDOTE){
					try {
						if(magoCPU != null){
							turnoJugador( danno);
							actualizarVistaSacerdote();
							if(magoCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							
							turnoCPU(dannoCPU);
							actualizarVistaCPUMago();
							if(sacerdoteJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							sacerdoteJugador.regeneracionFe();
							magoCPU.regeneracionMana();
						}
						if(sacerdoteCPU != null){
							turnoJugador(danno);
							actualizarVistaSacerdote();
							if(sacerdoteCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							
							turnoCPU(dannoCPU);
							actualizarVistaCPUSacerdote();
							if(sacerdoteJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							sacerdoteJugador.regeneracionFe();
							sacerdoteCPU.regeneracionFe();
						}
								
						if(guerreroCPU != null){
							
							turnoJugador(danno);
							actualizarVistaSacerdote();
							if(guerreroCPU.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(2000);
								setVisible(false);
							}
							
							turnoCPU(dannoCPU);
							actualizarVistaCPUGuerrero();
							if(sacerdoteJugador.isMuerto()){
								JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Hs perdido.");
								Comunicacion.jugador.getColeccionMonstruos().get
									(Comunicacion.monstruoSeleccionado.getNombre()).aumentarExp(100);
								setVisible(false);
							}
							sacerdoteJugador.regeneracionFe();
							guerreroCPU.regeneracionDeIra(dannoCPU);
						}
					} catch (Exception e1) {
						// CAPTURAR
					}
					
				}
			}

			
		});
		
	}
	
	private void actualizarVistaCPUGuerrero(){
		textFieldVidaCPU.setText((new Integer (guerreroCPU.getSaludActual()).toString()));
	}
	private void actualizarVistaCPUMago(){
		textFieldVidaCPU.setText((new Integer (magoCPU.getSaludActual()).toString()));
	}
	private void actualizarVistaCPUSacerdote(){
		textFieldVidaCPU.setText((new Integer (sacerdoteCPU.getSaludActual()).toString()));
	}

	private void actualizarVistaGuerrero() {
		textFieldVidaJugador.setText((new Integer (guerreroJugador.getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(guerreroJugador.getIra())).toString());
		txtEstadisticas.setText("Salud máxima: "+guerreroJugador.getSaludMaxima()+
				"\nAtaque básico: "+guerreroJugador.getAtaqueBasico()+
				"\nPoder de habilidad: "+guerreroJugador.getPoderHabilidad()+
				"\nArmadura: "+guerreroJugador.getArmadura()+
				"\nResistencia mágica: "+guerreroJugador.getResistenciaMagica()+
				"\nProbabilidad de impacto crítico: "+guerreroJugador.getProbabilidadCritico()+
				"\nProbabilidad de esquivar: "+guerreroJugador.getProbabilidadEsquivar());
	}

	private void actualizarVistaMago() {
		textFieldVidaJugador.setText((new Integer (magoJugador.getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(magoJugador.getMana())).toString());
		txtEstadisticas.setText("Salud máxima: "+magoJugador.getSaludMaxima()+
				"\nAtaque básico: "+magoJugador.getAtaqueBasico()+
				"\nPoder de habilidad: "+magoJugador.getPoderHabilidad()+
				"\nArmadura: "+magoJugador.getArmadura()+
				"\nResistencia mágica: "+magoJugador.getResistenciaMagica()+
				"\nProbabilidad de impacto crítico: "+magoJugador.getProbabilidadCritico()+
				"\nProbabilidad de esquivar: "+magoJugador.getProbabilidadEsquivar());
	}
	
	private void actualizarVistaSacerdote() {
		textFieldVidaJugador.setText((new Integer (sacerdoteJugador.getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(sacerdoteJugador.getFe())).toString());
		txtEstadisticas.setText("Salud máxima: "+sacerdoteJugador.getSaludMaxima()+
				"\nAtaque básico: "+sacerdoteJugador.getAtaqueBasico()+
				"\nPoder de habilidad: "+sacerdoteJugador.getPoderHabilidad()+
				"\nArmadura: "+sacerdoteJugador.getArmadura()+
				"\nResistencia mágica: "+sacerdoteJugador.getResistenciaMagica()+
				"\nProbabilidad de impacto crítico: "+sacerdoteJugador.getProbabilidadCritico()+
				"\nProbabilidad de esquivar: "+sacerdoteJugador.getProbabilidadEsquivar());
	}
	
	private void monstruoAleatorio() throws NombreInvalidoException{
		Razas razaSeleccionada = Razas.values()[(int) Math.random()*(0-5)+5];
		Clase claseSeleccionada = Clase.values()[(int) Math.random()*(0-2)+2];
		switch(claseSeleccionada){
		case GUERRERO:
			switch(razaSeleccionada){
			case DEMONIO:
				guerreroCPU = new Guerrero("Monstruo", Razas.DEMONIO);
				break;
			case ELFO:
				guerreroCPU = new Guerrero("Monstruo", Razas.ELFO);
				break;
			case ENANO:
				guerreroCPU = new Guerrero("Monstruo", Razas.ENANO);
				break;
			case HUMANO:
				guerreroCPU = new Guerrero("Monstruo", Razas.HUMANO);
				break;
			case NOMUERTO:
				guerreroCPU = new Guerrero("Monstruo", Razas.NOMUERTO);
				break;
			case ORCO:
				guerreroCPU = new Guerrero("Monstruo", Razas.ORCO);
				break;
			}break;
		case MAGO:
			switch(razaSeleccionada){
			case DEMONIO:
				magoCPU = new Mago("Monstruo", Razas.DEMONIO);
				break;
			case ELFO:
				magoCPU = new Mago("Monstruo", Razas.ELFO);
				break;
			case ENANO:
				magoCPU = new Mago("Monstruo", Razas.ENANO);
				break;
			case HUMANO:
				magoCPU = new Mago("Monstruo", Razas.HUMANO);
				break;
			case NOMUERTO:
				magoCPU = new Mago("Monstruo", Razas.NOMUERTO);
				break;
			case ORCO:
				magoCPU = new Mago("Monstruo", Razas.ORCO);
				break;
			}
		case SACERDOTE:
			switch(razaSeleccionada){
			case DEMONIO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.DEMONIO);
				break;
			case ELFO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.ELFO);
				break;
			case ENANO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.ENANO);
				break;
			case HUMANO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.HUMANO);
				break;
			case NOMUERTO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.NOMUERTO);
				break;
			case ORCO:
				sacerdoteCPU = new Sacerdote("Monstruo", Razas.ORCO);
				break;
			}
		}
		
	}
	
	
		public boolean turnoJugador(int dannoVerdadero) throws ErrorEnElTurnoException{
			if(Comunicacion.monstruoSeleccionado.CLASE == Clase.GUERRERO){
					if(magoCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=guerreroJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=magoCPU.getProbabilidadEsquivar())
							return false;
						if(guerreroJugador.getTipo() == TipoDeDanno.FISICO){
							if(critico){
								danno=(dannoVerdadero - magoCPU.getArmadura())*2;
								if(danno<=0)
									return false;
								magoCPU.disminuirSaludActual(danno) ;
								return true;
							}
							danno=dannoVerdadero - magoCPU.getArmadura();
							if(danno<=0)
								return false;
							magoCPU.disminuirSaludActual(danno) ;
							return true;
						}
					}
					if(sacerdoteCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=guerreroJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=sacerdoteCPU.getProbabilidadEsquivar())
							return false;
						
						if(guerreroJugador.getTipo() == TipoDeDanno.FISICO){
							if(critico){
								danno=(dannoVerdadero - sacerdoteCPU.getArmadura())*2;
								if(danno<=0)
									return false;
								sacerdoteCPU.disminuirSaludActual(danno) ;
								return true;
							}
							danno=dannoVerdadero - sacerdoteCPU.getArmadura();
							if(danno<=0)
								return false;
							sacerdoteCPU.disminuirSaludActual(danno) ;
							return true;
						}
					}		
					if(guerreroCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=guerreroJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=guerreroCPU.getProbabilidadEsquivar())
							return false;
						if(guerreroJugador.getTipo() == TipoDeDanno.FISICO){
							if(critico){
								danno=(dannoVerdadero - guerreroCPU.getArmadura())*2;
								if(danno<=0)
									return false;
								guerreroCPU.disminuirSaludActual(danno) ;
								return true;
							}
							danno=dannoVerdadero - guerreroCPU.getArmadura();
							if(danno<=0)
								return false;
							guerreroCPU.disminuirSaludActual(danno) ;
							return true;
						}
					}
			}						
			if(Comunicacion.monstruoSeleccionado.CLASE == Clase.MAGO){
					if(magoCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=magoJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=magoCPU.getProbabilidadEsquivar())
							return false;
						
						if(magoJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - magoCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								magoCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - magoCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							magoCPU.disminuirSaludActual(danno);
							return true;
						}
					}
					if(sacerdoteCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=magoJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=sacerdoteCPU.getProbabilidadEsquivar())
							return false;
						
						if(magoJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - sacerdoteCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								sacerdoteCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - sacerdoteCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							sacerdoteCPU.disminuirSaludActual(danno);
							return true;
						}
					}		
					if(guerreroCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=magoJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=guerreroCPU.getProbabilidadEsquivar())
							return false;
						
						if(magoJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - guerreroCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								guerreroCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - guerreroCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							guerreroCPU.disminuirSaludActual(danno);
							return true;
						}
					}
			}
			if(Comunicacion.monstruoSeleccionado.CLASE == Clase.SACERDOTE){
					if(magoCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=sacerdoteJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=magoCPU.getProbabilidadEsquivar())
							return false;
						
						if(sacerdoteJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - magoCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								magoCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - magoCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							magoCPU.disminuirSaludActual(danno);
							return true;
						}
					}
					if(sacerdoteCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=sacerdoteJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=sacerdoteCPU.getProbabilidadEsquivar())
							return false;
						
						if(sacerdoteJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - sacerdoteCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								sacerdoteCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - sacerdoteCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							sacerdoteCPU.disminuirSaludActual(danno);
							return true;
						}
					}
							
					if(guerreroCPU != null){
						int dadosDefensor = (int) Math.random()*(0-100)+100;
						int dadosAtacante = (int) Math.random()*(0-100)+100;
						boolean critico=false;
						int danno;
						
						if(dannoVerdadero==0)
							return false;
						if(dadosAtacante<=sacerdoteJugador.getProbabilidadCritico())
							critico=true;
						if(dadosDefensor<=guerreroCPU.getProbabilidadEsquivar())
							return false;
						
						if(sacerdoteJugador.getTipo() == TipoDeDanno.MAGICO){
							if(critico){
								danno=((dannoVerdadero - guerreroCPU.getResistenciaMagica())*2);
								if(danno<=0)
									return false;
								guerreroCPU.disminuirSaludActual(danno);
								return true;
							}
							danno=dannoVerdadero - guerreroCPU.getResistenciaMagica();
							if(danno<=0)
								return false;
							guerreroCPU.disminuirSaludActual(danno);
							return true;
						}
					}
					throw new ErrorEnElTurnoException("No se ha podido realizar el turno");
			}
			return false;
		}
		
	
	public boolean turnoCPU(int dannoVerdadero) throws ErrorEnElTurnoException{
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.GUERRERO){
				if(magoCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=magoCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=guerreroJugador.getProbabilidadEsquivar())
						return false;
					
					if(magoCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - guerreroJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							guerreroJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - guerreroJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						guerreroJugador.disminuirSaludActual(danno);
						return true;
					}
				}
				if(sacerdoteCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=sacerdoteCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=guerreroJugador.getProbabilidadEsquivar())
						return false;
					
					if(sacerdoteCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - guerreroJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							guerreroJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - guerreroJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						guerreroJugador.disminuirSaludActual(danno);
						return true;
					}
				}		
				if(guerreroCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=guerreroCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=guerreroJugador.getProbabilidadEsquivar())
						return false;
					if(guerreroCPU.getTipo() == TipoDeDanno.FISICO){
						if(critico){
							danno=(dannoVerdadero - guerreroJugador.getArmadura())*2;
							if(danno<=0)
								return false;
							guerreroJugador.disminuirSaludActual(danno) ;
							return true;
						}
						danno=dannoVerdadero - guerreroJugador.getArmadura();
						if(danno<=0)
							return false;
						guerreroJugador.disminuirSaludActual(danno) ;
						return true;
					}
				}
		}						
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.MAGO){
				if(magoCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=magoCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=magoJugador.getProbabilidadEsquivar())
						return false;
					
					if(magoCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - magoJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							magoJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - magoJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						magoJugador.disminuirSaludActual(danno);
						return true;
					}
				}
				if(sacerdoteCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=sacerdoteCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=magoJugador.getProbabilidadEsquivar())
						return false;
					
					if(sacerdoteCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - magoJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							magoJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - magoJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						magoJugador.disminuirSaludActual(danno);
						return true;
					}
				}		
				if(guerreroCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=guerreroCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=magoJugador.getProbabilidadEsquivar())
						return false;
					
					if(guerreroCPU.getTipo() == TipoDeDanno.FISICO){
						if(critico){
							danno=(dannoVerdadero - magoJugador.getArmadura())*2;
							if(danno<=0)
								return false;
							magoJugador.disminuirSaludActual(danno) ;
							return true;
						}
						danno=dannoVerdadero - magoJugador.getArmadura();
						if(danno<=0)
							return false;
						magoJugador.disminuirSaludActual(danno) ;
						return true;
					}
				}
		}
		if(Comunicacion.monstruoSeleccionado.CLASE == Clase.SACERDOTE){
				if(magoCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=magoCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=sacerdoteJugador.getProbabilidadEsquivar())
						return false;
					
					if(magoCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - sacerdoteJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							sacerdoteJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - sacerdoteJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						sacerdoteJugador.disminuirSaludActual(danno);
						return true;
					}
				}
				if(sacerdoteCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=sacerdoteCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=sacerdoteJugador.getProbabilidadEsquivar())
						return false;
					
					if(sacerdoteCPU.getTipo() == TipoDeDanno.MAGICO){
						if(critico){
							danno=((dannoVerdadero - sacerdoteJugador.getResistenciaMagica())*2);
							if(danno<=0)
								return false;
							sacerdoteJugador.disminuirSaludActual(danno);
							return true;
						}
						danno=dannoVerdadero - sacerdoteJugador.getResistenciaMagica();
						if(danno<=0)
							return false;
						sacerdoteJugador.disminuirSaludActual(danno);
						return true;
					}
				}
						
				if(guerreroCPU != null){
					int dadosDefensor = (int) Math.random()*(0-100)+100;
					int dadosAtacante = (int) Math.random()*(0-100)+100;
					boolean critico=false;
					int danno;
					
					if(dannoVerdadero==0)
						return false;
					if(dadosAtacante<=guerreroCPU.getProbabilidadCritico())
						critico=true;
					if(dadosDefensor<=sacerdoteJugador.getProbabilidadEsquivar())
						return false;
					
					if(guerreroCPU.getTipo() == TipoDeDanno.FISICO){
						if(critico){
							danno=(dannoVerdadero - sacerdoteJugador.getArmadura())*2;
							if(danno<=0)
								return false;
							sacerdoteJugador.disminuirSaludActual(danno) ;
							return true;
						}
						danno=dannoVerdadero - sacerdoteJugador.getArmadura();
						if(danno<=0)
							return false;
						sacerdoteJugador.disminuirSaludActual(danno) ;
						return true;
					}
				}
				throw new ErrorEnElTurnoException("No se ha podido realizar el turno");
		}
		return false;
	}	
}
