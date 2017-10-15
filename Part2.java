
public class Part2 {
    public int howMany(String stringa,String stringb){
        int count = 0;
        int index = stringb.indexOf(stringa);
        if(index!= -1) count++;
        while(index!=-1){
            index = stringb.indexOf(stringa,index);
            if(index!=-1) count++;
        }
        return count;
    }
    
    public void testHowMany(){
        int count = howMany("AA","ATAAAA");
        System.out.println("count :"+count);
    }
}
