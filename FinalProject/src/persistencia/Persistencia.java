package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Administrador;
import model.Cliente;

public class Persistencia {
	public static final String ESPECTACULO_RUTA = "src/resources/Espectaculo.xml";
	public static final String ADMINISTRADORES_RUTA = "src/resources/Administradores.txt";
	public static final String CLIENTES_RUTA = "src/resources/Clientes.txt";

	/**
	 * Metodo estatico que permite serializar un objeto dada una ruta
	 * 
	 * @param ruta   donde se guarda el objeto
	 * @param objeto que se desea guardar
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public static void serializarXML(String ruta, Object objeto) throws FileNotFoundException {
		FileOutputStream archivo = new FileOutputStream(ruta);
		XMLEncoder codificador = new XMLEncoder(archivo);
		codificador.writeObject(objeto);
		codificador.close();
	}

	/**
	 * Metodo estatico que permite leer la informacion de un objeto
	 * 
	 * @param ruta donde esta el archivo
	 * @return el objeto
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public static Object deserializarObjetoXML(String ruta) throws FileNotFoundException {
		FileInputStream archivo = new FileInputStream(ruta);
		XMLDecoder decodificador = new XMLDecoder(archivo);
		Object objeto = decodificador.readObject();
		decodificador.close();
		return objeto;
	}

	/**
	 * Metodo estatico que permite guardar Clientes en un archivo de texto
	 * 
	 * @param misClientes datos a guardar en archivo
	 * @throws IOException si ocurre algun problema en la entrada/salida de datos
	 */
	public static void guardarClientesEnArchivo(HashMap<String, Cliente> misClientes) throws IOException {
		Iterator<String> iterator = misClientes.keySet().iterator();
		ArrayList<String> contenidoArchivo = new ArrayList<String>();
		String info = "";
		Cliente miCliente;
		while (iterator.hasNext()) {
			miCliente = misClientes.get(iterator.next());
			info = "<" + miCliente.getNombre() + "," + miCliente.getApellido() + "," + miCliente.getId() + ","
					+ miCliente.getCiudadDeResidencia() + "," + miCliente.getDireccion() + "," + miCliente.getEmail()
					+ "," + miCliente.getMiEstadoCivil() + ", Estrato: " + miCliente.getMiEstrato() + ","
					+ miCliente.getMiFechaDeNacimiento() + "," + miCliente.getMiGenero() + ","
					+ miCliente.getMiNivelDeEstudio() + ">";
			contenidoArchivo.add(info);
		}
		Archivo.guardarEnArchivo(CLIENTES_RUTA, contenidoArchivo);
	}

	/**
	 * Metodo estatico que permite guardar Administradores en un archivo de texto
	 * 
	 * @param misAdministradores datos a guardar
	 * @throws IOException Si ocurre algun problema con el proceso de entrada/salida
	 *                     de datos
	 */
	public static void guardarAdministradoresEnArchivo(HashMap<String, Administrador> misAdministradores)
			throws IOException {
		Iterator<String> iterator = misAdministradores.keySet().iterator();
		ArrayList<String> contenidoArchivo = new ArrayList<String>();
		String info = "";
		Administrador miAdministrador;
		while (iterator.hasNext()) {
			miAdministrador = misAdministradores.get(iterator.next());
			info = "<" + miAdministrador.getNombre() + "," + miAdministrador.getApellido() + ","
					+ miAdministrador.getId() + "," + miAdministrador.getEmail() + "," + miAdministrador.getMiGenero()
					+ ">";
			contenidoArchivo.add(info);
		}
		Archivo.guardarEnArchivo(ADMINISTRADORES_RUTA, contenidoArchivo);
	}

}