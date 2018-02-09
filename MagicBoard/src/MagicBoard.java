


import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MagicBoard {
	//游戏面板
	private GridPane gridPane;
	
	//游戏内容对应数组
	private ArrayList<Object> array;
	
	//逆序对数量
	private int Reverse_order;
	
	//游戏难度，n*n
	private int n;
	
	//通过用户输入的难度创建新游戏
	public MagicBoard(int n) {
		this.n = n;
		
		//生成数组
		array = new ArrayList<Object>();
		for(int i = 1; i < n*n; ++i) {
			array.add(new Integer(i));
		}
		Reverse_order = 1;
		
		//对数组进行重排列，直到逆序对为偶数对
		while (Reverse_order % 2 == 1) {
			Collections.shuffle(array);
			Binary_Indexed_Tree bit = new Binary_Indexed_Tree(n*n);
			Reverse_order = 0;
			for(int i = array.size()-1; i >= 0; --i) {
				Reverse_order += bit.query(((Integer) (array.get(i))).intValue());
				bit.update(((Integer) (array.get(i))).intValue(), 1);
			}
		} 
		array.add(""); //在数组中增加空项
		build(); //生成游戏
	}
	
	//利用数组重建游戏并更新
	void rebuild() {
		build();
		Function.update();
	}
	
	//建立游戏内容中的按钮
	void build() {
		//新建游戏网格面板
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(50));
		
		for(int i = 0; i < array.size(); ++i) {
			Button bt = new Button((array.get(i).toString()));
			
			//设置按钮样式
			bt.setPrefSize(60, 60);
			bt.setFont(new Font(20));
			bt.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			
			//设置按钮方法
			bt.setOnAction(ActionEvent -> {
				if(!bt.getText().equals("") && !Main.isPause && Main.hasGame) {
					
					//所点按钮坐标
					int x = 0;
					int	y = 0;
					
					//空白按钮坐标
					int space_x = 0;
					int space_y = 0;
					
					//获取所点按钮
					for(int j = 0; j < n; ++j) {
						for(int k = 0; k < n; ++k) {
							if(gridPane.getChildren().get(j*n+k).equals(bt)){
								x = j;
								y = k;
							}
						}
					}
					
					//获取空白按钮
					for(int j = 0; j < n; ++j) {
						for(int k = 0; k < n; ++k) {
							if(((Button)(gridPane.getChildren().get(j*n+k))).getText().equals("")){
								space_x = j;
								space_y = k;
							}
						}
					}
					
					//判断所点按钮是否和空白按钮相邻
					if(Math.abs(space_x-x)+Math.abs(space_y-y) <= 1) {
						
						//在数组中交换数据
						Object temp = array.get(x*n+y);
						array.set(x*n+y, array.get(space_x*n+space_y));
						array.set(space_x*n+space_y, temp);
						
						//更新面板
						rebuild();
					}
				}
			});
			gridPane.add(bt, i%n, i/n);
		}
	}
	
	//获取游戏难度
	public int getLevel() {
		return this.n;
	}
	
	//检查是否胜利
	public boolean check() {
		boolean result = true;
		for(int i = 0; i < array.size(); ++i) {
			if(array.get(i) instanceof Integer) {
				//判断第i个是否为i+1
				if(((Integer)(array.get(i))).intValue() != i+1) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	//获取游戏面板
	public GridPane getView() {
		return this.gridPane;
	}
	
	
}

//用来判断逆序对的树状数组类
class Binary_Indexed_Tree{
	
	//树状数组本体
	private int[] array;
	
	//数组数组长度
	private int n;
	
	public Binary_Indexed_Tree(int n) {	
		array = new int[n];
		this.n= n;
	}
	
	//求数字二进制最低位
	int lowbit(int x) {
		return x&-x;
	}
	
	//查询数状数组中前n项和
	public int query(int x) {
		int ans = 0;
		for(int i = x; i > 0; i -= lowbit(i)) {
			ans += array[i];
		}
		return ans;
	}
	
	//数组数组单点修改值
	public void update(int x, int y) {
		for(int i = x; i < n; i += lowbit(i)) {
			array[i] += y;
		}
	}
}