function dateSubStr(value, row, index){
    if(value!="" && value!=null){
        return value.substr(0,value.lastIndexOf(":"));
    }
}