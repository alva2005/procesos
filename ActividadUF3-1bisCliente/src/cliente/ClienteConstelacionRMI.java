package cliente;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import constelacionInterface.ConstelacionInterfaceRMI;

/**
 * Clase ClienteConstelacionRMI se encarga de conectarse al registro que está en el servidor. Para ello se da la dirección IP del servidor
 * ,en este  caso localhost, y el puerto. Se busca la entrada del registro que nos interesa y se obtiene el objeto remoto.
 * Una vez hecho esto se pueden invocar métodos de manera remota y hacer operaciones con los datos que están en el servidor
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
			String nombre;
			String observaciones;
			String menu;
			do {
				/*
				 * Menú para elegir las distintas operaciones que vamos a realizar con los datos del servidor. Podemos 
				 * añadir,buscar y eliminar. Para terminar el programa introducimos F. Ninguna de las opciones tiene en cuenta
				 * si se introduce en mayúsculas o minúsculas
				 * */
				System.out.println("          MENU DE CONSTELACIONES");
				System.out.println("____________________________________________");
				System.out.println("1. Introduce A para añadir");
				System.out.println("2. Introduce B para buscar");
				System.out.println("3. Introduce E para eliminar");
				System.out.println("4. Introduce F para terminar el programa");
				System.out.println("___________________________________________");
				
				System.out.print("Introduce opción:");
				System.out.println();
				menu = lector.nextLine().toUpperCase();
				switch(menu) {
				case "A": //añadir constelacion
					
					/*Introducimos por teclado los datos de la constelación y 
					 * se añade a los datos del servidor con método addConstelacion, a través del stub*/
					
					System.out.print("Escribe nombre de la constelación:");
					nombre = lector.nextLine();
					System.out.println();
					System.out.println("Escribe las observaciones sobre la constelación:");
					observaciones = lector.nextLine();
					System.out.println();
					boolean add= constelaciones.addConstelacion(nombre,observaciones);
					//Si todo es correcto se muestra un mensaje indicando que la operación ha tenido éxito
					//si no también se muestra un mensaje indicando que no se ha añadido
					if(add) System.out.println("Constelación añadida");
					else  System.out.println("Error al añadir la Constelación. Constelación no añadida");
				   break;
				   
				case "B": //buscar constelacion
					do {
						/*Mientras no pulsemos fin, leemos por teclado el nombre de la constelación 
						 * y se busca en los datos del servidor con del método buscarNombre a través del stub, un nombre
						 * que contenga lo introducido por teclado, no es necesario que sean iguales.
						 * No se tiene en cuenta las minúsculas o mayúsculas
						 */
						System.out.println("Escribe nombre de la constelación <FIN para terminar>");
					
						buscado = lector.nextLine().toUpperCase();
						if(!buscado.toUpperCase().equals("FIN")) {
							if(!buscado.equals("")) {
								resultado=constelaciones.buscarNombre(buscado);
								/*Se controla el resultado devuelto y se muestran mensajes, si no hay resultado o no se
								 * ha escrito nada.
								*Si todo es correcto se muestran las constelaciones que contengan la entrada*/
								if(resultado.equals("")) {
					        	 	System.out.println("No se ha encontrado nigún resultado");
					        	 	System.out.println();
								}
								else    System.out.println(resultado);	
					               
							} else {
								System.out.println("No se ha introducido nigún nombre");
								System.out.println();
							}
						} else { //Cuando se escribe fin se muestra un mensaje de fin de búsquedas
							System.out.println("Búsquedas finalizadas");
							System.out.println();
						}
			
					} while (!buscado.equals("FIN"));
		 
					break;
				case "E": //eliminar constelacion
					
					/*Introducimos por teclado el nombre de la constelación y con el método eliminarConstelacion
					 * si se coincide con una entrada en los datos del servidor, se elimina*/
					 
					System.out.print("Escribe nombre de la constelación:");
					nombre= lector.nextLine().toUpperCase();
					System.out.println();
					boolean eliminado=constelaciones.eliminarConstelacion(nombre);
					/*Si el resultado es exitoso se muestra un mensaje indicándolo, si no se encuentra
					 * también se muestra un mensaje */
					if(eliminado) System.out.println("Constelación eliminada");
					else System.out.println("Constelación no existe");
					break;
					
				default: //Si se pulsa f se acaba el programa, si no aparece un mensaje indicando que no existe esa opción
					if(!menu.equals("F")) System.out.println("Opción incorrecta");
					
				}
			} while (!menu.equals("F"));	
			System.out.println("Programa finalizado");
		} catch (RemoteException | NotBoundException e) {
			//control de las excepciones
			System.out.println(e.getMessage());
		 }
		//cerramos el buffer
		lector.close();
	}
	
	
}