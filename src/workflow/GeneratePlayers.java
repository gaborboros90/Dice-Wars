package workflow;

import java.util.ArrayList;

import model.PlayerBean;
import model.PlayerColor;

public class GeneratePlayers {
	public static ArrayList<PlayerBean> createPlayers(int nmbOfPlayers, int tableWidth) {
		int numberOfTerritoriesPerPlayer = (int) (Math.pow(tableWidth, 2) / nmbOfPlayers);
		ArrayList<PlayerBean> players = new ArrayList<PlayerBean>();
		
		for(int i = 0; i < nmbOfPlayers ; i++) {
			players.add(new PlayerBean(PlayerColor.values()[i], numberOfTerritoriesPerPlayer , numberOfTerritoriesPerPlayer * 3));
			System.out.println(numberOfTerritoriesPerPlayer);
		}
		
		return players;
	}
}
