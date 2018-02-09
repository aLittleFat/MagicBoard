

import java.io.EOFException;
import java.util.Optional;

import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class Function {
	
	//每次魔板游戏内容发生变化时更新变化用的函数
	public static void update() {
		GridPane gameView = Main.magicBoard.getView();
		Main.root.setBottom(gameView);
		if(Main.magicBoard.check()) {
			WinGame();
		}
	}
	
	//游戏胜利后停下游戏以及弹出输入名字框
	public static void WinGame() {
		Main.isPause = true;
		Main.hasGame = false;
		Main.mymenu.DisableContinue();
		Main.mymenu.DisablePause();
		TextInputDialog dialog = new TextInputDialog("name");
		dialog.setTitle("恭喜");
		dialog.setHeaderText("恭喜你通关了！");
		dialog.setContentText("请留下你的名字:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			Player player = new Player(name, Main.timer.getTime(), Main.magicBoard.getLevel());
			PlayersIO.write(player);
		});
		showBoard();
	}
	
	//显示排行榜
	public static void showBoard() {
		try {
			Main.rankBoard = new RankBoard();
		} catch (ClassNotFoundException | EOFException e1) {
			e1.printStackTrace();
		}
    	Main.rankBoard.showAndWait();
	}
	
	//新建游戏
	public static void CreateGame(int n) {
		Main.hasGame = true;
		Main.isPause = false;
		Main.timer = new Timer();
		Main.root.setLeft(Main.timer);
    	Main.magicBoard = new MagicBoard(n);
    	Main.mymenu.EnablePause();
    	Main.mymenu.EnableStop();
    	update();
	}
	
	//暂停状态下继续游戏
	public static void Continue() {
		if(Main.hasGame && Main.isPause) {
    		Main.isPause = false;
    		update();
    		Main.mymenu.DisableContinue();
    		Main.mymenu.EnablePause();
    	}
	}
	
	//暂停游戏
	public static void Pause() {
		if(Main.hasGame && !Main.isPause) {
    		Main.isPause = true;
    		Main.root.setBottom(null);
    		Main.mymenu.EnableContinue();
    		Main.mymenu.DisablePause();
    	}
	}
	
	//停止当前游戏
	public static void Stop() {
    	Main.hasGame = false;
    	Main.root.setLeft(null);
		Main.root.setBottom(null);
		Main.isPause = false;
		Main.mymenu.DisableContinue();
		Main.mymenu.DisablePause();
		Main.mymenu.DisableStop();
	}
}
