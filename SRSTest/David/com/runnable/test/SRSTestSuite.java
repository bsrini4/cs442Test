package com.runnable.test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.Database.Test.DatabaseConnectionTest;
import com.business.test.*;
import com.business.update.Test.*;
import com.bean.test.*;


// add the classes you need to execute to the srs test suite
@RunWith(Suite.class)
@SuiteClasses({SchoolTest.class,
	DatabaseConnectionTest.class,
	ReportModelTest.class,
	IllinoisUpdateStrategyTest.class})
public class SRSTestSuite {

	
}
