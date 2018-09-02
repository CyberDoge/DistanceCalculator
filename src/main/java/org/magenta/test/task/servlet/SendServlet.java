package org.magenta.test.task.servlet;

import org.magenta.test.task.service.UploadFileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Path("upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class SendServlet {

    private UploadFileService uploadFileService;

    public void init() {
//        uploadFileService = (UploadFileService) getServletContext().getAttribute("uploadFileService");
    }

    @GET
    public void doGet(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        uploadFileService.saveToDb(filePart.getInputStream());
        resp.setStatus(200);
    }
}
