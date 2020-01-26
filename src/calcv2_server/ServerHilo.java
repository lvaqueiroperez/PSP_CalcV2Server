package calcv2_server;

//CLASE CON MÉTODOS STATIC A LA QUE ACCEDERÁ EL CÓDIGO DE LA UI PARA TRABAJAR00
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHilo {

    //Hacemos atributos para poder usarlos en cualquier método
    //no deja crear objeto ServerSocket??
    public static void conexion() {

        try {
            System.out.println("***** Creando socket servidor calculadora *****");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("***** Realizando el bind *****");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            System.out.println("****** Aceptando conexiones ******");

            Socket newSocket = serverSocket.accept();

            //EN CUANTO SE RECIBE UNA CONEXÓN:
            System.out.println("****** Conexion recibida ******");

            //DEVUELVE UN INPUT/OUTPUT STREAM PARA LEER/ESCRIBIR BYTES !!!
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            //UTILIZAMOS EL INPUT STREAM PARA IR LEYENDO LOS MENSAJES EN BYTE
            //ALMACENAMOS LA OPERACIÓN EN UNA VARIABLE INT
            int operacion = is.read();

            System.out.println("OPERACIÓN: " + operacion);

            //OPERADOR 1
            int operador1 = is.read();
            System.out.println("OPERADOR 1: " + operador1);

            //OPERADOR 2
            int operador2 = is.read();
            System.out.println("OPERADOR 2: " + operador2);

            //COMO PARECE EL SWITCH DE JAVA NO FUNCIONA BIEN COMPARANDO STRINGS, 
            //IDENTIFICAMOS CADA OPERACIÓN CON UN NÚMERO
            int result = 0;

            switch (operacion) {

                case 1:
                    result = operador1 + operador2;
                    os.write(result);
                    System.out.println("***** Resultado enviado *****");
                    break;
                case 2:
                    result = operador1 - operador2;
                    os.write(result);
                    System.out.println("***** Resultado enviado *****");
                    break;
                case 3:
                    result = operador1 * operador2;
                    os.write(result);
                    System.out.println("***** Resultado enviado *****");
                    break;
                case 4:
                    result = operador1 / operador2;
                    os.write(result);
                    System.out.println("***** Resultado enviado *****");
                    break;

                default:
                    System.out.println("ATENCIÓN: TIPO DE OPERACIÓN INCORRECTA");

            }
            System.out.println("***** Cerrando el nuevo socket *****");

            newSocket.close();

            //BUCLE QUE PERMITA ESCOGER SI QUEREMOS SEGUIR O NO?
            System.out.println("***** Cerrando el socket servidor *****");

            serverSocket.close();

            System.out.println("***** Terminado *****");

        } catch (IOException e) {
        }

    }

    public static void desconexion() {

    }

}
