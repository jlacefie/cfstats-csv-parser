package com.jl.CfStatsParser;

import org.junit.Assert;
import org.junit.Test;

import com.jl.CfStatsParser.ImportCFStats;
import com.jl.CfStatsParser.ParseCFStats;

import static com.jl.CfStatsParser.utils.FileUtils.writeCFStatsToFile;

public class TestCfStatsParser {

	@Test
	public void testCfsStatsParser(){
		ImportCFStats iCfs = new ImportCFStats();
		ParseCFStats pCfs = new ParseCFStats(iCfs.importFile("/Users/jlacefield/Desktop/DataStax/Clients/Xirrus/cfstats.txt"));
		
		pCfs.parseCFStats();
		Assert.assertNotNull(pCfs.getCsvList());
		writeCFStatsToFile(pCfs.getCsvList(),"/Users/jlacefield/Desktop/test.csv");
	}
	
}
