package com.business.report.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
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
			
		}finally {
			oldfile =new File("./SchoolRankingSystem1.properties");
			newfile =new File("./SchoolRankingSystem.properties");
	 
			if(!oldfile.renameTo(newfile)){
				fail("renaming failed");
			}
		}
	}

	@Test
	public void testGenerate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetExecutionId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetEngineConfiguration() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetService() {
		fail("Not yet implemented"); // TODO
	}

}
