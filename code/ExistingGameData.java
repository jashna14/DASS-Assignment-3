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

    public void delete_data(Party party) {
        ArrayList<Object> dataout = (ArrayList<Object>) get_data();
        String partyDelete = "";
        for(int i = 0;i<party.getMembers().size();i++) {
            Bowler bowler = (Bowler) party.getMembers().get(i);
            partyDelete += bowler.getNickName() + " ";
        }

        for(int i = dataout.size()-1;i > -1 ;i--) {

            ArrayList<Object> partyData = (ArrayList<Object>)dataout.get(i);

            Vector members = (Vector) partyData.get(0);
            String partyName = "";
            for(int j =0;j<members.size();j++){
                Bowler bowler = (Bowler) members.get(j);
                partyName +=  bowler.getNickName() + " ";
            }

            if(partyDelete.equals(partyName)) {
                dataout.remove(i);
            }

        }
        set_data(dataout);
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
