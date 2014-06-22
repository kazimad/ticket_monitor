package com.example.ticket_monitor.app;

import android.os.AsyncTask;
import android.util.Log;

import com.example.electronicqueue.server.MyBundle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Олег on 19.06.2014.
 */
public class GetMonitorViewAsyncTask extends AsyncTask<Void, Void, Void> {

    public static final String IP = "10.0.0.107";
    public static final int PORT = 8080;
    String response = "";
    Socket socket;
    public static final String MON = "mon";
    private int numberTicket;
    private int numberWindow;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;
    public ArrayList<MyBundle> list;

    @Override
    protected Void doInBackground(Void... params) {
        InetAddress serverAddr = null;

        try {
            serverAddr = InetAddress.getByName(IP);
            socket = new Socket(serverAddr, PORT);
            Log.d(MainActivity.mylog, "socket is");


            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(MON);
            objectOutputStream.flush();
            Log.d(MainActivity.mylog, "print MON ");


            if (objectOutputStream != null) {
                Log.d(MainActivity.mylog, "OutputStream!=null");
            }


            objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {

                Object object = objectInputStream.readObject();
                list = (ArrayList<MyBundle>) object;

//                MyBundle myBundle = (MyBundle) object;
                for (MyBundle myBundle:  list) {

                    numberWindow = myBundle.getNumberWindow();
                    numberTicket = myBundle.getNumberTicket();
                }
                switch (numberWindow) {
                    case 1:
                        MainActivity.ticket1.setText(numberTicket);
                        break;
                    case 2:
                        MainActivity.ticket2.setText(numberTicket);
                        break;
                    case 3:
                        MainActivity.ticket3.setText(numberTicket);
                        break;
                    case 4:
                        MainActivity.ticket4.setText(numberTicket);
                        break;
                    case 5:
                        MainActivity.ticket5.setText(numberTicket);
                        break;
                }
            }

//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
//            Log.d(MainActivity.mylog, "after byteArrayOutputStream ");
//            byte[] buffer = new byte[1024];
//            Log.d(MainActivity.mylog, "after buffer ");
//
//
//            InputStream inputStream = socket.getInputStream();
//            Log.d(MainActivity.mylog, "inputStream");
//            if (inputStream != null) {
//                Log.d(MainActivity.mylog, "inputStream != null");
//            }
//            if (inputStream == null) {
//                Log.d(MainActivity.mylog, "inputStream == null");
//            }
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer, 0, bytesRead);
//                response = byteArrayOutputStream.toString();
//                String TOLOG_response = response;
//                Log.d(MainActivity.mylog, "response " + TOLOG_response);
//            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
//    } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (OptionalDataException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        }
    }
}