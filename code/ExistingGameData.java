import java.io.*;
import java.util.*;

public class ExistingGameData implements Serializable{


    public Object get_data()
    {
        ArrayList<Object> dataout = new ArrayList<Object>();

        try {
            FileInputStream fileIn  = new FileInputStream("data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dataout = (ArrayList<Object>)in.readObject();
            in.close();
            fileIn.close();

            return dataout;
        } catch (IOException i) {
            i.printStackTrace();
            return 0;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return 0;
        }
    }

    public void set_data(ArrayList<Object> Object) {

        try {
            FileOutputStream fileOut = new FileOutputStream("data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Object);
            out.close();
            fileOut.close();
            System.out.println("data saved");
        } catch(IOException i) {
            i.printStackTrace();
        }

    }

}
