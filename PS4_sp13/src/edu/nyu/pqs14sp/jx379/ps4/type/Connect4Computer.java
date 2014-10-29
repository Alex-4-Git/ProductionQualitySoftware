package edu.nyu.pqs14sp.jx379.ps4.type;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Config;
import edu.nyu.pqs14sp.jx379.ps4.conf.Connect4Util;

/**
 * The computer in PvC mode.
 * 
 * @author jing
 *
 */
public class Connect4Computer implements IConnect4Player {
	private Connect4PlayerType type = Connect4PlayerType.Computer;
	private Color color;

	private Connect4Computer(Builder builder) {
		color = builder.color;
	}

	/**
	 * 
	 * The builder pattern for initialize this object.
	 * @author jing
	 *
	 */
	public static class Builder {

		private Color color;

		public Builder(Color color) {
			this.color = color;
		}

		public Connect4Computer build() {
			return new Connect4Computer(this);
		}
	}

	@Override
	public Connect4PlayerType getPlayerType() {
		return type;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	/**
	 * Calculate the next step. If going to win then place on that column, otherwise pick a random column to drop.
	 * 
	 * @param board		The board of current game.
	 * @return			The number of column.
	 */
	public int getNextStep(List<CopyOnWriteArrayList<IConnect4Player>> board){		
		if(board==null){
			// something wrong
			throw new IllegalStateException("Missing Board.");
		}
		for(int j=0;j<board.size();j++){
			board.get(j).add(this);
			if(Connect4Util.checkConnect4Won(board)){
				board.get(j).remove(board.get(j).size()-1);
				return j;
			}else{
				board.get(j).remove(board.get(j).size()-1);
			}
		}
		int result = (int)(Math.random()*Connect4Config.CONNECT_4_BOARD_COLUMN);
		while(board.get(result).size()>=Connect4Config.CONNECT_4_BOARD_ROW){
			result = (int)(Math.random()*Connect4Config.CONNECT_4_BOARD_COLUMN);
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connect4Computer other = (Connect4Computer) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * String like "Connect4Computer [type=Computer, color=Color.RED]"
	 */
	@Override
	public String toString() {
		return "Connect4Computer [type=" + type + ", color=" + color + "]";
	}

	
}
