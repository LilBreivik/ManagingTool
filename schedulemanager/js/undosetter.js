var UNDO = false; 
var REDO = false;

function KeyPress(e) {
    
   /* var evtobj = window.event? event : e
    if (evtobj.keyCode == 90 && evtobj.ctrlKey){

        UNDO = true; 
    }
    else if (evtobj.keyCode == 86 && evtobj.ctrlKey){

        REDO = true; 
    }*/
} 

function updateNoticeInformation(headline, notice){

    document.getElementById("noticeHeadline").value = headline;

    document.getElementById("noticeMessage").value = notice; 

}
 
function initUndoSetter(){
  
    document. onkeydown = KeyPress;
}

function getREDO(){

    return REDO; 
}

function setREDO(redoFlag){
    REDK  = redoFlag; 
}

function getUNDO(){

    return UNDO; 
}

function setUNDO(undoFlag){
    UNDO = undoFlag; 
}