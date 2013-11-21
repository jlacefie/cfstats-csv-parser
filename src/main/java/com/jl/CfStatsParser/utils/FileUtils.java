package com.jl.CfStatsParser.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class FileUtils {

	public static List<String> readFileIntoList(String fileNameWithPath) {

		List<String> fileList = new ArrayList<String>();
		BufferedReader br = null;
		File file = new File(fileNameWithPath);

		try {
			String currentLine;
			br = new BufferedReader(new FileReader(file));

			while ((currentLine = br.readLine()) != null) {
				fileList.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}			
		}
		return fileList;
	}
	
	public static void writeCFStatsToFile(List<String[]> csvList, String csvFile){
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(csvFile), ',');
			writer.writeAll(csvList);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
