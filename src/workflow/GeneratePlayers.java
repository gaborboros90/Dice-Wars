package workflow;

import java.util.ArrayList;

import model.PlayerBean;
import model.PlayerColor;

public class GeneratePlayers {
	/**
	 * J�t�kosok l�trehoz�s��rt felel�s statikus met�dus
	 * 
	 * @param nmbOfPlayers H�ny j�t�kos legyen?
	 * @param tableWidth T�bla m�rete
	 * @return J�t�kosok
	 */
	
	public static ArrayList<PlayerBean> createPlayers(int nmbOfPlayers, int tableWidth) {
		int numberOfTerritoriesPerPlayer = (int) (Math.pow(tableWidth, 2) / nmbOfPlayers);
		ArrayList<PlayerBean> players = new ArrayList<PlayerBean>();
		
		for(int i = 0; i < nmbOfPlayers ; i++) {
			players.add(new PlayerBean(PlayerColor.values()[i], numberOfTerritoriesPerPlayer , numberOfTerritoriesPerPlayer * 3));
		}
		
		return players;
	}
}
