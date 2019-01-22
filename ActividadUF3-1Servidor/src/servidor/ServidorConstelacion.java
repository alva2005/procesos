package servidor;
import java.net.InetAddress;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Clase ServidorConstelaci�n: realiza las operaciones del servidor. Obtenemos la direcci�n de la m�quina del servidor; si no existe
 * ning�n problema creamos el registro en �ste en el puerto indicado, le damos un nombre y lo activamos para que se
 * pueda publicar al exterior. Si no existe problema se indica un mensaje indicando que el servicio se ha registrado en una ip
 * y un puerto. Si es al contrario se indica el error
 * */
public class ServidorConstelacion {
	
	public static void main(String[] args) {
		String host;
		int puerto = 5055;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("No se ha podido obtener la direcci�n IP");
			System.out.println(e.getMessage());
			return;
		}
		
		try {
			
			Registry registro = LocateRegistry.createRegistry(puerto);
			ConstelacionRMI constelacion = new ConstelacionRMI();
			registro.rebind("miConstelacion", constelacion);
			
			System.out.println("Servicio registrado en host " + host + " y puerto " + puerto);
		} catch (RemoteException e) {
			System.out.println("No se ha podido registrar el servicio");
			System.out.println(e.getMessage());
		}
	}
}