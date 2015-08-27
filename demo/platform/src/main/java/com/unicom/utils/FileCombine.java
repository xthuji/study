package com.unicom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author qibaichao
 * @ClassName FileCombine
 * @Date Sep 11, 2013
 * @Description:文件合并
 */
public class FileCombine {

	public void combineFiles(List<File> files, String saveFileName)
			throws IOException {
		File mFile = new File(saveFileName);

		if (!mFile.exists()) {
			mFile.createNewFile();
		}
		FileChannel mFileChannel = new FileOutputStream(mFile).getChannel();
		FileChannel inFileChannel;
		for (File file : files) {

			inFileChannel = new FileInputStream(file).getChannel();
			inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);

			inFileChannel.close();
		}

		mFileChannel.close();

	}

	public static void main(String[] args) {
		FileCombine fc = new FileCombine();

		File file = new File("d:\\test\\");

		List<File> files = new ArrayList<File>();
		for (int i = 1; i < 3; i++) {
			files.clear();
			for (File tmpFile : file.listFiles()) {
				if (tmpFile.getName().startsWith(i + "")) {
					files.add(tmpFile);
				}
			}
			try {

				Collections.sort(files, new Comparator<File>() {

					@Override
					public int compare(File o1, File o2) {
						// TODO Auto-generated method stub
						int result = 0;
						String name1 = o1.getName().replace(".dat", "")
								.replace(".", "");
						String name2 = o2.getName().replace(".dat", "")
								.replace(".", "");
						double d = Double.parseDouble(name1) * 100
								- Double.parseDouble(name2) * 100;
						System.out.println(d);
						if (d > 0) {
							result = 1;
						}
						else if (d < 0) {
							result = -1;
						}
						else {
							result = 0;
						}
						return result;
					}
				});
				System.out.println(Arrays.toString(files.toArray()));
				fc.combineFiles(files, "d:\\test\\test.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}