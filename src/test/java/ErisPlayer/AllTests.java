package ErisPlayer;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	DataStructureTest.class,
	DownloadManagerTest.class,
	ContentManagerTest.class,
	SocketServerTests.class,
	SchedulerTests.class
})
public class AllTests {
}
