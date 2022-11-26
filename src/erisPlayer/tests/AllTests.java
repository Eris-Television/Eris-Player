package erisPlayer.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	DataStructureTest.class,
	DownloadManagerTest.class,
	ContentManagerTest.class
})
public class AllTests {
}
