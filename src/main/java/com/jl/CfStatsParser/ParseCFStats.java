package com.jl.CfStatsParser;

import java.util.ArrayList;
import java.util.List;

import static com.jl.CfStatsParser.utils.Constants.AVGLCPERSLICE;
import static com.jl.CfStatsParser.utils.Constants.AVGTPERSLICE;
import static com.jl.CfStatsParser.utils.Constants.BLOOMFFP;
import static com.jl.CfStatsParser.utils.Constants.BLOOMFFR;
import static com.jl.CfStatsParser.utils.Constants.BLOOMFSU;
import static com.jl.CfStatsParser.utils.Constants.COLUMNFAMILY;
import static com.jl.CfStatsParser.utils.Constants.COMPACTEDRMAXS;
import static com.jl.CfStatsParser.utils.Constants.COMPACTEDRMEANS;
import static com.jl.CfStatsParser.utils.Constants.COMPACTEDRMINS;
import static com.jl.CfStatsParser.utils.Constants.KEYSPACE;
import static com.jl.CfStatsParser.utils.Constants.MEMTABLECC;
import static com.jl.CfStatsParser.utils.Constants.MEMTABLEDS;
import static com.jl.CfStatsParser.utils.Constants.MEMTABLESC;
import static com.jl.CfStatsParser.utils.Constants.NUMKEYS;
import static com.jl.CfStatsParser.utils.Constants.PENDINGTASKS;
import static com.jl.CfStatsParser.utils.Constants.READCOUNT;
import static com.jl.CfStatsParser.utils.Constants.READLATENCY;
import static com.jl.CfStatsParser.utils.Constants.SPACEUSEDL;
import static com.jl.CfStatsParser.utils.Constants.SPACEUSEDT;
import static com.jl.CfStatsParser.utils.Constants.SSTABLECOUNT;
import static com.jl.CfStatsParser.utils.Constants.SSTABLECR;
import static com.jl.CfStatsParser.utils.Constants.SSTABLEIEL;
import static com.jl.CfStatsParser.utils.Constants.WRITECOUNT;
import static com.jl.CfStatsParser.utils.Constants.WRITELATENCY;

public class ParseCFStats {
	
	private List<String[]> newCsvList;
	private List<String> fileList;

	public ParseCFStats(){
		newCsvList = new ArrayList<String[]>();
	}
	
	public ParseCFStats(List<String> fileList){
		newCsvList = new ArrayList<String[]>();
		this.fileList = fileList;
	}
	
	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	
	public List<String[]> getCsvList(){
		return newCsvList;
	}
	
