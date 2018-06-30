package objects;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootXMLFile
{
    @XmlElement(name = "category")
    private ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
	return categories;
    }

    public void setCategories(final ArrayList<Category> categories) {
	this.categories = categories;
    }

    public void addQuestionToCategory(Question question, String categoryName){
	for (Category category : categories) {
	    if(category.getName().equals(categoryName)){
	        category.addQuestion(question);
	    }
	}

    }

    public void addCategory(Category newCategory){
        categories.add(newCategory);
    }

    public ArrayList<Question> getCategoryQuestions(String categoryName){
	for (Category category : categories) {
	    if(category.getName().equals(categoryName)){
	        return category.getQuestionList();
	    }
	}
	return null; // Borde inte komma hit
    }
}

