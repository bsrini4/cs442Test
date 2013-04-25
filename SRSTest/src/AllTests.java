import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.Database.Test.DatabaseConnectionTest;
import com.business.test.*;
import com.business.update.Test.*;
import com.bean.test.*;


@RunWith(Suite.class)
@SuiteClasses({SchoolTest.class,
	DatabaseConnectionTest.class,
	ReportModelTest.class,
	IllinoisUpdateStrategyTest.class})
public class AllTests {

}
