

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

//排行榜类继承Stage
public class RankBoard extends Stage {
	//主面板
	private TabPane tabpane;
	
	//从文件获取到的玩家数组
	private ArrayList<Player> list;
	
	//表格
	private TableView<Player>[] tableViews;
	
	//TabPane中的不同标签页
	private Tab[] tabs;
	
	//表格中的名字列
	private TableColumn<Player, String> name;
	
	//表格中的时间类
	private TableColumn<Player, String> time;
	
	//表格中的数据对象
	private final ObservableList<Player>[] data;  
	
	
	@SuppressWarnings("unchecked")
	public RankBoard() throws ClassNotFoundException, EOFException {
		this.setTitle("排行榜");
		tabpane = new TabPane();
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs = new Tab[3];
		list = PlayersIO.read();
		tableViews = new TableView[3];
		data = new ObservableList[3];
		for(int i = 0; i < 3; ++i) {
			
			data[i] = FXCollections.observableArrayList();  
			tableViews[i] = new TableView<Player>();
			
			//获取不同难度的玩家信息
			for(Player players : list) {
				if(players.getLevel() == i+3) {
					data[i].add(players);
				}
			}
			
			//表格名字列的定义
			name = new TableColumn<Player, String>("名字");  
	        name.setMinWidth(200);  
	        name.setCellValueFactory(  
	                new PropertyValueFactory<>("name"));  
	        
	        //表格时间列的丁颖
	        time = new TableColumn<Player, String>("时间");  
	        time.setMinWidth(200);  
	        time.setCellValueFactory(  
	                new PropertyValueFactory<>("time"));
			
	        //数据排序
	        Collections.sort(data[i]);
			
	        //将表格列整合到表格中
	        tableViews[i].setItems(data[i]);
			tableViews[i].getColumns().addAll(name, time); 
			tabs[i] = new Tab("难度" + (i+3), tableViews[i]);
			tabpane.getTabs().add(tabs[i]);

		}
		Scene scene = new Scene(tabpane,400,200);
		this.setScene(scene);
	}
}
