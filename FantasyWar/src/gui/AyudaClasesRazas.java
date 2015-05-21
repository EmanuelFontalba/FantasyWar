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

public class AyudaClasesRazas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AyudaClasesRazas dialog = new AyudaClasesRazas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AyudaClasesRazas() {
		setTitle("Ayuda de clases y razas");
		setBounds(100, 100, 622, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JEditorPane dtrpnClasesmagoCampos = new JEditorPane();
			dtrpnClasesmagoCampos.setBackground(SystemColor.control);
			dtrpnClasesmagoCampos.setEditable(false);
			dtrpnClasesmagoCampos.setText("Clases:\r\n\r\n\t-Mago:\r\n\t\tCampos: man\u00E1=200 regeneraci\u00F3n de mana= 10% por turno\r\n\t\tHechizos: bolaDeFuego, escudoDeEscarcha, meditaci\u00F3n, lluviaDeMete\u00F3ros.\r\n\t-Guerrero\r\n\t\tCampos: ira= 100 regeneraci\u00F3n de ira = 4% por turno\r\n\t\tHechizos: Rajar, Ejecutar, Protecci\u00F3n, filoTormenta.\r\n\t-Sacerdote\r\n\t\tCampos: f\u00E9 = 150 regeneraci\u00F3n de f\u00E9 = 15 % por turno\r\n\t\tHechizos: energiaDivina, curaci\u00F3nAncestral, escudoDivino, curaci\u00F3nAbsoluta\r\n\r\n-Razas:\r\n\r\n\t-Orco ( salud 1200, at.B 150, pH 80, armadura 20, rM 20, pC 12%, pE 5%)\r\n\t-No Muerto ( salud 900, at.B 100, pH 140, armadura 10, rM 40, pC 3%, pE 10%)\r\n\t-Demonio( salud 1100, at.B 130, pH 90, armadura 40, rM 30, pC 15%, pE 15%)\r\n\t-Humano( salud 1000, at.B 120, pH 120, armadura 30, rM 30, pC 12%, pE 9%)\r\n\t-Elfo( salud 900, at.B 120, pH 100, armadura 20, rM 30, pC 10%, pE 17%)\r\n\t-Enano( salud 1300, at.B 140, pH 60, armadura 40, rM 10, pC 14%, pE 3%)");
			dtrpnClasesmagoCampos.setBounds(0, 0, 606, 401);
			contentPanel.add(dtrpnClasesmagoCampos);
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
