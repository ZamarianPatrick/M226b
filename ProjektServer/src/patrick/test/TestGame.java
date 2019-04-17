package patrick.test;

import patrick.game.Game;
import patrick.game.RunningRound;
import patrick.game.options.CheckOption;
import patrick.game.options.RadioOption;
/**
 * <p>Ein TestSpiel, welches für das JUnitTestCase verwendet wird
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TestGame extends RunningRound{

	/**
	 * @see patrick.game.RunningRound#onStart()
	 */
	
	@Override
	public void onStart() {
		
	}
	
	/**
	 * @see patrick.game.RunningRound#gameSetup()
	 */

	@Override
	public Game gameSetup() {
		int defaultPlayerAmount = 2;
		Game testGame = new Game("TestingGame");
		testGame.setImage("test.png");
		testGame.setDefaultPlayerAmount(defaultPlayerAmount);
		testGame.addPlayerAmount(2);
		testGame.addPlayerAmount(3);
		testGame.addPlayerAmount(4);
		testGame.addOption(new CheckOption("isTest", "Soll das ein Test sein?", "Test aktivieren", true));
		RadioOption rOption = new RadioOption("color", "Wähle eine Hintergrundfarbe");
		rOption.addChooseable("Blau");
		rOption.addChooseable("Rot");
		rOption.addChooseable("Grün");
		rOption.setDefaultValue("Rot");
		testGame.addOption(rOption);
		return testGame;
	}
	

}
