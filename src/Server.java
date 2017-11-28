import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread{
    private InetAddress ipAddress;
    private ServerSocket svs;
    private Socket socket;
    private PrintStream OutStream;
    private BufferedReader InStream;
    private JFServer jfs;
    public Server(JFServer JFS){
        jfs = JFS;
        try{
            ipAddress = InetAddress.getLocalHost();
            svs = new ServerSocket(2222);
        }catch (UnknownHostException e){
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }catch (IOException ioe){
            JOptionPane.showMessageDialog(null,"Error"+ioe.toString());
        }
    }
    public void run(){
        try{
            socket = svs.accept();
            OutStream = new PrintStream(socket.getOutputStream());
            InStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //send2client("Welcome");
            String str = "";
            while(!(str = InStream.readLine()).equals("")){
                jfs.addMessage(str);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
    public void send2client(){
        try{
            if(OutStream!=null){
              //  OutStream.println(msg);
            }else{
                JOptionPane.showMessageDialog(null,"Error:make connect first");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
        }
    }
}
