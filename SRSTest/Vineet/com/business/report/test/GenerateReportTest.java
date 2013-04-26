package com.business.report.test;

import static org.junit.Assert.*;

import java.io.File;
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
		} catch(Exception e) {
			fail("properties file failed");
		}finally {
			//teardown
			oldfile =new File("./SchoolRankingSystem1.properties");
			newfile =new File("./SchoolRankingSystem.properties");

			if(!oldfile.renameTo(newfile)){
				fail("renaming failed");
			}
		}
	}

	@Test
	public void testGenerateInvalidReportType() {
		boolean check = GenerateReport.generate("test", "1, 2,3","percentPassingOverall,totalEnrollment");

		assertFalse("generate report returns false",check);
	}

	@Test
	public void testGenerateInvalidIdAsCharacter() {

		boolean check =GenerateReport.generate("Pie Chart", "A,2,3","percentPassingOverall");
		assertFalse("error in rep generation",check);
	}

	@Test
	public void testGenerateInvalidIdAsInt() {

		boolean check =GenerateReport.generate("Pie Chart", "-2,2,3","percentPassingOverall");
		assertTrue("no error in rep generation",check);
	}

	@Test
	public void testGenerateInvalidParameter() {

		boolean check =GenerateReport.generate("Pie Chart", "22,2,3","percentPassingOverall2");
		assertFalse("error in rep generation",check);
	}

	@Test
	public void testGenerateInvalidNoofParameterForPieChart() {

		boolean check =GenerateReport.generate("Pie Chart", "22,2,3","percentPassingOverall,graduationRateLowIncome");
		assertFalse(" error in rep generation",check);
	}

	@Test
	public void testGenerateInvalidNoofParameterForScatterPlot() {

		boolean check =GenerateReport.generate("Scatter Plot", "22,2,3","percentPassingOverall,graduationRateLowIncome,graduationRateDisabled");
		assertFalse(" error in rep generation",check);
	}
}
