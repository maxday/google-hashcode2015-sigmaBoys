package sigma;

public class Point {
	
	int c;
	int r;
	
	int R = 75;
	int C = 300;
	
	public int distance(Point p) {

		int rowdist = Math.abs(this.r - p.r);
		int columndist = Math.min(Math.abs(this.c - p.c),C-Math.abs(this.c - p.c));	

		return columndist + rowdist;
	}

}
