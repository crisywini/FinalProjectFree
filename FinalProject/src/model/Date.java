package model;

import java.io.Serializable;

public class Date implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int day;
	private static int month;
	private static int year;

	public Date(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	public static int getDay() {
		return day;
	}

	public static void setDay(int day) {
		Date.day = day;
	}

	public static int getMonth() {
		return month;
	}

	public static void setMonth(int month) {
		Date.month = month;
	}

	public static int getYear() {
		return year;
	}

	public static void setYear(int year) {
		Date.year = year;
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
}