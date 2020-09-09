package com.msagatov98;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет магжан ибне

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Hyper Desk Server");
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setSize(320, 240);
        jFrame.getContentPane().setBackground(Color.GRAY);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel jLabel = new JLabel("Server is \n working");

        jLabel.setForeground(Color.GREEN);
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        jLabel.setBounds(60, 60, 220, 80);

        jFrame.add(jLabel);

        try {
            ServerSocket ss = new ServerSocket(9993);
            Socket soc = ss.accept();
/*
            // установив связь и воссоздав сокет для общения с клиентом можно перейти
                // к созданию потоков ввода/вывода.
                // теперь мы можем принимать сообщения
                in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                // и отправлять
                out = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));

                String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                System.out.println(word);
                // не долго думая отвечает клиенту
                out.write(word + "\n");
                out.flush(); // выталкиваем все из буфера
*/

        } catch (Exception e) {
            e.printStackTrace();
            jLabel.setText("Server doesn't\n working");
            jLabel.setForeground(Color.RED);
        }
    }
}
