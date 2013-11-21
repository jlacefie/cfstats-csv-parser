package com.jl.CfStatsParser;

import static com.jl.CfStatsParser.utils.FileUtils.readFileIntoList;

import java.util.List;

//import org.apache.log4j.Logger;


public class ImportCFStats {

//	private static Logger logger=Logger.getLogger("ImportOrder");
	
	public List<String> importFile(String fileNameWithPath){
		return readFileIntoList(fileNameWithPath);
	}	
}