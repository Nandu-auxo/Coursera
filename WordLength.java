
import edu.duke.*;
public class WordLength {
    public void countWordLengths(FileResource fr,int[] count){
        for(String word : fr.words()){
            int length = word.length();
            if(length == 1 && !Character.isLetter(word.charAt(0))) continue;
            if(!Character.isLetter(word.charAt(0))) length--;
            if(!Character.isLetter(word.charAt(word.length()-1))) length--; 
            System.out.println(length +" ");
            if(length<count.length)
                count[length] = count[length]+1;
            else count[count.length - 1]++;
        }
        for(int i=1;i<40;i++){
            System.out.println(i + " : " + count[i]);
        }
    }
    
    public int indexOfMax(int[] values){
        int max= values[0],maxIndex=0;
        for(int i=1;i<values.length;i++){
            if(max<values[i]) {
                max=values[i];
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    public  void testCountWordLengths(){
        FileResource fr = new FileResource("manywords.txt");
        int[] count = new int[40];
        countWordLengths(fr,count);
        int maxIndex = indexOfMax(count);
        System.out.println("Max index : "+ maxIndex);
    }

}
