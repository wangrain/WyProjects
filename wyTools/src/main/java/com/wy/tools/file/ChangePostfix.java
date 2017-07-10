package com.wy.tools.file;

import java.io.File;

/**
 * 修改某个目录内除指定后缀外所有文件后缀
 * @author Rain
 *
 */
public class ChangePostfix {
	//开始目录
	public static String thePath = "D:\\OneDrive\\Soft\\Minecraft1.7.5\\.minecraft\\tor.a";
	
	public static void main(String[] args) {
		File path = new File(thePath);
		loopFile(path);
	}
	
	private static void loopFile(File path){
		File[] files = path.listFiles();//取出当前目录下所有文件
		for( int i = 0;i<files.length;i++){
			//判断该文件是否目录
			if(files[i].isDirectory()){//目录
				//若该文件为目录，则继续迭代
				loopFile(files[i]);
			}else if(files[i].isFile()){//文件
//				addPostfix(files[i]);//增加后缀
				deletePostfix(files[i]);//删除后缀
			}
		}
	}

	/**
	 * 给文件增加后缀
	 * @param file
	 */
	private static void addPostfix(File file){
		if(!file.getName().endsWith(".torrent")
				&& !file.getName().endsWith(".txt")
				&& !file.getName().endsWith(".mxl")){
			//更改文件名为：原文件名 + .mxl
			String newName = file.getAbsoluteFile()+".mxl";//新文件名必须包含绝对路径
			file.renameTo(new File(newName));//重命名文件
			System.out.println("重命名文件："+file.getName());
		}else{
			System.out.println("跳过文件："+file.getName());
		}
	}

	/**
	 * 删除指定后缀
	 * @param file
	 */
	private static void deletePostfix(File file){
		if(file.getName().endsWith(".mxl")){
            String oldName = file.getAbsoluteFile().toString();
			String newName = oldName.substring(0, oldName.length()-4);//新文件名必须包含绝对路径
			file.renameTo(new File(newName));//重命名文件
			System.out.println("重命名文件："+file.getName());
		}else{
			System.out.println("跳过文件："+file.getName());
		}
	}
}
