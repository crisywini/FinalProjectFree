package util;

import java.io.*;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePoi {

	/**
	 * Metodo que permite leer un excel y obtener la informacion de forma organizada
	 * en una matriz dinamica
	 * 
	 * @param excelFile datos para leer
	 * @return un {@link ArrayList}
	 */
	public static ArrayList<ArrayList<String>> readData(File excelFile) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		XSSFWorkbook book;
		try {
			book = new XSSFWorkbook(excelFile);
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
					ArrayList<String> info = new ArrayList<String>();
					while (cells.hasNext()) {
						cell = cells.next();
						if (cellCounter == 4) {
							String number = cell.getNumericCellValue() + " ";
							info.add(number);
						} else {
							if (cellCounter != 0) {
								info.add(cell.getStringCellValue());
							}
						}
						cellCounter++;
					}
					data.add(info);
				}
				rowCounter++;
				cellCounter = 0;
			}
			book.close();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
