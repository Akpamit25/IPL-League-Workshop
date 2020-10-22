package com.Capgemini.IPLleague;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import Model.IplRunsCSV;
import Model.SortedField;
import Exception.IplAnalyserException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IplAnalyser {

	List<IplRunsCSV> iplCSVList = new ArrayList<>();;
	Map<SortedField, Comparator<IplRunsCSV>> sortedMap = new HashMap<>();

	public IplAnalyser() {

		this.sortedMap.put(SortedField.AVERAGE, Comparator.comparing(ipldata -> ipldata.battingAvg));
		 this.sortedMap.put(SortedField.STRIKE_RATE,Comparator.comparing(ipldata -> ipldata.strikeRate));
	        this.sortedMap.put(SortedField.NO_OF_4S_AND_6S,Comparator.comparing(ipldata -> ipldata.noOfFours+ipldata.noOfSixes));

	}

	public int loadIplData(String csvFilePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			CsvToBeanBuilder<IplRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IplRunsCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IplRunsCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IplRunsCSV> censusCSVIterator = csvToBean.iterator();
			int numOfEateries = 0;
			while (censusCSVIterator.hasNext()) {
				numOfEateries++;
				IplRunsCSV runsData = censusCSVIterator.next();
				iplCSVList.add(runsData);
			}
			return numOfEateries;
		} catch (IOException e) {
			throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
		}
	}

	public String getSortedCricketData(SortedField sortedField) {

		if (iplCSVList == null || iplCSVList.size() == 0) {
			throw new IplAnalyserException("No Data", IplAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
		}
		this.sort(iplCSVList, this.sortedMap.get(sortedField));
		Collections.reverse(iplCSVList);
		String sortedStateCensus = new Gson().toJson(iplCSVList);
		return sortedStateCensus;
	}

	private void sort(List<IplRunsCSV> cricketCSVList, Comparator<IplRunsCSV> censusComparator) {
		for (int i = 0; i < cricketCSVList.size() - 1; i++) {
			for (int j = 0; j < cricketCSVList.size() - i - 1; j++) {
				IplRunsCSV run1 = cricketCSVList.get(j);
				IplRunsCSV run2 = cricketCSVList.get(j + 1);
				if (censusComparator.compare(run1, run2) > 0) {
					cricketCSVList.set(j, run2);
					cricketCSVList.set(j + 1, run1);
				}
			}
		}
	}
}