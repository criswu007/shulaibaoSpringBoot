package com.example.socket.rpc.rpc01;

import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket sc = new Socket("127.0.0.1", 8088);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeInt(123);

        sc.getOutputStream().write(byteArrayOutputStream.toByteArray());
        sc.getOutputStream().flush();

        //重要
        DataInputStream dataInputStream = new DataInputStream(sc.getInputStream());
        int id = dataInputStream.readInt();
        String name = dataInputStream.readUTF();

        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        dataOutputStream.close();
        sc.close();
    }
}
