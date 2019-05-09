package ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio4 {

	// listas de ejemplo, pueden variarse su contenido, 
	static Integer[] valoresLista1 = {1, 2, 5, 8, 10, 30, 20, 8, 9, 10};
	static Integer[] valoresLista2 = {1, 2, 4, 20, 5, 10, 7, 8, 10, 9};

	/**	 
	 * Para ejecutar el método main se debe hacer boton derecho sobre la clase
	 * "Run As --> Java Application" 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("**** inicializando datos ****");
		
		List<Integer> lista1 = new ArrayList<Integer>(Arrays.asList(valoresLista1));
		List<Integer> lista2 = new ArrayList<Integer>(Arrays.asList(valoresLista2));
	
		System.out.println("**** inicializacion exitosa ****");

		// EJERCICIO 4.1: explicar salidas y sugerir mejoras
		//informacion(lista1, 10);
		
		// EJERCICIO 4.2: corregir el metodo
		List<Integer> union = unionListas(lista1, lista2);
		System.out.println("union: " + union.toString());
		
		// EJERCICIO 4.3: implementar
		List<Integer> interseccion = interseccionListas(lista1, lista2);
		System.out.println("interseccion: " + interseccion.toString());
		
		// EJERCICIO 4.4: implementar
		List<Integer> orden1 = ordenaListaAscendente(lista1);
		System.out.println("orden asc: " + orden1);
		
		// EJERCICIO 4.5: implementar
		List<Integer> orden2 = ordenaListaDescendente(lista2);
		System.out.println("orden desc: " + orden2);

		// EJERCICIO 4.6: implementar
		boolean b = tienenMismoContenido(lista1, lista2);
		System.out.println("mismo contenido: " + b);
		
	}

	private static void informacion(List<Integer> lista1, Integer numero) {
		// TODO: explicar salidas de los system out y sugerir alguna mejora a la implementacion
		// 1 saber la cantidad de pares e impares en un mismo for
		// 2 cambiar int pares por un array de pares para tambien saber cuales son los pares
		// 3 cambiar c=c+1 por c++
		// 4 reemplazar en todos los lugares donde aparezca lista1.size()/2 por la variable p que tiene ese mismo valor
		
		List<Integer> pares = new ArrayList<Integer>();
		List<Integer> impares = new ArrayList<Integer>();
		
		for (Integer n: lista1) {
			if (n % 2 == 0) {
				pares.add(n);
			}else{
				impares.add(n);
			}
		}

		//muestra todos los pares
		System.out.println("... Pares " + pares.toString());
		// total de pares
		System.out.println("... Total " + pares.size());
		//muestra todos los impares
		System.out.println("... Impares " + impares.toString());
		// total de impares
		System.out.println("... Total " + impares.size());
		
		int p = lista1.size() / 2;
	
		// muestra P que es el num que esta en la mitad del array que es 5
		// y la posicion que se encuentra ese numero en el array que es 2
		System.out.println("... p="+ p +" Posicion en la lista1=" + lista1.indexOf(p));
		
		// cuenta la cantidad de numeros que hay en la lista1 mayor
		// al numero que le pasamos por parametro
		int c = 0;
		for (Integer n: lista1) {
			if (n > numero) {
				c++;
			}
		}
		
		if (c > p) {
			System.out.println("... "+ c + ">" +p);
		} else if (c > 0) {
			System.out.println("... "+ c +" > 0");
		} else {
			System.out.println("... "+ c +" < 0");
		}
	}

	/***
	 * @param lista1
	 * @param lista2
	 * 
	 * retornar una lista que contenga los elementos de ambas listas, sin elementos repetidos 
	 * 
	 */
	private static List<Integer> unionListas(List<Integer> lista1, List<Integer> lista2) {
		// TODO: corregir el metodo para que funcione correctamente 
		
		// list union contiene lista1 y lista2 con todos los numeros repetidos
		// los uno para hacer solo un for
		List<Integer> union = new ArrayList<Integer>();
		union.addAll(lista1);
		union.addAll(lista2);
		
		// listaRetorno va a contener todos los numeros sin repetir
		List<Integer> listaRetorno = new ArrayList<Integer>();
		for (Integer m: union) {
			if (!listaRetorno.contains(m)) {
				listaRetorno.add(m);
			}
		}
		 
		//ordeno la lista de menor a mayor
		Collections.sort(listaRetorno);
		
		return listaRetorno;
	}

	/***
	 * @param lista1
	 * @param lista2
	 * 
	 * retornar una lista que contenga los elementos que estan presentes en ambas listas, sin elementos repetidos 
	 * 
	 */
	private static List<Integer> interseccionListas(List<Integer> lista1, List<Integer> lista2) {

		List<Integer> union = new ArrayList<Integer>();
		union.addAll(lista1);
		union.addAll(lista2);
		
		// listaRetorno va a contener todos los numeros sin repetir
		List<Integer> listaRetorno = new ArrayList<Integer>();
		for (Integer m: union) {
			if (!listaRetorno.contains(m)&&lista1.contains(m)&&lista2.contains(m)) {
				listaRetorno.add(m);
			}
		}
		
		//ordeno la lista de menor a mayor
		Collections.sort(listaRetorno);
		
		return listaRetorno;
	}

	/***
	 * @param lista1
	 * 
	 * retornar la lista recibida, ordenada en forma ascendente
	 * 
	 */
	private static List<Integer> ordenaListaAscendente(List<Integer> lista1) {
		// TODO:

		List<Integer> ordenada = new ArrayList<Integer>();
		ordenada.addAll(lista1);
		Collections.sort(ordenada);
		
		return ordenada;
	}

	/***
	 * @param lista2
	 * 
	 * retornar la lista recibida, ordenada en forma descendente
	 * 
	 */
	private static List<Integer> ordenaListaDescendente(List<Integer> lista2) {
		// TODO:
		
		List<Integer> ordenada = new ArrayList<Integer>();
		ordenada.addAll(lista2);
		
		Comparator<Integer> comparador = Collections.reverseOrder();
		Collections.sort(ordenada, comparador);
		
		return ordenada;
	}

	/***
	 * @param lista1
	 * @param lista2
	 * 
	 * devuelve true si contienen los mismos elementos
	 * NO se considera valido que esten en diferente orden
	 * NO se considera valido que la cantidad de repeticiones de los elementos sea diferente
	 * 
	 */
	private static boolean tienenMismoContenido(List<Integer> lista1, List<Integer> lista2) {
		// TODO:
		
	    return lista1.equals(lista2);
		
	}

}
