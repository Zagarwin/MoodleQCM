import javafx.scene.control.TreeItem;
import main.java.sample.SuperBank;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SuperBankTest {
    SuperBank superBank;
    @Before
    public void setUp() throws ParserConfigurationException {
        superBank=new SuperBank();
    }

    @Test
    public void testBankAsDirectory(){
        assertEquals(true,superBank.getDirBank().isDirectory());
    }

    @Test
    public void testHaveDirectory(){
        assertEquals(false,superBank.havefiles());
    }
    @Test
    public void testIsXMLFile(){
        File fileXML = new File("bank/fich1.xml");
        assertEquals(true,superBank.isXmlFile(fileXML));
    }
    @Test
    public void testExtractQuestion() throws IOException, SAXException {
        String[] strings = new String[2];
        strings[0] = "655656";
        strings[1] = "C:\\Users\\Louis Berthier\\Desktop\\Informatique\\L3\\MoodleQCM\\MoodleQCM\\bank\\fich1.xml";
        File file = new File("bank/fich1.xml");
        assertEquals(strings[0],superBank.extractQuestion(file)[0]);
        assertEquals(strings[1],superBank.extractQuestion(file)[1]);

    }
    @Test
    public void testExtractId_Path() throws IOException, SAXException {
        String string = "655656";
        ArrayList arrayList = superBank.extractId_Path();
        String[] strings = (String[]) arrayList.get(0);
        assertEquals(string, strings[0]);
    }
    @Test
    public void testExtractID_PathSize() throws IOException,SAXException{
        assertEquals(3,superBank.extractId_Path().size());
    }
    @Test
    public void testFindId() throws IOException, SAXException {
        superBank.extractId_Path();
        assertEquals("C:\\Users\\Louis Berthier\\Desktop\\Informatique\\L3\\MoodleQCM\\MoodleQCM\\bank\\Maths\\question1.xml",superBank.find("655644"));

    }
    @Test
    public void testGenerateTree() throws IOException, SAXException {
        TreeItem<String> treeItem = new TreeItem<String>();
        TreeItem<String> maths = new TreeItem<String>("Maths");
        TreeItem<String> carre = new TreeItem<String>("Carre");
        TreeItem<String> question2 = new TreeItem<String>("question2");
        TreeItem<String> question1 = new TreeItem<String>("question1");
        TreeItem<String> fich1 = new TreeItem<String>("fich1");
        treeItem.setExpanded(true);
        carre.getChildren().addAll(question2);
        maths.getChildren().addAll(carre,question1);
        treeItem.getChildren().addAll(maths,fich1);

        assertEquals(treeItem,superBank.generateTree());

    }




}
