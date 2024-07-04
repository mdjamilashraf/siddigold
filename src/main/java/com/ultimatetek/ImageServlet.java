package com.ultimatetek;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.ultimatetek.config.ProjectConst;

@WebServlet("/vehimages/*")
@Configuration
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String filename = request.getPathInfo().substring(1); File file = new
		 * File("D:\\TourtravelsBackup\\vehicle_img\\VOLVO_COACH", filename);
		 * response.setHeader("Content-Type",
		 * getServletContext().getMimeType(filename));
		 * response.setHeader("Content-Length", String.valueOf(file.length()));
		 * response.setHeader("Content-Disposition", "inline; filename=\"" + filename +
		 * "\""); Files.copy(file.toPath(), response.getOutputStream());
		 */
		try {
			// Get image file.
			String file = request.getPathInfo().substring(1);
			String fileDir = ProjectConst.ROOT_DIR+File.separator+"TourtravelsBackup"+File.separator+"vehicle_img";
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileDir + File.separator + file));
			// Get image contents.
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			// Write image contents to response.
			response.getOutputStream().write(bytes);

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
