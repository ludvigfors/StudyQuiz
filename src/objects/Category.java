package objects;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category
{
    @XmlElementWrapper(name = "questions")
    @XmlElement(name = "question")
    private ArrayList<Question> questionList;
    private String name;

    public Category(final String name) {
	this.name = name;
	this.questionList = new ArrayList<>();
    }

    public Category() {
    }

    public ArrayList<Question> getQuestionList() {
	return questionList;
    }

    public void setCategoryQuerys(final ArrayList<Question> bookList) {
	this.questionList = bookList;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public void addQuestion(Question question){
        this.questionList.add(question);
    }

}