	public void parseCFStats(){
		String keySpace = "";
		String columnFamily = "";
		String type = "";
		String line = "";
		String[] rArray = new String[24];
		int i = 0;
		
		createHeaderRow();
		
		for(String s : fileList){
			line = s.trim();
			String key = "";
			String value = "";
			
			if (isNewPrintLn(key, value, columnFamily, keySpace, i, fileList.size())){
				createNewRow(rArray);
				rArray = new String[24];
			}
			
			//Ignore lines that do not have keys
			if (line.indexOf(":") >= 0){
				key = line.substring(0, line.indexOf(":"));
				value = line.substring(line.indexOf(":") + 2, line.length());
				
				if (isNewPrintLn(key, value, columnFamily, keySpace, i, fileList.size())){
					createNewRow(rArray);
					rArray = new String[24];
				}
				
				if (key.equals(KEYSPACE)){
					if (isNewKeySpace(key,value,keySpace)){
						keySpace = value;
						type = KEYSPACE;
						rArray[0] = type;
						rArray[1] = value;
						rArray[7] = "";
						rArray[8] = "";
						rArray[9] = "";
						rArray[10] = "";
						rArray[11] = "";
						rArray[12] = "";
						rArray[13] = "";
						rArray[14] = "";
						rArray[15] = "";
						rArray[16] = "";
						rArray[17] = "";
						rArray[18] = "";
						rArray[19] = "";
						rArray[20] = "";
						rArray[21] = "";
						rArray[22] = "";
						rArray[23] = "";
					}
				}
				
				if (key.equals(COLUMNFAMILY)){
					if (isNewColumnFamily(key,value,columnFamily)){
						columnFamily = value;
						type = COLUMNFAMILY;
						rArray[0] = type;
						rArray[1] = value;
					}
				} 
				
				if (key.equals(READCOUNT)){
					rArray[2] = value;
				} 
				
				if (key.equals(READLATENCY)){
					rArray[3] = cleanMsString(value);
				}
				
				if (key.equals(WRITECOUNT)){
					rArray[4] = value;
				}
				
				if (key.equals(WRITELATENCY)){
					rArray[5] = cleanMsString(value);
				}
				
				if (key.equals(PENDINGTASKS)){
					rArray[6] = value;
				}
				
				if (key.equals(SSTABLECOUNT)){
					rArray[7] = value;
				}
				
				if (key.equals(SSTABLEIEL)){
					rArray[8] = value;
				}
				
				if (key.equals(SPACEUSEDL)){
					rArray[9] = value;
				}
				
				if (key.equals(SPACEUSEDT)){
					rArray[10] = value;
				}
				
				if (key.equals(SSTABLECR)){
					rArray[11] = value;
				}
				
				if (key.equals(NUMKEYS)){
					rArray[12] = value;
				}
				
				if (key.equals(MEMTABLECC)){
					rArray[13] = value;
				}
				
				if (key.equals(MEMTABLEDS)){
					rArray[14] = value;
				}
				
				if (key.equals(MEMTABLESC)){
					rArray[15] = value;
				}
				
				if (key.equals(BLOOMFFP)){
					rArray[16] = value;
				}
				
				if (key.equals(BLOOMFFR)){
					rArray[17] = value;
				}
				
				if (key.equals(BLOOMFSU)){
					rArray[18] = value;
				}
				
				if (key.equals(COMPACTEDRMINS)){
					rArray[19] = value;
				}
				
				if (key.equals(COMPACTEDRMAXS)){
					rArray[20] = value;
				}
				
				if (key.equals(COMPACTEDRMEANS)){
					rArray[21] = value;
				}
				
				if (key.equals(AVGLCPERSLICE)){
					rArray[22] = value;
				}
				
				if (key.equals(AVGTPERSLICE)){
					rArray[23] = value;
				}
			}// end line if
			i++;
		}//end for
		
		
	}
	
	private void createNewRow(String[] rArray){
		newCsvList.add(rArray);
	}
	
	private boolean isNewKeySpace(String key, String value, String keySpace){
		if (key.equals(KEYSPACE) && !value.equals(keySpace)){
			return true;
		}
		return false;
	}
	
	private boolean isNewColumnFamily(String key, String value, String columnFamily){
		if (key.equals(COLUMNFAMILY) && !value.equals(columnFamily)){
			return true;
		}
		return false;
	}
	
	private boolean isNewPrintLn(String key, String value, String columnFamily, String keySpace, int i, int size){
		//i is used to filter out the first row
		if (i > 0 && (key.equals(KEYSPACE) || key.equals(COLUMNFAMILY))){
			if(isNewKeySpace(key,value,keySpace) || isNewColumnFamily(key,value,columnFamily)){
				return true;
			}
			
		} 
		
		//if this is the last line in the file, then we should print
		if (i == (size - 1)){
			return true;
		}
		
		return false;	
	}
	
	private String cleanMsString(String s){
		if (s.contains("NaN")){
			return "0";
		}
		return s.replace(" ms.", "");
	}
	
	private void createHeaderRow(){
		String str = "Row Type#Entity#Read Count#Read Latency(ms)#Write Count#Write Latency(ms)#Pending Tasks#SSTable count#SSTables in each level#Space used (live)#Space used (total)#"
				+ "SSTable Compression Ratio#Number of Keys (estimate)#Memtable Columns Count#Memtable Data Size#Memtable Switch Count#Bloom Filter False Positives#"
				+ "Bloom Filter False Ratio#Bloom Filter Space Used#Compacted row minimum size#Compacted row maximum size#Compacted row mean size#"
				+ "Average live cells per slice (last five minutes)#Average tombstones per slice (last five minutes)";
		newCsvList.add(str.split("#"));
	}
}
