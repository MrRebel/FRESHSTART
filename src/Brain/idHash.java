package Brain;

import java.util.HashMap;
import java.util.Map;

public class idHash {
	public HashMap<Integer, double[]> enemyId = ;
	
	public double[] eInfo(int id){
		return (double[]) enemyId.get(id);
	}
	
}
