package sigma;

public class Loon {

	int currentPosR;
	int currentPosC;
	
	int currentAlt;
	

	Point goal;
	
	public Loon(int startR, int startC, Point goal) {
		this.currentAlt = 0;
		this.currentPosR = startR;
		this.currentPosC = startC;
		this.goal = goal;
	}
	
	public void up(MovVector[][][] matrix) {
		
		Point nextPoint = getNextPosition(1, matrix);
		
		if(null != nextPoint) {
			currentAlt++;
			currentPosR=nextPoint.r;
			currentPosC=nextPoint.c;
		}
		

	}
	
	public void down(MovVector[][][] matrix) {
		
		Point nextPoint = getNextPosition(-1, matrix);
		
		if(null != nextPoint) {
			currentAlt--;
			currentPosR=nextPoint.r;
			currentPosC=nextPoint.c;
		}
		

	}
	
	public void stay(MovVector[][][] matrix) {
		
		Point nextPoint = getNextPosition(0, matrix);
		
		if(null != nextPoint) {
			currentPosR=nextPoint.r;
			currentPosC=nextPoint.c;
		}
		

	}
	
	public void move(MovVector[][][] matrix) {
		
		int direction = rightDecision(matrix);
		
		if(direction == 1) {
			up(matrix);
		}
		
		if(direction == -1) {
			down(matrix);
		}
		
		if(direction == 0) {
			stay(matrix);
		}
		
	}
	
	public int rightDecision(MovVector[][][] matrix)  {
		
		
		Point up = getNextPosition(1, matrix);
		int dUp = (null == up) ? 100000 : Point.distance(up, goal);
		
		Point down = getNextPosition(-1, matrix);
		int dDown = (null == down) ? 100000 : Point.distance(down, goal);
		
		Point stay = getNextPosition(0, matrix);
		int dStay = Point.distance(stay, goal);
		
		System.out.println(dUp);
		System.out.println(dDown);
		System.out.println(dStay);
		
		int min = Math.min(dUp, Math.min(dUp, dStay));
		
		if(dUp == min)
			return 1;
		
		if(dDown == min)
			return -1;
		
		return 0;
		
	}
	
	public Point getNextPosition(int altitudeChoice, MovVector[][][] matrix) {
		
		int c = 300;
		int r = 75;
		int maxAlt = 7;
		
		if(altitudeChoice == 0) {
			MovVector vector = matrix[currentAlt][currentPosR][currentPosC];
			
			int newR = currentPosR + vector.deltaR;
			int newC = (currentPosC + vector.deltaC) % c;
			
			if(newR >= r)  {
				//lost loon ;/
				return null;
			}
			else {
				return new Point(newR, newC);
			}
		}
		
		if(altitudeChoice == -1 && currentAlt > 1) {
			MovVector vector = matrix[currentAlt-1][currentPosR][currentPosC];
			
			int newR = currentPosR + vector.deltaR;
			int newC = (currentPosC + vector.deltaC) % c;
			
			if(newR >= r)  {
				//lost loon ;/
				return null;
			}
			else {
				return new Point(newR, newC);
			}
		}
		
		if(altitudeChoice == 1 && currentAlt < maxAlt) {
			MovVector vector = matrix[currentAlt+1][currentPosR][currentPosC];
			
			int newR = currentPosR + vector.deltaR;
			int newC = (currentPosC + vector.deltaC) % c;
			
			if(newR >= r)  {
				//lost loon ;/
				return null;
			}
			else {
				return new Point(newR, newC);
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "Loon [ currentPosC="
				+ currentPosC + ", currentPosR=" + currentPosR + ",currentAlt=" + currentAlt + "]";
	}
	
	
	
	
	
/*
 * 
 * 
 * 
 * 
 * 
	TargetCell targetCell;
	
	chooseNextAltitude(Point p)

		Vecteur = giveVecteur(p.currentPosition, Altitude (0))
		nextPosition = givePosition(currentPosition, Vecteur)
		distancez = distance(currentPosition, NextPosition)
	
		Vecteur = giveVecteur(p.currentPosition, Altitude (1))
		nextPosition = givePosition(currentPosition, Vecteur)
		distancep = distance(currentPosition, NextPosition)
	
		Vecteur = giveVecteur(p.currentPosition, Altitude (-1))
		nextPosition = givePosition(currentPosition, Vecteur)
		distancen = distance(currentPosition, NextPosition)
	
		if distancez < distancen
			if distancez < distancep
				return 0
			else
				return 1
		else
			if distancen < distancep
				return -1
			else
				return 1
	*/
}
