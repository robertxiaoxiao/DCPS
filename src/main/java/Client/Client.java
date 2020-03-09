package Client;

import java.io.*;
import java.net.Socket;

public class Client {

    Socket client;
    String host = "127.0.0.1";
    static int port = 12380;

    public Client() throws IOException {
        client = new Socket(host, port);
    }

    public void Start() throws IOException {
        Request("");
        System.out.println(getRemoteCode());
    }

    //获取输出流，向服务器端发送信息
    public void Request(String request) throws IOException {
        OutputStream outputStream = client.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(outputStream); //将输出流包装为打印流
        pw.write("用户名：admin;密码：123");
        pw.flush();
    }

    //获取输入流，读取服务器端的响应
    public String getRemoteCode() throws IOException {
        InputStream inputStream = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String info = null;
        StringBuffer sb = new StringBuffer();
        while ((info = br.readLine()) != null) {
            System.out.println(info);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        /*Client client = new Client();
        client.Start();*/
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            //获取输出流，向服务器端发送信息
            OutputStream outputStream = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(outputStream); //将输出流包装为打印流
            pw.write("用户名：admin;密码：123");
            pw.flush();
            socket.shutdownOutput();

            //获取输入流，读取服务器端的响应
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务器说：" + info);
            }
            socket.shutdownInput();

            //关闭资源
            br.close();
            inputStream.close();
            pw.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
