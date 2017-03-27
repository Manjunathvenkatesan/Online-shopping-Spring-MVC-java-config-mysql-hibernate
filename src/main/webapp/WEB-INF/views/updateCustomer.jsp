<%@include file="/WEB-INF/views/template/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-wrapper">
    
    <div class="container">
   
  

	   <div class="page-header title">
	              <h1>Update Customer Info</h1>
	              
	              <p class="lead">Fill the below information to update account</p>
	    </div>
	    
	    
	   
	     
		<div class="form-layout">
	       <form:form   action="${pageContext.request.contextPath}/customer/update"   method="post" commandName="customer"     class="form-horizontal"  >
				
				
				
				
				
				
				<div class="form-group has-success">
					<label class="col-xs-3 control-label" ></label>
					<div class="col-xs-9">
						
					   <h1>Give Old  username and Password:</h1>
					</div>
				</div>
				
				
				<div class="form-group has-success">
					<label class="col-xs-3 control-label" for="username">Username :</label>
					<div class="col-xs-9">
						
					   
					   <input type="text" class="form-control" placeholder="Enter Username" name="username" id="username"/>
					</div>
				</div>
				
				
				
				<div class="form-group has-success">
					<label class="col-xs-3 control-label" for="password">Password :</label>
					<div class="col-xs-9">
						
					   
					   <input type="password" class="form-control"  name="password" placeholder="Enter Password" id="password"/>
					</div>
				</div>
				
				
				
				
				
				
				
				
				
				
				<div class="form-group has-success">
					<label class="col-xs-3 control-label"></label>
					<div class="col-xs-9">
						 <input type="submit" value="Submit" class="btn btn-default">
             <a href="<c:url value="/" />"  class="btn btn-default">Cancel</a>
					</div>
				</div>
			
			 
			</form:form>
		</div>
    
    </div>

</div>



<%@include file="/WEB-INF/views/template/footer.jsp"%>