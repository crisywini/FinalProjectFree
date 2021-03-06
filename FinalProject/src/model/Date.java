package model;

import java.io.Serializable;

public class Date implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;
	private int month;
	private int year;

	public Date() {
		this(0, 0, 00);
	}

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Metodo equals para comparar fechas
	 */
	@Override
	public boolean equals(Object obj) {

		boolean centi = true;
		Date fechaAux;
		if (obj instanceof Date) {
			fechaAux = (Date) obj;
			if (fechaAux.day != this.day || fechaAux.month != this.month || fechaAux.year != this.year) {
				centi = false;
			}

		}

		return centi;
	}

	@Override
	public String toString() {
		String info = day + "/" + month + "/" + year;
		return info;
	}
}