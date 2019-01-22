package cliente;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import constelacionInterface.ConstelacionInterfaceRMI;

/**
 * Clase ClienteConstelacionRMI se encarga de conectarse al registro que est� en el servidor. Para ello se da la direcci�n IP del servidor
 * ,en este  caso localhost, y el puerto. Se busca la entrada del registro que nos interesa y se obtiene el objeto remoto.
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
			String nombre;
			String observaciones;
			String menu;
			do {
				/*
				 * Men� para elegir las distintas operaciones que vamos a realizar con los datos del servidor. Podemos 
				 * a�adir,buscar y eliminar. Para terminar el programa introducimos F. Ninguna de las opciones tiene en cuenta
				 * si se introduce en may�sculas o min�sculas
				 * */
				System.out.println("          MENU DE CONSTELACIONES");
				System.out.println("____________________________________________");
				System.out.println("1. Introduce A para a�adir");
				System.out.println("2. Introduce B para buscar");
				System.out.println("3. Introduce E para eliminar");
				System.out.println("4. Introduce F para terminar el programa");
				System.out.println("___________________________________________");
				
				System.out.print("Introduce opci�n:");
				System.out.println();
				menu = lector.nextLine().toUpperCase();
				switch(menu) {
				case "A": //a�adir constelacion
					
					/*Introducimos por teclado los datos de la constelaci�n y 
					 * se a�ade a los datos del servidor con m�todo addConstelacion, a trav�s del stub*/
					
					System.out.print("Escribe nombre de la constelaci�n:");
					nombre = lector.nextLine();
					System.out.println();
					System.out.println("Escribe las observaciones sobre la constelaci�n:");
					observaciones = lector.nextLine();
					System.out.println();
					boolean add= constelaciones.addConstelacion(nombre,observaciones);
					//Si todo es correcto se muestra un mensaje indicando que la operaci�n ha tenido �xito
					//si no tambi�n se muestra un mensaje indicando que no se ha a�adido
					if(add) System.out.println("Constelaci�n a�adida");
					else  System.out.println("Error al a�adir la Constelaci�n. Constelaci�n no a�adida");
				   break;
				   
				case "B": //buscar constelacion
					do {
						/*Mientras no pulsemos fin, leemos por teclado el nombre de la constelaci�n 
						 * y se busca en los datos del servidor con del m�todo buscarNombre a trav�s del stub, un nombre
						 * que contenga lo introducido por teclado, no es necesario que sean iguales.
						 * No se tiene en cuenta las min�sculas o may�sculas
						 */
						System.out.println("Escribe nombre de la constelaci�n <FIN para terminar>");
					
						buscado = lector.nextLine().toUpperCase();
						if(!buscado.toUpperCase().equals("FIN")) {
							if(!buscado.equals("")) {
								resultado=constelaciones.buscarNombre(buscado);
								/*Se controla el resultado devuelto y se muestran mensajes, si no hay resultado o no se
								 * ha escrito nada.
								*Si todo es correcto se muestran las constelaciones que contengan la entrada*/
								if(resultado.equals("")) {
					        	 	System.out.println("No se ha encontrado nig�n resultado");
					        	 	System.out.println();
								}
								else    System.out.println(resultado);	
					               
							} else {
								System.out.println("No se ha introducido nig�n nombre");
								System.out.println();
							}
						} else { //Cuando se escribe fin se muestra un mensaje de fin de b�squedas
							System.out.println("B�squedas finalizadas");
							System.out.println();
						}
			
					} while (!buscado.equals("FIN"));
		 
					break;
				case "E": //eliminar constelacion
					
					/*Introducimos por teclado el nombre de la constelaci�n y con el m�todo eliminarConstelacion
					 * si se coincide con una entrada en los datos del servidor, se elimina*/
					 
					System.out.print("Escribe nombre de la constelaci�n:");
					nombre= lector.nextLine().toUpperCase();
					System.out.println();
					boolean eliminado=constelaciones.eliminarConstelacion(nombre);
					/*Si el resultado es exitoso se muestra un mensaje indic�ndolo, si no se encuentra
					 * tambi�n se muestra un mensaje */
					if(eliminado) System.out.println("Constelaci�n eliminada");
					else System.out.println("Constelaci�n no existe");
					break;
					
				default: //Si se pulsa f se acaba el programa, si no aparece un mensaje indicando que no existe esa opci�n
					if(!menu.equals("F")) System.out.println("Opci�n incorrecta");
					
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