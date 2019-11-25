package util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class, where we will create methods for training read and write excel
 * files, with <a href="https://poi.apache.org/">Apache POI</a>, we use
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java
 * API To Access Microsoft</a> HSSF is the POI Project's pure Java
 * implementation of the Excel '97(-2007) file.
 * 
 * Clase de utilidades, donde crearemos métodos para el aprendizaje de la
 * lectura y escritura de ficheros excel con
 * <a href="https://poi.apache.org/">Apache POI</a>, usaremos
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java
 * API To Access Microsoft</a> HSSF es el proyecto POI de implementación total
 * en Java para ficheros Excel '97(-2007).
 *
 * @author Xules You can follow me on my website http://www.codigoxules.org/en
 *         Puedes seguirme en mi web http://www.codigoxules.org).
 */
public class ApachePoi {
	/**
	 * Explanation of the method by which we read the excel file we pass as
	 * parameter if exists, in this example we print the content in the console.
	 * Explicación del método con el que leemos el fichero excel que pasamos como
	 * parámetro si existe, en este ejemplo mostramos el contenido por la consola.
	 * <h3>Example (Ejemplo)</h3>
	 * 
	 * <pre>
	 * JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
	 * javaPoiUtils.readExcelFile(new File("/home/xules/codigoxules/apachepoi/PaisesIdiomasMonedas.xls"));
	 * </pre>
	 *
	 * @param excelFile <code>String</code> excel File we are going to read. Fichero
	 *                  excel que vamos a leer.
	 */
	public void readExcelFile(File excelFile) {
		InputStream excelStream = null;
		try {
			excelStream = new FileInputStream(excelFile);
			// High level representation of a workbook.
			// Representación del más alto nivel de la hoja excel.
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
			// We chose the sheet is passed as parameter.
			// Elegimos la hoja que se pasa por parámetro.
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			// An object that allows us to read a row of the excel sheet, and extract from
			// it the cell contents.
			// Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el
			// contenido de las celdas.
			HSSFRow hssfRow;
			// Initialize the object to read the value of the cell
			// Inicializo el objeto que leerá el valor de la celda
			HSSFCell cell;
			// I get the number of rows occupied on the sheet
			// Obtengo el número de filas ocupadas en la hoja
			int rows = hssfSheet.getLastRowNum();
			// I get the number of columns occupied on the sheet
			// Obtengo el número de columnas ocupadas en la hoja
			int cols = 0;
			// A string used to store the reading cell
			// Cadena que usamos para almacenar la lectura de la celda
			String cellValue;
			// For this example we'll loop through the rows getting the data we want
			// Para este ejemplo vamos a recorrer las filas obteniendo los datos que
			// queremos
			for (int r = 0; r < rows; r++) {
				hssfRow = hssfSheet.getRow(r);
				if (hssfRow == null) {
					break;
				} else {
					System.out.print("Row: " + r + " -> ");
					for (int c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
						/*
						 * We have those cell types (tenemos estos tipos de celda): CELL_TYPE_BLANK,
						 * CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN,
						 * CELL_TYPE_ERROR
						 */
						cellValue = hssfRow.getCell(c) == null ? ""
								: (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_STRING)
										? hssfRow.getCell(c).getStringCellValue()
										: (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC)
												? "" + hssfRow.getCell(c).getNumericCellValue()
												: (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN)
														? "" + hssfRow.getCell(c).getBooleanCellValue()
														: (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK)
																? "BLANK"
																: (hssfRow.getCell(c)
																		.getCellType() == Cell.CELL_TYPE_FORMULA)
																				? "FORMULA"
																				: (hssfRow.getCell(c)
																						.getCellType() == Cell.CELL_TYPE_ERROR)
																								? "ERROR"
																								: "";
						System.out.print("[Column " + c + ": " + cellValue + "] ");
					}
					System.out.println();
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
		} catch (IOException ex) {
			System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
		} finally {
			try {
				excelStream.close();
			} catch (IOException ex) {
				System.out.println(
						"Error in file processing after close it (Error al procesar el fichero después de cerrarlo): "
								+ ex);
			}
		}
	}

	/**
	 * Metodo que permite leer un excel y obtener la informacion de forma organizada
	 * en una matriz dinamica
	 * 
	 * @param excelFile datos para leer
	 * @return un {@link ArrayList}
	 */
	public ArrayList<ArrayList<String>> readData(File excelFile) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		data.add(new ArrayList<String>());// Evento
		data.add(new ArrayList<String>());// Nombre
		data.add(new ArrayList<String>());// Apellido
		data.add(new ArrayList<String>());// Cedula
		data.add(new ArrayList<String>());// ¿Como le parecio el espectaculo?
		data.add(new ArrayList<String>());// ¿Que le parecio el lugar?
		data.add(new ArrayList<String>());// ¿Que le parecio el sistema?
		data.add(new ArrayList<String>());// ¿Que le parecio la atencion brindada?
		data.add(new ArrayList<String>());// ¿Que le pareio la relacion calidad-precio?
		try {
			FileInputStream fileInput = new FileInputStream(excelFile);
			XSSFWorkbook book = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			Iterator<Cell> cells;
			Cell cell;
			Row row;
			int rowCounter = 0;
			int cellCounter = 0;
			while (rows.hasNext()) {
				row = rows.next();
				cells = row.cellIterator();
				if (rowCounter != 0) {
					while (cells.hasNext()) {
						cell = cells.next();
						if (cellCounter == 1) {
							data.get(0).add(cell.getStringCellValue());
						}
						if (cellCounter == 2) {
							data.get(1).add(cell.getStringCellValue());
						}
						if (cellCounter == 3) {
							data.get(2).add(cell.getStringCellValue());
						}
						if (cellCounter == 4) {
							String number = cell.getNumericCellValue() + " ";
							data.get(3).add(number);
						}
						if (cellCounter == 5) {
							data.get(4).add(cell.getStringCellValue());
						}
						if (cellCounter == 6) {
							data.get(5).add(cell.getStringCellValue());
						}
						if (cellCounter == 7) {
							data.get(6).add(cell.getStringCellValue());
						}
						if (cellCounter == 8) {
							data.get(7).add(cell.getStringCellValue());
						}
						if (cellCounter == 9) {
							data.get(8).add(cell.getStringCellValue());
						}
						cellCounter++;
					}
				}
				rowCounter++;
				cellCounter = 0;
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Main method for the tests for the methods of the class <strong>Java read
	 * excel</strong> and <strong>Java create excel</strong> with
	 * <a href="https://poi.apache.org/">Apache POI</a>. <br />
	 * Método main para las pruebas para los método de la clase, pruebas de
	 * <strong>Java leer excel</strong> y <strong>Java crear excel</strong> con
	 * <a href="https://poi.apache.org/">Apache POI</a>.
	 * 
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		ApachePoi javaPoiUtils = new ApachePoi();
		URL url = new URL(ApachePoi.class.getResource("EspectaculoRespuestasDataset.xlsx"), "");
		File newFile = new File(url.getFile());
		System.out.println(javaPoiUtils.readData(newFile).toString());
	}

}
