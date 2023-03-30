import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MountPoint {
    private Socket socket = null;
    private DataOutputStream dataOutputStream = null;
    private DataInputStream dataInputStream = null;
    private String name;
    public String getName(){return this.name;}
    public final static String BYE = "bye";
    public final static String SEP = "`";

    public boolean isConnected(){return socket!=null;}

    public MountPoint(Socket socket) throws IOException {
        this.socket=socket;
        this.dataInputStream=new DataInputStream(socket.getInputStream());
        this.dataOutputStream=new DataOutputStream(socket.getOutputStream());
        this.name = dataInputStream.readUTF();
    }

    public void write(String msg) throws IOException {
        String[] vals = msg.split(SEP);
        if(vals[0].trim().equals(this.name)) return;
        dataOutputStream.writeUTF(vals[0]+":"+vals[1]);
    }

    public String read() throws IOException {
        String msg = dataInputStream.readUTF();
        if(msg.equals(BYE)){
            socket.close();
            socket=null;
            return BYE;
        }
        return this.name+SEP+msg;
    }
}
