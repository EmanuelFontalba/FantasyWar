package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clasesPrincipales.NombreInvalidoException;
import comunicacionConGui.Comunicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

public class EditarJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldNivel;
	private JTextField textFieldExp;
	private JTextField textFieldCreacion;
	private JTextField textFieldNumMons;
	/**
	 * Patrón para nombres correctos. Deben de empezar por una mayúscula.
	 */
	private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Z][a-z]*[a-z]$");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarJugador dialog = new MostrarJugador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarJugador() {
		setModal(true);
		setTitle("Edici\u00F3n de jugador");
		setAlwaysOnTop(true);
		setBounds(100, 100, 337, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 22, 76, 14);
			contentPanel.add(lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(textFieldNombre.getText() == null)
						return;
					if(nombreValido(textFieldNombre.getText()))
						textFieldNombre.setForeground(Color.BLACK);
					else
						textFieldNombre.setForeground(Color.RED);
				}
			});
			textFieldNombre.setText(Comunicacion.jugador.getAlias());
			textFieldNombre.setBounds(76, 19, 235, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lblNivel = new JLabel("Nivel:");
			lblNivel.setBounds(10, 53, 46, 14);
			contentPanel.add(lblNivel);
		}
		{
			textFieldNivel = new JTextField();
			textFieldNivel.setText(new Integer(Comunicacion.jugador.getNivel()).toString());
			textFieldNivel.setEditable(false);
			textFieldNivel.setBounds(76, 50, 76, 20);
			contentPanel.add(textFieldNivel);
			textFieldNivel.setColumns(10);
		}
		{
			JLabel lblExp = new JLabel("Exp:");
			lblExp.setBounds(10, 84, 46, 14);
			contentPanel.add(lblExp);
		}
		{
			textFieldExp = new JTextField();
			textFieldExp.setText(new Integer(Comunicacion.jugador.getExp()).toString());
			textFieldExp.setEditable(false);
			textFieldExp.setBounds(76, 81, 76, 20);
			contentPanel.add(textFieldExp);
			textFieldExp.setColumns(10);
		}
		{
			textFieldCreacion = new JTextField();
			textFieldCreacion.setText(Comunicacion.jugador.getfNacimiento().toString());
			textFieldCreacion.setEditable(false);
			textFieldCreacion.setBounds(76, 112, 235, 20);
			contentPanel.add(textFieldCreacion);
			textFieldCreacion.setColumns(10);
		}
		{
			JLabel lblCreacin = new JLabel("Creaci\u00F3n:");
			lblCreacin.setBounds(10, 115, 76, 14);
			contentPanel.add(lblCreacin);
		}
		{
			textFieldNumMons = new JTextField();
			textFieldNumMons.setText(new Integer(Comunicacion.jugador.getColeccionMonstruos().size()).toString());
			textFieldNumMons.setEditable(false);
			textFieldNumMons.setBounds(76, 143, 76, 20);
			contentPanel.add(textFieldNumMons);
			textFieldNumMons.setColumns(10);
		}
		{
			JLabel lblColeccin = new JLabel("Colecci\u00F3n:");
			lblColeccin.setBounds(10, 146, 76, 14);
			contentPanel.add(lblColeccin);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					private Component contentPane;

					public void actionPerformed(ActionEvent e) {
						try {
							Comunicacion.jugador.setAlias(textFieldNombre.getText());
							setVisible(false);
						} catch (NombreInvalidoException e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
