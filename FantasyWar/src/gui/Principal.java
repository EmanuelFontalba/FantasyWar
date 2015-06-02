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
	private static JFrame frmPartidaDe;
	static JTextField textFieldNombrePJ;
	static JTextPane txtpnEstadisticas;
	static JLabel lblImagenDelMonstruo;
	private static Component contentPane;
	private static Component parent;
	private static FileNameExtensionFilter filtro = new FileNameExtensionFilter("OBJ",
			"obj");
	private static JFileChooser fc = new JFileChooser();
	private static Principal window;
	
	
	static {
		fc.setFileFilter(filtro);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setWindow(new Principal());
					Principal.frmPartidaDe.setVisible(true);
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
				nuevaPartida();
			}
		});
		mnArchivo.add(mntmNuevaPartida);

		JMenuItem mntmCargarPartida = new JMenuItem("Cargar partida");
		mntmCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPartida();

			}
		});
		mnArchivo.add(mntmCargarPartida);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPartida();
			}
		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				guardarComo();
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
				mostrarJugador();
			}
		});
		mnJugador.add(mntmMostrar);

		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarJugador();
			}
		});
		mnJugador.add(mntmEditar);

		JMenu mnColeccin = new JMenu("Colecci\u00F3n");
		mnColeccin.setMnemonic('c');
		menuBar.add(mnColeccin);

		JMenuItem mntmAadirMonstruo = new JMenuItem("A\u00F1adir monstruo");
		mntmAadirMonstruo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annadirMonstruo();
			}
		});
		mnColeccin.add(mntmAadirMonstruo);

		JMenuItem mntmEliminarMonstruo = new JMenuItem("Eliminar monstruo");
		mntmEliminarMonstruo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMonstruo();
			}
		});
		mnColeccin.add(mntmEliminarMonstruo);

		JMenu mnBuscarMonstruo = new JMenu("Buscar monstruo");
		mnColeccin.add(mnBuscarMonstruo);

		JMenuItem mntmNombre = new JMenuItem("Nombre");
		mntmNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPorNombre();
			}
		});
		mnBuscarMonstruo.add(mntmNombre);

		JMenuItem mntmRaza = new JMenuItem("Raza");
		mntmRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPorRaza();
			}
		});
		mnBuscarMonstruo.add(mntmRaza);

		JMenuItem mntmMostrarTodos = new JMenuItem("Mostrar todos");
		mntmMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTodos();
			}
		});
		mnColeccin.add(mntmMostrarTodos);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('h');
		menuBar.add(mnAyuda);

		JMenu mnAcercaDe = new JMenu("Manuales");
		mnAyuda.add(mnAcercaDe);

		JMenuItem mntmLucha = new JMenuItem("Lucha");
		mntmLucha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AyudaLucha ayuda = new AyudaLucha();
				ayuda.setVisible(true);
			}
		});

		JMenuItem mntmComoEmpezar = new JMenuItem("Como empezar");
		mntmComoEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComoEmpezar ayuda = new ComoEmpezar();
				ayuda.setVisible(true);
			}
		});
		mnAcercaDe.add(mntmComoEmpezar);
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

		JMenuItem mntmCrditos = new JMenuItem("Acerca de...");
		mntmCrditos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe acerca = new AcercaDe();
				acerca.setVisible(true);
			}
		});
		mnAyuda.add(mntmCrditos);
		frmPartidaDe.getContentPane().setLayout(null);

		JLabel lblPersonaje = new JLabel("Personaje: ");
		lblPersonaje.setForeground(Color.WHITE);
		lblPersonaje.setBounds(20, 21, 89, 14);
		frmPartidaDe.getContentPane().add(lblPersonaje);

		txtpnEstadisticas = new JTextPane();
		txtpnEstadisticas.setOpaque(false);
		txtpnEstadisticas.setFont(txtpnEstadisticas.getFont().deriveFont(
				txtpnEstadisticas.getFont().getStyle() | Font.BOLD));
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
				runLuchar();
			}
		});
		btnLuchar.setBounds(34, 233, 194, 118);
		frmPartidaDe.getContentPane().add(btnLuchar);

		JButton btnMazmorra = new JButton("Mazmorra");
		btnMazmorra.setForeground(new Color(165, 42, 42));
		btnMazmorra.setFont(new Font("LilyUPC", Font.PLAIN, 46));
		btnMazmorra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runMazmorras();
			}
		});
		btnMazmorra.setBounds(297, 233, 186, 118);
		frmPartidaDe.getContentPane().add(btnMazmorra);

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
	
	/**
	 * Carga una partida
	 */
	private void cargarPartida() {
		switch(fc.showOpenDialog(parent)){
		case JFileChooser.CANCEL_OPTION:
		case JFileChooser.ERROR_OPTION:
			return;
		}
		
		Comunicacion.setArchivoElegido(fc.getSelectedFile());
		try {

			Comunicacion.setJugador((Jugador) GestionFicheros
					.abrir(Comunicacion.getArchivoElegido()));
			Comunicacion.setGuardado(true);
			Comunicacion.setModificado(false);
			Comunicacion.setMonstruoSeleccionado(null);
				frmPartidaDe.setTitle("Fantasy War. - "
						+ Comunicacion.getArchivoElegido().getName());				
		} catch (ClassNotFoundException | IOException e1) {
			JOptionPane.showMessageDialog(contentPane,
					"El archivo elegido no corresponde con Fantasy War.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Guardar partida.
	 */
	private void guardarPartida() {
		if (Comunicacion.isGuardado())
			try {
				if (modificarCambios() == JOptionPane.YES_OPTION) {
					guardar();
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(contentPane,
						e1.getMessage(), "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		else
			guardarComo();
	}
	
	/**
	 * Guarda la partida.
	 * @throws IOException
	 * 			Error de entrada/salida.
	 */
	private static void guardar() throws IOException {
		GestionFicheros.guardar(
				Comunicacion.getJugador(),
				Comunicacion.getArchivoElegido());
		Comunicacion.setModificado(false);
	}
	
	/**
	 * Ventana guardar como.
	 */
	static void guardarComo() {
		try {
			switch(fc.showSaveDialog(parent)){
			case JFileChooser.CANCEL_OPTION:
			case JFileChooser.ERROR_OPTION:
				return;
			}
			
			Comunicacion.setArchivoElegido(fc.getSelectedFile());
			guardar();
			Comunicacion.setGuardado(true);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(contentPane, e1.getMessage(),
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (Comunicacion.getArchivoElegido() != null)
			frmPartidaDe.setTitle("Fantasy War. - "
					+ Comunicacion.getArchivoElegido().getName());
		else
			frmPartidaDe.setTitle("Fantasy War. - Sin título");
	}
	
	/**
	 * Crear nueva partida.
	 */
	private void nuevaPartida() {
		if (Comunicacion.getJugador() == null) {
			NuevoJugador nuevo = new NuevoJugador();
			nuevo.setVisible(true);
			Comunicacion.setMonstruoSeleccionado(null);
			return;
		}
		
		if (Comunicacion.isModificado()) {
			ventanaNuevo();
			Comunicacion.setArchivoElegido(null);
			Comunicacion.setMonstruoSeleccionado(null);
		} else {
			ventanaNuevo();
		}
		
		if (Comunicacion.getArchivoElegido() != null)
			frmPartidaDe.setTitle("Fantasy War. - "
					+ Comunicacion.getArchivoElegido().getName());
		else
			frmPartidaDe.setTitle("Fantasy War. - Sin título");
	}
	
	/**
	 * Ventana de confirmación al crear nueva partida.
	 */
	private void ventanaNuevo() {
		Nuevo nuevo = new Nuevo();
		nuevo.setVisible(true);
	}
	
	/**
	 * Mostrar todos los monstruos
	 */
	private void mostrarTodos() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Comunicacion.setMonstruosEncontrados(Comunicacion
					.getJugador().getColeccionMonstruos());
			if( Comunicacion.getJugador().getColeccionMonstruos().size() == 0)
				JOptionPane.showMessageDialog( contentPane, "Aun no hay ningun monstruo en tu colección", "ERROR", JOptionPane.ERROR_MESSAGE);
			else {
				Comunicacion.setMonstruoEncontrado(Comunicacion
						.getMonstruosEncontrados().get(0));
				MostrarMonstruoPadre mostrar = new MostrarMonstruoPadre();
				mostrar.setVisible(true);
			}
		}
	}
	
	/**
	 * Buscar monstruos por raza.
	 */
	private void buscarPorRaza() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			BuscarPorRaza buscar = new BuscarPorRaza();
			buscar.setVisible(true);
		}
	}
	
	/**
	 * Buscar monstruos por nombre
	 */
	private void buscarPorNombre() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			BuscarPorNombre buscar = new BuscarPorNombre();
			buscar.setVisible(true);
		}
	}
	
	/**
	 * Elimina un monstruo.
	 */
	private void eliminarMonstruo() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			BuscarParaEliminar eliminar = new BuscarParaEliminar();
			eliminar.setVisible(true);
		}
	}
	
	/**
	 * Añade nuevo monstruo
	 */
	private void annadirMonstruo() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			AnnadirMonstruo annadirMonstruo = new AnnadirMonstruo();
			annadirMonstruo.setVisible(true);
		}
	}
	
	/**
	 * Edita el jugador
	 */
	private void editarJugador() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			EditarJugador editar = new EditarJugador();
			editar.setVisible(true);
		}
	}
	
	/**
	 * Mostrar jugador.
	 */
	private void mostrarJugador() {
		if (Comunicacion.getJugador() == null) {
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			MostrarJugador mostrar = new MostrarJugador();
			mostrar.setVisible(true);
		}
	}
	
	/**
	 * Ejecuta la ventana de luchar
	 */
	private void runLuchar() {
		if (Comunicacion.getJugador() == null)
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener jugador.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		if (Comunicacion.getMonstruoSeleccionado() == null)
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener un monstruo seleccionado.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			Luchar lucha = new Luchar();
			lucha.setVisible(true);
		}
	}
	
	/**
	 * Ejecuta la ventana de mazmorras.
	 */
	private void runMazmorras() {
		if (Comunicacion.getMonstruoSeleccionado() != null) {
			MazmorrasGUI mazmorras = new MazmorrasGUI();
			mazmorras.setVisible(true);
		} else
			JOptionPane.showMessageDialog(contentPane,
					"No puedes sin tener un monstruo seleccionado.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Modificar cambios
	 * @return Respuesta.
	 */
	private static int modificarCambios() {
		return JOptionPane.showConfirmDialog(parentComponent,
				"Se va a perder información. ¿Quieres continuar?",
				"Sobreescritura", JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * Actualiza la ventana.
	 */
	public static void actualizar() {
		lblImagenDelMonstruo.setIcon(new ImageIcon(Comunicacion
				.getMonstruoEncontrado().getRutaImg()));
		lblImagenDelMonstruo.setSize(200, 150);
		textFieldNombrePJ.setText(Comunicacion.getMonstruoEncontrado()
				.getNombre());
		txtpnEstadisticas.setText("Nivel: "
				+ Comunicacion.getMonstruoSeleccionado().getNivel()
				+ "\rSalud máxima: "
				+ Comunicacion.getMonstruoEncontrado().getSaludMaxima()
				+ "\rAtaque básico: "
				+ Comunicacion.getMonstruoEncontrado().getAtaqueBasico()
				+ "\rPoder de habilidad: "
				+ Comunicacion.getMonstruoEncontrado().getPoderHabilidad()
				+ "\rArmadura: "
				+ Comunicacion.getMonstruoEncontrado().getArmadura()
				+ "\rResistencia mágica: "
				+ Comunicacion.getMonstruoEncontrado().getResistenciaMagica()
				+ "\rProbabilidad de impacto crítico: "
				+ Comunicacion.getMonstruoEncontrado().getProbabilidadCritico()
				+ "\rProbabilidad de esquivar: "
				+ Comunicacion.getMonstruoEncontrado()
						.getProbabilidadEsquivar());

	}

	public static Principal getWindow() {
		return window;
	}

	private static void setWindow(Principal window) {
		Principal.window = window;
	}

	
}
