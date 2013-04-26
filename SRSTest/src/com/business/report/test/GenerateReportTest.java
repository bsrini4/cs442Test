package com.business.report.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.business.report.GenerateReport;

public class GenerateReportTest {

	@Test
	public void testSetReportServerVariables() throws IOException {

		//setup
		File oldfile =new File("./SchoolRankingSystem.properties");
		File newfile =new File("./SchoolRankingSystem1.properties");


		if(!oldfile.renameTo(newfile)){
			fail("renaming failed");
		}

		//test
		try{
			GenerateReport.setReportServerVariables();
		} 
		finally {
			//teardown
			oldfile =new File("./SchoolRankingSystem1.properties");
			newfile =new File("./SchoolRankingSystem.properties");

			if(!oldfile.renameTo(newfile)){
				fail("renaming failed");
			}
		}
	}

	@Test
	public void testGenerateReportWithInvalidReportType() {
		boolean check = GenerateReport.generate("test", "1, 2,3","percentPassingOverall,totalEnrollment");

		assertFalse("generate report returns false",check);
	}
	
	@Test
	public void testGenerateReportWithInvalidReportType2() {
		boolean check = GenerateReport.generate("", "1, 2,3","percentPassingOverall,totalEnrollment");

		assertFalse("generate report returns false",check);
	}

	@Test
	public void testGenerateReportWithInvalidIdAsCharacter() {

		boolean check =GenerateReport.generate("Pie Chart", "A,2,3","percentPassingOverall");
		assertFalse("error in rep generation",check);
	}

	@Test
	public void testGenerateReportWithInvalidIdAsNegative() {

		boolean check = GenerateReport.generate("Pie Chart", "-2,2,3","percentPassingOverall");
		assertTrue("no error in rep generation",check);
	}
	@Test
	public void testGenerateReportWithInvalidIdAsBlank() {

		boolean check = GenerateReport.generate("Pie Chart", "","percentPassingOverall");
		assertFalse("no error in rep generation",check);
	}

	@Test
	public void testGenerateReportWithInvalidParameter() {

		boolean check = GenerateReport.generate("Bar Graph", "22,2,3","percentPassingOverall2");
		assertFalse("error in rep generation",check);
	}
	
	@Test
	public void testGenerateReportWithInvalidParameterAsBlank() {

		boolean check = GenerateReport.generate("Pie Chart", "22,2,3","");
		assertFalse("error in rep generation",check);
	}

	@Test
	public void testGenerateReportWithInvalidMoreNoofParameterForPieChart() {

		boolean check = GenerateReport.generate("Pie Chart", "22,2,3","percentPassingOverall,graduationRateLowIncome");
		assertFalse(" error in rep generation",check);
	}
	@Test
	public void testGenerateReportWithInvalidLessNoofParameterForPieChart() {

		boolean check = GenerateReport.generate("Pie Chart", "22,2,3","");
		assertFalse(" error in rep generation",check);
	}

	@Test
	public void testGenerateReportWithInvalidMoreNoofParameterForScatterPlot() {

		boolean check = GenerateReport.generate("Scatter Plot", "22,2,3","percentPassingOverall,graduationRateLowIncome,graduationRateDisabled");
		assertFalse(" error in rep generation",check);
	}
	
	@Test
	public void testGenerateReportWithInvalidLessNoOfParameterForScatterPlot() {

		try {
		 GenerateReport.generate("Scatter Plot", "22,2,3","percentPassingOverall");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidBrGraph() {
		boolean check = GenerateReport.generate("Bar Graph", "22,2,3","percentPassingOverall,graduationRateLowIncome,graduationRateDisabled");
		assertTrue(" error in rep generation",check);
	
	}
	
	@Test
	public void testValidPieChart() {
		boolean check = GenerateReport.generate("Pie Chart", "22,2,3","percentPassingOverall");
		assertTrue(" error in rep generation",check);
	}
	
	@Test 
	public void testValidScatterPlot() {
		boolean check = GenerateReport.generate("Scatter Plot", "22,2,3","percentPassingOverall,graduationRateDisabled");
		assertTrue(" error in rep generation",check);
	}
}
