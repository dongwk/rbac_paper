/**
 *
 */
package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private CountDownLatch latch = new CountDownLatch(1);
	private CyclicBarrier barrier = new CyclicBarrier(1);
	private int i = 0;

	@Autowired
	public HttpServletRequest request;

	@PostMapping
	public String auth(@Valid @RequestBody LoginMo loginMo){

		return null;
	}

	@RequestMapping("/exce")
	public String exce(){
		throw new Exce();
	}

	@RequestMapping(value="/str")
	@ResponseBody
	public ResponseEntity<String> str(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader","MyValue");
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("/r1")
	@ResponseBody
	public String r1(){
		String a = null;
		try {
			latch.await();
			System.out.println(request.hashCode());
			a = request.getParameter("a");
			System.out.println("r1:"+a);
			System.out.println("i:"+i);
			latch = new CountDownLatch(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a;
	}

	@RequestMapping("/r2")
	@ResponseBody
	public String r2(){
		System.out.println(request.hashCode());
		String a = request.getParameter("a");
		System.out.println("r2:"+a);
		System.out.println("i:"+i);
		i = 2;
		latch.countDown();
		return a;
	}
}