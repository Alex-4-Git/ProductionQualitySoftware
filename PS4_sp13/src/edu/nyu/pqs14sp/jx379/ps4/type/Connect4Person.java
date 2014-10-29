package edu.nyu.pqs14sp.jx379.ps4.type;

import java.awt.Color;

import edu.nyu.pqs14sp.jx379.ps4.api.IConnect4Player;

/**
 * 
 * The computer in PvP mode.
 * 
 * @author jing
 *
 */
public class Connect4Person implements IConnect4Player {

	private Connect4PlayerType type;
	private Color color;

	private Connect4Person(Builder builder) {

		type = builder.type;
		color = builder.color;
	}

	/**
	 * 
	 * The builder pattern for initialize this object.	 * 
	 * @author jing
	 *
	 */
	public static class Builder {

		private Connect4PlayerType type;
		private Color color;

		public Builder(Connect4PlayerType type, Color color) {

			this.type = type;
			this.color = color;

		}

		public Connect4Person build() {
			return new Connect4Person(this);
		}
	}

	@Override
	public Connect4PlayerType getPlayerType() {
		return type;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
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
		Connect4Person other = (Connect4Person) obj;
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
	 * String like "Connect4Person [type=PlayOne, color=Color.RED]"
	 */
	@Override
	public String toString() {
		return "Connect4Person [type=" + type + ", color=" + color + "]";
	}

}
