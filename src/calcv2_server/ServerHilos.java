package calcv2_server;
//http://www.webtutoriales.com/articulos/comunicacion-entre-un-servidor-y-multiples-clientes

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServerHilos extends Thread {

    //Creamos un nuevo socket para ir recibiendo a los clientes
    private Socket newSocket;
    private Socket socket;
    private int id;
    private OutputStream os;
    private InputStream is;

    //CONSTRUCTOR
    public ServerHilos(Socket socket, int id) {

        this.socket = socket;
        this.id = id;
        //DENTRO DEL CONSTRUCTOR, PONEMOS OS IS/OS, DE MANERA QUE CADA HILO TENDRÁ EL SUYO DIFERENCIADO
        try {
            os = socket.getOutputStream();

            is = socket.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(ServerHilos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        try {

            System.out.println("EJECUTANDO HILO CLIENTE");

            //LEEMOS (SI VEMOS QUE HAY PROBLEMAS CON LOS INTS, LEER CON BYTES)
            int op1 = is.read();
            System.out.println(op1);

            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerHilos.class.getName()).log(Level.SEVERE, null, ex);
            }

            int op2 = is.read();
            System.out.println(op2);

            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerHilos.class.getName()).log(Level.SEVERE, null, ex);
            }

            int oper = is.read();
            System.out.println(oper);

            switch (oper) {

                case 1:
                    int resultado1 = op1 + op2;
                    os.write(resultado1);
                    break;
                case 2:
                    int resultado2 = op1 - op2;
                    os.write(resultado2);
                    break;
                case 3:
                    int resultado3 = op1 * op2;
                    os.write(resultado3);
                    break;
                case 4:
                    int resultado4 = op1 / op2;
                    os.write(resultado4);
                    break;

                default:
                    System.out.println("ERROR");

            }

            //CERRAMOS SOCKET
            System.out.println("***** CERRANDO SOCKET *****");
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ServerHilos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
