

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PlayersIO {
	
	//将单条玩家信息追加写入到文件中
	public static void write(Player player) {
		File file = new File("PlayerInformation.dat");
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			System.out.println(file.length());
			
			//判断文件中是否已经存在数据，若存在，需要跳过特定头信息
			if(file.exists() && (file.length() > 4)) {
				long pos = out.getChannel().position()-4;
				out.getChannel().truncate(pos);
			}
			
			oos.writeObject(player);
			oos.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取文件中的玩家信息，并将其存到数组中
	public static ArrayList<Player> read() throws ClassNotFoundException, EOFException {
		ArrayList<Player> list = new ArrayList<>();
		File file = new File("PlayerInformation.dat");
		try {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream oin = new ObjectInputStream(in);
			try {
				while(true) {
					Player player = (Player)(oin.readObject());
					list.add(player);
				}
			} catch (EOFException e) {
				e.printStackTrace();
			}
			oin.close();
			in.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return list;
	}
}
