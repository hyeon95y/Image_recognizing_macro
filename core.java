// 1. Load packages

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import org.opencv.core.*;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.lang.Math;
import java.lang.Integer;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.lang.NullPointerException;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.awt.datatransfer.*;


// 2. Core functions

class TemplateMatching {


	public Coord findcoordinate(String inFile, String templateFile)
	{
		Coord value = new Coord();
		value = findcoordinate_index(inFile, templateFile, 8000000);
		return value;
	}

	public Coord findcoordinate_index(String inFile, String templateFile, int value)
	{

		System.out.println("findcoordinate ������");
		Coord resultcoord = new Coord();
		///�ܾ����� �κ�

		boolean match = true;
        System.out.println("inprocess1");
        System.out.println(inFile);
        System.out.println(templateFile);

		try{
	        Mat img = Imgcodecs.imread(inFile);
	        Mat templ = Imgcodecs.imread(templateFile);
	        System.out.println(inFile + "�� ���� ������ ������");
	        System.out.println(templateFile);

	        Imgproc.cvtColor(img,  img, Imgproc.COLOR_RGB2GRAY);
	        Imgcodecs.imwrite(inFile, img);
	        System.out.println(inFile + "grayscale�� ��ȯ");
	        Imgproc.cvtColor(templ,  templ, Imgproc.COLOR_RGB2GRAY);
	        Imgcodecs.imwrite(templateFile, templ);
	        System.out.println(templateFile + "grayscale�� ��ȯ");

	        System.out.println("inprocess2");
	        // / Create the result matrix
	        int result_cols = img.cols() - templ.cols() + 1;
	        int result_rows = img.rows() - templ.rows() + 1;
	        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
	        System.out.println("inprocess3");
	        // / Do the Matching and Normalize
	        try{
	        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCOEFF);
	        }
	        catch (Exception e)
	        {
	        	System.out.println("image matching error");
	        }
	        System.out.println("Matrix result��" + result);
	        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
	        System.out.println("inprocess4");

	        // / Localizing the best match with minMaxLoc
	        MinMaxLocResult mmr = Core.minMaxLoc(result);

	        System.out.println(mmr.minLoc.x);
	        System.out.println(mmr.minLoc.y);
	        System.out.println(mmr.maxLoc.x);
	        System.out.println(mmr.maxLoc.y);
	        System.out.println(mmr);
	        System.out.println("mmr minval" + mmr.minVal);
	        System.out.println("mmr maxval" + mmr.maxVal);




	        if( (int) mmr.maxLoc.x == 0 || (int) mmr.maxLoc.y == 0 || (mmr.maxVal >= 0.75 && mmr.maxVal<=value) )
	        {
	        	match = false;
	        } //�Ϻθ� ��ġ�� ����..? ������ ��� �������� �׽�Ʈ�غ���!





	        ////��ǥ�Էºκ�

				resultcoord.x = (int) mmr.maxLoc.x;


				resultcoord.y = (int) mmr.maxLoc.y;

				  System.out.println("Point matchLoc��ã����ǥ��? " + resultcoord.x + " , " + resultcoord.y + " ");

		}



	        catch(Exception AWTException)
	        {

	        }




		///

