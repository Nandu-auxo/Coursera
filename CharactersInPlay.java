import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public CharactersInPlay(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        person = person.toLowerCase();
        int index = myWords.indexOf(person);
        if(index == -1){
            myWords.add(person);
            myFreqs.add(1);
        }
        else{
            int num = myFreqs.get(index);
            myFreqs.set(index,num+1);
        }
    }
    
    public void findAllCharacters(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource("errors.txt");
        for(String line : fr.lines()){
            int index = line.indexOf(".");
            if(index == -1) continue;
            String person = line.substring(0,index);
            update(person);
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        System.out.println("The characters occured are \n");
        for(int i = 0; i < myWords.size(); i++){
            if(myFreqs.get(i)>=num1 && myFreqs.get(i)<=num2)
                System.out.println(myWords.get(i) + " occured "+ myFreqs.get(i)+"times");
        }
    }
    
    public void tester(){
        findAllCharacters();
        System.out.println("The characters occured are \n");
        for(int i = 0; i < myWords.size(); i++){
            if(myFreqs.get(i)>3)
                System.out.println(myWords.get(i) + " occured "+ myFreqs.get(i)+"times");
        }
        charactersWithNumParts(10,15);
    }
}
