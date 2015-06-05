package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import clasesPrincipales.Ataques;
import clasesPrincipales.Clase;
import clasesPrincipales.Guerrero;
import clasesPrincipales.Mago;
import clasesPrincipales.Monstruo;
import clasesPrincipales.MonstruoNoExisteException;
import clasesPrincipales.NombreInvalidoException;
import clasesPrincipales.Razas;
import clasesPrincipales.Sacerdote;
import comunicacionConGui.Comunicacion;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.SwingConstants;

public class Luchar extends JDialog {
	private static final long serialVersionUID = 7047068511824982051L;
	private static final Component parentComponent = null;
	protected Component contentPane;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSeparator;
	private JTextField textFieldSeparator2;
	private JTextField textFieldEnergia;
	protected static Monstruo monstruoCPU;
	private JTextField textFieldSalud;
	private JTextField textFieldSaludCPU;
	private JTextPane textPaneEstadisticas;
	private JTextField textFieldNivelCPU;
	protected JButton btnNewButton;
	final JComboBox<Object> comboBoxHabilidad = new JComboBox<Object>();

	/**
	 * Create the dialog.
	 */
	public Luchar() {
		setResizable(false);
		setModal(true);
		setTitle("Lucha");
		monstruoAleatorio();
		setBounds(100, 100, 436, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(143, 188, 143));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTu = new JLabel(Comunicacion.getMonstruoSeleccionado().getNombre());
			lblTu.setHorizontalAlignment(SwingConstants.CENTER);
			lblTu.setFont(lblTu.getFont().deriveFont(lblTu.getFont().getStyle() | Font.BOLD));
			lblTu.setForeground(new Color(255, 255, 255));
			lblTu.setBounds(58, 11, 103, 14);
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
			JLabel lblCpu = new JLabel(monstruoCPU.getNombre());
			lblCpu.setHorizontalAlignment(SwingConstants.CENTER);
			lblCpu.setFont(lblCpu.getFont().deriveFont(lblCpu.getFont().getStyle() | Font.BOLD));
			lblCpu.setForeground(Color.WHITE);
			lblCpu.setBounds(280, 11, 97, 14);
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

		comboBoxHabilidad.setModel(new DefaultComboBoxModel<Object>(Ataques.getArray(Comunicacion.getMonstruoSeleccionado().getClass())));
		btnNewButton = new JButton("Atacar");
		btnNewButton.setBounds(235, 244, 161, 66);
		contentPanel.add(btnNewButton);
		
		textFieldSalud = new JTextField();
		textFieldSalud.setEditable(false);
		textFieldSalud.setBounds(114, 40, 86, 20);
		contentPanel.add(textFieldSalud);
		textFieldSalud.setColumns(10);
		
		textFieldSaludCPU = new JTextField();
		textFieldSaludCPU.setEditable(false);
		textFieldSaludCPU.setBounds(280, 71, 86, 20);
		contentPanel.add(textFieldSaludCPU);
		textFieldSaludCPU.setColumns(10);
		
		JLabel lblNewLabelRazaCPU = new JLabel(monstruoCPU.getRaza().toString());
		lblNewLabelRazaCPU.setFont(lblNewLabelRazaCPU.getFont().deriveFont(lblNewLabelRazaCPU.getFont().getStyle() | Font.BOLD));
		lblNewLabelRazaCPU.setForeground(Color.WHITE);
		lblNewLabelRazaCPU.setBounds(234, 27, 110, 14);
		contentPanel.add(lblNewLabelRazaCPU);
		
		JLabel lblNewLabelClaseCPU= new JLabel();
		lblNewLabelClaseCPU.setFont(lblNewLabelClaseCPU.getFont().deriveFont(lblNewLabelClaseCPU.getFont().getStyle() | Font.BOLD));
		lblNewLabelClaseCPU.setForeground(Color.WHITE);
		lblNewLabelClaseCPU.setBounds(235, 43, 96, 14);
		contentPanel.add(lblNewLabelClaseCPU);
		if(monstruoCPU.getClass() == Guerrero.class)
			lblNewLabelClaseCPU.setText(Clase.GUERRERO.toString());
		if(monstruoCPU.getClass() == Mago.class)
			lblNewLabelClaseCPU.setText(Clase.MAGO.toString());
		if(monstruoCPU.getClass() == Sacerdote.class)
			lblNewLabelClaseCPU.setText(Clase.SACERDOTE.toString());
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setBounds(359, 27, 60, 14);
		contentPanel.add(lblNivel);
		
		textFieldNivelCPU = new JTextField();
		textFieldNivelCPU.setEditable(false);
		textFieldNivelCPU.setBounds(359, 40, 60, 20);
		contentPanel.add(textFieldNivelCPU);
		textFieldNivelCPU.setColumns(10);
		textFieldNivelCPU.setText(""+monstruoCPU.getNivel());
		
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
			JLabel lblSalud_1 = new JLabel("Salud: ");
			lblSalud_1.setFont(lblSalud_1.getFont().deriveFont(lblSalud_1.getFont().getStyle() | Font.BOLD));
			lblSalud_1.setForeground(new Color(255, 255, 255));
			lblSalud_1.setBounds(224, 74, 46, 14);
			contentPanel.add(lblSalud_1);
		}
		
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("src\\imagenes\\fondoLucha.jpg"));
			label.setBounds(-38, -55, 677, 506);
			contentPanel.add(label);
		}
		
		
		

		actualizarVista();
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					turno(comboBoxHabilidad);
					actualizarVista();
			}
		});

	}

	/**
	 * Actualiza la vista de la ventana.
	 */
	protected void actualizarVista() {
		textFieldSaludCPU.setText((new Integer(monstruoCPU.getSaludActual())).toString());
		textFieldSalud.setText((new Integer(Comunicacion.getMonstruoSeleccionado().getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(Comunicacion.getMonstruoSeleccionado().getPotenciador())).toString());
		textPaneEstadisticas.setText("Nivel: "+Comunicacion.getMonstruoSeleccionado().getNivel()+
				"\rSalud máxima: "+Comunicacion.getMonstruoSeleccionado().getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.getMonstruoSeleccionado().getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.getMonstruoSeleccionado().getPoderHabilidad()+
				"\rArmadura: "+(Comunicacion.getMonstruoSeleccionado().getArmadura()+Comunicacion.getMonstruoSeleccionado().getArmaduraProvisional())+
				"\rResistencia mágica: "+(Comunicacion.getMonstruoSeleccionado().getResistenciaMagica()+Comunicacion.getMonstruoSeleccionado().getResistenciaMagicaProvisional())+
				"\rProbabilidad de impacto crítico: "+Comunicacion.getMonstruoSeleccionado().getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.getMonstruoSeleccionado().getProbabilidadEsquivar());
	}


	/**
	 * Genera un monstruo aleatorio.
	 * @throws NombreInvalidoException
	 * 				Si el nombre no empieza por mayuscula
	 */
	private void monstruoAleatorio(){
		Razas razaSeleccionada = Razas.values()[(int) (Math.random()*6)];
		Clase claseSeleccionada = Clase.values()[(int) (Math.random()*3)];
		int seleccionNivel = (int) (Math.random()*3);
		
		creaMonstruoCPU(razaSeleccionada, claseSeleccionada);
		
		estableceNivelCPU(seleccionNivel);
		
		monstruoCPU.reestablecerse();

	}
	
	/**
	 * Establece el nivel del monstruo CPU.
	 * @param seleccionNivel
	 * 				Por debajo (0), por encima(2) o igual(3) al nivel del monstruo del usuario.
	 */
	private void estableceNivelCPU(int seleccionNivel) {
		switch(seleccionNivel){
		case 0:
			if(Comunicacion.getMonstruoSeleccionado().getNivel() == 1){
				break;
			}
			while(Comunicacion.getMonstruoSeleccionado().getNivel()-1 > monstruoCPU.getNivel())
				monstruoCPU.aumentarNivel();
			break;
		case 1:
			while(Comunicacion.getMonstruoSeleccionado().getNivel() > monstruoCPU.getNivel())
				monstruoCPU.aumentarNivel();
			break;
		case 2:
			while(Comunicacion.getMonstruoSeleccionado().getNivel()+1 > monstruoCPU.getNivel())
				monstruoCPU.aumentarNivel();
			break;
		}
		monstruoCPU.reestablecerse();
	}

	/**
	 * Crea un monstruo.
	 * @param razaSeleccionada
	 * 			Raza aleatoria.
	 * @param claseSeleccionada
	 * 			Clase aleatoria.
	 */
	private void creaMonstruoCPU(Razas razaSeleccionada, Clase claseSeleccionada) {
		try {
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
		} catch (NombreInvalidoException e) {
			// No llega nunca.
			e.printStackTrace();
		}
	}	
	
	/**
	 * Establece el nombre de la energia dependiendo de la clase.
	 * @return Nombre de la energia.
	 */
	private String nombreEnergia(){
		if(Comunicacion.getMonstruoSeleccionado().getClass() == Mago.class)
			return "Mana";
		if(Comunicacion.getMonstruoSeleccionado().getClass() == Guerrero.class)
			return "Ira";
		if(Comunicacion.getMonstruoSeleccionado().getClass() == Sacerdote.class)
			return "Fe";
		return "¿?";
	}
	
	/**
	 * Realiza un turno de la lucha.
	 * @param comboBoxHabilidad
	 * 				ComboBox de habilidades.
	 */
	private void turno(final JComboBox<Object> comboBoxHabilidad) {
		atacaJugador(comboBoxHabilidad);
		if(cpuMuerto())
			return;

		atacaCPU();
		if(jugadorMuerto())
			return;
	}
	
	/**
	 * Combrueba si el monstruo del jugador está muerto.
	 * @return True si esta muerto, false lo contrario.
	 */
	private boolean jugadorMuerto() {
		if(Comunicacion.getMonstruoSeleccionado().isMuerto()){
			setVisible(false);
			try {
				Comunicacion.getJugador().getColeccionMonstruos().get
				(Comunicacion.getMonstruoSeleccionado().getNombre()).aumentarExp(100);
			} catch (MonstruoNoExisteException
					| NombreInvalidoException e1) {
				JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			Comunicacion.getJugador().aumentarExp(50);
			Comunicacion.getMonstruoSeleccionado().reestablecerse();
			Principal.actualizar();
			JOptionPane.showMessageDialog(parentComponent, "Tu monstruo ha muerto. Has perdido.");
			return true;
		}
		return false;
	}
	
	/**
	 * Ataca el monstruo de la CPU
	 */
	private void atacaCPU() {
		try {
			monstruoCPU.luchar(monstruoCPU.ataqueInteligente(Comunicacion.getMonstruoSeleccionado()), Comunicacion.getMonstruoSeleccionado());
		} catch (Exception e1) {
			//No se captura
			e1.printStackTrace();
		}
	}
	
	/**
	 * Comprueba si el monstruo de la cpu está muerto.
	 * @return	True si está muerto, false lo contrario.
	 */
	private boolean cpuMuerto() {
		if(monstruoCPU.isMuerto()){
			setVisible(false);
			try {
				Comunicacion.getJugador().getColeccionMonstruos().get(Comunicacion.getMonstruoSeleccionado().getNombre()).aumentarExp(2000);
			} catch (MonstruoNoExisteException
					| NombreInvalidoException e1) {
				JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			Comunicacion.getJugador().aumentarExp(1000);
			Comunicacion.getMonstruoSeleccionado().reestablecerse();
			Principal.actualizar();
			JOptionPane.showMessageDialog(parentComponent, "EL monstruo enemigo ha muerto. Has ganado.");
			return true;
		}
		return false;
	}
	
	/**
	 * Ataque del monstruo del jugador.
	 * @param comboBoxHabilidad
	 * 				ComboBox de habilidades.
	 */
	private void atacaJugador(final JComboBox<Object> comboBoxHabilidad) {
		try {
			Comunicacion.getMonstruoSeleccionado().luchar((Ataques)comboBoxHabilidad.getSelectedItem(), monstruoCPU);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
