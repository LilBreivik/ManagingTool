
function setUpModal(errorResponse) {
  
    document.getElementById("modalUtility").style.display = "block";

    document.getElementById("modalUtility").classList += "in"; 

    document.getElementById("myModalLabel").textContent = errorResponse.httpStatus; 

    document.getElementById("myModalContent").textContent = errorResponse.moreInfo; 

    document.getElementById("myModalFooter").textContent = errorResponse.errorMessage; 
  }	
   