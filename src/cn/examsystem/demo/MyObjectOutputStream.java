package cn.examsystem.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {

	public MyObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		super.reset();
	}


	public static ObjectOutputStream newInstance(File file) throws FileNotFoundException, IOException {
		long length = file.length();
		ObjectOutputStream oos = null;
		if (length == 0) {
			oos = new ObjectOutputStream(new FileOutputStream(file, true));
		} else {
			oos = new MyObjectOutputStream(new FileOutputStream(file, true));
		}
		return oos;
	}
}
