
function uploadAsset2(input) {
  var reader = new FileReader();           
  reader.readAsDataURL(input.files[0]);
           
  return reader;   
             
}	
