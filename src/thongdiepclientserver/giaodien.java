package thongdiepclientserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class giaodien extends Frame implements ActionListener{
	 private static final int PREF_W = 370;
	 private static final int PREF_H = 600;
	 private static final int LE = 10;
	 private static final int BUTTON = 50;
	 private static final int BUTTON_H = 30;
	 private static final int TEXTF = 90;
	 static String s,p4,IP;
	 static TextArea area;
	 final JFileChooser  fileDialog = new JFileChooser
			 (".\\folder_c");
	 JFrame f;
	 static int port;
	 Button l,p,k,i,r;
	 JRadioButton radiol,radior;
	 TextField textFieldp,textFieldl,textFieldi,textFieldr;
	 
	 giaodien() {
		 	f = new JFrame("client2");
		 	// file ma trận trong số
		 	l = new Button("file");
	        l.setBounds(LE, BUTTON_H*4+LE*5, BUTTON, BUTTON_H);
	        l.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	link();
	            }
	        });
	        textFieldl = new TextField();
	        textFieldl.setBounds(LE, BUTTON_H*2+LE*3, TEXTF, BUTTON_H); 
	      //random
	        textFieldr = new TextField();
	        textFieldr.setBounds(LE, BUTTON_H*3+LE*4, TEXTF, BUTTON_H);
	        //IP
	        i = new Button("IP");
	        i.setBounds(LE, LE, BUTTON, BUTTON_H);
	        i.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	IP();
	            }
	        });
	        textFieldi = new TextField("localhost");
	        textFieldi.setBounds(LE*2+BUTTON, LE, TEXTF, BUTTON_H); 	        
		 	// cổng kết nối
		 	p = new Button("port");
	        p.setBounds(LE, BUTTON_H+LE*2, BUTTON, BUTTON_H);
	        p.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	port();
	            }
	        });
	        textFieldp = new TextField("9999");
	        textFieldp.setBounds(LE*2+BUTTON, BUTTON_H+LE*2, TEXTF, BUTTON_H);
	        //kết nối	        
	        k = new Button("connect");
	        k.setBounds(LE*3+BUTTON*2, BUTTON_H*4+LE*5, BUTTON, BUTTON_H);
	        k.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	client2.ketnoi(f,IP,port,p4,s);
	            }
	        });	         
	        //radio button
	        radiol=new JRadioButton("đồ thị có sẵn");
	        radior=new JRadioButton("đồ thị random");
	        radiol.setBounds(2*LE+TEXTF, BUTTON_H*2+LE*3, TEXTF*2, BUTTON_H);
	        radior.setBounds(2*LE+TEXTF, BUTTON_H*3+LE*4, TEXTF*2, BUTTON_H);
	        ButtonGroup bg = new ButtonGroup();
	        bg.add(radiol);
	        bg.add(radior);	 
	        //area
			area= new TextArea();
	        area.setBounds(LE, BUTTON_H*5+LE*6, TEXTF*3+BUTTON, TEXTF*3+BUTTON);
	        area.setBackground(Color.pink);  
	        area.setForeground(Color.DARK_GRAY); 
	        
	        f.pack();
	        f.add(area);
	        f.add(l);
	        f.add(textFieldl);
	        f.add(textFieldr);
	        f.add(p);
	        f.add(textFieldp);
	        f.add(i);
	        f.add(textFieldi);	        
	        f.add(k);
	        f.add(radiol);
	        f.add(radior);
	        	        
	        f.setSize(PREF_W, PREF_H);// thiết lập kích thước cho của sổ
	        f.setLayout(null);// không sử dụng trình quản lý bố cục
	        f.setVisible(true);// hiển thị cửa sổ
	        f.setLocation(100,50);
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	}
	
	public static void main(String[] args) {
		//nghe bảo chạy giao diện thế này cho chương trình an toàn
		SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	        	  new giaodien();
	          }
	       });                    
    }
	
	public static void append(TextArea area, String newText){
        area.setText(area.getText() + newText);
	}	
	private void port() {
		String p1 = textFieldp.getText();
    	port= Integer.parseInt(p1);
    	append(area, "port: "+p1+"\n"); 
    	return;
	}
	
	private void IP() {
		String p2 = textFieldi.getText();
    	IP= p2;
    	append(area, "IP: "+p2+"\n"); 
    	return;
	}
	
	private void link()  {
		if (radiol.isSelected()) {
			FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt files", "txt"); 
			fileDialog.setFileFilter(restrict); 
			int returnVal = fileDialog.showOpenDialog(f);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	           File file = fileDialog.getSelectedFile();
	           textFieldl.setText(file.getName());
	           p4= textFieldl.getText();
	        }
	        else{
	           textFieldl.setText("Open file, please..." );           
	        }
	        return;
        }
        if (radior.isSelected()) {
        	p4= "input.txt";
        	Random random = new Random();
  	      	int maxDataPoints =Integer.parseInt(textFieldr.getText());//số đỉnh
  	      	int maxScore = 9;//giá trị cạnh
  	      	int matrix[][]= new int[maxDataPoints][maxDataPoints];
  	      	for (int i = 0; i < maxDataPoints; i++)
  	    	  for (int j = 0; j < maxDataPoints; j++) {
  	    		if(i==j)matrix[i][j]=0;
  	    		if(j==i+1||j==i+2)matrix[i][j]=random.nextInt(maxScore)+1;
  	    		matrix[j][i]=matrix[i][j];  	    	
  			}
  	      File file1= new File("folder_c//"+p4);
  	      if(file1.exists()) {
  	    	try {
  	    	 OutputStream outputStream = new FileOutputStream(file1);
  	    	 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
  	    	 outputStreamWriter.write(maxDataPoints+"\n");
  	    	 append(area,maxDataPoints+"\n");
  	    	 for (int i = 0; i < maxDataPoints; i++) {
  	    	 	for (int j = 0; j < maxDataPoints; j++) {  	    		 	  	  	            
					outputStreamWriter.write(matrix[i][j]+" ");
					append(area, matrix[i][j]+" ");
  	    	 	}
  	    	 	outputStreamWriter.write("\n"); 
  	    	 	append(area,"\n");
  	    	 }
  	    	 outputStreamWriter.close();
  	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	      }
  	      return;
        }		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}