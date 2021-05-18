package thongdiepclientserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client1 {
	public static void main(String[] args) {

		Socket inComing = null; //Đối tượng socket
		PrintWriter out = null; //Đối tượng gửi dữ liệu qua socket
		Scanner in = null; //Đối tượng nhận dữ liệu từ socket

		try {			
			inComing = new Socket("localhost",9999);
			System.out.println("This is client side. Coded by Bui Thanh Lam.\n" + 
					"Client is connected to socket server!");

			File file= new File("folder_c//input.txt");
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
			System.out.println("Client: đã gửi file đề");
			// Tạo đối tượng PrintWriter, tham số truyền vào là luồng Output của socket
			// Tham số true để PrintWrite tự động flush
			out = new PrintWriter(inComing.getOutputStream(), true);
			in = new Scanner(inComing.getInputStream());						
			// Hiển thị thông báo của Server
			System.out.println(in.nextLine());
			// Vòng lặp nhập xuất
			String userInput=" ";
			int count=0;
			// Tạo đối tượng Scanner đọc thông tin từ bàn phím
			Scanner sc = new Scanner(System.in);
			while (!userInput.equalsIgnoreCase("9999")) {
				count++;
				
				if(count%3==1)userInput="0";
				else {System.out.print("Client: ");
					userInput = sc.nextLine();}
				out.println(userInput);
				System.out.println(in.nextLine());
			}
			out.close();
			in.close();
			inComing.close();
			sc.close();
		} catch (IOException e) {
			System.err.println("Không thể kết nối máy chủ");
			System.exit(1);
		}
	}
}
