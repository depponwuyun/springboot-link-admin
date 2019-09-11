package com.springboot.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", e.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	
/*   //json exceptin
 * 	 @ExceptionHandler(value = Exception.class)
	    @ResponseBody
	    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	        ErrorInfo<String> r = new ErrorInfo<>();
	        r.setMessage(e.getMessage());
	        r.setCode(ErrorInfo.ERROR);
	        r.setData("Some Data");
	        r.setUrl(req.getRequestURL().toString());
	        return r;
	    }*/
}