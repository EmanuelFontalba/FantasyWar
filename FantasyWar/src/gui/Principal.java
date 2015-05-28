package gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import clasesPrincipales.GestionFicheros;
import clasesPrincipales.Jugador;
import comunicacionConGui.Comunicacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;

public class Principal {

	private static Component parentComponent;
	private JFrame frmPartidaDe;
	static JTextField textFieldNombrePJ;
	static JTextPane txtpnEstadisticas ;
	static JLabel lblImagenDelMonstruo;
	private Component contentPane;
	private static Component parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPartidaDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPartidaDe = new JFrame();
		frmPartidaDe.setResizable(false);
		frmPartidaDe.setTitle("Fantasy War. - Sin título");
		frmPartidaDe.setBounds(100, 100, 515, 425);
		frmPartidaDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPartidaDe.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva partida");
		mntmNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					NuevoJugador nuevo = new NuevoJugador();
					nuevo.setVisible(true);
					Comunicacion.monstruoSeleccionado = null;
					return;
				}
				if(Comunicacion.modificado){
					Nuevo nuevo = new Nuevo();
					nuevo.setVisible(true);
					Comunicacion.archivoElegido=null;
					Comunicacion.monstruoSeleccionado = null;
				}
				else{
					Nuevo nuevo = new Nuevo();
					nuevo.setVisible(true);
				}
				if(Comunicacion.archivoElegido!=null)
					frmPartidaDe.setTitle("Fantasy War. - "+ Comunicacion.archivoElegido.getName());
				else
					frmPartidaDe.setTitle("Fantasy War. - Sin título");
			}
		});
		mnArchivo.add(mntmNuevaPartida);

		JMenuItem mntmCargarPartida = new JMenuItem("Cargar partida");
		mntmCargarPartida.addActionListener(new ActionListener() {
			private Component contentPane;
			

			public void actionPerformed(ActionEvent e) {
				try {
					
					FileNameExtensionFilter filtro = new FileNameExtensionFilter("OBJ", "obj");
					JFileChooser fc = new JFileChooser();
					fc.setFileFilter(filtro);
					
					int respuesta = fc.showOpenDialog(parent);

					if (respuesta == JFileChooser.APPROVE_OPTION)
					{
						Comunicacion.archivoElegido = fc.getSelectedFile();
					}
					if (respuesta == JFileChooser.CANCEL_OPTION){
						
					}
					
					if(Comunicacion.archivoElegido==null)
						return;
					Comunicacion.jugador=(Jugador)GestionFicheros.abrir(Comunicacion.archivoElegido);
					Comunicacion.guardado=true;
					Comunicacion.modificado = false;
					Comunicacion.monstruoSeleccionado = null;
					if(Comunicacion.archivoElegido!=null)
						frmPartidaDe.setTitle("Fantasy War. - "+ Comunicacion.archivoElegido.getName());
					else
						frmPartidaDe.setTitle("Fantasy War. - Sin título");
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		mnArchivo.add(mntmCargarPartida);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.guardado)
					if(Comunicacion.modificado)
						try {
							if(modificarCambios() == JOptionPane.YES_OPTION){
								GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
								Comunicacion.modificado = false;
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					else
						try {
							GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
							Comunicacion.modificado=false;
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
				else
					try {
						guardarComo();
						if(Comunicacion.archivoElegido==null)
							return;
						GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
						Comunicacion.guardado=true;
						Comunicacion.modificado = false;
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				if(Comunicacion.archivoElegido!=null)
					frmPartidaDe.setTitle("Fantasy War. - "+ Comunicacion.archivoElegido.getName());
				else
					frmPartidaDe.setTitle("Fantasy War. - Sin título");
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					guardarComo();
					if(Comunicacion.archivoElegido==null)
						return;
					GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
					Comunicacion.guardado=true;
					Comunicacion.modificado = false;
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				if(Comunicacion.archivoElegido!=null)
					frmPartidaDe.setTitle("Fantasy War. - "+ Comunicacion.archivoElegido.getName());
				else
					frmPartidaDe.setTitle("Fantasy War. - Sin título");
			}

			
		});
		mnArchivo.add(mntmGuardarComo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnJugador = new JMenu("Jugador");
		mnJugador.setMnemonic('j');
		menuBar.add(mnJugador);
		
		JMenuItem mntmMostrar = new JMenuItem("Mostrar");
		mntmMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					MostrarJugador mostrar = new MostrarJugador();
					mostrar.setVisible(true);
				}
			}
		});
		mnJugador.add(mntmMostrar);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					EditarJugador editar = new EditarJugador();
					editar.setVisible(true);
				}
			}
		});
		mnJugador.add(mntmEditar);
		
		JMenu mnColeccin = new JMenu("Colecci\u00F3n");
		mnColeccin.setMnemonic('c');
		menuBar.add(mnColeccin);
		
		JMenuItem mntmAadirMonstruo = new JMenuItem("A\u00F1adir monstruo");
		mntmAadirMonstruo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					AnnadirMonstruo annadirMonstruo = new AnnadirMonstruo();
					annadirMonstruo.setVisible(true);
				}
			}
		});
		mnColeccin.add(mntmAadirMonstruo);
		
		JMenuItem mntmEliminarMonstruo = new JMenuItem("Eliminar monstruo");
		mntmEliminarMonstruo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					BuscarParaEliminar eliminar = new BuscarParaEliminar();
					eliminar.setVisible(true);
				}
			}
		});
		mnColeccin.add(mntmEliminarMonstruo);
		
		JMenu mnBuscarMonstruo = new JMenu("Buscar monstruo");
		mnColeccin.add(mnBuscarMonstruo);
		
		JMenuItem mntmNombre = new JMenuItem("Nombre");
		mntmNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					BuscarPorNombre buscar = new BuscarPorNombre();
					buscar.setVisible(true);
				}
			}
		});
		mnBuscarMonstruo.add(mntmNombre);
		
		JMenuItem mntmRaza = new JMenuItem("Raza");
		mntmRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					BuscarPorRaza buscar = new BuscarPorRaza();
					buscar.setVisible(true);
				}
			}
		});
		mnBuscarMonstruo.add(mntmRaza);
		
		JMenuItem mntmMostrarTodos = new JMenuItem("Mostrar todos");
		mntmMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null){
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
					Comunicacion.monstruosEncontrados = Comunicacion.jugador.getColeccionMonstruos();
					if(Comunicacion.monstruosEncontrados.size() == 0)
						System.out.println("Array vacio"); //controlar
					else{
						Comunicacion.monstruoEncontrado = Comunicacion.monstruosEncontrados.get(0);
						MostrarMonstruoPadre mostrar = new MostrarMonstruoPadre();
						mostrar.setVisible(true);
					}
				}
			}
		});
		mnColeccin.add(mntmMostrarTodos);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('h');
		menuBar.add(mnAyuda);
		
		JMenu mnAcercaDe = new JMenu("Acerca de...");
		mnAyuda.add(mnAcercaDe);
		
		JMenuItem mntmLucha = new JMenuItem("Lucha");
		mntmLucha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AyudaLucha ayuda = new AyudaLucha();
				ayuda.setVisible(true);
			}
		});
		mnAcercaDe.add(mntmLucha);
		
		JMenuItem mntmCreacin = new JMenuItem("Creaci\u00F3n");
		mntmCreacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AyudaCreación ayuda = new AyudaCreación();
				ayuda.setVisible(true);
			}
		});
		mnAcercaDe.add(mntmCreacin);
		
		JMenuItem mntmClasesYRazas = new JMenuItem("Clases y razas");
		mntmClasesYRazas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AyudaClasesRazas ayuda = new AyudaClasesRazas();
				ayuda.setVisible(true);
			}
		});
		mnAcercaDe.add(mntmClasesYRazas);
		
		JMenuItem mntmCrditos = new JMenuItem("Cr\u00E9ditos");
		mnAyuda.add(mntmCrditos);
		frmPartidaDe.getContentPane().setLayout(null);
		
		JLabel lblPersonaje = new JLabel("Personaje: ");
		lblPersonaje.setForeground(Color.WHITE);
		lblPersonaje.setBounds(20, 21, 89, 14);
		frmPartidaDe.getContentPane().add(lblPersonaje);
		
		txtpnEstadisticas = new JTextPane();
		txtpnEstadisticas.setOpaque(false);
		txtpnEstadisticas.setFont(txtpnEstadisticas.getFont().deriveFont(txtpnEstadisticas.getFont().getStyle() | Font.BOLD));
		txtpnEstadisticas.setForeground(new Color(255, 255, 255));
		txtpnEstadisticas.setBackground(new Color(189, 183, 107));
		txtpnEstadisticas.setEditable(false);
		txtpnEstadisticas.setText("Estadisticas");
		txtpnEstadisticas.setBounds(270, 11, 214, 196);
		frmPartidaDe.getContentPane().add(txtpnEstadisticas);
		
		JButton btnLuchar = new JButton("Luchar");
		btnLuchar.setFont(new Font("LilyUPC", Font.PLAIN, 46));
		btnLuchar.setForeground(new Color(165, 42, 42));
		btnLuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null)
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
				if(Comunicacion.monstruoSeleccionado == null)
					JOptionPane.showMessageDialog(contentPane, "No puedes sin tener un monstruo seleccionado.", "ERROR", JOptionPane.ERROR_MESSAGE);
				else{
					Luchar lucha = new Luchar();
					lucha.setVisible(true);
				}
			}
		});
		btnLuchar.setBounds(34, 233, 194, 118);
		frmPartidaDe.getContentPane().add(btnLuchar);
		
		JButton btnMazmorra = new JButton("Mazmorra");
		btnMazmorra.setBounds(297, 233, 186, 118);
		frmPartidaDe.getContentPane().add(btnMazmorra);
		btnMazmorra.setEnabled(false);
		
		textFieldNombrePJ = new JTextField();
		textFieldNombrePJ.setEditable(false);
		textFieldNombrePJ.setBounds(89, 18, 86, 20);
		frmPartidaDe.getContentPane().add(textFieldNombrePJ);
		textFieldNombrePJ.setColumns(10);
		
		lblImagenDelMonstruo = new JLabel("");
		lblImagenDelMonstruo.setBounds(34, 49, 274, 183);
		frmPartidaDe.getContentPane().add(lblImagenDelMonstruo);
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon("src\\imagenes\\fondoPrincipal.jpg"));
		labelFondo.setBounds(-28, -37, 784, 406);
		frmPartidaDe.getContentPane().add(labelFondo);
	}
	
	private static int modificarCambios() {
		return JOptionPane.showConfirmDialog(parentComponent, "Se va a perder información. ¿Quieres continuar?", "Sobreescritura" , JOptionPane.YES_NO_OPTION);
	}

	public static void actualizar() {
		lblImagenDelMonstruo.setIcon(new ImageIcon(Comunicacion.monstruoSeleccionado.getRutaImg()));		
		textFieldNombrePJ.setText(Comunicacion.monstruoSeleccionado.getNombre());
		txtpnEstadisticas.setText("Salud máxima: "+Comunicacion.monstruoEncontrado.getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.monstruoEncontrado.getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.monstruoEncontrado.getPoderHabilidad()+
				"\rArmadura: "+Comunicacion.monstruoEncontrado.getArmadura()+
				"\rResistencia mágica: "+Comunicacion.monstruoEncontrado.getResistenciaMagica()+
				"\rProbabilidad de impacto crítico: "+Comunicacion.monstruoEncontrado.getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.monstruoEncontrado.getProbabilidadEsquivar());
		
	}
	static void guardarComo() {
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("OBJ", "obj");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filtro);
		int respuesta = fc.showSaveDialog(parent);

		if (respuesta == JFileChooser.APPROVE_OPTION)
		{
			Comunicacion.archivoElegido = fc.getSelectedFile();
		}
		if (respuesta == JFileChooser.CANCEL_OPTION){
			fc.setVisible(false);
		}
	}
}