		return resultcoord;
	}



	public boolean test(String inFile, String templateFile)
	{
		boolean value;
		value = this.test_index(inFile, templateFile, 8000000);
		return value;
	}


    public boolean test_index(String inFile, String templateFile, int value)
    {
    	System.out.println("test������");
    	boolean match = true;
        System.out.println("inprocess1");
        System.out.println(inFile);
        System.out.println(templateFile);

        try{
        	Mat img = Imgcodecs.imread(inFile);
	        Mat templ = Imgcodecs.imread(templateFile);
	        System.out.println(inFile + "�� ���� ������ ������");
	        System.out.println(templateFile);

	        Imgproc.cvtColor(img,  img, Imgproc.COLOR_RGB2GRAY);
	        Imgcodecs.imwrite(inFile, img);
	        System.out.println(inFile + "grayscale�� ��ȯ");
	        Imgproc.cvtColor(templ,  templ, Imgproc.COLOR_RGB2GRAY);
	        Imgcodecs.imwrite(templateFile, templ);
	        System.out.println(templateFile + "grayscale�� ��ȯ");

        System.out.println("inprocess2");
        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
        System.out.println("inprocess3");
        // / Do the Matching and Normalize
        try{
        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCOEFF);
        }
        catch (Exception e)
        {
        	System.out.println("image matching error");
        }
        System.out.println("Matrix result��" + result);
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        System.out.println("inprocess4");

        // / Localizing the best match with minMaxLoc
        MinMaxLocResult mmr = Core.minMaxLoc(result);

        System.out.println(mmr.minLoc.x);
        System.out.println(mmr.minLoc.y);
        System.out.println(mmr.maxLoc.x);
        System.out.println(mmr.maxLoc.y);
        System.out.println(mmr);
        System.out.println("mmr minval" + mmr.minVal);
        System.out.println("mmr maxval" + mmr.maxVal);
        System.out.println("�Է¹��� value ���� : " + value);
        System.out.println("0.75 >= mmr maxval Ȥ�� " + value + " < mmr mxval�̸� true");
        System.out.println("int casted mmr maxval : " + (int)mmr.maxVal );

        if( mmr.maxLoc.x == 0 || mmr.maxLoc.y == 0 || (mmr.maxVal >= 0.75 && (int)mmr.maxVal<=value))
        {
        	match = false;
        } //�Ϻθ� ��ġ�� ����..? ������ ��� �������� �׽�Ʈ�غ���!

        System.out.println("��ġ ���� : " + match); //console�� test����






        Point matchLoc;

            matchLoc = mmr.maxLoc;


        Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));

        System.out.println("Point matchLoc��ã����ǥ��? raw data" + matchLoc + "��ǥ" + matchLoc.x + " , " + matchLoc.y + " ");

        // Save the visualized detection.
        System.out.println("Writing " + new Path().current("result.jpg"));
        Imgcodecs.imwrite(new Path().current("result.jpg"), img);





        }
        catch(Exception AWTException)
        {

        }












        return match;


    }

}







class Control {

	public int find_whichone_time_index(String filename1, String filename2, int settime, int index1, int index2 )
	{
		int whichone = 0 ;
		int waitingtime = 0;

		while(waitingtime < settime && whichone ==0)
		{
			if(new TemplateMatching().test_index(this.screenshot_name(), filename1,index1 ) == true)
			{
				whichone = 1;
				break;
			}

			if(new TemplateMatching().test_index(this.screenshot_name(), filename2,index2 ) == true)
			{
				whichone = 2;
				break;
			}
			this.delay(1000);
			waitingtime++;


			if(waitingtime ==settime && whichone == 0)
			{
				int result;
				result = JOptionPane.showConfirmDialog(null, "��ư�� ã�� ���߽��ϴ�. (���Ӵ��� / ���� ó���� ����)");
				// (result ���ϰ�)
				// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION
				if(result == JOptionPane.NO_OPTION)
				{
				break;}
				if(result == JOptionPane.YES_OPTION)
				{
					waitingtime = 0;
				}
			}

		}
		return whichone;
	}


	public int find_whichone(String filename1, String filename2)
	{
		int whichone = 0 ;
		int waitingtime = 0;

		while(waitingtime <50 && whichone ==0)
		{
			if(new TemplateMatching().test_index(this.screenshot_name(), filename1,4000000 ) == true)
			{
				whichone = 1;
				break;
			}

			if(new TemplateMatching().test_index(this.screenshot_name(), filename2,4000000 ) == true)
			{
				whichone = 2;
				break;
			}
			this.delay(100);
			waitingtime++;


			if(waitingtime ==49 && whichone == 0)
			{
				int result;
				result = JOptionPane.showConfirmDialog(null, "��ư�� ã�� ���߽��ϴ�. (���Ӵ��� / ���� ó���� ����)");
				// (result ���ϰ�)
				// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION
				if(result == JOptionPane.NO_OPTION)
				{
				break;}
				if(result == JOptionPane.YES_OPTION)
				{
					waitingtime = 0;
				}
			}

		}
		return whichone;
	}



	public void waitfor(String filename)
	{
		this.waitfor_index(filename, 8000000);
	}

	public void waitfor_index(String filename, int value)
	{
		System.out.println("�̰Ž� waitfor_index");
		int totaltime = 1;
		this.screenshot();
		while(new TemplateMatching().test_index(new Path().current("data\\current.jpg"), filename, value)  == false)
		{

		//new Control().delay(100);
		DelayTime(1);
		totaltime++;
		this.screenshot();
		System.out.println("�����ð� " + totaltime);
		if(totaltime > 30)
		{
			System.out.println("��ǻ���� �ٲ���");
			int result = 0;
			result = JOptionPane.showConfirmDialog(null, "30�� �̻� ������ �����ϴ�. (���Ӵ��� / ���� ó���� ����)");
			// (result ���ϰ�)
			// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION
			if(result == JOptionPane.NO_OPTION)
			{
			break;}
			if(result == JOptionPane.YES_OPTION)
			{
				totaltime = 0;

				}
			}
		}
		}

