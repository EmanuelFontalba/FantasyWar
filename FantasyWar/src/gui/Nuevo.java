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
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Nuevo extends JDialog {
	private static final long serialVersionUID = 8511107895833514297L;
	private final JPanel contentPanel = new JPanel();
	private Component contentPane;

	/**
	 * Create the dialog.
	 */
	public Nuevo() {
		setResizable(false);
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
			if(Comunicacion.getArchivoElegido()!=null)
				txtpnVaACrear.setText("Va a crear una nueva partida. \u00BFDesea guardar los cambios hechos a "+Comunicacion.getArchivoElegido().getName()+"?");
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
						setVisible(false);
						guardar();
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
						setVisible(false);
						nuevo();
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

	private void guardar() {
		if(Comunicacion.isGuardado())
				try {
						GestionFicheros.guardar(Comunicacion.getJugador(),Comunicacion.getArchivoElegido());
						Comunicacion.setModificado(false);
						nuevo();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
		else {
			if(Principal.guardarComo()){			
				NuevoJugador nuevoJugador = new NuevoJugador();
				nuevoJugador.setVisible(true);
				Comunicacion.setMonstruoSeleccionado(null);
			}
		}
		setVisible(false);
	}

	private void nuevo() {
		NuevoJugador nuevoJugador = new NuevoJugador();
		nuevoJugador.setVisible(true);
		Comunicacion.setGuardado(false);
		Comunicacion.setModificado(false);
		setVisible(false);
	}

}
