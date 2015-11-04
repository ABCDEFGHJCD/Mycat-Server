package io.mycat.backend.postgresql.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/*****
 * PostgreSQL io工具类
 * 
 * @author Coollf
 *
 */
public class PostgreSQLIOUtils {

	/**
     * Sends a 4-byte integer to the back end
     *
     * @param val the integer to be sent
     * @exception IOException if an I/O error occurs
     */
	public static void SendInteger4(int val, ByteBuffer buffer) {
		byte[] _int4buf = new byte[4];
		_int4buf[0] = (byte) (val >>> 24);
		_int4buf[1] = (byte) (val >>> 16);
		_int4buf[2] = (byte) (val >>> 8);
		_int4buf[3] = (byte) (val);
		buffer.put(_int4buf);
	}
	
	public static int redInteger4(ByteBuffer buffer, int offset) {		
		return buffer.getInt(offset);
	}
	

	/**
	 * Sends a 2-byte integer (short) to the back end
	 *
	 * @param val
	 *            the integer to be sent
	 * @exception IOException
	 *                if an I/O error occurs or <code>val</code> cannot be
	 *                encoded in 2 bytes
	 */
	public static void SendInteger2(int val, ByteBuffer buffer) throws IOException {
		if (val < Short.MIN_VALUE || val > Short.MAX_VALUE)
			throw new IOException("Tried to send an out-of-range integer as a 2-byte value: " + val);

		byte[] _int2buf = new byte[2];
		_int2buf[0] = (byte) (val >>> 8);
		_int2buf[1] = (byte) val;
		buffer.put(_int2buf);
	}
	

	public static void Send(byte[] encodedParam, ByteBuffer buffer) {
		buffer.put(encodedParam);
	}

	public static void SendChar(int i, ByteBuffer buffer) {
		buffer.put((byte) i);
	}
	
	/***
	 * 读取数组信息
	 * @param buffer
	 * @param offset
	 * @param length
	 * @return
	 */
	public static byte[] redByteArray(ByteBuffer buffer, int offset, int length) {
		byte[] dst = new byte[length];
		for(int i = 0;i< length;i++){
			dst[i] = buffer.get(offset+i);
		}
		return dst;
	}

	
	
	public static void SendString(String password, ByteBuffer buffer) {
		buffer.put(password.getBytes(Charset.forName("utf-8")));
	}
	
	


	
	
	

}
