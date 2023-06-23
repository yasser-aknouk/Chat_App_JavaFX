package emsi.ma.clientschats;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;




public class ConnectionController  {
    @FXML
    TextField HostID;
    @FXML
    TextField PortID;
    @FXML
    ListView ListUser;
    @FXML
    private Button connBtn;
    @FXML
    private Button sendBtn;
    @FXML
    private TextField message_txt;

    @FXML
    private ListView ListMessage;
    PrintWriter pw;
    BufferedReader br;

    @FXML
    protected void onconnectButtonClick() throws IOException {

        String host = HostID.getText();
        int port = Integer.parseInt(PortID.getText());

        Socket s = new Socket(host, port);
        InputStream is = s.getInputStream();
        InputStreamReader isreader = new InputStreamReader(is);
         br = new BufferedReader(isreader);
        OutputStream os = s.getOutputStream();
        String IpAdd = s.getRemoteSocketAddress().toString();
         pw = new PrintWriter(os, true);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String res = br.readLine();
                    Platform.runLater(() -> {
                        ListUser.getItems().add(res);


                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    protected void onCancelButtonClick() {
        HostID.clear();
        PortID.clear();
        message_txt.clear();
    }


    @FXML
    protected void onsendButtonClick() {
        String messageToSend = message_txt.getText();
           pw.println(messageToSend);

            new Thread(()-> {
                    while(true)
                    {
                        try {
                            String message = br.readLine();
                            Platform.runLater(() -> {
                                ListMessage.getItems().add(message);

                            });


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();


        }
}











