import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import sample.SuperBank;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SuperBankTest {
    SuperBank superBank;
    @Before
    public void setUp(){
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
    public void testXMLInFile(){
        ArrayList<String[]> arrayList = new ArrayList<>();
        assertEquals(arrayList.size(),superBank.xmlInfile(superBank.getDirBank()));
    }

}