	public void waitfor_Hometax(String filename)
	{
		this.waitfor_HomeTax_index(filename, 8000000);
	}


	public void waitfor_HomeTax_index(String filename, int value)
	{
		int totaltime = 1;
		this.screenshot();
		while(new TemplateMatching().test_index(new Path().current("data\\current.jpg"), filename, value)  == false)
		{

		DelayTime(1);
		totaltime++;
		this.screenshot();
		System.out.println("�����ð� " + totaltime);
		if(totaltime > 600)
		{
			System.out.println("��ǻ���� �ٲ���");
			int result = 0;
			result = JOptionPane.showConfirmDialog(null, "10�� �̻� ������ �����ϴ�. (���� / ���� ó���� ����)");
			// (result ���ϰ�)
			// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION
			if(result == JOptionPane.NO_OPTION)
			{
			break;}
			if(result == JOptionPane.YES_OPTION)
			{
				System.exit(1);
			}
		}
		}
	}

	 public static void DelayTime(int delaySec) {

	        Robot robot = null;
	        try {
	            robot = new Robot();
	        } catch (AWTException e) {
	        }
	        robot.delay(delaySec * 1000);

	    }


	public Coord return_resolution()
	{
		Coord temp = new Coord();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		temp.x = res.width;
		temp.y = res.height;
		return temp;
	}

	public void screenshot()
	{
		  try {

			  Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
			  System.out.println("�ػ��� : " + res.width + " x " + res.height);
	            Robot robot = new Robot();
	            BufferedImage bi=robot.createScreenCapture(new Rectangle(res.width,res.height));
	            ImageIO.write(bi, "jpg", new File(new Path().current("data\\current.jpg")));

	        } catch (AWTException |IOException e) {
	            e.printStackTrace();
	        }
	}


	public String screenshot_name()
	{
		  try {

			  Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
			  System.out.println("�ػ��� : " + res.width + " x " + res.height);
	            Robot robot = new Robot();
	            BufferedImage bi=robot.createScreenCapture(new Rectangle(res.width,res.height));
	            ImageIO.write(bi, "jpg", new File(new Path().current("data\\current.jpg")));

	        } catch (AWTException |IOException e) {
	            e.printStackTrace();
	        }
		  String result = new String(new Path().current("data\\current.jpg"));
		  return result;
	}


	public void mousemove(int x, int y) {
		// TODO Auto-generated method stub

	}


	public void mouse_move(int xcoord, int ycoord) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(xcoord, ycoord);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void mouse_move_coord(Coord data) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(data.x, data.y);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void mouse_click(int xcoord, int ycoord) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(xcoord, ycoord);
	      new Robot().mousePress(InputEvent.BUTTON1_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON1_MASK);
	      new Robot().delay(500);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void mouse_doubleclick(int xcoord, int ycoord) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(xcoord, ycoord);
	      new Robot().mousePress(InputEvent.BUTTON1_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON1_MASK);
	      new Robot().delay(50);
	      new Robot().mousePress(InputEvent.BUTTON1_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON1_MASK);
	      new Robot().delay(500);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void mouse_click_r(int xcoord, int ycoord) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(xcoord, ycoord);
	      new Robot().mousePress(InputEvent.BUTTON2_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON2_MASK);
	      new Robot().mousePress(InputEvent.BUTTON3_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON3_MASK);
	      new Robot().delay(500);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void mouse_click_coord(Coord data) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {
	      Robot robot = new Robot();
	      robot.mouseMove(data.x, data.y);
	      new Robot().mousePress(InputEvent.BUTTON1_MASK);
	      new Robot().mouseRelease(InputEvent.BUTTON1_MASK);
	      new Robot().delay(500);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }

	public void delay(int delaytime) //mousemove : Ŀ���� ������ ��ġ�� �̵�
	{
	    try
	    {

	      new Robot().delay(delaytime);
	    }
	    catch (AWTException ae)
	    {
	      ae.printStackTrace();
	    }
	  }
	/*
	public void mousePress(int buttons)
	public void mouseRelease(int buttons)
	InputEvent.BUTTON1_MASK, InputEvent.BUTTON2_MASK,InputEvent.BUTTON3_MASK
	*/
	// ���� �޼ҵ带 �̿��Ͽ� ���콺 Ŭ�� ����


