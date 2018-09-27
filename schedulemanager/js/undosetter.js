var UNDO = false; 
var REDO = false;

function KeyPress(e) {
    
    var evtobj = window.event? event : e
    if (evtobj.keyCode == 90 && evtobj.ctrlKey){

        UNDO = true; 
    }
    else if (evtobj.keyCode == 86 && evtobj.ctrlKey){

        REDO = true; 
    }
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