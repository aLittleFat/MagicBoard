

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//玩家名字
	private String name;
	
	//玩家所用时间
	private int time;
	
	//玩家所选难度
	private int level;
	
	Player(String name, int time, int level){
		this.name = name;
		this.time = time;
		this.level = level;
	}
	public String getName() {
		return name;
	}

	//获取时间格式化字符串
	public String getTime() {
		int n = this.time;
		n/=1000;
		int s = n%60;
		n/=60;
		int m = n%60;
		n/=60;
		int h = n;
		return h + "小时" + m + "分钟" + s + "秒";
	}
	
	//获取时间整数变量
	public int getTimeInt() {
		return this.time;
	}
	
	//获取游戏难度
	public int getLevel() {
		return level;
	}
	
	//重写Player排序方法
	@Override
	public int compareTo(Player o) {
		if(this.time == o.time) {
			return this.name.compareTo(o.getName());
		} else {
			if(this.time < o.getTimeInt()) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