	public void alttab() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public void ctrlActrlC() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(17);//CTRL
		robot.delay(100);
		robot.keyPress(65);//A
		robot.keyRelease(17);
		robot.keyRelease(65);

		robot.keyPress(17);//CTRL
		robot.delay(100);
		robot.keyPress(67);//A
		robot.keyRelease(17);
		robot.keyRelease(67);
	}

	public void ctrlV() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(17);//CTRL
		robot.delay(100);
		robot.keyPress(86);//V
		robot.keyRelease(17);
		robot.keyRelease(86);


	}


	//���ڸ� string���� ��ȯ�ؼ� �Ѱ��ָ� �̰� �״��� ���� ����!
	public void keyboard_string(String input_data)
	{
		String data = input_data;
		String convereted_data = new String();

		int keyInput[] = new int[data.length()];
		char temp[] = new char[data.length()];


		try
		{
			Robot robot = new Robot();

			for(int i=0; i<data.length(); i++) //keycode�� ��ȯ�ϴ� ����
			{
				System.out.println("���⼭�͠���");
				temp = data.substring(0).toCharArray();
				System.out.println("���䰡");
				keyInput[i] =  KeyEvent.getExtendedKeyCodeForChar(temp[i]);
				System.out.println("��ȯ�۾��� temp " + temp + "  keyIntput[i] " + keyInput[i]);
			}

			for(int i=0; i<keyInput.length; i++) // ��ȯ�� keycode �Է���
			{
				robot.keyPress(keyInput[i]);
				robot.keyRelease(keyInput[i]);
			}
		}

		catch  (AWTException ae)

		{
			ae.printStackTrace();
		}

		//�ʿ信 ���� robot.delay(int delaytime)���� ������ų�� ����!
	}





	public void keyboard_string_line(String input_data)
	{
		String data = input_data;
		String convereted_data = new String();

		int keyInput[] = new int[data.length()];
		char temp[] = new char[data.length()];


		try
		{
			Robot robot = new Robot();

			for(int i=0; i<data.length(); i++) //keycode�� ��ȯ�ϴ� ����
			{
				temp = data.substring(0).toCharArray();
				keyInput[i] =  KeyEvent.getExtendedKeyCodeForChar(temp[i]);
			}

			for(int i=0; i<keyInput.length; i++) // ��ȯ�� keycode �Է���
			{
				robot.keyPress(keyInput[i]);
				robot.keyRelease(keyInput[i]);
			}

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}

		catch  (AWTException ae)

		{
			ae.printStackTrace();
		}

		//�ʿ信 ���� robot.delay(int delaytime)���� ������ų�� ����!
	}


}













class Path{

	String current(String filename)
	{
		String upperpath = getClass().getResource("").getPath();


		String result = new String(this.conversion(upperpath) + filename);

		return result;
	}

	String conversion(String filename)
	{
		String newname = new String("");

		for(int i=0; i<filename.length(); i++)
		{
			char temp;
			temp = filename.charAt(i);


			if(temp != 47) // 47 = '/' �̴�!
			{

				newname = newname.concat(new Character(temp).toString());


			}

			if(temp == 47)
			{
				char temp2 = 92;

				if(i==0)
				{

				}
				else{
				newname = newname + "\\";}
			}


			if(temp == ' ')
			{
				break;
			}


		}



		return newname;
	}
}


class Coord{
	int x;
	int y;

	public Coord()
	{
		this.x=0;
		this.y=0;
	}

	public Coord(int data1, int data2)
	{
		this.x = data1;
		this.y = data2;
	}

	public void getx(int data)
	{
		this.x = data;
	}
}



class Getcoord{

	Coord[][] tap_coord5to10_data = new Coord[7][12];





