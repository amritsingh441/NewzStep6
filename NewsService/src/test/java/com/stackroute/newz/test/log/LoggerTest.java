package  com.stackroute.newz.test.log;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xml.sax.SAXException;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoggerTest {

	@Test
	public void logBackXMLFileCreatedOrNotTestCase() {
		ClassLoader classLoader = getClass().getClassLoader();
		File logFile = new File(classLoader.getResource("logback.xml").getFile());
		assertTrue(logFile.exists(),"You have to create logback.xml file in resources folder");

	}

	@Test
	public void logFileGeneratedOrNotTestCase() throws SAXException, IOException {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		File logFile = ConfigurationWatchListUtil.getConfigurationWatchList(context).getCopyOfFileWatchList().get(0);
		assertTrue(logFile.exists(),"You have to create logback.xml file in resources folder");
		File clientLogFile;
		FileAppender<?> fileAppender = null;

		for (Logger logger : context.getLoggerList()) {
			for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
				Object appender = index.next();
				if (appender instanceof FileAppender) {
					fileAppender = (FileAppender<?>) appender;
				}
			}
		}
		assertNotNull(fileAppender,"File appender is not proper.  please check logback.xml file");
		clientLogFile = new File(fileAppender.getFile());
		assertNotNull(clientLogFile,"Log file not generated.  please check logback.xml file");
	}

	@Test
	public void logggedTheMessagesInFileOrNotTestCase() throws SAXException, IOException {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		File logFile = ConfigurationWatchListUtil.getConfigurationWatchList(context).getCopyOfFileWatchList().get(0);
		assertTrue(logFile.exists(),"You have to create logback.xml file in resources folder");
		File clientLogFile;
		FileAppender<?> fileAppender = null;

		for (Logger logger : context.getLoggerList()) {
			for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
				Object appender = index.next();
				if (appender instanceof FileAppender) {
					fileAppender = (FileAppender<?>) appender;
				}
			}
		}
		assertNotNull(fileAppender,"File appender is not proper.  please check logback.xml file");
		clientLogFile = new File(fileAppender.getFile());
		assertNotNull(clientLogFile,"Log file not generated.  please check logback.xml file");

		assertNotEquals(clientLogFile.length(),0,"Log file is created but it is empty");
	}
}