package patrick.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import patrick.Server;
import patrick.game.Game;
import patrick.game.options.CheckOption;
import patrick.game.options.Option;
import patrick.game.options.RadioOption;
import patrick.packets.out.GameOptionPacket;
import patrick.packets.out.RequestRoundPacket;
import patrick.packets.out.WaitingRoundInfoPacket;
import patrick.utils.Message;
/**
 * <p>Testet verschiedene vorkommende Fälle des Servers</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
class ServerTest {
	
	/**
	 * Zeit in Millisekunden, die jeweils gewartet wird, um eine Antwort
	 * des Servers zu erhalten
	 */
	
	private int waitingForRequest = 1000;
	
	/**
	 * Port des Servers
	 */
	
	private int serverPort = 5555;
	
	/**
	 * Anzahl registrierte Spiele
	 */
	
	private static int gameCount = 0;
	
	/**
	 * Vor jedem Test soll der SErver gestartet und ein Testspiel registriert werden
	 */
	
	@BeforeEach
	public void serverStart() {
		Server.start();
		System.out.println("Server gestartet!");
		TestGame gameRound = new TestGame();
		Game game = gameRound.gameSetup();
		gameCount = Server.getAllGames().size();
		Server.registerGame(game);
	}
	
	/**
	 * Nach jedem Test soll der Server gestoppt werden
	 */
	
	@AfterEach
	public void serverStop() {
		Server.stop();
		System.out.println("Server gestoppt!");
	}
	
	/**
	 * Testet, ob der Server überhaupt startet
	 */
	
	@Test
	void testStart() {
		assertNotEquals(null, Server.getSocket());
	}
	
	/**
	 * Baut eine Verbindung zum Server auf und testet diese
	 */
	
	@Test
	void testConnection() {
		try {
			Socket socket = new Socket(getIpAdress(), serverPort);
			assertEquals(socket.isConnected(), true);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	
	/**
	 * Verbindet sich mit dem Server, und testet ob der Server den Client angenommen hat
	 */
	
	@Test
	void testServerClientList() {
		try {
			Socket socket = new Socket(getIpAdress(), serverPort);
			
			Thread.sleep(waitingForRequest);
			
			assertEquals(1, Server.getConnectedClients().size());
			socket.close();
			Thread.sleep(waitingForRequest);
			
			assertEquals(0, Server.getConnectedClients().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Baut eine Verbindung zum Server auf
	 * Registriert einen neuen Listener
	 * Schickt eine Test-Nachricht die an den Listener ankommen sollte
	 * Prüft ob die Nachricht angekommen ist
	 */
	
	@Test
	void testServerMessageAndListener() {
		try {
			new TestMessageListener();
			Socket socket = new Socket(getIpAdress(), serverPort);
			TestServerListener listener = new TestServerListener(socket);
			listener.start();
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("test");
			writer.flush();
			Thread.sleep(waitingForRequest);
			
			assertEquals("test success", listener.getLastMessage());
			writer.close();
			listener.exit();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Prüft, ob das Test-Spiel erfolgreich registriert wurde
	 */
	
	@Test
	void testGetGame() {
		assertEquals(null, Server.getGame("keinName"));
		assertNotEquals(null, Server.getGame("TestingGame"));
	}
	
	/**
	 * Baut eine Verbindung zum Server auf
	 * Schickt eine Anfrage, welche eine Spielrunde erstellen sollte
	 * Prüft ob diese Spielrunde nun existiert
	 * Baut mit einem neuen Client Verbindung zum Server auf
	 * neuer Client versucht die Spielrunde zu erhalten durch eine Anfrage
	 * neuer Client versucht in die Spielrunde beizutreten
	 * neuer Client prüft, ob alle Informationen erhalten worden sind
	 * erster Client überprüft, ob der Spieler bei ihm in der Runde angekommen ist
	 */
	
	@Test
	void testCreateRound() {
		try {
			Socket socket = new Socket(getIpAdress(), serverPort);
			TestServerListener listener = new TestServerListener(socket);
			listener.start();
			Thread.sleep(waitingForRequest);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			int roundCount = Server.getAllGameRounds().size();
			writer.println("create:TestingGame:Patrick:3:isTest=false;color=Blau");
			writer.flush();
			Thread.sleep(waitingForRequest);			
			
			//check round count increment
			assertEquals(roundCount+1, Server.getAllGameRounds().size());
			
			//check player count in round increment
			assertEquals(1, Server.getPlayers().size());
			
			//check if  the packet called Test
			assertEquals("TestingGame", Server.getAllGameRounds().get(roundCount).getGame().getName());
			
			//check if exists the round 'Patrick'
			assertEquals("Patrick", Server.getAllGameRounds().get(roundCount).getHost().getName());
			
			//check if the setting isTest = false
			assertEquals(false, Server.getAllGameRounds().get(roundCount).getSettings().getCheckSetting("isTest"));
			
			//check if the setting color is setted
			assertEquals("Blau", Server.getAllGameRounds().get(roundCount).getSettings().getChooseable("color"));
			
			//create new Client
			Socket socket2 = new Socket(getIpAdress(), serverPort);
			TestServerListener listener2 = new TestServerListener(socket2);
			listener2.start();
			Thread.sleep(waitingForRequest);
			
			PrintWriter writer2 = new PrintWriter(socket2.getOutputStream());
			writer2.println("join:TestingGame");
			writer2.flush();
			Thread.sleep(waitingForRequest);

			String[] args = listener2.getLastMessage().split(":");
			RequestRoundPacket packet = new RequestRoundPacket(listener2.getLastMessage());

			//check if the client has received the round information
			assertEquals("rounds", args[0]);
			assertEquals("TestingGame", packet.getGameName());
			assertNotEquals(null, packet.getRoundPacket("Patrick"));
			assertEquals(null, packet.getRoundPacket("existiertNicht"));
			assertEquals(3, packet.getRoundPacket("Patrick").getMaxPlayerAmount());
			assertEquals(1, packet.getRoundPacket("Patrick").getPlayerAmount());
			
			writer2.println("join:TestingGame:Patrick:patrick");
			writer2.flush();
			Thread.sleep(waitingForRequest);
			
			assertEquals(Message.playerAlreadyExists, listener2.getLastMessage());
			
			writer2.println("join:TestingGame:Patrick:Peter");
			writer2.flush();
			Thread.sleep(waitingForRequest);
			
			
			WaitingRoundInfoPacket receivedPacket = new WaitingRoundInfoPacket(listener.getLastMessage());
			WaitingRoundInfoPacket receivedPacket2 = new WaitingRoundInfoPacket(listener2.getLastMessage());
			
			//check round name from both packets
			assertEquals("Patrick", receivedPacket.getRound().getName());
			assertEquals("Patrick", receivedPacket2.getRound().getName());
		
			//check playercount of both packets
			assertEquals(2, receivedPacket.getRound().getPlayerAmount());
			assertEquals(2, receivedPacket2.getRound().getPlayerAmount());
			
			//check max player count of both packets
			assertEquals(3, receivedPacket.getRound().getMaxPlayerAmount());
			assertEquals(3, receivedPacket2.getRound().getMaxPlayerAmount());

			//check players of both packets
			assertEquals(true, receivedPacket.getPlayers().contains("Peter"));
			assertEquals(true, receivedPacket2.getPlayers().contains("Peter"));
			assertEquals(true, receivedPacket.getPlayers().contains("Patrick"));
			assertEquals(true, receivedPacket2.getPlayers().contains("Patrick"));
			
			Socket socket3 = new Socket(getIpAdress(), serverPort);
			TestServerListener listener3 = new TestServerListener(socket3);
			listener3.start();
			Thread.sleep(waitingForRequest);
			
			writer.close();
			listener.exit();
			writer2.close();
			listener2.exit();
			listener3.exit();
			Thread.sleep(waitingForRequest);
			assertEquals(0, Server.getAllRunningGameRounds().size());
		}catch(Exception ex) {ex.printStackTrace();fail();}
	}
	
	/***
	 * Baut eine Verbindung zum Server auf
	 * versucht die Spiele durch eine Serveranfrage zu erhalten
	 * versucht die Spieloptionen des Test-Spiels durch eine Serveranfrage zu erhalten
	 * 
	 * Prüft alle erhaltenen Informationen des Servers
	 */
	
	@Test
	void testGamesSendToClient() {
		String imageName = "test.png";
		String gameName = "TestingGame";
		int defaultPlayerAmount = 2;
		try {
			Socket socket = new Socket(getIpAdress(), serverPort);
			TestServerListener listener = new TestServerListener(socket);
			listener.start();
			Thread.sleep(waitingForRequest);
			String received = listener.getLastMessage();
			assertNotEquals(null, received);
			String[] split = received.split(":");
			String[] games = split[1].split(";");
			
			assertEquals(gameCount+1, Server.getAllGames().size());
			assertEquals("games", split[0]);
			assertEquals(gameName, games[gameCount].split(",")[0]);
			assertEquals(imageName, games[gameCount].split(",")[1]);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("game:host:TestingGame");
			writer.flush();
			Thread.sleep(waitingForRequest);
			
			assertNotEquals(Message.gameDontExists, listener.getLastMessage());
			
			GameOptionPacket packet = new GameOptionPacket(listener.getLastMessage());
			
			assertEquals(defaultPlayerAmount, packet.getDefaultPlayerAmount());
			assertEquals(true, packet.getPlayerAvaibleAmounts().contains(2));
			assertEquals(true, packet.getPlayerAvaibleAmounts().contains(3));
			assertEquals(true, packet.getPlayerAvaibleAmounts().contains(4));
			assertEquals(true, packet.containsOption("isTest"));
			assertEquals(false, packet.containsOption("ungültigerName"));
			assertEquals(true, packet.containsOption("color"));
			assertEquals(2, packet.getOptions().size());
			
			for(Option option : packet.getOptions()) {
				if(option instanceof CheckOption) {
					CheckOption checkOption = (CheckOption) option;
					
					assertEquals("Soll das ein Test sein?", checkOption.getText());
					assertEquals("Test aktivieren", checkOption.getCheckBoxText());
					assertEquals(true, checkOption.getDefaultValue());
					
				}else if(option instanceof RadioOption) {
					RadioOption radioOption = (RadioOption) option;
					
					assertEquals("Wähle eine Hintergrundfarbe", radioOption.getText());
					assertEquals(true, radioOption.getChooseable().contains("Rot"));
					assertEquals(true, radioOption.getChooseable().contains("Grün"));
					assertEquals(true, radioOption.getChooseable().contains("Blau"));
					assertEquals("Rot", radioOption.getDefaultValue());
				}
			}
			writer.close();
			listener.exit();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Lässt 30 Clients auf den Server verbinden und trennt diese wieder
	 * 
	 * Prüft ob keine Fehler auftreten
	 */
	
	@Test
	void testMassiveExtention() {
		List<Socket> list = new ArrayList<Socket>();
		for(int i = 0; i < 30; i++) {
			try {
				Socket socket = new Socket(getIpAdress(), serverPort);
				TestServerListener listener = new TestServerListener(socket);
				listener.start();
				list.add(socket);
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				writer.println("create: ladlge'ga ::");
				writer.flush();
				listener.exit();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * Liefert die eigene IP-Adresse zurück
	 * 
	 * @return eigene IP-Adresse
	 */
	
	public String getIpAdress() {
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			String ip = socket.getLocalAddress().getHostAddress();
			socket.close();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
