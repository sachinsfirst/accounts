<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>

<%@ include file = "common_layout.jsp" %>

<%-- <%@page import="com.captcha.botdetect.web.servlet.Captcha"%>  --%>



</head>
<body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>
            
      <div class="login_wrapper">
        <div class="animate form login_form">
           <section class="login_content">
         <!--   <img alt="logo" src="../img/re.jpg"/  width="100%" height="70%"> -->
            <form id="loginform" name="loginform" action="/submitlogin" method="post">
              <h1>Please enter the details</h1>
              <span style="color: green;">${message}</span><br>
              <span style="color: red;">${errormsg}</span><br>
              <div>
                <input type="text" name="userName" class="form-control" placeholder="Username" required="required" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="Password" required="required" />
              </div>

<%-- <%
	// Adding BotDetect Captcha to the page 
	Captcha captcha = Captcha.load(request, "exampleCaptcha");
	captcha.setUserInputID("captchaCode");

	String captchaHtml = captcha.getHtml();
	out.write(captchaHtml);
%>
<div><input id="captchaCode" type="text" name="captchaCode" /></div> --%>

					<!-- <div class="form-cont">
                        <img src="CaptchaImg.jpg" alt="image">
                    </div> -->
                    <!-- class="captcha input-elm" -->
                    <div> Captcha : <input type="text" value="${captcha}"  required="required" class="form-control col-md-4 col-xs-12" readonly/></div>
                    
                     <input type="text"  class="form-control" tabindex="3"  id="captcha" name="captcha"
                        placeholder="Captcha Code" name="j_captcha_response" id="j_captcha_response" 
                         maxlength="9"/>
                    

			<div>
              	<button type="submit" class="btn btn-success">Log in</button>
                <a class="reset_pass" href="/resetpassword">Lost your password?</a>
              </div>

              <!-- <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div> -->
                <br />

              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>