/**
 * Created by arymar on 10.12.15.
 */

$(function() {

});

function getShortLink(){
    var originalUrl = $("#originalUrl").val();

    if(originalUrl){
        $.ajax({
            url: '/get-short-url?originalUrl='+originalUrl,
            type: 'POST',
            beforeSend: function() {
                //waitingDialog.show('Wait please...');
            },
            complete: function(){
                //waitingDialog.hide();
            },
            success: function (response) {
                if(response.data){
                  var shortLink = $("#shortUrl");
                    shortLink.text(response.data);
                }
            }
        });
    }

    return false;
}

function getStatistic(){
    var workZone = $("#work-zone");
    var statZone = $("#statistic-zone");

    workZone.fadeOut(500, function(){
        statZone.fadeIn(500, function(){
            $("#map-container").datamaps();
        });
    });

    return false;
}
