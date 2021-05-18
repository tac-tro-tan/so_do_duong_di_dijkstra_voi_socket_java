package thongdiepclientserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import thongdiepclientserver.thuattoan.Pair;

public class server1 {
	private static ServerSocket server=null;
	public static void main(String[] args) {
		try {			
			server = new ServerSocket(9999);
			System.out.println("Server: Đã khởi động máy chủ");
			Socket soc = server.accept();
			InputStream in= soc.getInputStream();
			FileOutputStream outfile = new FileOutputStream("folder_s//45.txt");			
			BufferedOutputStream diOutputStream = new BufferedOutputStream(outfile);
			byte[] buffer= new byte[1024];
			int count=in.read(buffer,0,buffer.length);
			diOutputStream.write(buffer, 0, count);
			diOutputStream.close();
			// Tạo đối tượng PrintWriter, tham số truyền vào là luồng Output của socket
			// Tham số true để PrintWrite tự động flush
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			Scanner in1 = new Scanner(soc.getInputStream());
			out.println("Server: Đã nhận file đề");
			count=0;
			int k, d=1,l=6;
			String inputLine;
			// Vòng lặp nhập xuất
			while (true){
				if(!in1.hasNextLine())
					break;
				inputLine=in1.nextLine();
				k= Integer.parseInt(inputLine);				
				if(inputLine.equalsIgnoreCase("9999"))
				{
					out.println("Server: Ngắt kết nối máy khách");
					break;
				}else {
					count++;
					switch (count) {
					case 1:
						out.println("Server:nhập đỉnh bắt đầu:");
						break;
					case 2:
						d= k;
						out.println("Server:nhập đỉnh kết thúc:");						 
						break;
					case 3:
						 l= k;
		  	  			break;
					default:
						out.println("Server: ai mà biết được");
						break;
					}
					if(count==3) {
						Pair e = thuattoan.name3(d,l);
						int Dai[]= e.getDai();
						int P[]= e.getp();
						int a= e.geta();
						int b=e.getb();
						int i= e.geti();
						int sum= e.getsum();
									    	  
		  	  			if (Dai[b] > 0 && Dai[b] < sum) {
		  	  				//out.print("Server:dinh "+(a+1)+" den dinh "+
		  	  				//			(b+1)+" co do dai la "+ Dai[b]+", quang duong la ");   
		  	  			String s="";
		  	  			while (i != a) {
		  	  				s=s+(i+1)+" ";    	        	
		  	  				i = P[i];
		  	  			}
		  	  			s=s+(a+1);   	
		  	  			StringBuffer reverse = new StringBuffer(s);
		  	  			reverse.reverse().toString();
		  	  			out.println(reverse);  			    	        	 		    	        
		  	  			} else {
		  	  				//out.println("khong co duong di tu dinh "+ (a+1)+" den dinh "+ (b+1));
		  	  				out.println(" ");
		  	  			}   
		  	  			count=0;		  	  			
					}					
				}
			}
			out.close();
			in1.close();
			server.close();

		} catch (IOException e) {
			System.out.println("Không thể tạo kết nối");
			System.exit(1);
		}
	}
}
