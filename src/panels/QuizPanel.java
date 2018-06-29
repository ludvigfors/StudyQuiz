package panels;

import mainprogram.ActionHandler;
import mainprogram.Constants;
import net.miginfocom.swing.MigLayout;
import objects.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuizPanel extends JPanel implements CustomPanels
{
    private ActionHandler handler;
    private JComboBox categoryComboBox = new JComboBox();

    public QuizPanel(ActionHandler handler) {
	this.handler = handler;
	displayButton();
    }

    private void displayButton() {
	JPanel p = new JPanel();
	p.setLayout(new MigLayout());
	//p.setBackground(Color.GREEN);
	p.setPreferredSize(Constants.PREFFERED_SIZE);
	JButton button = new JButton("Tillbaka");
	button.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		handler.startCreate();
	    }
	});

	JLabel comboBoxTitle = new JLabel();
	comboBoxTitle.setText("Vilken kurs vill du förhöra dig på?");


	// Go Button
	JButton goButton = new JButton("GO!!!");

	p.add(button, "wrap");
	p.add(comboBoxTitle,"wrap,skip1,sg 1");
	p.add(categoryComboBox,"wrap,skip1,sg 1");
	p.add(goButton,"skip1,align center");
	add(p);
    }

    public void updateComboBox(ArrayList<Category> categoryList) {
	categoryComboBox.removeAllItems();
	for (Category category : categoryList) {
	    categoryComboBox.addItem(category.getName());
	}
    }
}
