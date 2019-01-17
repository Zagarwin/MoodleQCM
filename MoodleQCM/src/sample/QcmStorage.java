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
import sample.Qcm;
import java.util.Calendar;



public class QcmStorage{
    private String name, path;
    private Set<Qcm> list_qcm;

    public QcmStorage(){
        list_qcm = new HashSet<Qcm>();
        name = "QcmStorage Defaut";
    }

    public QcmStorage(String xml_path){
        path = xml_path;
        list_qcm = new HashSet<Qcm>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(xml_path));
            Element racine = document.getDocumentElement();
            Element name_0 = (Element)racine.getElementsByTagName("name");

            name = name_0.getTextContent();
            final NodeList list_qcm_0 = racine.getElementsByTagName("qcm_list");
            final int nb_qcms_Elements = list_qcm_0.getLength();
            for(int i = 0; i<nb_qcms_Elements; i++) {
                final Element qcm = (Element) list_qcm_0.item(i);
                final Element path_qcm = (Element) qcm.getElementsByTagName("qcm_path");
                Qcm q = new Qcm(path_qcm.getTextContent());
                list_qcm.add(q);
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



    public void save(){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(path));

            final Element racine = document.createElement("QS");
            document.appendChild(racine);
            final Comment commentaire = document.createComment("List Qcm");
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement("name");
            final Element qcm_name_list = document.createElement("qcm_list");
            racine.appendChild(name_0);
            racine.appendChild(qcm_name_list);
            name_0.appendChild(document.createTextNode(name));

            for (Qcm qc:list_qcm) {
                final Element qcm = document.createElement("qcm");
                qcm_name_list.appendChild(qcm);
                final Element qcm_name = document.createElement("qcm_name");
                final Element qcm_path = document.createElement("qcm_path");
                qcm.appendChild(qcm_name);
                qcm.appendChild(qcm_path);
                qcm_name.appendChild(document.createTextNode(qc.getName()));
                qcm_path.appendChild(document.createTextNode(qc.getPath()));
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


    public void addQcm(Qcm qc_0){
        list_qcm.add(qc_0);
    }


    public void deleteQcm(Qcm qc_0){
        list_qcm.remove(qc_0);
    }



}
