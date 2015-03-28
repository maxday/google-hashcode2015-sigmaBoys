package sigma;

public class Loon {

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
	
}
