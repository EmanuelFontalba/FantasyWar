package gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import Monstruos.Guerrero;
import Monstruos.Mago;
import clasesPrincipales.GestionFicheros;
import clasesPrincipales.Jugador;
import comunicacionConGui.Comunicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextField;

import enumeraciones.Razas;

public class Principal {

	private static Component parentComponent;
	private JFrame frmPartidaDe;
	static JTextField textFieldNombrePJ;
	static JTextPane txtpnEstadisticas ;
	static JLabel lblImagenDelMonstruo;

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
		frmPartidaDe.setTitle("Fantasy War. - Sin título");
		frmPartidaDe.setBounds(100, 100, 772, 428);
		frmPartidaDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPartidaDe.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva partida");
		mntmNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.modificado){
					Nuevo nuevo = new Nuevo();
					nuevo.setVisible(true);
					Comunicacion.archivoElegido=null;
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
					Abrir menuAbrir= new Abrir();
					if(Comunicacion.archivoElegido==null)
						return;
					Comunicacion.jugador=(Jugador)GestionFicheros.abrir(Comunicacion.archivoElegido);
					Comunicacion.guardado=true;
					Comunicacion.modificado = false;
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				if(Comunicacion.archivoElegido!=null)
					frmPartidaDe.setTitle("Fantasy War. - "+ Comunicacion.archivoElegido.getName());
				else
					frmPartidaDe.setTitle("Fantasy War. - Sin título");
			}
		});
		mnArchivo.add(mntmCargarPartida);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			private Component contentPane;

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
						GuardarComo menuGuardarComo = new GuardarComo();
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
					GuardarComo menuGuardarComo = new GuardarComo();
					if(Comunicacion.archivoElegido==null)
						return;
					GestionFicheros.guardar(Comunicacion.jugador,Comunicacion.archivoElegido);
					Comunicacion.guardado=true;
					Comunicacion.modificado = false;
				} catch (IOException e1) {
					System.out.println(e1);
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
					//PANEL
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
					//PANEL
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
					//PANEL
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
					//PANEL
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
					//PANEL
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
					//PANEL
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
					//PANEL
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
		
		JMenuItem mntmTrucos = new JMenuItem("Trucos");
		mnAyuda.add(mntmTrucos);
		
		JMenu mnAcercaDe = new JMenu("Acerca de...");
		mnAyuda.add(mnAcercaDe);
		
		JMenuItem mntmLucha = new JMenuItem("Lucha");
		mnAcercaDe.add(mntmLucha);
		
		JMenuItem mntmCreacin = new JMenuItem("Creaci\u00F3n");
		mnAcercaDe.add(mntmCreacin);
		
		JMenuItem mntmClasesYRazas = new JMenuItem("Clases y razas");
		mnAcercaDe.add(mntmClasesYRazas);
		
		JMenuItem mntmCrditos = new JMenuItem("Cr\u00E9ditos");
		mnAyuda.add(mntmCrditos);
		frmPartidaDe.getContentPane().setLayout(null);
		
		JLabel lblPersonaje = new JLabel("Personaje: ");
		lblPersonaje.setBounds(10, 11, 89, 14);
		frmPartidaDe.getContentPane().add(lblPersonaje);
		
		txtpnEstadisticas = new JTextPane();
		txtpnEstadisticas.setEditable(false);
		txtpnEstadisticas.setText("Estadisticas");
		txtpnEstadisticas.setBounds(347, 36, 399, 196);
		frmPartidaDe.getContentPane().add(txtpnEstadisticas);
		
		JButton btnLuchar = new JButton("Luchar");
		btnLuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Comunicacion.jugador == null || Comunicacion.monstruoSeleccionado == null){
					//PANEL
				}
				else{
					Luchar lucha = new Luchar();
					lucha.setVisible(true);
				}
			}
		});
		btnLuchar.setBounds(347, 240, 194, 118);
		frmPartidaDe.getContentPane().add(btnLuchar);
		
		JButton btnMazmorra = new JButton("Mazmorra");
		btnMazmorra.setBounds(560, 240, 186, 118);
		frmPartidaDe.getContentPane().add(btnMazmorra);
		
		textFieldNombrePJ = new JTextField();
		textFieldNombrePJ.setEditable(false);
		textFieldNombrePJ.setBounds(88, 8, 86, 20);
		frmPartidaDe.getContentPane().add(textFieldNombrePJ);
		textFieldNombrePJ.setColumns(10);
		
		lblImagenDelMonstruo = new JLabel("");
		lblImagenDelMonstruo.setBounds(10, 49, 298, 183);
		frmPartidaDe.getContentPane().add(lblImagenDelMonstruo);
	}
	
	private static int modificarCambios() {
		return JOptionPane.showConfirmDialog(parentComponent, "Se va a perder información. ¿Quieres continuar?", "Sobreescritura" , JOptionPane.YES_NO_OPTION);
	}

	public static void actualizar() {
//		if(Comunicacion.monstruoSeleccionado.getRaza() == Razas.ENANO && Comunicacion.monstruoSeleccionado.getClass() == Mago.class){
//			lblImagenDelMonstruo.setText("");
//			lblImagenDelMonstruo.setIcon(null);
//			lblImagenDelMonstruo.setIcon(new ImageIcon("C:\\Users\\Azahara\\Desktop\\ema\\repositoriosGit\\FantasyWar\\FantasyWar\\enanoMago.jpg"));
//		}
//		if(Comunicacion.monstruoSeleccionado.getRaza() == Razas.ENANO && Comunicacion.monstruoSeleccionado.getClass() == Guerrero.class){
//			lblImagenDelMonstruo.setText("");
//			lblImagenDelMonstruo.setIcon(null);
//			lblImagenDelMonstruo.setIcon(new ImageIcon("C:\\Users\\Azahara\\Desktop\\ema\\repositoriosGit\\FantasyWar\\FantasyWar\\enanoGuerrero.jpg"));
//		}
//		else{
//			lblImagenDelMonstruo.setText("Aún sin imagen");
//			lblImagenDelMonstruo.setIcon(null);
//		}
		textFieldNombrePJ.setText(Comunicacion.monstruoSeleccionado.getNombre());
		txtpnEstadisticas.setText("Salud máxima: "+Comunicacion.monstruoEncontrado.getSaludMaxima()+
				"\rAtaque básico: "+Comunicacion.monstruoEncontrado.getAtaqueBasico()+
				"\rPoder de habilidad: "+Comunicacion.monstruoEncontrado.getPoderHabilidad()+
				"\rArmadura: "+Comunicacion.monstruoEncontrado.getArmadura()+
				"\rResistencia mágica: "+Comunicacion.monstruoEncontrado.getResistenciaMagica()+
				"\rProbabilidad de impacto crítico: "+Comunicacion.monstruoEncontrado.getProbabilidadCritico()+
				"\rProbabilidad de esquivar: "+Comunicacion.monstruoEncontrado.getProbabilidadEsquivar());
		
	}
}
