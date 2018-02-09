

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Mymenu extends MenuBar{
	//菜单中的继续按钮
	private MenuItem continueMenuItem;
    
	//菜单中的暂停按钮
	private MenuItem pauseMenuItem;
    
	//菜单中的停止按钮
	private MenuItem stopMenuItem;
	
	public Mymenu() {
		Menu gameMenu = new Menu("游戏");
	    MenuItem newMenuItem = new MenuItem("新游戏");
	    continueMenuItem = new MenuItem("继续");
	    pauseMenuItem = new MenuItem("暂停");
	    stopMenuItem = new MenuItem("停止");
	    MenuItem rankMenuItem = new MenuItem("排行榜");
	    MenuItem exitMenuItem = new MenuItem("退出");
	    
	    newMenuItem.setOnAction(e -> {
	    	ArrayList<Integer> choices = new ArrayList<Integer>();
	    	choices.add(3);
	    	choices.add(4);
	    	choices.add(5);

	    	ChoiceDialog<Integer> dialog = new ChoiceDialog<>(3, choices);
	    	dialog.setTitle("新游戏");
	    	dialog.setHeaderText("请选择你想要的难度");
	    	dialog.setContentText("选择一个数字n，游戏难度为n*n:");
	    	Optional<Integer> result = dialog.showAndWait();
	    	result.ifPresent(letter -> Function.CreateGame(result.get().intValue()));

	    });
	    
	    continueMenuItem.setOnAction(e -> Function.Continue());
	    
	    pauseMenuItem.setOnAction(e -> Function.Pause());
	    
	    stopMenuItem.setOnAction(e -> Function.Stop());
	    
	    rankMenuItem.setOnAction(e -> {
	    	Function.Pause();
	    	Function.showBoard();
	    	Function.Continue();
	    });
	    
	    exitMenuItem.setOnAction(e -> Platform.exit());
	    
	    gameMenu.getItems().addAll(newMenuItem,new SeparatorMenuItem(), continueMenuItem, pauseMenuItem, 
	    		stopMenuItem, new SeparatorMenuItem(), rankMenuItem, new SeparatorMenuItem(), exitMenuItem);
	    
	    
	    //帮助菜单
	    Menu helpMenu = new Menu("帮助");
	    MenuItem helpMenuItem = new MenuItem("帮助");
	    MenuItem aboutMenuItem = new MenuItem("关于");
	    helpMenuItem.setOnAction(e -> {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("帮助");
	    	alert.setHeaderText("游戏说明");
	    	alert.setContentText("每次可以将空白格周围的格子与空白格交换，将空白格移动到右下角，且其他各种数字有序即胜利。");
	    	alert.showAndWait();
	    });
	    
	    aboutMenuItem.setOnAction(e -> {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("关于");
	    	alert.setHeaderText("魔板游戏\n版本：1.0.0.0（正式版）");
	    	alert.setContentText("版权所有2018  张宏海®保留所有权利。");
	    	alert.showAndWait();
	    });
	    
	    helpMenu.getItems().addAll(helpMenuItem, new SeparatorMenuItem(), aboutMenuItem);
	    
	    this.DisableContinue();
	    this.DisablePause();
	    this.DisableStop();
	    
	    this.getMenus().addAll(gameMenu, helpMenu);
	}
	
	//禁用继续按钮
	public void DisableContinue() {
		this.continueMenuItem.setDisable(true);
	}
	
	//禁用暂停按钮
	public void DisablePause() {
		this.pauseMenuItem.setDisable(true);
	}
	
	//禁用停止按钮
	public void DisableStop() {
		this.stopMenuItem.setDisable(true);
	}
	
	//启用继续按钮
	public void EnableContinue() {
		this.continueMenuItem.setDisable(false);
	}
	
	//启用暂停按钮
	public void EnablePause() {
		this.pauseMenuItem.setDisable(false);
	}
	
	//启用停止按钮
	public void EnableStop() {
		this.stopMenuItem.setDisable(false);
	}
}
