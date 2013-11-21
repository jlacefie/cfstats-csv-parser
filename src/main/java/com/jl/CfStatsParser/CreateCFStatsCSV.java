package com.jl.CfStatsParser;

import com.jl.CfStatsParser.ImportCFStats;
import com.jl.CfStatsParser.ParseCFStats;

import static com.jl.CfStatsParser.utils.FileUtils.writeCFStatsToFile;

public class CreateCFStatsCSV {

	public static void main(String[] args){
		if (args.length < 2 || args[0].equals("") || args[1].equals("")){
			System.out.println("Please pass in a fully qualified path to the csfstats source "
								+ "file as arg[0] and a fully qualified path to the dsired .csv file as arg[1]");
		} else{
			ImportCFStats iCfs = new ImportCFStats();
			ParseCFStats pCfs = new ParseCFStats(iCfs.importFile(args[0]));
			pCfs.parseCFStats();
			writeCFStatsToFile(pCfs.getCsvList(),args[1]);
			System.out.println("Created the new csv - " + args[1]);
		}
	}
}
