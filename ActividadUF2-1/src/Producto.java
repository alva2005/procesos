/*Clase Productos con los atributos nombre, stock y precio*/

public class Producto {
	private String nombre;
	private int stock;
	private float precio;
	
	public Producto(String nombre, int stock, float precio) {
		super();
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/* Sobreescribimos el método toString que usaremos para mandar al cliente los 
	 * elementos de producto requerido*/
	@Override
	public String toString() {
		return  nombre + "; Cantidad=" + stock + "; Precio=" + precio ;
	}
	
	

}
