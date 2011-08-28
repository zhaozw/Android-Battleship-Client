package edu.neumont.battleship.http;

import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;
import edu.neumont.battleship.testharness.ServerComm;
import android.util.Log;

public class BattleshipServerConnector {
	public static final String TAG = BattleshipActivity.TAG;
	private final static String contentType = "application/xml";
	private static final String HOST = "http://joe-bass.com:8800/BattleshipServer/";
	
	public static XMLResponse newGame(String playerID, PlayerType opponent) throws Exception {
			return new XMLResponse(ServerComm.call(
					HOST + "NewGame",
					XMLStringBuilder.newGame(playerID, opponent), 
					contentType));
	}

	public static XMLResponse joinGame(String playerID, String gameID) {
		try {
			return new XMLResponse(ServerComm.call(
					HOST + "Join",
					XMLStringBuilder.joinGame(playerID, gameID), 
					contentType));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static XMLResponse placeShip(String coordinates, 
			Direction direction, ShipType ship) 
	{
		try {
			return new XMLResponse(ServerComm.call(
					HOST + "PlaceShip", 
					XMLStringBuilder.placeShip(coordinates, direction, ship), 
					contentType));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static XMLResponse fire(String coordinates) {
		try {
			return new XMLResponse(ServerComm.call(
					HOST + "Fire",
					XMLStringBuilder.fire(coordinates), 
					contentType));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static boolean ping()
	{
		try
		{
			//TODO: find out what a bad call looks like
			if(ServerComm.call(HOST, "", contentType).equals(""))
				return false;
			else
				return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public static String getGameList()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
