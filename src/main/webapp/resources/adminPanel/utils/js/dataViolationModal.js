document.addEventListener('DOMContentLoaded', function(){

    let flag = $('#modalToggle');

    if (flag.html() === 'error' ){

        let link = $("<a id='link22' hidden href='#' data-toggle='modal' data-target='#dataViolationModal'><span>link</span></a>");
        $('body').append(link);
        $('#link22 span').trigger('click');

        flag.html('');
    }
});