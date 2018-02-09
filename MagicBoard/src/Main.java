

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;

public class Main extends Application {
	//判断目前是否存在游戏
	public static boolean hasGame = false;
	
	//判断是否暂停游戏状态
	public static boolean isPause = false;
	
	//主界面根面板
	static BorderPane root;
	
	//主游戏类对象
	static MagicBoard magicBoard;
	
	//计时器对象
	static Timer timer;
	
	//排行榜对象
	static RankBoard rankBoard;
	
	//菜单栏对象
	static Mymenu mymenu;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("模板游戏");
			root = new BorderPane();
			Scene scene = new Scene(root,400,450);
			mymenu = new Mymenu();
			mymenu.prefWidthProperty().bind(primaryStage.widthProperty());
			
			root.setTop(mymenu); //将菜单置于面板顶部

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
