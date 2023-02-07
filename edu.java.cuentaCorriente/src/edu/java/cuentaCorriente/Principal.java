package edu.java.cuentaCorriente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase de acceso a la aplicación de gestión de cuentas
 * @author garfe
 */
public class Principal {

	public static void main(String[] args) {
		
		//Inicializar el tipo cuenta
		CuentaCorriente ccc = new CuentaCorriente();
		//Listado de cuentas (BD)
		List<CuentaCorriente> listaCcc = new ArrayList<>();
		
		// Imprimir el menú por consola
		// Scanner con la opción del usuario
		Scanner entradaOpcion = new Scanner(System.in);		
		Boolean cerrarMenu = false;
		int opcion;
		String dniUsuario;
		while(!cerrarMenu) {	
			//Mostramos el menú
			mostrarMenu();
			System.out.println("Introduza la opción deseada: ");
			opcion = entradaOpcion.nextInt();
			System.out.println("[INFO] - Has seleccionado la opcion " + opcion);
			
			switch (opcion) {			
				case 1:
					// CREAR CUENTA
					listaCcc.add(ccc.crearCuenta());					
					System.out.println("Cuenta creada: " + listaCcc.get(listaCcc.size()-1).toString());
					break;
				case 2:
					// INGRESAR DINERO
					//llamamos al método
					listaCcc = ccc.ingresoCuenta(listaCcc);
					break;
				case 3:
					// RETIRAR DINERO
					// Pedimos el DNI del usuario
					System.out.print("Introduzca su DNI: ");
					dniUsuario = entradaOpcion.next();
					listaCcc = ccc.retirarDineroCuenta(listaCcc, dniUsuario);
					break;
				case 4:
					// MOSTRAR INFORMACIÓN
					// Pedimos el DNI del usuario
					System.out.print("Introduzca su DNI: ");
					dniUsuario = entradaOpcion.next();
					ccc.mostrarCuentasUsuario(dniUsuario, listaCcc);
					break;
				case 5:
					cerrarMenu = true;
					break;
				default:

			}
		}
		
		entradaOpcion.close();

	}
	static void mostrarMenu() {
		System.out.println("1. Crear cuenta");
		System.out.println("2. Ingresar dinero");
		System.out.println("3. Retirar dinero");
		System.out.println("4. Mostrar información cuenta");
		System.out.println("5. Salir");
	}
	

}
