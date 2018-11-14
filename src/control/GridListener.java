package control;

import java.awt.event.*;

import entity.*;
import view.View;

public class GridListener implements ActionListener{
	private Position position;
	private View view;
	public GridListener(int row, int column, View view) {
		position = new Position(row, column);
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {}
}

