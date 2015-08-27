package com.work.test.usetest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

	public static void main(String[] args) throws Exception {  
        String infile = "a.txt";  
        String outfile = "copy.txt";  
        // 获取源文件和目标文件的输入输出流  
        FileInputStream fin = new FileInputStream(getRealPath(infile));  
        FileOutputStream fout = new FileOutputStream(getRealPath(outfile));  
        // 获取输入输出通道  
        FileChannel fcin = fin.getChannel();  
        FileChannel fcout = fout.getChannel();  
        // 创建缓冲区  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
        while (true) {  
            // clear方法重设缓冲区，使它可以接受读入的数据  
            buffer.clear();  
            // 从输入通道中将数据读到缓冲区  
            int r = fcin.read(buffer);  
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1  
            if (r == -1) {  
                break;  
            }  
            // flip方法让缓冲区可以将新读入的数据写入另一个通道  ,写模式转换成读模式
            buffer.flip();  
            // 将缓冲区 中的数据 写入 输出通道
            fcout.write(buffer);  
        }
        fcin.close();
        fcout.close();
        fin.close();
        fout.close();
    }  
	
	private static String getRealPath(String path) {
		String basePath = new ReadFile().getClass().getClassLoader()
				.getResource("").getPath();
		String pathString = basePath + path;
		System.out.println(pathString);
		return pathString;
	}
}
