import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class NetworkIO implements Serializable{
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public NetworkIO(Socket s) throws IOException{

        socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public NetworkIO(Socket s, ObjectInputStream Ois, ObjectOutputStream Oos) throws IOException {

        socket = s;
        oos = Oos;
        ois = Ois;
    }

    public NetworkIO(String ip,int port) throws IOException{

        socket = new Socket(ip, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void write(Object object){
        try {

            oos.writeObject(object);
            oos.flush(); oos.reset();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeString(String commandLine){
        try {

            oos.writeObject(commandLine);
            oos.flush(); oos.reset();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object read(){

        Object object = null;
        try {

            object = ois.readObject();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    public Socket getSocket() { return socket; }
    public void closeSocket() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}