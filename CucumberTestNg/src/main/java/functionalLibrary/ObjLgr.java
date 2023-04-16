package functionalLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ObjLgr {
	
	private static Logger log = (Logger) LogManager.getLogger(ObjLgr.class.getName());
	ExtentTest test;
	
	public ObjLgr(ExtentTest test) {
		this.test = test;
	}

	public void info(String message) {
		log.info(message);
		test.log(Status.INFO, message);
	}
	
	public void error(String message) {
		log.error(message);
		test.log(Status.FAIL, message);
	}
	
	public void debug(String message) {
		log.debug(message);
		test.log(Status.WARNING, message);
	}
	
}
