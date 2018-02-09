

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Timer extends VBox {
	//计时器
	private Timeline timeline;
	
	//显示时间用的标签
	private Label label;
	
	//时间变量
	private int duration;

	public Timer() {
		this.setPrefWidth(400);
		this.setPrefHeight(100);
		timeline = new Timeline();
		label = new Label();
		label.setFont(new Font(40));
		label.setTextFill(Color.RED);
		
		//时间控制器绑定
		EventHandler<ActionEvent> eventHandler = e -> {
			if(Main.hasGame && !Main.isPause) {
				duration += 10;
				label.setText(TimeToString(duration));
			}
		};
		
		timeline = new Timeline(new KeyFrame(Duration.millis(10), eventHandler));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		this.getChildren().add(label);
		System.out.println(label.getText());
	}
	
	//获取开始游戏后的时间
	public int getTime() {
		return this.duration;
	}
	
	//获取格式化后的时间
	private static String TimeToString(int n) {
		int ms = n%1000;
		ms/=100;
		n/=1000;
		int s = n%60;
		n/=60;
		int m = n%60;
		n/=60;
		int h = n;
		return h + ":" + m + ":" + s + ":" + ms;
	}
	
}
