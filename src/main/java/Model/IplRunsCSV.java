package Model;

import com.opencsv.bean.CsvBindByName;

public class IplRunsCSV {

	@CsvBindByName(column = "PLAYER", required = true)
	public String playerName;
	
	@CsvBindByName(column = "Avg", required = true)
	public double battingAvg;


}