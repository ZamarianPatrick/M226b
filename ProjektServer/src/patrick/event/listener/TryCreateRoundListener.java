package patrick.event.listener;

import java.util.Map.Entry;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.CreateRoundEvent;
import patrick.event.events.TryCreateRoundEvent;
import patrick.game.Game;
import patrick.game.GameSettings;
import patrick.game.options.CheckOption;
import patrick.game.options.Option;
import patrick.game.options.RadioOption;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf TryCreateRoundEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TryCreateRoundListener implements Listener{

	/**
	 * Erzeugt ein TryCreateRoundListener und registriert diesen
	 */
	
	public TryCreateRoundListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein TryCreateRoundEvent ausgelöst wurde
	 * 
	 * @param TryCreateRoundEvent, welcher ausgelöst wurde
	 */
	
	public void onTryCreateRound(TryCreateRoundEvent e) {
		GameSettings settings;
		if(e.getPacket().getGame() == null) {
			Server.sendMessage(e.getClient(), Message.gameDontExists);
			return;
		}
		if(e.getPacket().getHosterName() == null) {
			Server.sendMessage(e.getClient(), Message.playerAlreadyExists);
			return;
		}
		
		Game game = e.getPacket().getGame();

		if(game.getAvaiblePlayerAmounts().contains(e.getPacket().getPlayerAmount())) {
			settings = new GameSettings(e.getPacket().getPlayerAmount());
		}else {
			settings = new GameSettings(game.getDefaultPlayerAmount());
		}
		
		for(Entry<String, String> entry : e.getPacket().getSettings().entrySet()) {
			String name = entry.getKey();
			String value = entry.getValue();
			String validatedSetting = game.getValidatedSetting(name, value);
			if(validatedSetting != null) {
				if(validatedSetting.equals("false") || validatedSetting.equals("true")) {
					settings.addCheckSetting(name, validatedSetting.equals("true"));
				}else {
					settings.addChooseable(name, validatedSetting);
				}
			}
		}
		
		for(Option option : game.getGameOptions()) {
			if(option instanceof RadioOption) {
				if(settings.getChooseables().containsKey(option.getName()) == false) {
					RadioOption rOption = (RadioOption) option;
					settings.addChooseable(rOption, rOption.getDefaultValue());
				}
			}else if(option instanceof CheckOption) {
				if(settings.getCheckSettings().containsKey(option.getName()) == false) {
					CheckOption checkOption = (CheckOption) option;
					settings.addCheckSetting(checkOption, checkOption.getDefaultValue());
				}
			}
		}
		if(Server.getPlayer(e.getPacket().getHosterName()) == null) {
			CreateRoundEvent event = new CreateRoundEvent(e.getClient(), e.getPacket().getHosterName(), game, settings);
			EventHandler.callEvent(event);
		}else {
			Server.sendMessage(e.getClient(), Message.playerAlreadyExists);
		}
	}
	
}
