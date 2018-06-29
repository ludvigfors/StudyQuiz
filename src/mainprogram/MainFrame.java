package mainprogram;


import objects.Category;
import objects.Question;
import objects.RootXMLFile;
import panels.CreationPanel;
import panels.QuizPanel;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class MainFrame extends JFrame implements ActionHandler
{
    public static final String QUESTIONS_XML = "questions.xml";
    public static final String CREATE_CONSTRAINT = "create";
    public static final String QUIZ_CONSTRAINT = "quiz";
    private final JAXBContext context;
    private String[] dummyCred = {"TATA65","TDDD78"};
    private CardLayout cardLayout;
    private JPanel mainPanel = new JPanel();

    private CreationPanel createPanel;
    private QuizPanel quizPanel;

    private RootXMLFile rootXMLFile;


    public MainFrame() throws JAXBException {
	super("Study Quiz");

	// create JAXB context
	context = JAXBContext.newInstance(RootXMLFile.class);
	//categoryComboBox = new JComboBox<>();
	cardLayout = new CardLayout();
	readXMLFile();
	//updateComboBox();
	createContent();
	createFrame();
    }


    private void createContent() {
	mainPanel.setLayout(cardLayout);
	createPanel = new CreationPanel(rootXMLFile.getCategories(),this);
	quizPanel = new QuizPanel(this);
	mainPanel.add(createPanel, CREATE_CONSTRAINT);
	mainPanel.add(quizPanel, QUIZ_CONSTRAINT);
	cardLayout.show(mainPanel,CREATE_CONSTRAINT);
    }





    private void createFrame() {
        add(mainPanel);
      	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      	pack();
      	//setSize(400,200);
      	setVisible(true);
      	//setResizable(false);

          }
    @Override
    public void startQuiz() {
        quizPanel.updateComboBox(rootXMLFile.getCategories());
        cardLayout.show(mainPanel,QUIZ_CONSTRAINT);

        /*
        getContentPane().removeAll();
        getContentPane().add(new QuizPanel(this));
        getContentPane().validate();
        */
    }
    @Override
    public void startCreate() {
         cardLayout.show(mainPanel,CREATE_CONSTRAINT);

          /* getContentPane().removeAll();
           getContentPane().add(new CreationPanel(rootXMLFile.getCategories(),this));
           getContentPane().validate();
       */
       }

    @Override public ArrayList<Category> getCategories() {
	return rootXMLFile.getCategories();
    }


    @Override
    public void addNewCategory(String categoryName) {
	if(!categoryName.isEmpty() && categoryExists(categoryName)){
	    Category newCategory = new Category(categoryName);
	    rootXMLFile.addCategory(newCategory);
	    writeToXMLFile();
	    readXMLFile();
	}
    }

    private boolean categoryExists(String wantedcategoryName) {
	for (Category category : rootXMLFile.getCategories()) {
	    if(category.getName().equals(wantedcategoryName)){
		return false;
	    }
	}
	return true;
    }
@Override
    public void addNewQuestion(Question question, String selectedCategory) {
        rootXMLFile.addQuestionToCategory(question, selectedCategory);
	writeToXMLFile();
    }

    private void writeToXMLFile() {
	try {
	    // instaniate marshaller
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

	    //Write to System.out
	    m.marshal(rootXMLFile,System.out);

	    //Write to xml file
	    m.marshal(rootXMLFile, new File(QUESTIONS_XML)); // måste det vara new?

	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }


    private void readXMLFile()  {
	try {
	    // instansiate unmarshaller
	    Unmarshaller um = context.createUnmarshaller();
	    rootXMLFile = (RootXMLFile) um.unmarshal(new FileReader(QUESTIONS_XML)); // Behövs new ?

	} catch (JAXBException | FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

}
