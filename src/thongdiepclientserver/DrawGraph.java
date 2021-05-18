package thongdiepclientserver;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
   private static final int PREF_W = 650;
   private static final int PREF_H = 650;
   private static final int LE = 30;
   private static final Color DINH_COLOR = Color.magenta;
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int DINH_BK = 12;
   private int maxDataPoints;
   private int[] q;
   private int[][] matrix;

   public DrawGraph(int maxDataPoints, int[][] matrix, int[] q) {
      this.maxDataPoints = maxDataPoints;
      this.matrix=matrix; 
      this.q=q;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      //nét đẹp
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setFont(new Font("Arial", Font.PLAIN, 16));
      int xScale = ( getWidth() - 2 * LE) / (maxDataPoints - 1);
      int yMax = ( getHeight() - 2 * LE);
      //vị trí đỉnh
      int yy=0;
      int h=0;
      List<Point> graphPoints = new ArrayList<Point>();
      int k=(maxDataPoints)/2;
      for (int i = 0; i < maxDataPoints; i++) {  	  
    	 if(i>k)h=maxDataPoints-i;else h=i;
    	 if(i%2==1) yy=30;else {
			yy=10;
			h=-h;
		}
    	 if(i==0||i==maxDataPoints-1)yy=20;
         int x1 =  i * xScale + LE;
         int y1 = (h+yy)* yMax/40 + LE;
         graphPoints.add(new Point(x1, y1));
         if(i%2==1)g2.drawString(Integer.toString(i+1), x1, y1+25);
         else g2.drawString(Integer.toString(i+1), x1, y1-15);
         setForeground(Color.RED);
      }
      //scores.get(i)=x  với scores.add(x);
      //vẽ cạnh
      Stroke oldStroke = g2.getStroke();
      //lớp định nghĩa cách vẽ
      g2.setStroke(GRAPH_STROKE);
     
      for (int i = 0; i < graphPoints.size() ; i++)
      	for (int j = 0; j < graphPoints.size(); j++) {
      		if(matrix[i][j]>0) {
      			int x1 = graphPoints.get(i).x;
      			int y1 = graphPoints.get(i).y;
      			int x2 = graphPoints.get(j).x;
      			int y2 = graphPoints.get(j).y;
      			g2.setColor(Color.yellow);
      			g2.drawLine(x1, y1, x2, y2); 
      			g2.setColor(Color.blue);     			
      			g2.drawString(Integer.toString(matrix[i][j]), (x1+x2-1)/2, (y1+y2-1)/2);
      	 }
      	}
    
      //vẽ đường
      g2.setColor(Color.black);
      int do_dai=0;
      for (int i = 0; i < q.length-1 ; i++){
        		if(matrix[q[i]][q[i+1]]>0) {
        			int x1 = graphPoints.get(q[i]).x;
        			int y1 = graphPoints.get(q[i]).y;
        			int x2 = graphPoints.get(q[i+1]).x;
        			int y2 = graphPoints.get(q[i+1]).y;
        			g2.drawLine(x1, y1, x2, y2); 
        			
        	 }
        		do_dai+=matrix[q[i]][q[i+1]];
        	}
      g2.drawString("dinh "+(q[0]+1)+" den dinh "+(q[q.length-1]+1)+" co do dai la "+do_dai, LE,LE);
      // vẽ đỉnh
      g2.setStroke(oldStroke);      
      g2.setColor(DINH_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - DINH_BK / 2;
         int y = graphPoints.get(i).y - DINH_BK / 2;
         int ovalW = DINH_BK;
         int ovalH = DINH_BK;
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   public static void createAndShowGui(int maxDataPoints,int[][] matrix, int[] q) {
      
      DrawGraph mainPanel = new DrawGraph(maxDataPoints,matrix,q);

      JFrame frame = new JFrame("DrawGraph");
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
     
      frame.pack();
      frame.setLocation(700,0);
      frame.setVisible(true);
      frame.setResizable(true);
      
   }
   public static void main(String[] args) {
	   Random random = new Random();
	      int maxDataPoints =7;//số đỉnh
	      int maxScore = 9;//giá trị cạnh
	      int matrix[][]= new int[maxDataPoints][maxDataPoints];
	      for (int i = 0; i < maxDataPoints; i++)
	    	  for (int j = 0; j < maxDataPoints; j++) {
	    		if(i==j)matrix[i][j]=0;
	    		if(j==i+1||j==i+2)matrix[i][j]=random.nextInt(maxScore)+1;
	    		matrix[j][i]=matrix[i][j];
			}
	      
	      String dString="1 3 5";
	      String[] words = dString.split("\\s");
	      int q[]= new int[words.length];
	      int k=0;
			for (String w : dString.split("\\s")) {
				   q[k]=Integer.parseInt(w)-1;
				   k++;
				  }
	      SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	             createAndShowGui(maxDataPoints,matrix,q);
	          }
	       });
   }
}