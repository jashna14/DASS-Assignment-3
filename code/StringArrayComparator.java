import java.util.Comparator;

public class StringArrayComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] first, String[] second){
        if(first!=null && second !=null){ return (Integer.parseInt(second[2]) > Integer.parseInt(first[2]))? 1:-1;}
        return 0;
    }
}
