var thisJsScript = $('script[src*=typeahead-set-and-call]');

//First input
let userEmailString = thisJsScript.attr('userEmailList');
userEmailString = userEmailString.replace("[", "");
userEmailString = userEmailString.replace("]", "");
let userEmailArray = userEmailString.split(", ");

//Second input
let institutionNameString = thisJsScript.attr('institutionNameList');
institutionNameString = institutionNameString.replace("[", "");
institutionNameString = institutionNameString.replace("]", "");
institutionNameString = institutionNameString.slice(0, -1);
institutionNameString = institutionNameString.replace(/%/g, "\"");
let institutionNameArray = institutionNameString.split("#");

var $input = $("#userEmail");
var $input2 = $("#institutionName");

$input.typeahead({
    name: 'userEmail',
    source: userEmailArray,
    autoSelect: false
});

$input2.typeahead({
    name: 'institutionName',
    source: institutionNameArray,
    autoSelect: false
});