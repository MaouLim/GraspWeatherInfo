package cn.bupt.util;

import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Created by Shun Lee on 2018/01/22
 */

public class ExcelHelper {
	private String path;
	private Workbook workbook;
	private Sheet sheet;
	
	public ExcelHelper(String path) {
		this.path = path;
		workbook = new XSSFWorkbook();
		initializeSheet("Weather");
	}
	
	
	public void writeExcelRow(int count,JSONArray data) throws IOException {
		CellStyle style = workbook.createCellStyle();
		int index = 0;	
		
		Row row;
		Cell cell;
		JSONObject rowObject;
		
		for(int i = 0;i < data.size();++i) {
			//create a new row
			row = sheet.createRow(index);
	
			rowObject = JSONObject.fromObject(data.get(i));
			
			for(int k = 0;k < count;++k) {
				cell = row.createCell(k);
				cell.setCellValue(rowObject.get(RestfulClient.elements[k]).toString());
				cell.setCellStyle(style);
			}
			++index;
		}
		writeExcelFile();
	}
	
	
	private void writeExcelFile() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		workbook.write(outputStream);
		workbook.close();
	}
	
	private void initializeSheet(String sheetName) {
		sheet = (Sheet) workbook.createSheet(sheetName);
	}
}
