/*
 * Copyright 2023 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ultimatetek.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Md Junaid Khan
 */
@WebServlet(urlPatterns = {"/design/images/*"})
@Configuration
public class DisplayImages extends javax.servlet.http.HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayImages.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("image/png");
        ServletOutputStream out;
        out = response.getOutputStream();
        String fileName = request.getPathInfo().substring(1);

        // Printing using for each loop
        try {
            String path = ProjectConst.ROOT_DIR + File.separator + "design_sample";
            File file = new File(path + File.separator + fileName + ".png");
//            File file = new File(path + File.separator + fileName);
            BufferedOutputStream bout = null;
            try ( FileInputStream fin = new FileInputStream(file)) {
                BufferedInputStream bin = new BufferedInputStream(fin);
                bout = new BufferedOutputStream(out);
                int ch = 0;
                while ((ch = bin.read()) != -1) {
                    bout.write(ch);
                }

                bin.close();
                if (bout != null) {
                    bout.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                LOGGER.error("Exception FileNotFoundException " + ex.getMessage());
            }
        } //        catch (IOException ex) {
        //            LOGGER.error("Exception IOException " + ex.getMessage());
        //        } 
        catch (Exception e) {
            LOGGER.error("Exception ======= " + e.getMessage());
        }
    }
}
