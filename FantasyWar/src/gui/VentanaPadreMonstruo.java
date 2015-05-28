package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import clasesPrincipales.Clase;
import clasesPrincipales.Razas;

public class VentanaPadreMonstruo extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JTextField textFieldNombre;
	protected JLabel lblNombre;
	protected JLabel lblRaza;
	protected JComboBox comboBoxRaza = new JComboBox();
	protected JLabel lblClase = new JLabel("Clase:");
	protected JComboBox comboBoxClase = new JComboBox();
	protected JPanel buttonPane;
	protected JButton buttonPrevious;
	protected JButton buttonNext;
	protected JButton okButton;
	protected JButton cancelButton;
	/**
	 * Patrón para nombres correctos. Deben de empezar por una mayúscula.
	 */
	private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Z][a-z]*[a-z]$");

	/**
	 * Create the dialog.
	 */
	public VentanaPadreMonstruo() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 224, 273);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 40, 66, 14);
			contentPanel.add(lblNombre);
		}
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(86, 37, 99, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblRaza = new JLabel("Raza:");
		lblRaza.setBounds(10, 94, 66, 14);
		contentPanel.add(lblRaza);
		
		
		comboBoxRaza.setBounds(86, 91, 99, 20);
		contentPanel.add(comboBoxRaza);
		comboBoxRaza.setModel(new DefaultComboBoxModel(Razas.values()));
		
		
		lblClase.setBounds(10, 156, 46, 14);
		contentPanel.add(lblClase);
		
		
		comboBoxClase.setBounds(86, 153, 99, 20);
		contentPanel.add(comboBoxClase);
		comboBoxClase.setModel(new DefaultComboBoxModel(Clase.values()));
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			buttonPrevious = new JButton("<");
			buttonPane.add(buttonPrevious);
			
			buttonNext = new JButton(">");
			buttonPane.add(buttonNext);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Comprueba si un nombre es válido
	 * @param nombre
	 * 			Nombre a comprobar
	 * @return true si es valido si no false.
	 */
	public static boolean nombreValido(String nombre){
		return PATRON_NOMBRE.matcher(nombre).matches();
	}
}
