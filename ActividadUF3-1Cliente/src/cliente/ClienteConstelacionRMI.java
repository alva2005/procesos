package cliente;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import constelacionInterface.ConstelacionInterfaceRMI;

/**
 * Clase ClienteConstelacionRMI: se encarga de conectarse al registro que est� en el servidor. Para ello se da la direcci�n 
 * IP del servidor ,en este  caso localhost, y el puerto. Se busca la entrada del registro que nos interesa y se obtiene el 
 * objeto remoto.
 * Una vez hecho esto se pueden invocar m�todos de manera remota y hacer operaciones con los datos que est�n en el servidor
 * 
 * */

public class ClienteConstelacionRMI {

	public static void main(String[] args) {
        Registry registry;
        Scanner lector = new Scanner(System.in);
		try {
			registry = LocateRegistry.getRegistry("localhost", 5055);
			System.out.println("Hemos obtenido el registro");
			ConstelacionInterfaceRMI constelaciones = (ConstelacionInterfaceRMI) registry.lookup("miConstelacion"); 
			System.out.println("Hemos obtenido el objeto remoto");
			System.out.println(); 
			
			String buscado;
			String resultado;
			
			/*
			 * Mientras no pulsemos fin, leemos por teclado el nombre de la constelaci�n 
			 * y se busca en los datos del servidor con del m�todo buscarNombre a trav�s del stub, un nombre
			 * que contenga lo introducido por teclado, no es necesario que sean iguales.
			 * No se tiene en cuenta las min�sculas o may�sculas
			 */
			
			do {
				System.out.println("Escribe nombre de la constelaci�n <FIN para terminar>");
				
						buscado = lector.nextLine().toUpperCase();
						if(!buscado.toUpperCase().equals("FIN")) {
							if(!buscado.equals("")) {
						         resultado=constelaciones.buscarNombre(buscado);
						         /*Se controla el resultado devuelto y se muestran mensajes, si no hay resultado o no se
								  * ha escrito nada.
								  * Si todo es correcto se muestran las constelaciones que contengan la entrada*/
						          if(resultado.equals("")) {
						        	 	System.out.println("No se ha encontrado nig�n resultado");
						        	 	System.out.println();
						          }
						        else    System.out.println(resultado);	
						               
							} else {
				        	 	System.out.println("No se ha introducido nig�n nombre");
				        	 	System.out.println();
				          }
							//Cuando se escribe fin se muestra un mensaje de fin de programa
						} else System.out.println("Programa finalizado");
				
			} while (!buscado.equals("FIN"));
			 
			
		} catch (RemoteException | NotBoundException e) {
			//control de la excepciones
			System.out.println(e.getMessage());
		}
		//cerramos el buffer
		lector.close();
	}
	
	
}