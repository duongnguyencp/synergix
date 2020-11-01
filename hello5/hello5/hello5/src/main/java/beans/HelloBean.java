package beans;

import java.time.LocalDateTime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class HelloBean {

  public String getMessage() {
      return "Hi there!";
  }

  public LocalDateTime getTime() {
      return LocalDateTime.now();
  }
}
