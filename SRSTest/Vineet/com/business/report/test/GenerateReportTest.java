package com.business.report.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.business.report.GenerateReport;

public class GenerateReportTest {

	@Test
	public void testSetReportServerVariables() throws IOException {
		
		File oldfile =new File("./SchoolRankingSystem.properties");
		File newfile =new File("./SchoolRankingSystem1.properties");
 
	
		if(!oldfile.renameTo(newfile)){
			fail("renaming failed");
		}
		try{
		GenerateReport.setReportServerVariables();
		} catch(Exception e) {
			fail("properties file failed");
		}finally {
			oldfile =new File("./SchoolRankingSystem1.properties");
			newfile =new File("./SchoolRankingSystem.properties");
	 
			if(!oldfile.renameTo(newfile)){
				fail("renaming failed");
			}
		}
	}

	@Test
	public void testGenerateReportType() {
		boolean check = GenerateReport.generate("test", "1, 2,3","percentPassingOverall,totalEnrollment");
		
		assertFalse("generate report returns false",check);
	}

}
