package sigma;

import java.util.ArrayList;
import java.util.Collections;

public class Loon {

	int currentPosR;
	int currentPosC;
	
	int currentAlt;
	

	Point goal;
	
	
	public int randomWithRange(int min, int max) {
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min;
		}
	
	public Loon(int startR, int startC, Point goal) {
		this.currentAlt = 0;
		this.currentPosR = startR;
		this.currentPosC = startC;
		this.goal = goal;
	}

	public Loon(int startR, int startC, Point goal, int alt) {
		this.currentAlt = alt;
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
	
	public int move(int steps, MovVector[][][] matrix) {
		
		//int direction = rightDecision(matrix);
		int direction = nextChoice(steps, matrix);
		
		if(direction == 1) {
			up(matrix);
		}
		
		if(direction == -1) {
			down(matrix);
		}
		
		if(direction == 0) {
			stay(matrix);
		}
		return direction;
	}
	
	public int rightDecision(MovVector[][][] matrix)  {
		
		
		Point up = getNextPosition(1, matrix);
		int dUp = (null == up) ? 100000 : Point.distance(up, goal);

		Point down = getNextPosition(-1, matrix);
		int dDown = (null == down) ? 100000 : Point.distance(down, goal);

		Point stay = getNextPosition(0, matrix);
		int dStay = (null == stay) ? 100000 : Point.distance(stay, goal);
		

		int min = Math.min(dUp, Math.min(dDown, dStay));
		// System.out.println(min);




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
			int newC = (currentPosC + vector.deltaC + 600) % c;
			
			if(newR >= r || newR < 0)  {
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
			int newC = (currentPosC + vector.deltaC + 900) % c;
			
			if(newR >= r || newR < 0)  {
				//lost loon ;/
				return null;
			}
			else {
				return new Point(newR, newC);
			}
		}
		
		if(altitudeChoice == 1 && currentAlt < maxAlt) {

			try {
				MovVector vector = matrix[currentAlt + 1][currentPosR][currentPosC];

				int newR = currentPosR + vector.deltaR;
				int newC = (currentPosC + vector.deltaC + 900) % c;

				if(newR >= r || newR < 0)  {
					/* lost loon */
					return null;
				}
				else {
					return new Point(newR, newC);
				}
			} catch (Exception e) {
				System.out.println("toto");
			}
		}
		
		return null;
	}

	public ArrayList<PositionTarget> getReachablePositions(int step, ArrayList<PositionTarget> listPositions, MovVector[][][] matrix) {
		ArrayList<PositionTarget> newList = new ArrayList<PositionTarget>();
		if (step == 1) {
			for (PositionTarget p : listPositions) {
				Point pUp = p.getLoon().getNextPosition(+1, matrix);
				if (pUp != null) {
					Loon newPointUp = new Loon(pUp.r, pUp.c, null, p.getLoon().currentAlt + 1);
					ArrayList<Integer> path = new ArrayList<Integer>(p.seq);
					path.add(+1);
					newList.add(new PositionTarget(newPointUp, path));
				}

				Point pDown = p.getLoon().getNextPosition(-1, matrix);
				if (pDown != null) {
					Loon newPointDown = new Loon(pDown.r, pDown.c, null, p.getLoon().currentAlt - 1);
					ArrayList<Integer> path = new ArrayList<Integer>(p.seq);
					path.add(-1);
					newList.add(new PositionTarget(newPointDown, path));
				}

				Point pStay = p.getLoon().getNextPosition(0, matrix);
				if (pStay != null) {
					Loon newPointStay = new Loon(pStay.r, pStay.c, null, p.getLoon().currentAlt);
					ArrayList<Integer> path = new ArrayList<Integer>(p.seq);
					path.add(0);
					newList.add(new PositionTarget(newPointStay, path));
				}
			}
			return newList;
		} else {
			newList.addAll(this.getReachablePositions(1, listPositions, matrix));
			return this.getReachablePositions(step - 1, newList, matrix);
		}
	}

	public int nextChoice(int steps, MovVector[][][] matrix) {
		ArrayList<PositionTarget> al = new ArrayList<PositionTarget>();
		al.add(new PositionTarget(this, new ArrayList<Integer>()));
		ArrayList<PositionTarget> l = this.getReachablePositions(steps, al, matrix);

		ArrayList<Integer> listDistance = new ArrayList<Integer>();
		for (PositionTarget pt : l) {
			listDistance.add(Point.distance(pt.getPoint(), this.goal));
		}
		int minIndex = listDistance.indexOf(Collections.min(listDistance)); // plusieurs égalités ?

		return l.get(minIndex).seq.get(0);
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
