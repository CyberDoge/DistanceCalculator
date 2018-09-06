package org.magenta.test.task.servlet;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.magenta.test.task.helper.FileUploadForm;
import org.magenta.test.task.service.UploadFileService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response uploadFile(MultipartFormDataInput multipart) throws IOException {
        uploadFileService.saveToDb(multipart.getFormDataMap().get("file").get(0).getBody(InputStream.class, null));
        return Response.status(200).build();
    }
}
