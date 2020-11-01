package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ValueChangeEvent;
@ManagedBean(name="myBean",eager=true)
public class MyBean {

    // Init ----------------------------------------------------------------------------------------

    private HtmlInputText inputComponent;
    private String inputValue;
    private HtmlOutputText outputComponent;
    private String outputValue;
    private HtmlMessages htmlMessageComponent;

    // Constructors -------------------------------------------------------------------------------

    public MyBean() {
        log("constructed");
    }

    // Actions ------------------------------------------------------------------------------------

    public void inputChanged(ValueChangeEvent event) {
        log(event.getOldValue() + " to " + event.getNewValue());
    }

    public void action() {
        outputValue = inputValue;
        log("succes");
    }

    // Getters/setters ----------------------------------------------------------------------------

    public HtmlMessages getMessageComponent() {
        log(htmlMessageComponent);
        return htmlMessageComponent;
    }
    public void setMessageComponent(HtmlMessages htmlMessage) {
        log(htmlMessageComponent);
        this.htmlMessageComponent=htmlMessage;
    }
    public HtmlInputText getInputComponent() {
        log(inputComponent);
        return inputComponent;
    }
    public void setInputComponent(HtmlInputText inputComponent) {
        log(inputComponent);
        this.inputComponent = inputComponent;
    }

    public String getInputValue() {
        log(inputValue);
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        log(inputValue);
        this.inputValue = inputValue;
    }

    public HtmlOutputText getOutputComponent() {
        log(outputComponent);
        return outputComponent;
    }

    public void setOutputComponent(HtmlOutputText outputComponent) {
        log(outputComponent);
        this.outputComponent = outputComponent;
    }

    public String getOutputValue() {
        log(outputValue);
        return outputValue;
    }

    // Helpers ------------------------------------------------------------------------------------

    private void log(Object object) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("MyBean " + methodName + ": " + object);
    }
}
