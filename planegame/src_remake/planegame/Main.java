package planegame;

import java.awt.Image;

import javax.swing.*;

import restart.GamePanel;

public class Main extends JFrame {	
	private static ImageIcon background = new ImageIcon("image/myBackGround.jpg");
	
	public static void main(String args[]) {
		
		GamePanel g = new GamePanel("1944");
		g.startGame();
	}
}


/* 참고 자료
 * https://binghedev.tistory.com/56
 * http://cris.joongbu.ac.kr/course/java/api/java/awt/Graphics.html
 * 
 */
/*
네, 맞습니다. MVC 패턴은 Model, View, Controller의 세 가지 구성 요소로 이루어진 아키텍처 패턴입니다. 각 요소의 역할은 다음과 같습니다:

1. Model: 데이터와 비즈니스 로직을 처리합니다. 이는 객체들의 상태(예: 이미지 변수)를 저장하는 것을 포함하며, 
데이터의 변경 사항을 View와 Controller에 알립니다.

2. View: 사용자에게 보여지는 UI 부분을 담당합니다. 
이는 화면에 출력되는 코드를 작성하는 것을 포함하며, 
사용자로부터의 입력을 Controller에 전달합니다.

3. Controller: 사용자의 입력을 받아 Model을 업데이트하고, 
그 결과를 View에 반영합니다. 
이는 사용자의 신호를 입력받아 객체를 움직이는 것을 포함합니다.

그러니까, 여러분이 요약하신 내용은 MVC 패턴의 기본 개념을 잘 설명하고 있습니다. 하지만 MVC 패턴은 이보다 더 복잡하고 유연한 패턴이므로,
실제로 MVC 패턴을 사용할 때는 각 요소의 역할을 더 세분화하거나 확장할 수 있습니다.

 */




