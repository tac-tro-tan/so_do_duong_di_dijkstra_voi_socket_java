package thongdiepclientserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class client2 {
	static void ketnoi(JFrame f,String IP,int port,String p4,String s){
		Socket inComing = null; 
		PrintWriter out = null; 
		Scanner in = null; 
		try {			
			inComing = new Socket(IP,port);			
			System.out.println( "This is client side. Coded by Bui Thanh Lam. " + 
					"Client is connected to socket server!");
			File file= new File("folder_c//"+p4);
			if(file.exists()) {
				OutputStream out1= inComing.getOutputStream();
				FileInputStream insend= new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int count;
				while ((count = insend.read(buffer))>=0) {
					out1.write(buffer,0,count);	
				}
				insend.close();
			}
			//file-> ma trận
			int x[]= new int[1000];
			int q1[]= new int[1000];
			 x=thuattoan.doc_file("folder_c//"+p4);
			 int maxDataPoints=x[0];
			 int matrix[][]= new int[maxDataPoints][maxDataPoints];
				for (int i = 0; i <maxDataPoints; i++)
					for (int j = 0; j < maxDataPoints; j++) 
					{
						matrix[i][j]=x[1+i*maxDataPoints+j];                        
					}
			DrawGraph.createAndShowGui(maxDataPoints,matrix,q1);
			
			System.out.println("Client: đã gửi file đề");			
			out = new PrintWriter(inComing.getOutputStream(), true);
			in = new Scanner(inComing.getInputStream());									
			System.out.println(in.nextLine());			
			String userInput="3";         
			int count=0;	
			//1 vòng lớn:server hỏi 2 lần,client trả lời 2 lần  rồi server đưa đáp án
			while (!userInput.equalsIgnoreCase("9999")) {
				count++;	        				
				if(count%3==1) {
					int n = JOptionPane.showConfirmDialog(f,"bạn muốn bắt đầu?","Question",
							JOptionPane.YES_NO_OPTION);
					
				    if(n == JOptionPane.YES_OPTION){
				    	JOptionPane.showMessageDialog(f, "ok bro!!!");
				    }
				    else {
				    	userInput="9999";
				    	System.exit(0);
				    }
				}
				else {	
					System.out.print("Client: ");
					userInput=JOptionPane.showInputDialog(f,"nhập đỉnh:");
					System.out.println( userInput);
				}
				out.println(userInput);
				s=in.nextLine()+"\n";
				if(count%3==0) {
					String[] words = s.split("\\s");
					int q[]= new int[words.length];
					for (int i = 0; i < words.length; i++) {
						q[i]=Integer.parseInt(words[i])-1;
					}					
					DrawGraph.createAndShowGui(maxDataPoints,matrix,q);
				}
				System.out.print(s);
			}
			out.close();
			in.close();
			inComing.close();
		} catch (IOException e1) {
			System.err.println("Không thể kết nối máy chủ");
			System.exit(1);
		}	
    	return;
	}
}
