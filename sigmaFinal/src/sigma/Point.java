package sigma;

public class Point {
	
	int c;
	int r;
	
	static int R = 75;
	static int C = 300;
	
	
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	
	
	
	
	public int distance(Point p) {

		int rowdist = Math.abs(this.r - p.r);
		int columndist = Math.min(Math.abs(this.c - p.c),C-Math.abs(this.c - p.c));	

		return columndist + rowdist;
	}
	
	public static int distance(Point p, Point p2) {

		int rowdist = Math.abs(p2.r - p.r);
		int columndist = Math.min(Math.abs(p2.c - p.c),C-Math.abs(p2.c - p.c));	

		return columndist + rowdist;
	}




	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + "]";
	}
	
	

}
