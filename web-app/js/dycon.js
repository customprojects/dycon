$(document).ready(function() {

    function reloadContentList(){

      var newLocation = window.location.origin + window.location.pathname;
      newLocation += "?pageId=" + $('#pageName').val();
      newLocation += "&live=" +  $('#live').is(':checked');
      window.location.href = newLocation;

    }

    $('#pageName').change(function(){
        reloadContentList();
    });

    $('#live').change(function(){
        reloadContentList();
    });


});
