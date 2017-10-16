import java.util.Random;
public class DiceRolling {
    public void roll(int roll){
        Random random = new Random();
        int[] counts = new int[6];
        for(int i=0;i<roll;i++){
            int num = random.nextInt(6)+1;
            counts[num-1]++;
        }
        for(int i=0;i<6;i++){
            System.out.println((i+1) + ":" + counts[i]);
        }
    }
}
