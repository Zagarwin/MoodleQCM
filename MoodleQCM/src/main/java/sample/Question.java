package sample;

import main.java.sample.Answer;
import main.java.sample.WrongQuestionTypeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private boolean loaded;

    private int id;

    private boolean single;
    private int shuffleanswers;
    private int hidden;
    private String name, questiontext, generalfeedback, correctfeedback, partiallycorrectfeedback, incorrectfeedback;
    private String qt_format, gf_format, cf_format, pcf_format, if_format;
    private String answeringnumbering;
    private double defaultgrade;
    private double penalty;

    private List<Answer> answers;

    public Question(String path) throws WrongQuestionTypeException {
        answers = new ArrayList<>();
        loaded = false;
        init(path);
    }

    public String toString() {
        String str = "";
        str += "id: " + id + " single: " + single + " shuffleanswers: " + shuffleanswers + " hidden: " + hidden + "\n";
        str += name + "\n";
        str += questiontext + "\n";
        str += generalfeedback + "\n";
        str += correctfeedback + "\n";
        str += partiallycorrectfeedback + "\n";
        str += incorrectfeedback + "\n";
        str += answeringnumbering + "\n";
        str += defaultgrade + "\n";
        str += penalty + "\n";
        int i = 0;
        for (Answer a : answers) {
            i++;
            str += "Answer [" + i + "] :" + "\n";
            str += a.getText() + "\n";
            str += a.getTextFormat() + "\n";
            str += a.getFeedback() + "\n";
            str += a.getFeedbackFormat() + "\n";
            str += a.getFraction() + "\n";
        }
        return str;
    }

    public Element getQuestionXml() {
        return null;
    }

    public void save() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("question_data");
            Element x_header = document.createElement("id_header");
            root.appendChild(x_header);

            Element x_id = document.createElement("id");
            x_header.appendChild(x_id);
            x_id.appendChild(document.createTextNode(Integer.toString(id)));

            Element x_name = document.createElement("name");
            x_header.appendChild(x_name);
            x_name.appendChild(document.createTextNode(name));

            Element x_body = getQuestionXml();
            root.appendChild(x_body);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult sortie = new StreamResult(new File(" ... "));   //TODO: relier à getPath de Louis
            transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, sortie);
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void init(String xml_path) throws WrongQuestionTypeException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xml_path));
            Element root = document.getDocumentElement();
            Element id_header = (Element) root.getElementsByTagName("id_header").item(0);

            name = ((Element)id_header.getElementsByTagName("name").item(0)).getElementsByTagName("text").item(0).getTextContent(); // Init name
            id = Integer.parseInt(id_header.getElementsByTagName("id").item(0).getTextContent());  //Init ID

            final Element x_question = (Element) document.getElementsByTagName("question").item(0);
            if (!x_question.getAttribute("type").equals("multichoice")) {
                throw new WrongQuestionTypeException();
            }
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String xml_path) {  //ne devra pas prendre de paramètre

        if (loaded) {
            return;
        }

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xml_path));

            Element root = document.getDocumentElement();

            Element x_question = (Element) document.getElementsByTagName("question").item(0);

            Element x_questiontext = (Element) x_question.getElementsByTagName("questiontext").item(0);
            qt_format = x_questiontext.getAttribute("format");     // Init qt_format
            questiontext = x_questiontext.getElementsByTagName("text").item(0).getTextContent();  // Init questiontext

            Element x_generalfeeback = (Element) x_question.getElementsByTagName("generalfeedback").item(0);
            gf_format = x_generalfeeback.getAttribute("format");     // Init gf_format
            generalfeedback = x_generalfeeback.getElementsByTagName("text").item(0).getTextContent();  // Init generalfeedback

            Element x_correctfeedback = (Element) x_question.getElementsByTagName("correctfeedback").item(0);
            cf_format = x_correctfeedback.getAttribute("format");     // Init cf_format
            correctfeedback = x_correctfeedback.getElementsByTagName("text").item(0).getTextContent();  // Init correctfeedback

            Element x_partiallycorrectfeedback = (Element) x_question.getElementsByTagName("partiallycorrectfeedback").item(0);
            pcf_format = x_partiallycorrectfeedback.getAttribute("format");     // Init pcf_format
            partiallycorrectfeedback = x_partiallycorrectfeedback.getElementsByTagName("text").item(0).getTextContent();  // Init partiallycorrectfeedback

            Element x_incorrectfeedback = (Element) x_question.getElementsByTagName("incorrectfeedback").item(0);
            if_format = x_incorrectfeedback.getAttribute("format");     // Init if_format
            incorrectfeedback = x_incorrectfeedback.getElementsByTagName("text").item(0).getTextContent();  // Init incorrectfeedback

            defaultgrade = Double.parseDouble(x_question.getElementsByTagName("defaultgrade").item(0).getTextContent());
            penalty = Double.parseDouble(x_question.getElementsByTagName("penalty").item(0).getTextContent());
            hidden = Integer.parseInt(x_question.getElementsByTagName("hidden").item(0).getTextContent());
            single = Boolean.parseBoolean(x_question.getElementsByTagName("single").item(0).getTextContent());
            answeringnumbering = x_question.getElementsByTagName("answernumbering").item(0).getTextContent();

            NodeList x_answers = x_question.getElementsByTagName("answer");
            int answers_nb = x_answers.getLength();
            for (int ans = 0; ans < answers_nb; ans++) {
                Element answer = (Element) x_answers.item(ans);
                Double fraction = Double.parseDouble(answer.getAttribute("fraction"));
                String text = answer.getElementsByTagName("text").item(0).getTextContent();
                String t_format = answer.getAttribute("format");
                Element feedback = (Element) answer.getElementsByTagName("feedback").item(0);
                String f_format = feedback.getAttribute("format");
                String q_feedback = feedback.getElementsByTagName("text").item(0).getTextContent();
                answers.add(new Answer(fraction, text, t_format, q_feedback, f_format));
            }

        }
        catch(final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch(final SAXException e) {
            e.printStackTrace();
        }
        catch(final IOException e) {
            e.printStackTrace();
        }

        loaded = true;
    }

    public int getID() {
        return id;
    }
}
