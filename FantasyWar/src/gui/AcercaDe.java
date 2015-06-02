package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcercaDe extends JDialog {
	private static final long serialVersionUID = 4223976270277122921L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setBounds(100, 100, 382, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnautorEmanuelGalvan = new JTextPane();
			txtpnautorEmanuelGalvan.setOpaque(false);
			txtpnautorEmanuelGalvan.setEditable(false);
			txtpnautorEmanuelGalvan.setContentType("text/html");
			txtpnautorEmanuelGalvan.setText("<html><strong>Autor:</strong><h1> Emanuel Galvan Fontalba</h1><p>Proyecto realizado para fin de curso de 1 de Desarrollo de aplicaciones web </p></html>");
			txtpnautorEmanuelGalvan.setBounds(10, 11, 359, 158);
			contentPanel.add(txtpnautorEmanuelGalvan);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
