<form:form  commandName="order" class="form-horizontal" >
        
          
            
            
            
            
            <h3>Shipping  Address</h3>
            
            
            <div class="form-group">
                <label for="shippingStreet">Street Name</label> 
                 <form:input path="cart.customer.shippingAddress.strretName" id="shippingStreet" class="form-control" disabled="true"/>
            </div>
            
            <div class="form-group">
                <label for="shippingApartmentNumber">Apartment Number</label> 
                 <form:input path="cart.customer.shippingAddress.apartmentNumber" id="shippingApartmentNumber" class="form-control" disabled="true"/>
            </div>
            
            
            <div class="form-group">
                <label for="shippingCity">City</label> 
                 <form:input path="cart.customer.shippingAddress.city" id="shippingCity" class="form-control" disabled="true"/>
            </div>
            
            
            
            <div class="form-group">
                <label for="shippingState">State</label> 
                 <form:input path="cart.customer.shippingAddress.state" id="shippingState" class="form-control" disabled="true"/>
            </div>
            
            
            
            <div class="form-group">
                <label for="shippingCountry">Country</label> 
                 <form:input path="cart.customer.shippingAddress.country" id="shippingCountry" class="form-control" disabled="true"/>
            </div>
            
            
            <div class="form-group">
                <label for="shippingZipCode">ZipCode</label> 
                 <form:input path="cart.customer.shippingAddress.zipCode" id="shippingZipCode" class="form-control" disabled="true"/>
            </div>
            
            
            
            
            <!-- _flowExecutionKey =indicate this is part of our work flow -->
            <input type="hidden" name="_flowExecutionKey">
             
             <br/><br/>
             
              <button  class="btn btn-default" name="_eventId_backToCollectCustomerInfo">Back</button>
             <!-- -eventId_customerInfoCollected = transaction result when clicked -->
             <input type="submit" value="Next" class="btn btn-default" name="_eventId_shippingDetailCollected">
             <button  class="btn btn-default" name="_eventId_cancel">Cancel</button>              
             
        </form:form>