package constelacionInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface ConstelacionInterfaceRMI: Esta interfaz indica que m�todos son los que se pueden usar remotamente 
 * para ello extienden la clase Remote. 
 * Todos los m�todos indican que la excepci�n por comunicaci�n remota se controlar� en la clase que los llame
 */

public interface ConstelacionInterfaceRMI extends Remote {
	/**
	 * Busca los nombres de constelaci�n en el servidor que contengan la cadena introducida como par�metro y
	 * los devuelve como cadena
	 * @param nombre cadena a comparar con el nombre de la constelacion
	 * @return String cadena con todas las constelaciones que contengan el par�metro de entrada
	 * @throws RemoteException devuelve una excepci�n de este tipo si hay fallo en la comunicaci�n remota
	 */
	public String buscarNombre(String nombre) throws RemoteException;
	
	
}
