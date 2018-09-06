package org.magenta.test.task.servlet;

import org.magenta.test.task.service.UploadFileService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("upload")
public class UploadServlet {
    @Context
    ServletContext servletContext;
    @Inject
    UploadFileService uploadFileService;


    @GET
    @Produces("text/html")
    public InputStream openPage() throws IOException {
        return new FileInputStream( servletContext.getAttribute("basedir") + "/upload.html");
    }


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        uploadFileService.saveToDb(filePart.getInputStream());
        resp.setStatus(200);
    }
}
