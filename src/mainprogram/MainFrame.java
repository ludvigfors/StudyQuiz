package mainprogram;


import panels.CreationPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JPanel panel;
    private BorderLayout layout;



    public MainFrame() {
        super("Study Quiz");
	createPanel();
	createFrame();
    }

    private void createPanel() {
        panel = new JPanel();
        setLayout(new BorderLayout());
	CreationPanel crePanel = new CreationPanel();
	add(crePanel,BorderLayout.NORTH);
    }

    private void createFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	pack();
	//setSize(400,200);
	setVisible(true);
	//setResizable(false);

    }
}
