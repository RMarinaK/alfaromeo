package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.LayoutBase;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.LoggerFactory;


public class ReportNGLayout extends LayoutBase<ILoggingEvent>{



	
	@Override
	public String doLayout(ILoggingEvent event) {
		StringBuffer sbuf = new StringBuffer(128);
		sbuf.append(event.getTimeStamp());
		sbuf.append("");
		String newMsg= StringEscapeUtils.escapeHtml4(event.toString());
		Logger rootLogger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		LoggingEvent encodedEvent = new LoggingEvent(event.getCallerData()[1].toString(), rootLogger, event.getLevel(), newMsg, (Throwable)event.getThrowableProxy(), event.getCallerData());
	//	String baseFmt = super.append(encodedEvent).replace("@{{","<").replace("@}}",">");
		return "<div class=step${"+event.getLevel().toString()+"}>${"+"baseFmt"+"}</div><br/>";
	}

	
//	 public TestNGAppender(){
//	        super()
//	    }
//
//	    public TestNGAppender(Layout layout) {
//	        super()
//	        setLayout(layout)
//	    }


}
