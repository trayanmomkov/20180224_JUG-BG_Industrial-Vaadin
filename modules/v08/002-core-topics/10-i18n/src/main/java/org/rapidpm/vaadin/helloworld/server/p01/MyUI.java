package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import org.rapidpm.vaadin.helloworld.server.CoreUI;
import org.rapidpm.vaadin.helloworld.server.PropertyService;
import org.rapidpm.vaadin.helloworld.server.PropertyServiceInMemory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.function.Supplier;

/**
 *
 */
public class MyUI extends CoreUI {

  @Override
  public Supplier<Component> componentSupplier() {
    return () -> {
      final PropertyService propertyService = new PropertyServiceInMemory();

      ((PropertyServiceInMemory) propertyService).postConstruct();

      final Button btnOK = new Button();
      btnOK.setCaption(propertyService.resolve("generic.ok"));
      return btnOK;
    };
  }


  @WebServlet("/*")
  @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
  public static class CoreServlet extends VaadinServlet {
  }

  @Override
  public Class<? extends VaadinServlet> servletClass() {
    return CoreServlet.class;
  }

  public static void main(String[] args) throws ServletException {
    new MyUI().startup();
  }
}
