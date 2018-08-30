package org.magenta.test.task.service;

import java.io.InputStream;

public interface UploadFileService {
    void saveToDb(InputStream inputStream, String name);
}
