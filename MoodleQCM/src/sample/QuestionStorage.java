import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.HashSet;
import java.util.Set;



class QuestionStorage{
    private Set<Integer> listeIDs;

    public QuestionStorage(String strXML){
        listeIDs = new HashSet<Integer>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(strXML));
            Element racine = document.getDocumentElement();
            final NodeList list_Id = racine.getElementsByTagName("question_id_list");
            final int nbIDsElements = list_Id.getLength();
            for(int i = 0; i<nbIDsElements; i++) {
                final Element Id = (Element) list_Id.item(i);
                listeIDs.add(Integer.parseInt(Id.getTextContent()));
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

}
