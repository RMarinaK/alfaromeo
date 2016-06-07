package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */
import java.io.IOException;
import org.testng.Reporter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

public  class ReportNGAppender extends ConsoleAppender<ILoggingEvent>{
	
	
//	LayoutWrappingEncoder<ILoggingEvent> encoder;
	 
	 @Override
	  public void start() {
		 
		 super.setName("ReportNG");
		 super.setContext(context);
		 super.setEncoder(encoder);
		 super.setOutputStream(getOutputStream());
		 super.start();

	  }

	
	protected void append(ILoggingEvent event) {
			try {
				super.getEncoder().doEncode(event);
				Reporter.log("<p>"+((LayoutWrappingEncoder<ILoggingEvent>) super.getEncoder()).getLayout().doLayout(event)+ "<p/>");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
//	public LayoutWrappingEncoder getEncoder() {
//		    return encoder;
//		  }
//
//	 public void setEncoder(LayoutWrappingEncoder encoder) {
//		    this.encoder = encoder;
//		  }

}
