package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import constelacionInterface.ConstelacionInterfaceRMI;

/**
 * Clase que desarrolla los métodos con los que se va a comunicar el cliente a través del stub.Extiende la clase 
 * UnicastRemoteObject usada para exportar un objeto remoto y obtener un stub. Esta clase es seriabilizable, por eso
 * hay que añadir un ID
 * */

public class ConstelacionRMI extends UnicastRemoteObject implements ConstelacionInterfaceRMI {
	private static final long serialVersionUID = -4817856459999901795L;
	
	//Creamos un objeto ArrayList con las constelaciones que vamos a utilizar como datos 
	private ArrayList<Constelacion> constelaciones;
	
	public ConstelacionRMI() throws RemoteException {
		constelaciones = new ArrayList<Constelacion>();
	
		
		constelaciones.add(new Constelacion("Osa Menor", "Su estrella más conocida es la polar que se encuentra en la prolongación del eje de la tierra."));

		constelaciones.add(new Constelacion("Tauro", "Una de las constelaciones más conocidas desde tiempos remotos."));

		constelaciones.add(new Constelacion("Leo", "De las más brillantes del Zodíaco."));

		constelaciones.add(new Constelacion("Escorpio", "Sus estrellas forman un escorpión."));

		constelaciones.add(new Constelacion("Can Mayor", "Contiene la estrella Sirio, la más brillante en el cielo nocturno."));

		constelaciones.add(new Constelacion("Casiopea", "Tiene forma de M o W. Es conocida desde mucha antigüedad."));

		constelaciones.add(new Constelacion("El Boyero", "Contiene la estrella Arturo, uno de las más luminosas del cielo."));

		constelaciones.add(new Constelacion("Cruz del sur", "Señala al polo sur. Constelación muy pequeña."));

		constelaciones.add(new Constelacion("Acuario", "Una de las más antiguas. Incluye 56 estrellas."));

		constelaciones.add(new Constelacion("Géminis", "Destaca por sus dos gemelos, las estrellas Cástor y Pólux."));
	}

	/**
	 * Este método desarrollado aqui, ha sido definido en la interfaz ConstelacionInterfaceRMI. 
	 * Este método busca todas las constelaciones que contenga la cadena introducida como parámetro. Las concatena en una cadena
	 *  y las devuelve
	 *  
	 *  @see constelacionInterface.ConstelacionInterfaceRMI#buscarNombre(java.lang.String)
	 *  @param nombre cadena a buscar dentro de los nombres de las constelaciones
	 *  @return cadena con todas las constelaciones que contienen el parámetro de entrada
	 * */

	
	@Override
	public String buscarNombre(String nombre) throws RemoteException {
		String resultado = "";
		for (Constelacion c : constelaciones) {
			if (c.getNombre().toUpperCase().contains(nombre)) {
				resultado = resultado + c + "\n";
			}
		}
		return resultado;
	}
 
}