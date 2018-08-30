package org.magenta.test.task.servlet;

import org.magenta.test.task.service.UploadFileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("upload")
public class SendServlet extends HttpServlet {

    private UploadFileService uploadFileService;

    @Override
    public void init() {
        uploadFileService = (UploadFileService) getServletContext().getAttribute("uploadFileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        uploadFileService.saveToDb(filePart.getInputStream(), filePart.getName());
    }
}
