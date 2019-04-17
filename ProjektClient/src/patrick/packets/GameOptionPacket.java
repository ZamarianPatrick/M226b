package patrick.packets;

import java.util.ArrayList;
import java.util.List;

import patrick.packets.Packet;
import patrick.packets.utils.CheckOption;
import patrick.packets.utils.Option;
import patrick.packets.utils.RadioOption;
/**
 * <p>Verwertet eine Spieloptionen Nachricht, welche vom Server als Nachricht gesendet
 * wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOptionPacket extends Packet{

	/**
	 * Standard-Wert der Spieleranzahl-Variante
	 */
	private int defaultPlayerAmount;
	
	/**
	 * Verf�gbare Spieleranzahl-Varianten
	 */
	
	private List<Integer> avaiblePlayerAmounts;
	
	/**
	 * Verf�gbare Spieloptionen
	 */
	
	private List<Option> options;
	
	/**
	 * Erzeugt ein GameOptionPacket
	 * 
	 * @param defaultPlayerAmount Standard-Wert f�r Spieleranzahl-Variante
	 * @param avaiblePlayerAmounts verf�gbare Spieleranzahl-Varianten
	 * @param options verf�gbare Spieloptionen
	 */
	
	public GameOptionPacket(int defaultPlayerAmount, List<Integer> avaiblePlayerAmounts, List<Option> options) {
		this.defaultPlayerAmount = defaultPlayerAmount;
		this.avaiblePlayerAmounts = avaiblePlayerAmounts;
		this.options = options;
	}
	
	/**
	 * Erzeugt ein GameOptionPacket
	 * 
	 * @param line Nachricht, welche der Server versendet hat
	 */
	
	public GameOptionPacket(String line) {
		this.avaiblePlayerAmounts = new ArrayList<Integer>();
		this.options = new ArrayList<Option>();
		if(line.split(":").length == 2) {
			String nonSense = line.split(":")[1];
			String amountsText = nonSense.split(",")[0];
			if(amountsText != null) {
				String[] amounts = amountsText.split("-");
				for(int i = 0; i < amounts.length; i++) {
					try {
						if(i == 0) {
							defaultPlayerAmount = Integer.parseInt(amounts[i]);
						}else {
							avaiblePlayerAmounts.add(Integer.parseInt(amounts[i]));
						}
					}catch(NumberFormatException ex){}
				}
			}else {
				defaultPlayerAmount = 0;
			}
			if(amountsText.length() < nonSense.length()-1) {
				String optionsText = nonSense.substring(amountsText.length()+1);
				String[] optionText = optionsText.split(";");
				for(String option : optionText) {
					String type = option.split(",")[0];
					if(type != null) {
						option = option.substring(type.length()+1);
						String[] args = option.split(",");
						if(type.equals("check")) {
							String name = args[0];
							String text = args[1];
							String checkText = args[2];
							String defaultValue = args[3];
							if(oneIsNull(name, text, checkText, defaultValue) == false) {
								CheckOption checkOption = new CheckOption(name);
								checkOption.setCheckBoxText(checkText);
								checkOption.setDefaultValue(defaultValue.equals("true"));
								checkOption.setText(text);
								this.options.add(checkOption);
							}
						}else if(type.equals("radio")) {
							String name = args[0];
							String text = args[1];
							String defaultValue = args[2];
							if(oneIsNull(name, text, defaultValue) == false) {
								RadioOption rOption = new RadioOption(name);
								rOption.setDefaultValue(defaultValue);
								rOption.setText(text);
								if(args.length > 3) {
									for(int i = 3; i<args.length; i++) {
										rOption.addChooseable(args[i]);
									}
								}
								this.options.add(rOption);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Liefert die Standard Spieleranzahl-Variante zur�ck
	 * 
	 * @return Standard Spieleranzahl-Variante
	 */
	
	public int getDefaultPlayerAmount() {
		return this.defaultPlayerAmount;
	}
	
	/**
	 * Liefert eine Liste der verf�gbaren Spieleranzahl-Varianten zur�ck
	 * 
	 * @return Liste der verf�gbaren Spieleranzahl-Varianten
	 */
	
	public List<Integer> getPlayerAvaibleAmounts(){
		return this.avaiblePlayerAmounts;
	}
	
	/**
	 * Liefert eine Liste der verf�gbaren Spieloptionen zur�ck
	 * 
	 * @return Liste der verf�gbaren Spieloptionen
	 */
	
	public List<Option> getOptions(){
		return this.options;
	}
	
	/**
	 * Liefert ein Wahrheitswert, ob die Spieleinstellung existiert
	 * 
	 * @param name Name der Spieleinstellung
	 * 
	 * @return Wahrheitswert, ob die Spieleinstellung existiert
	 */
	
	public boolean containsOption(String name) {
		for(Option option : this.options) {
			if(option.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �berpr�ft, ob eines der gegebenen Objekte == null ist
	 * 
	 * @param objects Objekte, welche gepr�ft werden sollen
	 * 
	 * @return Wahrheitswert, ob eines der Objekte == null ist
	 */
	
	private boolean oneIsNull(Object...objects) {
		for(Object obj : objects) {
			if(obj == null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */

	@Override
	public String getLine() {
		String toSend = "gameoption:" + getDefaultPlayerAmount();
		for(int amount : avaiblePlayerAmounts) {
			toSend += "-"+amount;
		}
		for(Option option : getOptions()) {
			if(option instanceof CheckOption) {
				CheckOption checkOption = (CheckOption) option;
				toSend += ",check," + 
				checkOption.getName() + "," + 
				checkOption.getText() + "," + 
				checkOption.getCheckBoxText()+ "," +
				checkOption.getDefaultValue() + ";";
			}else if(option instanceof RadioOption) {
				RadioOption radioOption = (RadioOption) option;
				toSend += "radio," +
				radioOption.getName()+","+
				radioOption.getText()+","+
				radioOption.getDefaultValue();
				for(String chooseable : radioOption.getChooseable()) {
					toSend += ","+chooseable; 
				}
				toSend += ";";
			}
		}
		if(toSend.endsWith(";")) {
			toSend = toSend.substring(0, toSend.length()-1);
		}
		return toSend;
	}
	
}
