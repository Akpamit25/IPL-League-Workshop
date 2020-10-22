package com.Capgemini.IPLleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import Model.SortedField;
import org.junit.Test;
import Model.IplRunsCSV;
import Exception.IplAnalyserException;

public class IplAnalyserTest {
	private static IplAnalyser iplAnalyser;
	private static final String IPL_MOST_RUNS_FILE_PATH = "D:\\Capg_Bridgelabz\\IPLleague\\src\\main\\java\\resources\\MostRuns.csv";

	@BeforeClass
	public static void setUp() throws Exception {
		iplAnalyser = new IplAnalyser();
	}

	@Test
	public void givenIPLMostRunData_WhenSorted_ShouldReturnTopBattingAverage() throws IplAnalyserException {
		try {
			iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
			String sortedCricketData = iplAnalyser.getSortedCricketData(SortedField.AVERAGE);
			IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, IplRunsCSV[].class);
			Assert.assertEquals(83.2, mostRunCsv[0].battingAvg, 0.0);
		} catch (IplAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIPLMostRunData_WhenSorted_ShouldReturnTopStrikeRate() throws IplAnalyserException {
		try {
			iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
			String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.STRIKE_RATE);
			IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplRunsCSV[].class);
			Assert.assertEquals(333.33, mostRunCsv[0].strikeRate, 0.0);
		} catch (IplAnalyserException e) {
			e.printStackTrace();

		}
	}

	@Test
	public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sHittingCount() {
		iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
		String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.NO_OF_4S_AND_6S);
		IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplRunsCSV[].class);
		Assert.assertEquals(83, mostRunCsv[0].noOfFours + mostRunCsv[0].noOfSixes);

	}
	
	@Test
    public void givenIPLMostRunData_WhenSorted_ShouldReturnBest4sAnd6sStrikingRate() {
        iplAnalyser.loadIplData(IPL_MOST_RUNS_FILE_PATH);
        String sortedStrikeRateData = iplAnalyser.getSortedCricketData(SortedField.NO_OF_4S_AND_6S);
        IplRunsCSV[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, IplRunsCSV[].class);
        Assert.assertEquals(204.81,mostRunCsv[0].strikeRate,0.0);

    }
}
