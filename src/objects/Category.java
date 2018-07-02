package objects;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Has a list of question objects that belongs to a specific category.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category
{
    @XmlElementWrapper(name = "questions")
    @XmlElement(name = "question")
    private List<Question> questionList = null;
    private String name = null;

    public Category(final String name) {
	this.name = name;
	this.questionList = new ArrayList<>();
    }

    public Category() {} // Used by JAXB

    public List<Question> getQuestionList() {
	return questionList;
    }

    public void setQuestionList(final List<Question> questionList) {
	this.questionList = questionList;
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
