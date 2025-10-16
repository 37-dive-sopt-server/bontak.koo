package org.sopt.global.util;

import java.io.*;

public class SerializationUtils {

    // 직렬화
    public static void serialize(Object obj, OutputStream outputStream) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(obj);
        }
    }

    // 역직렬화
    public static Object deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return ois.readObject();
        }
    }
}
