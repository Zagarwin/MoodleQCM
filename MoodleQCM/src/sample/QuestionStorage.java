import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;



class QuestionStorage{
    private Set<Integer> listeIDs;
    private String bank_Name;

    public void QuestionStorage(){
        listeIDs = new HashSet<Integer>();
        bank_Name = "Name defaut";
    }

    public void QuestionStorage(String strXML){
        listeIDs = new HashSet<Integer>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(strXML));
            Element racine = document.getDocumentElement();
            Element name = (Element)racine.getElementsByTagName("name");

            bank_Name = name.getTextContent();
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







    public void saveBank(String strXML){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(strXML));

            final Element racine = document.createElement("Bank");
            document.appendChild(racine);
            final Comment commentaire = document.createComment("Question Bank");
            racine.appendChild(commentaire);
            final Element name = document.createElement("name");
            final Element question_id_list = document.createElement("question_id_list");
            racine.appendChild(name);
            racine.appendChild(question_id_list);
            name.appendChild(document.createTextNode(bank_Name));

            for (int i:listeIDs) {
                final Element question_id = document.createElement("question_id");
                question_id_list.appendChild(question_id);
                question_id.appendChild(document.createTextNode(i+""));
    		}
            Calendar c = Calendar.getInstance();
            final Element date = document.createElement("date");
            racine.appendChild(date);
            date.appendChild(document.createTextNode(c.get(Calendar.DATE)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)));

        }
        catch (final ParserConfigurationException e) {
	        e.printStackTrace();
    	}
        catch(final SAXException e) {
            e.printStackTrace();
        }
        catch(final IOException e) {
            e.printStackTrace();
        }

    }



    public void addQuestion(int id_question){
        listeIDs.add(id_question);
    }


    public void changeName(String new_name){
        bank_Name = new_name;
    }



}
