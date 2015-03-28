package sigma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vh186007 on 28/03/2015.
 */
public class PositionTarget {
    Point p;
    int altitude;
    ArrayList<Integer> seq;

    public PositionTarget(Point p, int altitude, ArrayList<Integer> seq) {
        this.p = p;
        this.altitude = altitude;
        this.seq = seq;
    }

    public PositionTarget(Loon loon, ArrayList<Integer> seq) {
        this.p = new Point(loon.currentPosR, loon.currentPosC);
        this.altitude = loon.currentAlt;
        this.seq = seq;
    }

    public Loon getLoon() {
        return new Loon(this.p.r, this.p.c, null, this.altitude);
    }

    public Point getPoint() {
        return new Point(this.p.r, this.p.c);
    }

    @Override
    public String toString() {
        return "PositionTarget{" +
                "p=" + p +
                ", altitude=" + altitude +
                ", seq=" + seq +
                '}';
    }
}
