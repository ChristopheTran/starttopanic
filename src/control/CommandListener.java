package control;
import java.awt.event.*;

import view.View;
public class CommandListener implements ActionListener{
	private View.Command type;
	private View view;
	public CommandListener(View.Command type, View view) {
		this.type = type;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		view.setSelectedCommand(type);
	}
}
