package edu.java.cuentaCorriente;

import java.util.List;
import java.util.Scanner;

/**
 * Modelo de las cuentas corrientes a gestionar
 * @author garfe *
 */
public class CuentaCorriente {
	
	/*
	 * Si no definimos ningún constructor por campos,
	 * se puede utilizar el constructor vacío por defecto.
	 * En el momento que se defina un constructor por campos
	 * específico, si se quiere usar el constructor por defecto
	 * será necesario definirlo en la clase de forma explícita.
	 */
	
	public CuentaCorriente(String dni, String nombreTitular, double saldo) {
		super();
		this.dni = dni;
		this.nombreTitular = nombreTitular;
		this.saldo = saldo;
	}

	public CuentaCorriente() {
		super();
	}

	//Atributos
	/*
	 * Modificador private: solo la misma clase podrá acceder a los
	 * atributos de forma directa.
	 */
	
	private String dni;
	private String nombreTitular;
	private double saldo;
	
	//Getters y Setters
	/*
	 * Se utilizar para que software externo a la clase pueda 
	 * tener acceso a los atributos.
	 */
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	//Sobre escritura toString
	@Override
	public String toString() {
		return "CuentaCorriente [dni=" + dni + ", nombreTitular=" + nombreTitular + ", saldo=" + saldo + "]";
	}
	
	//Métodos
	public CuentaCorriente crearCuenta () {
		
		System.out.println("CREAR CUENTA: ");
		Scanner entradaDni = new Scanner(System.in);	
		Scanner entradaNombreTitular = new Scanner(System.in);	
		String opcionEntradaDni;
		String opcionEntradaNombreTitular;
		System.out.println("Introduza el DNI del titular: ");
		opcionEntradaDni = entradaDni.next();
		System.out.println("Introduza el nombre completo del titular: ");
		opcionEntradaNombreTitular = entradaNombreTitular.next();
		
		//Opción 1, constructor vacío
		CuentaCorriente cccVacio = new CuentaCorriente();
		cccVacio.setDni(opcionEntradaDni);
		cccVacio.setNombreTitular(opcionEntradaNombreTitular);
		cccVacio.setSaldo(0);
		return cccVacio;

		//Opción 2, constructor con todos los campos
		/*CuentaCorriente cccInicializado = new CuentaCorriente(opcionEntradaDni, opcionEntradaNombreTitular, 0);
		return cccInicializado;*/
	}
	
	public List<CuentaCorriente> ingresoCuenta(List<CuentaCorriente> bd) {
		System.out.println("INGRESO CUENTA: ");
		//pedir dni
		Scanner entradaDni = new Scanner(System.in);
		System.out.println("Indique dni de cuenta: ");
		String opcionEntradaDni = entradaDni.next();
		//buscar la cuenta
		int contador = 0;
		boolean esEncontrado = false;
		for(CuentaCorriente cuenta: bd) {			
			String dniBd = cuenta.getDni();
			if(dniBd.equals(opcionEntradaDni)) {
				esEncontrado = true;
				break;
			}
			contador++;			
		}		

		if(esEncontrado) {
			System.out.println("Indique saldo a ingresar: ");
			Scanner entradaIngreso = new Scanner(System.in);
			double ingreso = entradaIngreso.nextDouble();
			double saldoActual = bd.get(contador).getSaldo();
			bd.get(contador).setSaldo(saldoActual+ingreso);
			double saldoNuevo = saldoActual+ingreso;
			System.out.println("Saldo anterior: "+saldoActual+"; Saldo nuevo: "+ingreso);
		}else {
			System.out.println("No existe cuenta para el dni indicaco: "+opcionEntradaDni);
			return bd;
		}
		return bd;
	}
	
	public void mostrarCuentasUsuario(String dniUsuario, List<CuentaCorriente> listaBD){
		// Buscamos la cuenta del usuario (suponemos que solo tiene una)
		// Para buscar vamos a usar un método privado
		CuentaCorriente usuario = buscaCuentaUsuario(listaBD, dniUsuario);
		
		// Ahora ya podemos mostrar la información de la cuenta
		System.out.println("\nDNI: "+ usuario.getDni() +  "; Nombre titular: "+usuario.getNombreTitular()
			+ "; Saldo: "+ usuario.getSaldo());
	}
	
	// MÉTODO AUXILIAR
	private CuentaCorriente buscaCuentaUsuario(List<CuentaCorriente> listaBD, String dniUsuario) {
		CuentaCorriente aux = new CuentaCorriente();
		for (CuentaCorriente var : listaBD) {
			if(var.getDni().equals(dniUsuario)) {
				aux = var;
				break;
			}
		}
		return aux;
	}
	
	/**
	 * Metodo retirar dinero
	 * ENTRADA:
	 * SALIDA:
	 */
	public List<CuentaCorriente> retirarDineroCuenta(List<CuentaCorriente> listaBD, String dniUsuario) {
		//
		// Declaramos las variables que vamos a necesitar
		double saldoActual, saldoRetirar, saldoRestante;
		Scanner entradaSaldoRetirar = new Scanner(System.in);
		
		// Buscamos la cuenta del usuario (suponemos que solo tiene una)
		// Para buscar vamos a usar un método privado
		CuentaCorriente usuario = buscaCuentaUsuario(listaBD, dniUsuario);
		
		// Ahora vamos a pedir el dinero a retirar
		System.out.println("Saldo a retirar: ");
		saldoRetirar = entradaSaldoRetirar.nextDouble();
		
		// Para comprobar si tiene saldo suficiente vamos a coger el saldo actual de la cuenta
		saldoActual = listaBD.get(listaBD.indexOf(usuario)).getSaldo();
		
		if(saldoActual > saldoRetirar) {
			
			// Si el saldo de la cuenta es mayor que el saldo a retirar, podremos completar la operación
			// Con el saldo actual y el saldo a retirar, vamos a calcular el saldo restante
			saldoRestante = saldoActual - saldoRetirar;
			// Actualizamos el saldo de la cuenta
			listaBD.get(listaBD.indexOf(usuario)).setSaldo(saldoRestante);
			
			// Mostraremos el saldo restante
			System.out.println("Saldo restante: "+ saldoRestante);
		}
		else {
			// Mostraremos un mensaje de error
			System.err.println("No hay suficiente saldo en la cuenta");
		}
		
		// Devolvemos la lista actualizada
		return listaBD;
	}
	
}
