package org.magenta.test.task.service;

import java.io.InputStream;

public interface SendFileService {
    void saveToDb(InputStream inputStream);
}
