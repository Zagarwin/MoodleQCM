package main.java.sample;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;



public class QuestionStorage{
    private Set<Question> list_question;
    private String name;
    private SuperBank super_bank;
    private boolean is_bank;


    public QuestionStorage(boolean is_bank){
        list_question = new HashSet<Question>();
        is_bank = is_bank;
        name = "QuestionStorage defaut";
    }

    public QuestionStorage(String xml_path, boolean is_bank){
        list_question = new HashSet<Question>();
        is_bank = is_bank;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document= builder.parse(new File(xml_path));
            Element racine = document.getDocumentElement();
            Element name_0 = (Element)racine.getElementsByTagName("name");

            name = name_0.getTextContent();
            final NodeList list_Id = racine.getElementsByTagName("question_id_list");
            final int nbIDsElements = list_Id.getLength();
            for(int i =  0; i<nbIDsElements; i++) {
                final Element Id = (Element) list_Id.item(i);
                Question new_question = super_bank.findQuestion(Id.getTextContent());
                list_question.add(new_question);
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
        } catch (WrongQuestionTypeException e) {
            e.printStackTrace();
        }
    }







    public void save(String xml_path){
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(xml_path));

            final Element racine = document.createElement("Bank");
            document.appendChild(racine);
            final Comment commentaire = document.createComment("Question Bank");
            racine.appendChild(commentaire);
            final Element name_0 = document.createElement("name");
            final Element question_id_list = document.createElement("question_id_list");
            racine.appendChild(name_0);
            racine.appendChild(question_id_list);
            name_0.appendChild(document.createTextNode(name));

            for (Question q:list_question) {
                final Element question_id = document.createElement("question_id");
                question_id_list.appendChild(question_id);
                question_id.appendChild(document.createTextNode(q.getID()+""));
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

    public void affichage(){

    }

    public void Import(String xml_path){

    }

    public void Export(String xml_path){

    }

    public void addQuestion(Question question){
        list_question.add(question);
    }

    public void deleteQuestion(Question question){
        list_question.remove(question);
    }


    public void changeName(String name_0){
        name = name_0;
    }

    public String getName(){
        return name;
    }



}
