package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import comunicacionConGui.Comunicacion;
import clasesPrincipales.GestionFicheros;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Nuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Component parentComponent;
	private Component contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Nuevo dialog = new Nuevo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Nuevo() {
		setModal(true);
		setTitle("Sobreescribir");
		setBounds(100, 100, 683, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnVaACrear = new JTextPane();
			txtpnVaACrear.setBackground(SystemColor.control);
			txtpnVaACrear.setEditable(false);
			txtpnVaACrear.setBounds(27, 33, 601, 22);
			txtpnVaACrear.setFont(new Font("Tahoma", Font.BOLD, 13));
			if(Comunicacion.archivoElegido!=null)
				txtpnVaACrear.setText("Va a crear una nueva partida. \u00BFDesea guardar los cambios hechos a "+Comunicacion.archivoElegido.getName()+"?");
			else
				txtpnVaACrear.setText("Va a crear una nueva partida. \u00BFDesea guardar los cambios hechos a Sin título?");
			contentPanel.add(txtpnVaACrear);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent e) {
						if(Comunicacion.guardado)
								try {
										GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
										Comunicacion.modificado=false;
										NuevoJugador nuevoJugador = new NuevoJugador();
										nuevoJugador.setVisible(true);
										Comunicacion.guardado=false;
										Comunicacion.monstruoSeleccionado = null;
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
								}
						else
							try {
								Principal.guardarComo();
								GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
								Comunicacion.modificado=false;
								NuevoJugador nuevoJugador = new NuevoJugador();
								nuevoJugador.setVisible(true);
								Comunicacion.monstruoSeleccionado = null;
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No guardar");
				cancelButton.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent arg0) {
						NuevoJugador nuevoJugador = new NuevoJugador();
						nuevoJugador.setVisible(true);
						Comunicacion.guardado=false;
						Comunicacion.modificado=false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
	}

}
