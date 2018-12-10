/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
/**
 *
 * @author Asus
 */
public class ComunicacaoClientes {
    private int TCPport;
    private ServerSocket socket;
    private boolean Ativo = false;
    static final int TIMEOUT = 10000;
    private Thread threadCliente;
    
    public ComunicacaoClientes(int TCPport, Servidor servidorMaster) {
        this.TCPport = TCPport;
    }
    
    public void Comunica(){
        Socket toClient; 
        try{
            this.socket = new ServerSocket(this.TCPport);
            this.socket.setSoTimeout(TIMEOUT);
      } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(this.socket == null){
            return;
        }
        
        this.Ativo = true;
        while(this.Ativo){
            try{
                toClient = socket.accept();
            }catch(IOException ex){
                return;
            }
            threadCliente = new Thread(new ThreadCliente(toClient,"MASTER"));
            threadCliente.setDaemon(true);
            threadCliente.start();
        }
    }
    
     public void TerminarThreads() {
        this.Ativo = false;

        // Fechar socket
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (threadCliente != null) {
            threadCliente.interrupt();
        }
    }
    
}
