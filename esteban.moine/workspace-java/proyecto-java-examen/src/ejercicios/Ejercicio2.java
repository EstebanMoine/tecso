package ejercicios;

/**
 * A. Crear una clase Persona con los siguientes campos
 * (con sus respectivos getters, setters y constructor)
 * 
 * TipoDocumento - enum (dni/libretacivica) 
 * NroDocumento - Integer
 * Nombre - String
 * Apellido - String
 * FechaNacimiento - Date
 * 
 * B. En el método main de la clase "Ejercicio2" crear una nueva instancia
 * de la clase persona y settearle valores reales con tus datos
 * 
 * 
 * C. En el método main de la clase "Ejercicio 2" imprimir los valores en 
 * consola 
 * (crear método main e imprimir valores) 
 *  
 * @author examen
 *
 */
public class Ejercicio2 {

	/**
	 * 
	 */
	public Ejercicio2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Persona persona=new Persona(
				 	Persona.TipoDocumento.dni.toString(),
		         	38979109,
		         	"Esteban",
		         	"Moine",
		         	"30-09-1995"
				 );
		
		 persona.imprimirValores();
		 
		 //imprimirValores(persona);

	}

	/*private static void imprimirValores(Persona persona) {
		// TODO Auto-generated method stub
		System.out.println("Tipo doc: "+persona.getTipoDoc());
		System.out.println("Nro doc: "+persona.getNroDocumento());
		System.out.println("Nombre: "+persona.getNombre());
		System.out.println("Apellido: "+persona.getApellido());
		System.out.println("Fecha nacimiento: "+persona.getFechaNacimiento());
	}*/

}
