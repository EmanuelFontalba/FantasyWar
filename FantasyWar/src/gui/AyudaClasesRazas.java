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
import javax.swing.JTextPane;

public class AyudaClasesRazas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AyudaClasesRazas() {
		setResizable(false);
		setModal(true);
		setTitle("Ayuda de clases y razas");
		setBounds(100, 100, 588, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnclasesmagoCampos = new JTextPane();
			txtpnclasesmagoCampos.setEditable(false);
			txtpnclasesmagoCampos.setOpaque(false);
			txtpnclasesmagoCampos.setContentType("text/html");
			txtpnclasesmagoCampos.setText("<html>\r\n<h1>Clases:</h1>\r\n<ul>\r\n\t<li><strong>Mago:</strong>\r\n\t\t<p>Campos: man\u00E1=200 regeneraci\u00F3n de mana= 10% por turno<br />\r\n\t\tHechizos: bolaDeFuego, escudoDeEscarcha, meditaci\u00F3n, lluviaDeMete\u00F3ros.</p></li>\r\n\t<li><strong>Guerrero</strong>\r\n\t\t<p>Campos: ira= 100 regeneraci\u00F3n de ira = 4% por turno<br />\r\n\t\tHechizos: Rajar, Ejecutar, Protecci\u00F3n, filoTormenta.</p></li>\r\n\t<li><strong>Sacerdote</strong>\r\n\t\t<p>Campos: f\u00E9 = 150 regeneraci\u00F3n de f\u00E9 = 15 % por turno<br />\r\n\t\tHechizos: energiaDivina, curaci\u00F3nAncestral, escudoDivino, curaci\u00F3nAbsoluta</p></li>\r\n</ul>\r\n<h1>Razas:</h2>\r\n<ul>\r\n\t<li><strong>Orco</strong> ( salud 1200, at.B 150, pH 80, armadura 20, rM 20, pC 12%, pE 5%)</li>\r\n\t<li><strong>No Muerto</strong> ( salud 900, at.B 100, pH 140, armadura 10, rM 40, pC 3%, pE 10%)</li>\r\n\t<li><strong>Demonio</strong>( salud 1100, at.B 130, pH 90, armadura 40, rM 30, pC 15%, pE 15%)</li>\r\n\t<li><strong>Humano</strong>( salud 1000, at.B 120, pH 120, armadura 30, rM 30, pC 12%, pE 9%)</li>\r\n\t<li><strong>Elfo</strong>( salud 900, at.B 120, pH 100, armadura 20, rM 30, pC 10%, pE 17%)</li>\r\n\t<li><strong>Enano</strong>( salud 1300, at.B 140, pH 60, armadura 40, rM 10, pC 14%, pE 3%)</li>\r\n</html>");
			txtpnclasesmagoCampos.setBounds(0, 0, 562, 446);
			contentPanel.add(txtpnclasesmagoCampos);
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
