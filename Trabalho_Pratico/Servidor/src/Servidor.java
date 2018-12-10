
import java.io.IOException;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Asus
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    static private Servidor servidorMaster;
    static private ThreadIniciarServidor threadIniciaServidor;
    static private Thread thread;
    static private String nome = "";
    private static  int portoUDP ;   // porto de escuta automático para comunicar com a diretoria
    private static int portoTCP ;   // porto de escuta automático para comunicar com o cliente
    
    private static ComunicacaoClientes  comunicaClientes;
   // private List<Cliente> lista_Clientes ;
    
    public Servidor(){  
    }
    
    public static void main(String[] args) {
     servidorMaster = new Servidor();
     
     threadIniciaServidor = new ThreadIniciarServidor();
     thread = new Thread(threadIniciaServidor);
     thread.setDaemon(true);
     thread.start();
    }
        
         
         
    static private class ThreadIniciarServidor implements Runnable{
  
        
         public  ThreadIniciarServidor(){
        }
    
        @Override
        public void run() {
          // Gerar porta para comunicar com a diretoria
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(0);
                portoUDP = ds.getLocalPort();
            } catch (Exception ex) {
                System.out.println("Erro ao tentar gerar porto UDP automático.");
                return;
            } finally {
                if (ds != null) {
                    ds.close();
                }
            }

            // Gerar porta para comunicar com os clientes
            ServerSocket s = null;
            try {
                s = new ServerSocket(0);
                portoTCP = s.getLocalPort();
            } catch (Exception ex) {
                System.out.println("Erro ao tentar gerar porto TCP automático.");
                return;
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            comunicaClientes = new ComunicacaoClientes(portoTCP,servidorMaster);

        }
    
    
}
    
        
   
    
}
