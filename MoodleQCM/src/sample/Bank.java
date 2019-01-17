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
import main.java.sample.QuestionStorage;




public class Bank extends QuestionStorage{
    // private static int name_default_nomber = 0;
    private static int number_default = 0;



    public Bank(){
        super();
        path = "./Bank_default"+number_default+".xml";
        // changeName("Qcm defaut" + name_default_nomber);
        // name_default_nomber++;
    }

    public Bank(String path){
        super(path);
    }

    public void save(){
        super.save(true);
    }

    public void Export(String xml_path, String name_for_xml, boolean isBank){
        super.Export(xml_path, name_for_xml, true);
    }



}
