package objects;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that represents the root of the xml document.
 *
 * Each category in the list of categories will be represented by a tag
 *
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootXMLClass
{
    @XmlElement(name = "category")
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
	return categories;
    }

    public void setCategories(final List<Category> categories) {
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

    public List<Question> getCategoryQuestions(String categoryName){
	for (Category category : categories) {
	    if(category.getName().equals(categoryName)){
	        return category.getQuestionList();
	    }
	}
	return null; // Borde inte komma hit
    }
}

