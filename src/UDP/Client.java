package UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private int port;

    public Client(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket();
    }

    public void send(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);

        byte[] data = baos.toByteArray();
        sendPacket = new DatagramPacket(data,data.length, InetAddress.getByName("localhost"),port);
        socket.send(sendPacket);

    }

    public Object receive() throws IOException,ClassNotFoundException {
        byte[] data = new byte[65535];

        receivePacket = new DatagramPacket(data, data.length);
        socket.receive(receivePacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }


}
