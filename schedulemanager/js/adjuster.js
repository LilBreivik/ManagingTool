function adjustDimensions() {
    
    var dimensions = new Object();

    dimensions.width = document.getElementsByClassName("table_cell")[0].offsetWidth;
               
    dimensions.height = document.getElementsByClassName("table_cell")[0].offsetHeight;

    return dimensions; 
}
  