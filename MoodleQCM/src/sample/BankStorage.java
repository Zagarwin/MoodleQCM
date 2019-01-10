package sample;

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


public class BankStorage{
    private String name;
    private Set<QuestionStorage> list_bank;

    public BankStorage(){
        list_bank = new HashSet<QuestionStorage>();
        name = "BankStorage Defaut";
    }

    public BankStorage(String xml_path){
        list_bank = new HashSet<QuestionStorage>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(xml_path));
            Element racine = document.getDocumentElement();
            Element name_0 = (Element)racine.getElementsByTagName("name");

            name = name_0.getTextContent();
            final NodeList list_bank_0 = racine.getElementsByTagName("bank_list");
            final int nb_banks_Elements = list_bank_0.getLength();
            for(int i = 0; i<nb_banks_Elements; i++) {
                final Element name_qs = (Element) list_bank_0.item(i);
                QuestionStorage qs = new QuestionStorage(name_qs.getTextContent(), true);
                list_bank.add(qs);
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



    public void save(String xml_path){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(xml_path));

            final Element racine = document.createElement("BS");
            document.appendChild(racine);
            final Comment commentaire = document.createComment("List Bank");
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement("name");
            final Element bank_name_list = document.createElement("bank_list");
            racine.appendChild(name_0);
            racine.appendChild(bank_name_list);
            name_0.appendChild(document.createTextNode(name));

            for (QuestionStorage qs:list_bank) {
                final Element bank = document.createElement("bank");
                bank_name_list.appendChild(bank);
                bank.appendChild(document.createTextNode(qs.getName()));
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

    public void changeName(String name_0){
        name = name_0;
    }


    public void addBank(QuestionStorage qs_0){
        list_bank.add(qs_0);
    }


    public void deleteBank(QuestionStorage qs_0){
        list_bank.remove(qs_0);
    }



}
