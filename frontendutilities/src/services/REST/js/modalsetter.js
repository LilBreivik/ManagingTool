function setUpErrorModal(errorResponse) {
  
  document.getElementById("errorModalLabel").textContent = errorResponse.httpStatus; 

  document.getElementById("errorModalContent").textContent = errorResponse.moreInfo; 
  
  document.getElementById("errorModalFooter").textContent = errorResponse.errorMessage; 
 
  $("#errorModal").modal()
}	
 
 
function setUpProgressModal() {
  
 // document.getElementById("progressModal").textContent = errorResponse.httpStatus;  

  $("#progressModal").modal('toggle')
}	   