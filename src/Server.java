import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Algorithms.*;

public class Server {

    private DatagramSocket socket;
    private int port;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;

    public Server(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket(port);
    }

    public void send(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();

        byte[] data = baos.toByteArray();
        sendPacket = new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
        socket.send(sendPacket);
    }

    public Object receive() throws IOException, ClassNotFoundException {
        byte[] data = new byte[65535];
        receivePacket = new DatagramPacket(data, data.length);
        socket.receive(receivePacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server(8000);
        System.out.println("Server Dang Chay...");
        while(true) {
            try{
                Map<Integer, String> dataNhan = (Map<Integer, String>) server.receive();

                String textInput = "";
                int khoa = 0;

                for(Map.Entry<Integer, String> data : dataNhan.entrySet()) {
                    khoa = data.getKey();
                    textInput = data.getValue();
                }


                textInput = (CaesarCipher.decrypt(textInput, khoa).toString());
                // Xu ly du lieu gui
                System.out.println("du lieu nhan :" + textInput);

                String[] a = textInput.split("");

                Map<String, Integer> map = new HashMap<>();

                List<String> checkExists = new ArrayList<>();
                for(String _char : a){
                    if(checkExists.contains(_char.toLowerCase())){
                        continue;
                    }
                    else {
                        checkExists.add(_char.toLowerCase());
                        map.put(_char, soLanKiTuXuatHien(_char, a));
                    }
                }

                server.send(map);
            }catch (Exception e){

            }
        }
    }

    private static int soLanKiTuXuatHien(String key , String[] a){
        int rs = 0;

        for(int i=0 ;i < a.length ;i++){
            if(key.equalsIgnoreCase(a[i])){
                rs++;
            }
        }
        return rs;
    }

}