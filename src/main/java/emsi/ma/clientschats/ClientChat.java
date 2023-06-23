package emsi.ma.clientschats;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class ClientChat {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    @FXML
    private ListView ListMessage;

    public ClientChat(String host, int port) {
        try {
            socket = new Socket(host, port);

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String res = reader.readLine();
                    Platform.runLater(() -> {
                        ListMessage.getItems().add(res);
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public void close() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
