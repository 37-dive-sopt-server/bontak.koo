package org.sopt.domain.member.repository.storage;

import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.FileException;
import org.sopt.global.util.SerializationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorage {
    private final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    public void write(Object object) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            SerializationUtils.serialize(object, fos);
        } catch (IOException e) {
            throw new FileException(ErrorCode.FILE_SAVE_ERROR);
        }
    }

    public Object read() {
        File file = new File(filePath);
        if (!file.exists()) return null;

        try (FileInputStream fis = new FileInputStream(file)) {
            return SerializationUtils.deserialize(fis);
        } catch (IOException | ClassNotFoundException e) {
            throw new FileException(ErrorCode.FILE_LOAD_ERROR);
        }
    }
}
