/**
 * 
 */
package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(value = "/img", method = RequestMethod.POST)
	public void img(@RequestParam("file") CommonsMultipartFile multipartFile) {
		System.out.println(multipartFile);
	}

}