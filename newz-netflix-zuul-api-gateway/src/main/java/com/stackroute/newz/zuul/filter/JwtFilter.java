package com.stackroute.newz.zuul.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */

public class JwtFilter extends GenericFilterBean {

	/*
	 * Override the doFilter method of GenericFilterBean.
	 * Retrieve the "authorization" header from the HttpServletRequest object.
	 * Retrieve the "Bearer" token from "authorization" header.
	 * If authorization header is invalid, throw Exception with message. 
	 * Parse the JWT token and get claims from the token using the secret key
	 * Set the request attribute with the retrieved claims
	 * Call FilterChain object's doFilter() method */


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		if(req.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
			chain.doFilter(req, res);
		}else {
			String authen=req.getHeader("Authorization");

			res.setHeader("Access-Control-Allow-Origin",req.getHeader("origin"));
			res.setHeader("Access-Control-Allow-Credentials", "true");
			res.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTION,DELETE,PATCH");


			if((authen==null)|| !authen.startsWith("Bearer"))
			{
				throw new ServletException("not found");
			}

			final String compact=authen.substring(7);
			try
			{
				JwtParser parseobj=Jwts.parser().setSigningKey("secretKey");

				Jwt jwt=parseobj.parse(compact);

				Claims claim=(Claims)jwt.getBody();

				req.setAttribute("claims", claim)	;

				String sub=claim.getSubject();
				HttpSession sess=req.getSession();
				sess.setAttribute("login", sub);


			}
			catch(SignatureException e)
			{
				throw new ServletException("invaid key");	
			}
			catch(MalformedJwtException e)
			{
				throw new ServletException("key modified by unauthorized person");

			}

			chain.doFilter(req, res);
		}

	}

}
