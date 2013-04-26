package com.bean.test;

import static org.junit.Assert.*;

import com.bean.School;
import java.util.HashMap;
import org.junit.Test;

public class SchoolTest {

	@Test
	public void testgetMap() {
		
		
		HashMap<String,String> testMap=School.getMap();
		System.out.println("output"+testMap.toString());
		assertNotNull("Should have returned an hashmap object",testMap);
		
		// validating the key-value pairs are populated correctly in the hashmap
		
		assertEquals("Assigned key values should match","Total Enrollment",testMap.get("totalEnrollment"));
		assertEquals("Assigned key values should match"," Average Teacher Salary",testMap.get("averageTeacherSalary"));
		assertEquals("Assigned key values should match","Average ACT",testMap.get("averageAct"));
		assertEquals("Assigned key values should match","Overall Passing Percentage",testMap.get("percentPassingOverall"));
		assertEquals("Assigned key values should match","ISAT Passing Percentage",testMap.get("percentPassingIsat"));
		assertEquals("Assigned key values should match","PSAE Passing Percentage",testMap.get("percentPassingPsae"));
		assertEquals("Assigned key values should match","IAA Passing Percentage",testMap.get("percentPassingIaa"));
		assertEquals("Assigned key values should match","Overall Graduation Rate",testMap.get("graduationRateOverall"));
		assertEquals("Assigned key values should match","Graduation Rate: Male",testMap.get("graduationRateMale"));
		assertEquals("Assigned key values should match","Graduation Rate: Female",testMap.get("graduationRateFemale"));
		
		
		assertEquals("Assigned key values should match","Graduation Rate: White",testMap.get("graduationRateWhite"));
		assertEquals("Assigned key values should match","Graduation Rate: Black",testMap.get("graduationRateBlack"));
		assertEquals("Assigned key values should match","Graduation Rate: Hispanic",testMap.get("graduationRateHispanic"));
		assertEquals("Assigned key values should match","Graduation Rate: Asian",testMap.get("graduationRateAsian"));
		assertEquals("Assigned key values should match","Graduation Rate: Hawaiian",testMap.get("graduationRateHawaiian"));
		
		assertEquals("Assigned key values should match","Graduation Rate: Indian",testMap.get("graduationRateIndian"));
		assertEquals("Assigned key values should match","Graduation Rate: Multi Racial",testMap.get("graduationRateMultiracial"));
				
		assertEquals("Assigned key values should match","Graduation Rate: LEP",testMap.get("graduationRateLep"));
		assertEquals("Assigned key values should match","Graduation Rate: Migrant",testMap.get("graduationRateMigrant"));
		assertEquals("Assigned key values should match","Graduation Rate: Disabled",testMap.get("graduationRateDisabled"));
		assertEquals("Assigned key values should match","Graduation Rate: Low Income",testMap.get("graduationRateLowIncome"));
		assertEquals("Assigned key values should match","Percent: White",testMap.get("percentWhite"));
		assertEquals("Assigned key values should match","Percent: Black",testMap.get("percentBlack"));
		assertEquals("Assigned key values should match","Percent: Hispanic",testMap.get("percentHispanic"));
		assertEquals("Assigned key values should match","Percent: Asian",testMap.get("percentAsian"));
		assertEquals("Assigned key values should match","Percent: Hawaiian",testMap.get("percentHawaiian"));
		
		
		assertEquals("Assigned key values should match","Percent: Indian",testMap.get("percentIndian"));
		assertEquals("Assigned key values should match","Percent: Multi Racial",testMap.get("percentMultiracial"));
		assertEquals("Assigned key values should match","Percent: Low Income",testMap.get("percentLowIncome"));
		assertEquals("Assigned key values should match","Percent: LEP",testMap.get("percentLep"));
		assertEquals("Assigned key values should match","Percent:  IEP",testMap.get("percentIep"));
		assertEquals("Assigned key values should match","Percent: HS Dropouts",testMap.get("percentHsDropout"));
		assertEquals("Assigned key values should match","Percent: Chronic Truancy",testMap.get("percentChronicTruancy"));
		assertEquals("Assigned key values should match","Percent: Low Mobility",testMap.get("percentLowMobility"));
		assertEquals("Assigned key values should match","Attendance Rate",testMap.get("attendanceRate"));

		
	}

}
