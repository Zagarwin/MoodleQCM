package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class SuperBank {
    private File dirBank;
    private ArrayList<String[]> questionList;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;

    public SuperBank() throws ParserConfigurationException {
        dirBank =new File("bank");
        questionList = new ArrayList();
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

    }

    public File getDirBank() {
        return dirBank;
    }

    public void setDirBank(File dirBank) {
        this.dirBank = dirBank;
    }

    public boolean havefiles() {
        if (dirBank.listFiles().length==0){
            return false;
        }
        return true;
    }
    public boolean havefiles(File dir){
        if (dir.listFiles().length==0){
            return false;
        }
        return true;
    }

    public ArrayList xmlInfile(File dirBank) throws IOException, SAXException {
        if (!havefiles(dirBank)) return null;
        for (File dir : dirBank.listFiles()){
            if (dir.isDirectory()) xmlInfile(dir);
            if (isXmlFile(dir)){



            }
        }
        return null;
    }

    public boolean isXmlFile(File file) {
        if (file.isFile() && file.getName().contains(".xml")) return true;
        return false;
    }

    public String[] extractQuestion(File file) throws IOException, SAXException {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] strings=new String[2];
        Element nodeId_Header;
        document = builder.parse(file);
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName()=="id_header") {
                nodeId_Header = (Element) nodeList.item(i);
                strings[0]= nodeId_Header.getElementsByTagName("id").item(0).getTextContent();
                strings[1]=file.getCanonicalPath();
            }

        }
        return strings;
    }



}