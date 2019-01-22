package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import constelacionInterface.ConstelacionInterfaceRMI;

/**
 * Clase que desarrolla los m�todos con los que se va a comunicar el cliente a trav�s del stub.Extiende la clase 
 * UnicastRemoteObject usada para exportar un objeto remoto y obtener un stub. Esta clase es seriabilizable, por eso
 * hay que a�adir un ID
 * */

public class ConstelacionRMI extends UnicastRemoteObject implements ConstelacionInterfaceRMI {
	private static final long serialVersionUID = -4817856459999901795L;
	
	//Creamos un objeto ArrayList con las constelaciones que vamos a utilizar como datos 
	private ArrayList<Constelacion> constelaciones;
	
	public ConstelacionRMI() throws RemoteException {
		constelaciones = new ArrayList<Constelacion>();
	
		
		constelaciones.add(new Constelacion("Osa Menor", "Su estrella m�s conocida es la polar que se encuentra en la prolongaci�n del eje de la tierra."));

		constelaciones.add(new Constelacion("Tauro", "Una de las constelaciones m�s conocidas desde tiempos remotos."));

		constelaciones.add(new Constelacion("Leo", "De las m�s brillantes del Zod�aco."));

		constelaciones.add(new Constelacion("Escorpio", "Sus estrellas forman un escorpi�n."));

		constelaciones.add(new Constelacion("Can Mayor", "Contiene la estrella Sirio, la m�s brillante en el cielo nocturno."));

		constelaciones.add(new Constelacion("Casiopea", "Tiene forma de M o W. Es conocida desde mucha antig�edad."));

		constelaciones.add(new Constelacion("El Boyero", "Contiene la estrella Arturo, uno de las m�s luminosas del cielo."));

		constelaciones.add(new Constelacion("Cruz del sur", "Se�ala al polo sur. Constelaci�n muy peque�a."));

		constelaciones.add(new Constelacion("Acuario", "Una de las m�s antiguas. Incluye 56 estrellas."));

		constelaciones.add(new Constelacion("G�minis", "Destaca por sus dos gemelos, las estrellas C�stor y P�lux."));
	}

	/**
	 * Este m�todo desarrollado aqui, ha sido definido en la interfaz ConstelacionInterfaceRMI. 
	 * Este m�todo busca todas las constelaciones que contenga la cadena introducida como par�metro. Las concatena en una cadena
	 *  y las devuelve
	 *  
	 *  @see constelacionInterface.ConstelacionInterfaceRMI#buscarNombre(java.lang.String)
	 *  @param nombre cadena a buscar dentro de los nombres de las constelaciones
	 *  @return cadena con todas las constelaciones que contienen el par�metro de entrada
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