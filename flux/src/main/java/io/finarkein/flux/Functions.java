package io.finarkein.flux;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Functions {

	public static final Function<String, byte[]> strToBytes = input -> input.getBytes(StandardCharsets.ISO_8859_1);
	public static final Function<byte[], String> bytesToStr = input -> new String(input, StandardCharsets.ISO_8859_1);

	public static final Function<byte[], byte[]> gzipCompression = uncompressedData->{
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressedData.length);
			 GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
			gzipOS.write(uncompressedData);
			// You need to close it before using bos
			gzipOS.close();
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	};

	public static final Function<byte[], byte[]> gzipDecompression = compressedData -> {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 GZIPInputStream gzipIS = new GZIPInputStream(bis)) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gzipIS.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	};

	public static final Function<String, BytesWrapper> toBytesWrapper = input -> new BytesWrapper(input.getBytes(StandardCharsets.UTF_8));
	public static final Function<BytesWrapper, String> fromBytesWrapper = bytesWrapper -> {
		if (bytesWrapper != null)
			return new String(bytesWrapper.getBytes(), StandardCharsets.UTF_8);
		return "";
	};

	public static final Function<ServerError, BytesWrapper> seToBytesWrapper = errorBean -> {
		try (Output output = new Output(10000)) {
			Kryo kryo = new Kryo();
			kryo.writeClassAndObject(output, errorBean);
			return new BytesWrapper(output.getBuffer());
		}
	};

	public static final Function<BytesWrapper, ServerError> fromBytesWrapperToSE = bytesWrapper -> {
		final byte[] bytes = bytesWrapper.getBytes();
		Input input = new Input(bytes);
		return new Kryo().readObject(input, ServerError.class);
	};
}
