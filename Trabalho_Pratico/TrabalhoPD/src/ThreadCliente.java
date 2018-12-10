
import java.io.*;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class ThreadCliente  implements Runnable{
    private Socket socketToClient;
    private String nomeServidor;

    public ThreadCliente(Socket s, String nomeServidor) {
        this.socketToClient = s;
        this.nomeServidor = nomeServidor;
    }

    @Override
    public void run() {
        ObjectInputStream in;
        ObjectOutputStream out;
        Object objecto_recebido;
        
        
        try{
            in = new ObjectInputStream(socketToClient.getInputStream());
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            
            objecto_recebido = in.readObject();
             if ((objecto_recebido == null)) {
                if (socketToClient != null) {
                    socketToClient.close();
                }
                return;
            }
             
            /*Continuar a Tratar os pedidos dos Clientes*/
            
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } finally {
            if (socketToClient != null) {
                try {
                    socketToClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
       
    }
}
