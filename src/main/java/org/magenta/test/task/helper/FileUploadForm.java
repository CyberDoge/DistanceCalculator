package org.magenta.test.task.helper;

import javax.ws.rs.FormParam;
public class FileUploadForm {
    private byte[] fileData;

    public FileUploadForm() {}

    public byte[] getFileData() {
        return fileData;
    }

    @FormParam("file")
    public void setFileData(final byte[] file) {
        this.fileData = file;
    }
}