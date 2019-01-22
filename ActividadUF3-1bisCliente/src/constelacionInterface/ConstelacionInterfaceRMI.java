package constelacionInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interface ConstelacionInterfaceRMI: Esta interfaz indica que métodos son los que se pueden usar remotamente 
 * para ello extienden la clase Remote. 
 * Todos los métodos indican que la excepción por comunicación remota se controlará en la clase que los llame
 */
public interface ConstelacionInterfaceRMI extends Remote {
	
	/**
	 * Busca los nombres de constelación en el servidor que contengan la cadena introducida como parámetro y
	 * los devuelve como cadena
	 * @param nombre cadena a comparar con el nombre de la constelacion
	 * @return String cadena con todas las constelaciones que contengan el parámetro de entrada
	 * @throws RemoteException devuelve una excepción de este tipo si hay fallo en la comunicación remota
	 */
	
	public String buscarNombre(String nombre) throws RemoteException;
	
	/**
	 * Añade una nueva constelación con los datos dados como parámetros. 
	 *
	 * @param nombre nombre de la constelación
	 * @param observaciones observaciones de la constelación
	 * @return true, si la operación es un éxito
	 * @throws RemoteException devuelve una excepción de este tipo si hay fallo en la comunicación remota
	 */
	
	public boolean addConstelacion(String nombre,String observaciones) throws RemoteException;
	
	/**
	 * Busca un nombre de constelación en el servidor que sea igual a la cadena introducida como parámetro y 
	 *elimina esta constelación. 
	 * @param nombre nombre de la constelación
	 * @return true, si la operación es un éxito
	 * @throws RemoteException devuelve una excepción de este tipo si hay fallo en la comunicación remota
	 */
	//
	public boolean eliminarConstelacion(String nombre) throws RemoteException;
	
}