	public Getcoord() throws IOException{

		FileInputStream in = null;
		BufferedReader inputStream = null;

		for (int m = 1; m < 7; m++) {
			for(int j=1; j<m+5; j++)
			{
	         this.tap_coord5to10_data[m][j] = new Coord();
	      //   System.out.println(m + " , " + j +" �� �ʱ�ȭ�ߴ�");

	     }
		}

		int numberoftaps = 5;

		try{
			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord5.txt")));
			String l;
			int i = 1;
			int j = 1;
			while( (l = inputStream.readLine()) != null && j<=5) {
				if(i%2 != 0)
				{
				//	System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

		//			System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
		//			System.out.println(Integer.parseInt(l));
					j++;
				//	System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}
			numberoftaps++;


			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord6.txt")));
			l = new String();
			i = 1;
			j = 1;
			while( (l = inputStream.readLine()) != null && j<=6) {
				if(i%2 != 0)
				{
				//	System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

			//		System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
			//		System.out.println(Integer.parseInt(l));
					j++;
				//	System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}
			numberoftaps++;


			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord7.txt")));
			l = new String();
			i = 1;
			j = 1;
			while( (l = inputStream.readLine()) != null && j<=7) {
				if(i%2 != 0)
				{
				//	System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
				//	System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

			//		System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
				//	System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
			//		System.out.println(Integer.parseInt(l));
					j++;
				//	System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}
			numberoftaps++;

			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord8.txt")));
			l = new String();
			i = 1;
			j = 1;
			while( (l = inputStream.readLine()) != null && j<=8) {
				if(i%2 != 0)
				{
			//		System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
			//		System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

			//		System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
			//		System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
			//		System.out.println(Integer.parseInt(l));
					j++;
			//		System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}
			numberoftaps++;

			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord9.txt")));
			l = new String();
			i = 1;
			j = 1;
			while( (l = inputStream.readLine()) != null && j<=9) {
				if(i%2 != 0)
				{
			//		System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
		//			System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

			//		System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
			//		System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
			//		System.out.println(Integer.parseInt(l));
					j++;
			//		System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}
			numberoftaps++;


			inputStream = new BufferedReader(new FileReader(new Path().current("data\\Coords\\coord10.txt")));
			l = new String();
			i = 1;
			j = 1;
			while( (l = inputStream.readLine()) != null && j<=10) {
				if(i%2 != 0)
				{
			//		System.out.println("�̶� not, i, j ��" + (numberoftaps-4) +" " + i + " " + j);
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("�״��� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));
			//		System.out.println("���� ���ڴ� �ҷ������ִ�" + Integer.parseInt(l));

					this.tap_coord5to10_data[numberoftaps-4][j].x = Integer.parseInt(l);
			//		System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " x �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].x) ;

			//		System.out.println(Integer.parseInt(l));
				}
				if(i%2 == 0)
				{
					this.tap_coord5to10_data[numberoftaps-4][j].y = Integer.parseInt(l);
			//		System.out.println("���� ������ ������� Ȯ��!" + (numberoftaps-4) + " , " + j + " y �� ��� ���� : " +tap_coord5to10_data[numberoftaps-4][j].y) ;
			//		System.out.println(Integer.parseInt(l));
					j++;
			//		System.out.println("index�� 1���� " + j + "����");
				}
				i++;

			}


		}
		finally {
			if(inputStream != null){
				inputStream.close();
			}

		}}


	public Coord[][] returndata(){

		return this.tap_coord5to10_data;
	}


}



class GetSettings{

	int[] tapnumber = new int[10];
	//int numberoftaps = 0;

	//Coord[][] tap_coord5to10 = new Coord[7][12];
	Coord IE = new Coord();





	public GetSettings() throws IOException{

		FileInputStream in = null;
		BufferedReader inputStream = null;



		try{
			inputStream = new BufferedReader(new FileReader(new Path().current("data\\settings.txt")));
			String l;
			int i = 1;
			int j = 1;
			while( (l = inputStream.readLine()) != null) {

					this.IE.x = Integer.parseInt(l);
					this.IE.y = Integer.parseInt(l);
					this.tapnumber[1] = Integer.parseInt(l);
					this.tapnumber[2] = Integer.parseInt(l);
					this.tapnumber[3] = Integer.parseInt(l);
					this.tapnumber[4] = Integer.parseInt(l);
					this.tapnumber[5] = Integer.parseInt(l);
					this.tapnumber[6] = Integer.parseInt(l);
					this.tapnumber[7] = Integer.parseInt(l);
					this.tapnumber[8] = Integer.parseInt(l);
					this.tapnumber[9] = Integer.parseInt(l);



			}

		}
		finally {
			if(inputStream != null){
				inputStream.close();
			}

		}}


	public Coord returnIE(){

		return this.IE;
	}

	public int[] returntapnumber(){

		return this.tapnumber;
	}

}
