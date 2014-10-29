package edu.nyu.pqs14sp.jx379.ps5.pojo;

/**
 * @author jing
 *
 *	The simple dot object to represent the position.
 */
public class Dot {
	private int x;
	private int y;

	private Dot(Builder builder) {
		x = builder.x;
		y = builder.y;
	}

	/**
	 * 
	 * The builder pattern for initialize this object. *
	 * 
	 * @author jing
	 *
	 */
	public static class Builder {

		private int x;
		private int y;

		public Builder(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Dot build() {
			return new Dot(this);
		}
	}

	/**
	 * @return	The position X.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return	The position Y.
	 */
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Dot other = (Dot) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	/**
	 * @return	The String like Dot [x=0,y=0].
	 */
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + "]";
	}

}
