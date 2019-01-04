import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


class QuestionStorage{
    private Set<Integer> listeIDs;

    public QuestionStorage(String strXML){
        listeIDs = new HashSet<Integer>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader sr = new StringReader(strXML);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            Element rootElement = doc.getDocumentElement();
            NodeList phones = rootElement.getElementsByTagName("type");
            for (int i = 0; i < phones.getLength(); i++) {
                Node type = phones.item(i);
                String id_string = ((Element)type).getAttribute("id");
                int id_int = (int)id_string;
                listeIDs.add(id_int);
                NodeList properties = type.getChildNodes();
                // for (int j = 0; j < properties.getLength(); j++) {
                //     Node property = properties.item(j);
                //     String nodeName = property.getNodeName();
                //     if (nodeName.equals("price")) {
                //         String price=property.getFirstChild().getNodeValue();
                //         System.out.println("price="+price);
                //     } else if (nodeName.equals("operator")) {
                //         String operator=property.getFirstChild().getNodeValue();
                //         System.out.println("operator="+operator);
                //     }
                // }
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void quizz(){
        
    }

    public void bank(){
        
    }

}

