/**
 * 
 */
package com.zz.learning.jxl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableWorkbook;

/**
 * @author Administrator
 *
 */
public class JxlTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String source = "D:/test22/sanjiu3.xls";
		//String target = "C:/Users/Administrator/Desktop/zhaoyan/target.xls";
		JxlTest t = new JxlTest();
		List<Bean> result = new ArrayList<Bean>();
		t.xls2Bean(source, result);
		
	/*	t.connection(result);
		t.writeSheet(target, result);*/
		
		
		
	}

	
	
	public void xls2Bean(String file, List<Bean> result) {

		try {
			FileInputStream fis = new FileInputStream(file);
			// StringBuilder sb = new StringBuilder();
			jxl.Workbook rwb = Workbook.getWorkbook(fis);
			Sheet[] sheet = rwb.getSheets();
			/*
			 * for (int i = 0; i < sheet.length; i++) { Sheet rs =
			 * rwb.getSheet(i); for (int j = 0; j < rs.getRows(); j++) { Cell[]
			 * cells = rs.getRow(j); for (int k = 0; k < cells.length; k++)
			 * sb.append(cells[k].getContents()); } }
			 */
			Sheet sheet0 = rwb.getSheet(0);
			for (int rownum = 0; rownum < sheet0.getRows(); rownum++) {
				Cell[] cells = sheet0.getRow(rownum);
				Bean b = new Bean();
				b.setC1(cells[0].getContents());
				b.setC2(cells[1].getContents());
				b.setC3(cells[2].getContents());
				/*b.setC4(cells[3].getContents());
				b.setC5(cells[4].getContents());*/
				result.add(b);
			}
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public void writeSheet(String filePath, List<Bean> result)
			throws Exception {
		// 创建Excel工作薄
		WritableWorkbook wwb;
		// 新建立一个jxl文件,即在d盘下生成testJXL.xls
		OutputStream os = new FileOutputStream(filePath);
		wwb = Workbook.createWorkbook(os);

		jxl.write.WritableSheet sheet = wwb.createSheet("sheet1", 0);
		int i = 0;
		for (Bean b : result) {
			Label label = new Label(0, i, b.getC1());
			sheet.addCell(label); // 0

			label = new Label(1, i, b.getC2());
			sheet.addCell(label);// 1

			label = new Label(2, i, b.getC3());
			sheet.addCell(label);// 2

			label = new Label(3, i, b.getC4());
			sheet.addCell(label);// 3

			label = new Label(4, i, b.getC5());
			sheet.addCell(label);// 4

			label = new Label(5, i, b.getC6());
			sheet.addCell(label);// 5

			label = new Label(6, i, b.getC7());
			sheet.addCell(label);// 6
			i++;

		}

		// 写入数据
		wwb.write();
		// 关闭文件
		wwb.close();
	}
}
