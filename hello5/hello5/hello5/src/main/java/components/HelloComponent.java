package components;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(createTag=true,tagName="helloComponent",namespace="http://example.com/tags")
public class HelloComponent extends UICommand {
	 @Override
	public String getFamily() {
		// TODO Auto-generated method stub
		return "Greeting";
	}
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		String message=(String) getAttributes().get("message");
		LocalDateTime time=(LocalDateTime) getAttributes().get("time");
		String formattedTime=time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
		ResponseWriter writer=context.getResponseWriter();
		writer.startElement("p", this);
		writer.writeAttribute("type","hidden","clientId");
		writer.write("Messages"+message);
		writer.endElement("p");
		writer.startElement("p", this);
		writer.write("Time"+formattedTime);
		writer.endElement("p");
		
	}
}
