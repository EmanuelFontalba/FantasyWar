package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AyudaCreación extends JDialog {
	private static final long serialVersionUID = -2321473708703526262L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AyudaCreación() {
		setResizable(false);
		setModal(true);
		setTitle("Ayuda para la creaci\u00F3n de monstruos.");
		setBounds(100, 100, 450, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JEditorPane dtrpnCreacinDeMonstruos = new JEditorPane();
			dtrpnCreacinDeMonstruos.setEditable(false);
			dtrpnCreacinDeMonstruos.setContentType("text/html");
			dtrpnCreacinDeMonstruos.setBackground(SystemColor.control);
			dtrpnCreacinDeMonstruos.setText("<html><h1>Creaci\u00F3n de monstruos.</h1>\r\n\r\n<strong>Consejos:</strong>\r\n<ul>\r\n<li>Debes de tomar buenas decisiones, ya que algunas razas beneficiar a algunas clases y/o hacerles el juego m\u00E1s dificil.</li>\r\n\r\n<li>Para saber que ofrecen las razas y clases, visita la ayuda Clases y Razas.</li>\r\n</ul>\r\n<br />\r\n<strong>Buenas combinaciones:</strong>\r\n<ul><li>NoMuerto - Mago</li>\r\n<li>Orco -Guerrero</li></ul>");
			dtrpnCreacinDeMonstruos.setBounds(0, 0, 434, 287);
			contentPanel.add(dtrpnCreacinDeMonstruos);
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
