package marsRover.commands;

import java.util.List;
import java.util.ArrayList;

public class inputCommands {
	private ArrayList<Character> listOfCommands = new ArrayList<>();

	public inputCommands() {
	}

	public inputCommands(String listOfCommands) {
		stringToCommandsList(listOfCommands);
	}

	public void setListOfCommands(String listOfCommands) {
		stringToCommandsList(listOfCommands);
	}

	private void stringToCommandsList(String commands) {
		int listSize = commands.length();
		this.listOfCommands.clear();

		for (int i = 0; i < listSize; i++) {
			char ch = Character.toUpperCase(commands.charAt(i));
			if (ch == 'B' || ch == 'F' || ch == 'L' || ch == 'R')
				this.listOfCommands.add(ch);
		}
	}

	public List<Character> getListOfCommands() {
		return this.listOfCommands;
	}
}