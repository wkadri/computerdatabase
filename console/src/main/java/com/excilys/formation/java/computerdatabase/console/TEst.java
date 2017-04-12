package com.excilys.formation.java.computerdatabase.console;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TEst {
  public static void main(String[] args) throws Exception {

  URL url = new URL("http://localhost:8080/webservice");

        //1st argument service URI, refer to wsdl document above
  //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.mkyong.com/", "HelloWorldImplService");

        Service service = Service.create(url, qname);

    }
}
