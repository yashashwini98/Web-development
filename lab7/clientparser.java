import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
  
public class Clientparser {
  
     public static void main(String[] args) throws ParserConfigurationException,
          SAXException, IOException {
  
        //Get Document Builder
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
  
          // Load the input XML document, parse it and return an instance of the
          // Document class.
          Document document = builder.parse(new File("client.xml"));
  
          List<Client> clients = new ArrayList<Client>();
          NodeList nodeList = document.getDocumentElement().getChildNodes();
          for (int i = 0; i < nodeList.getLength(); i++) {
               Node node = nodeList.item(i);
  
               if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
  
  
                    // Get the value of the ID attribute.
                    String ID = node.getAttributes().getNamedItem("ID").getNodeValue();
  
                    // Get the value of all sub-elements.
                    String firstname = elem.getElementsByTagName("Firstname")
                                        .item(0).getChildNodes().item(0).getNodeValue();
  
                    String lastname = elem.getElementsByTagName("Lastname").item(0)
                                        .getChildNodes().item(0).getNodeValue();
  
                    Integer age = Integer.parseInt(elem.getElementsByTagName("Age")
                                        .item(0).getChildNodes().item(0).getNodeValue());
  
                    Double tradingAmt = Double.parseDouble(elem.getElementsByTagName("tradingAmt")
                                        .item(0).getChildNodes().item(0).getNodeValue());
  
                    clients.add(new Client(ID, firstname, lastname, age, tradingAmt));
  
               }
          }
  
          // Print all employees.
          for (Client cl: clients)
               System.out.println(cl.toString());
     }
}