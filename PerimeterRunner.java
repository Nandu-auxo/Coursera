import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    public int getNumPoints(Shape s){
        int count=0;
        for(Point currpt:s.getPoints()){
              count++;
            }
        return count;
    }
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0; 
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public double getAverageLength(Shape s) {
        double average=(getPerimeter(s))/(getNumPoints(s));
        return average;
    }
    
    public double getLargestSide(Shape s) {
        double largest = 0;
        Point prevpt = s.getLastPoint();
        for(Point currpt : s.getPoints()){
            double currdist= prevpt.distance(currpt);
            if(currdist>largest) largest=currdist;
            prevpt = currpt;
        }
        return largest;
    }
    
    public double getLargestX(Shape s){
        double largestX = 0;
        for(Point currpt : s.getPoints()){
            double currX = currpt.getX();
            if(currX>largestX) largestX=currX;
        }
        return largestX;
    }
    
    public double getLargestPerimeterMultipleFiles(){
        double currPerimeter=0,largestPerimeter=0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerimeter = getPerimeter(s);
            if(currPerimeter>largestPerimeter) largestPerimeter=currPerimeter;    
        }
        return largestPerimeter;
    }
    
    public void testPerimeterMultipleFiles(){
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter : "+ largestPerimeter);
    }
    
    public File getFileWithLargestPerimeter(){
        File largestPerimeterFile=null; int flag=0;
        double currPerimeter=0,largestPerimeter=0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            if(flag==0){
                largestPerimeterFile = f;
                flag=1;
            }
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerimeter = getPerimeter(s);
            if(currPerimeter>largestPerimeter){
                largestPerimeter = currPerimeter;
                largestPerimeterFile = f;
            }
        }
        return largestPerimeterFile;
    }
    
    public void testFileWithLargestPerimeter(){
        File f = getFileWithLargestPerimeter();
        String fileName= f.getName();
        System.out.println("The file with largest perimeter :"+fileName);
        
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double average = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largeX= getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = "+ numPoints);
        System.out.println("Average of sides = "+ average);
        System.out.println("Longest side = "+longestSide);
        System.out.println("Largest x = "+largeX);
        //testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
        
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
