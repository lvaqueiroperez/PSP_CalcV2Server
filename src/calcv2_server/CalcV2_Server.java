package calcv2_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcV2_Server {

    public static void main(String[] args) {

        ServerUI obj = new ServerUI();
        obj.setVisible(true);

    }

}
