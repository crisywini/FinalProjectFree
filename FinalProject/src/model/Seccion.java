package model;

import java.io.Serializable;

public class Seccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoSeccion miTipo;
	private Puesto[][] misPuestos;
	private Escenario miEscenarioAsociado;
	/**
	 * Constructor vacio de la clase Seccion
	 */
	public Seccion() {
		this(TipoSeccion.SEGUNDO_PISO_3, new Escenario());
		misPuestos = new Puesto[1][1];
	}
	
	/**
	 * Constructor completo de la clase seccion
	 * @param miTipo tipo de seccion
	 * @param miEscenarioAsociado escenario asociado a esta seccion
	 */
	public Seccion(TipoSeccion miTipo, Escenario miEscenarioAsociado) {
		this.miTipo = miTipo;
		this.miEscenarioAsociado = miEscenarioAsociado;
		generarMatrizTriangularSuperiorInversa(4);
	}


	public Puesto[][] getMisPuestos() {
		return misPuestos;
	}

	public void setMisPuestos(Puesto[][] misPuestos) {
		this.misPuestos = misPuestos;
	}

	public Escenario getMiEscenarioAsociado() {
		return miEscenarioAsociado;
	}

	public void setMiEscenarioAsociado(Escenario miEscenarioAsociado) {
		this.miEscenarioAsociado = miEscenarioAsociado;
	}

	public TipoSeccion getMiTipo() {
		return miTipo;
	}

	public void setMiTipo(TipoSeccion miTipo) {
		this.miTipo = miTipo;
	}
	/**
	 * Metodo que permite generar una matriz de puestos de la siguiente manera:
	 * 1 1 1 1 
     * 0 1 1 1 
     * 0 0 1 1 
     * 0 0 0 1 
	 * @param filasYColumnas
	 */
	public void generarMatrizTriangularSuperior(int filasYColumnas) {
		misPuestos = new Puesto[filasYColumnas][filasYColumnas];
		char letra = 'A';
		int numero = 1;
		for (int i = 0; i < misPuestos.length; i++) {
			for (int j = 0; j < misPuestos[i].length; j++) {
				if (j >= i) {
					misPuestos[i][j] = new Puesto(letra, numero, EstadoPuesto.LIBRE);
					letra++;
					numero++;
				}
				if (letra > 'Z')
					letra = 'A';
			}
		}
	}
	/**
	 * Metodo que permite generar una matriz de puestos de la siguiente manera:
	 * 1 1 1 1 
     * 1 1 1 0 
     * 1 1 0 0 
     * 1 0 0 0 
	 * @param filasYColumnas
	 */
	public void generarMatrizTriangularSuperiorInversa(int filasYColumnas)
	{
		misPuestos = new Puesto[filasYColumnas][filasYColumnas];
		char letra = 'A';
		int numero = 1;
		int finalI  = misPuestos[0].length;
		for (int i = 0; i < misPuestos.length; i++) {
			for (int j = 0; j < finalI; j++) {
				misPuestos[i][j] = new Puesto(letra, numero, EstadoPuesto.LIBRE);
				letra++;
				numero++;
				if(letra>'Z')
					letra = 'A';
			}
			if(letra>'Z')
				letra = 'A';
			finalI--;
		}
	}
	/**
	 * Metodo que permite crear una matriz de la siguiente forma: 
	 * 1 1 1 1 
     * 1 1 1 1 
     * 1 1 1 1 
     * 1 1 1 1 
	 * @param filasYColumnas
	 */
	public void generarMatrizCuadrada(int filasYColumnas)
	{
		misPuestos = new Puesto[filasYColumnas][filasYColumnas];
		char letra = 'A';
		int numero = 1;
		for (int i = 0; i < misPuestos.length; i++) {
			for (int j = 0; j < misPuestos[i].length; j++) {
				misPuestos[i][j] = new Puesto(letra, numero, EstadoPuesto.LIBRE);
				letra++;
				numero++;
				if(letra>'Z')
					letra = 'A';
			}
			if(letra>'Z')
				letra = 'A';
		}
	}
	/**
	 * Metodo que permite crear una matriz de puestos de la siguiente forma:
	 * 1 0 0 0 
     * 1 1 0 0 
     * 1 1 1 0 
     * 1 1 1 1 
	 * @param filasYColumnas
	 */
	public void generarMatrizTriangularInferior(int filasYColumnas)
	{
		misPuestos = new Puesto[filasYColumnas][filasYColumnas];
		char letra = 'A';
		int numero = 1;
		for (int i = 0; i < misPuestos.length; i++) {
			for (int j = 0; j < misPuestos[i].length; j++) {
				if(i>=j)
				{
					misPuestos[i][j] = new Puesto(letra, numero, EstadoPuesto.LIBRE);
					letra++;
					numero++;
					if(letra>'Z')
						letra = 'A';
				}
				if(letra>'Z')
					letra = 'A';
			}
		}
	}
	/**
	 * Metodo que permite generar una matriz de puestos con la siguiente forma:
	 * 0 0 0 1 
     * 0 0 1 1 
     * 0 1 1 1 
     * 1 1 1 1 
	 * @param filasYColumnas
	 */
	public void generarMatrizTriangularInferiorInversa(int filasYColumnas)
	{
		misPuestos = new Puesto[filasYColumnas][filasYColumnas];
		char letra = 'A';
		int numero = 1;
		int inicioI = 0;
		int inicioJ = misPuestos.length-1;
		for (int i = 0; i < misPuestos.length; i++) {
			for (int j = inicioJ; j < misPuestos.length; j++) {
				misPuestos[inicioI][j] = new Puesto(letra, numero, EstadoPuesto.LIBRE);
			}
			inicioI++;
			inicioJ--;
		}
	}
}