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

public class LuchaMazmorra extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5287643969301302396L;
	private Component contentPane;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldSeparator;
	private JTextField textFieldSeparator2;
	private JTextField textFieldEnergia;
	private Monstruo monstruoCPU;
	private JTextField textFieldSalud;
	private JTextField textFieldSaludCPU;
	private JTextPane textPaneEstadisticas;
	private JTextField textFieldNivelCPU;

	/**
	 * Create the dialog.
	 */
	public LuchaMazmorra() {
		setResizable(false);
		setModal(true);
		setTitle("Lucha");
		try {
			estableceMonstruo();
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
			JLabel lblTu = new JLabel(NavegandoPorMazmorra.getMonstruoSeleccionado().getNombre());
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


		final JComboBox<Object> comboBoxHabilidad = new JComboBox<Object>();
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

		comboBoxHabilidad.setModel(new DefaultComboBoxModel<Object>(Ataques.getArray(NavegandoPorMazmorra.getMonstruoSeleccionado().getClass())));
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
			}
		});

	}

	/**
	 * Actualiza la vista de la ventana.
	 */
	private void actualizarVista() {
		textFieldSaludCPU.setText((new Integer(monstruoCPU.getSaludActual())).toString());
		textFieldSalud.setText((new Integer(NavegandoPorMazmorra.getMonstruoSeleccionado().getSaludActual())).toString());
		textFieldEnergia.setText((new Integer(NavegandoPorMazmorra.getMonstruoSeleccionado().getPotenciador())).toString());
		textPaneEstadisticas.setText("Nivel: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getNivel()+
				"\rSalud m�xima: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getSaludMaxima()+
				"\rAtaque b�sico: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getAtaqueBasico()+
				"\rPoder de habilidad: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getPoderHabilidad()+
				"\rArmadura: "+(NavegandoPorMazmorra.getMonstruoSeleccionado().getArmadura()+NavegandoPorMazmorra.getMonstruoSeleccionado().getArmaduraProvisional())+
				"\rResistencia m�gica: "+(NavegandoPorMazmorra.getMonstruoSeleccionado().getResistenciaMagica()+NavegandoPorMazmorra.getMonstruoSeleccionado().getResistenciaMagicaProvisional())+
				"\rProbabilidad de impacto cr�tico: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+NavegandoPorMazmorra.getMonstruoSeleccionado().getProbabilidadEsquivar());
	}



	private void estableceMonstruo() throws NombreInvalidoException{
		monstruoCPU = Comunicacion.getMonstruoMazmorra();
	}	
	
	private String nombreEnergia(){
		if(NavegandoPorMazmorra.getMonstruoSeleccionado().getClass() == Mago.class)
			return "Mana";
		if(NavegandoPorMazmorra.getMonstruoSeleccionado().getClass() == Guerrero.class)
			return "Ira";
		if(NavegandoPorMazmorra.getMonstruoSeleccionado().getClass() == Sacerdote.class)
			return "Fe";
		return "�?";
	}

	private void turno(final JComboBox<Object> comboBoxHabilidad) {
		try {
			NavegandoPorMazmorra.getMonstruoSeleccionado().luchar((Ataques)comboBoxHabilidad.getSelectedItem(), monstruoCPU);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if(monstruoCPU.isMuerto()){
			setVisible(false);
			JOptionPane.showMessageDialog(null, "EL monstruo enemigo ha muerto. Has ganado.");
			return;
		}

		try {
			monstruoCPU.luchar(monstruoCPU.ataqueInteligente(NavegandoPorMazmorra.getMonstruoSeleccionado()), NavegandoPorMazmorra.getMonstruoSeleccionado());
		} catch (Exception e1) {
			//No se captura
		}
		if(NavegandoPorMazmorra.getMonstruoSeleccionado().isMuerto()){
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Tu monstruo ha muerto. Has perdido.");
			return;
		}

		actualizarVista();
	}
	
}
