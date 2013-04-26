package com.runnable;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.Database.Test.DatabaseConnectionTest;
import com.business.report.test.GenerateReportTest;
import com.business.test.*;
import com.business.update.Test.*;
import com.bean.test.*;
import com.runnable.test.SchoolRankingTest;


// add the classes you need to execute to the srs test suite
@RunWith(Suite.class)
@SuiteClasses({SchoolTest.class,
	DatabaseConnectionTest.class,
	ReportModelTest.class,
	IllinoisUpdateStrategyTest.class,
	GenerateReportTest.class,
	SchoolRankingTest.class})
public class SRSTestSuite {

	
}
