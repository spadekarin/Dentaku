import java.util.Scanner;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.collections.*;
import java.util.*;
import java.text.*;

public class Dentaku extends Application{
	private TextField tf;
	private Button[][] bt=new Button[4][5];
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage stage)throws IOException{
		//�e�L�X�g�t�B�[���h
		tf=new TextField();
		tf.setEditable(false);
		tf.setMaxWidth(380);
		tf.setFont(Font.font("MonoSpace",40));
		tf.setAlignment(Pos.CENTER_RIGHT);
		
		//�{�^��
		String[][] bt_str={{"CE","C","BS","/"},{"7","8","9","*"},{"4","5","6","-"},{"1","2","3","+"},{"�}","0",".","="}};
		for(int i=0;i<4;i++){
			for(int j=0;j<5;j++){
				bt[i][j]=new Button(bt_str[j][i]);
				bt[i][j].setPrefWidth(95);
				bt[i][j].setPrefHeight(95);
				bt[i][j].setFont(Font.font("MonoSpace",30));
				bt[i][j].setOnAction(new ButtonEventHandler());
			}
		}
		
		//�O���b�h�y�C��
		GridPane gp=new GridPane();
		gp.setHgap(2);
		gp.setVgap(2);
		for(int i=0;i<4;i++){
			for(int j=0;j<5;j++){
				gp.add(bt[i][j],i,j);
			}
		}
		gp.setAlignment(Pos.CENTER);
		
		
		//�{�[�_�[�y�C��
		BorderPane bp=new BorderPane();
		bp.setTop(tf);
		bp.setAlignment(tf,Pos.CENTER);
		bp.setCenter(gp);
		
		
		Scene sc=new Scene(bp,400,600);
		stage.setScene(sc);
		stage.setTitle("�d��");
		stage.show();
		
		
		
	}
	
	
	
	
	class ButtonEventHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			
			//�{�^���̕������擾
			String in=((Button)e.getSource()).getText();
			//�e�L�X�g�t�B�[���h�̕�������擾
			StringBuffer stb=new StringBuffer(tf.getText());
			
			
			
			if(in != "="&&in != "CE"&&in != "C"&&in != "BS"&&in != "�}"){
				//�e�L�X�g�t�B�[���h�̕�����̖����Ƀ{�^���̕�����ǉ�
				stb.append(in);
				
				//�e�L�X�g�t�B�[���h�̕�������X�V
				String str=new String(stb.toString());
				tf.setText(str);
			}else if(in == "="){
				//"="�����͂����ƌv�Z
				String str=new String(stb.toString());
				
				String regex="[+\\-]?[0-9]+\\.?[0-9]*[+\\-\\*/]{1}[0-9]+\\.?[0-9]*";
				if(str.matches(regex)){
					String regex2="(?=[+\\-\\*/])";
					String[] operands=str.toString().split(regex2);
					double op1=Double.parseDouble(operands[0]);
					//substring()�w��ʒu����n�܂镶�����C�ӂ̒��������؂�o��
					double op2=Double.parseDouble(operands[1].substring(1));//�L��������
					
					
					
					double result=0;
					char kigou=operands[1].charAt(0);
					//2���ڂ̋L���ŕ���
					if(kigou=='+'){
						result=op1+op2;
					}else if(kigou=='-'){
						result=op1-op2;
					}else if(kigou=='*'){
						result=op1*op2;
					}else if(kigou=='/'){
						result=op1/op2;
					}
					
					//double�^�̐��l�̕�����ւ̕ϊ�
					tf.setText(String.valueOf(result));
				}
			}else if(in == "CE"||in == "C"){
				String str="";
				tf.setText(str);
			}else if(in == "BS"){
				stb.deleteCharAt(stb.length() - 1);
				
				//�e�L�X�g�t�B�[���h�̕�������X�V
				String str=new String(stb.toString());
				tf.setText(str);
			}else if(in == "�}"){
				String str=new String(stb.toString());
				char sento=str.charAt(0);
				if(sento=='0'||sento=='1'||sento=='2'||sento=='3'||sento=='4'||sento=='5'||sento=='6'||sento=='7'||sento=='8'||sento=='9'){
					stb.insert(0,'-');
				}
				str=stb.toString();
				tf.setText(str);
			}
			
			
		}
	}
			
}
