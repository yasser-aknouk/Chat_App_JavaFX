package emsi.ma.clientschats;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerChat extends Thread {
    private int ClientNbr;
    int Connected =0;
    private List<Communication> ClientsConnected = new ArrayList<Communication>();

    public static void main(String[] args) {
        new ServerChat().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("Le serveur essaie de demarer ... ");
            while (true) {
                Socket s = ss.accept();
                 Connected =++ClientNbr;
                Communication comm = new Communication(s, ClientNbr);
                ClientsConnected.add(comm);
                comm.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public class Communication extends Thread{
        protected Socket s;
        private int ClientNum ;

        Communication(Socket s, int ClientNum)
        {
            this.s=s;
            this.ClientNum=ClientNum;
        }
        @Override
        public void run(){

            try {
                InputStream is = s.getInputStream();
                InputStreamReader isreader = new InputStreamReader(is);
                BufferedReader br= new BufferedReader(isreader);
                OutputStream os = s.getOutputStream();
                String IpAdd=s.getRemoteSocketAddress().toString();
                System.out.println("Le client numero :" + ClientNum + " et Son @ Ip :"+ IpAdd);
                PrintWriter pw= new PrintWriter(os,true);
                if(Connected == 1)
                {
                    pw.println("There is no one already Connected To the server! Just You  \n" +
                               "You are connected To the server as   Client " + ClientNum );
                }
                else {
                    pw.println("There are " + (Connected-1) + " user already Connected To the server \n" +
                               "You are connected To the server as   Client " + ClientNum);
                }


                pw.println("Chat Start Here ");
                while(true){
                    String request = br.readLine();
                    if(request.contains("=>"))
                    {
                        String [] userMessage =  request.split("=>");
                        if( userMessage.length==2)
                        {
                            String message= userMessage[1];
                            int numeroClient = Integer.parseInt(userMessage[0]);
                            Send(message,s,numeroClient);
                        }
                    }
                    else{
                        Send(request,s,-1);
                    }
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
            }

        }

        public void Send(String Userrequest,Socket socket,int nbr) throws IOException {
            for(Communication clients : ClientsConnected)
            {
                if(clients.s !=socket) {
                    if (clients.ClientNum == nbr || nbr == -1)
                    {
                        PrintWriter pw = new PrintWriter(clients.s.getOutputStream(), true);
                        pw.println("Client" + ClientNum + " :" + Userrequest);

                    }
//

                }
                }

            }
        }
}


