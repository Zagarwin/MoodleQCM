package sample;

import java.io.File;
import java.util.ArrayList;
public class SuperBank {
    private File dirBank;

    public SuperBank() {
        dirBank =new File("bank");

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

    public ArrayList xmlInfile(File dirBank) {
        if (!havefiles(dirBank)) return null;
        for (File dir : dirBank.listFiles()){
            if (dir.isDirectory()) xmlInfile(dir);
        }
        return null;
    }

    public boolean isXmlFile(File file) {

        return false;
    }
}
