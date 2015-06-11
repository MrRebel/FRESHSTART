import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class directionCalculate {
	public static String finish;
	public static String setChange(int gx, int gy, String gDirection, String[] pDirections, int tx, int ty){
		double[] counters = {
				10000,10000,10000,10000
		};
		List <String> temp = new ArrayList<String>(Arrays.asList(pDirections));
		String[] pDirectionsf =  pDirections;
		pDirectionsf = pDirections;
		finish = "";
		int[] posnew = {
				(gx *20) -10 ,(gy *20) -10 
		};
		if(Positions.board[gy][gx] == 4){
			if((gDirection.equals("up")) || (gDirection.equals("down"))){
				if (Arrays.asList(pDirections).contains("left")){
					return "left";
				}else{
					return "right";
				}				
			}
			else if((gDirection.equals("left")) || (gDirection.equals("right"))){
				if (Arrays.asList(pDirections).contains("up")){
					return "up";
				}else{
					return "down";
				}
			}
		}else if (Positions.board[gy][gx] == 3 && ((gDirection.equals("left") || (gDirection.equals("right"))))){
			return gDirection;
		}else if (Positions.board[gy][gx] == 3 || Positions.board[gy][gx] == 2){
			for (int i = 0; i< pDirections.length; i++){
				int gyt = gy;
				int gxt = gx;
				if(pDirections[i].equals("up")&& !(gDirection.equals("down"))){
					gyt--;
					counters[0] = hyp(gx-tx,gyt-ty);
				}
				else if(pDirections[i].equals("left")&& !(gDirection.equals("right"))){
					gxt--;
					counters[1] = hyp(gx-tx,gyt-ty);
				}
				else if(pDirections[i].equals("down")&& !(gDirection.equals("up"))){
					gyt++;
					counters[2] = hyp(gx-tx,gyt-ty);
				}
				else if(pDirections[i].equals("right")&& !(gDirection.equals("left"))){
					gxt++;
					counters[3] = hyp(gxt-tx,gy-ty);
				}
			}
			int smalli = 0;
			double small = counters[0];
			for (int i = 1; i < 4; i++){
				if (counters[i] < small){
					small = counters[i];
					smalli = i;
				}
			}
			if (smalli == 0){
				return "up";
			}
			else if (smalli == 1){
				return "left";
			}
			else if (smalli == 2){
				return "down";
			}
			else if (smalli == 3){
				return "right";
			}
		}else if(Positions.board[gy][gx] == 5){
			if (gDirection.equals("up")){
				return "down";
			}
			if (gDirection.equals("down")){
				return "up";
			}
		}
		return gDirection;
	}
	public static double hyp(double distx, double disty){
		return Math.sqrt((Math.pow(distx,2))+(Math.pow(disty,2)));
	}
}