/**
 * Created by arymar on 10.12.15.
 */
var map;
var waitModal = $("#waitModal");

function showWaitModal(){
    waitModal.modal("show");
}

function hideWaitModal(){
    waitModal.modal("hide");
}

function getShortLink(){
    var originalUrl = $("#originalUrl").val();

    if(originalUrl){
        $.ajax({
            url: '/get-short-url?originalUrl='+originalUrl,
            type: 'POST',
            beforeSend: function() {
                showWaitModal();
            },
            complete: function(){
                hideWaitModal();
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
    var shortUrl = $("#shortUrlStat").val();

    if(shortUrl) {
        var workZone = $("#work-zone");
        var statZone = $("#statistic-zone");

        workZone.fadeOut(300, function () {
            statZone.fadeIn(300, function () {
                $.ajax({
                    url: '/statistic/calculate?shortUrl='+shortUrl,
                    type: 'POST',
                    beforeSend: function () {
                        showWaitModal();
                    },
                    complete: function () {
                        hideWaitModal();
                    },
                    success: function (response) {
                        if(response.data) {
                            populateStatisticPage(response.data);
                        }
                    }
                });
            });
        });
    }
    return false;
}

function populateStatisticPage(data) {
    var totalClicks = $("#totalClicks");

    var phonePart = $("#phonePart");
    var phoneClicks = $("#phoneClicks");
    var phoneAndroidPart = $("#phoneAndroidPart");
    var phoneAndroidClicks = $("#phoneAndroidClicks");
    var phoneIOSPart = $("#phoneIOSPart");
    var phoneIOSClicks = $("#phoneIOSClicks");
    var phoneWinmobPart = $("#phoneWinmobPart");
    var phoneWinmobClicks = $("#phoneWinmobClicks");
    var phoneJavaPart = $("#phoneJavaPart");
    var phoneJavaClicks = $("#phoneJavaClicks");
    var phoneUnknownPart = $("#phoneUnknownPart");
    var phoneUnknownClicks = $("#phoneUnknownClicks");

    var pcPart = $("#pcPart");
    var pcClicks = $("#pcClicks");
    var pcLinuxPart = $("#pcLinuxPart");
    var pcLinuxClicks = $("#pcLinuxClicks");
    var pcMacosPart = $("#pcMacosPart");
    var pcMacosClicks = $("#pcMacosClicks");
    var pcWindowsPart = $("#pcWindowsPart");
    var pcWindowsClicks = $("#pcWindowsClicks");
    var pcUnknownPart = $("#pcUnknownPart");
    var pcUnknownClicks = $("#pcUnknownClicks");

    totalClicks.text(data.totalClicks);
    phonePart.text(data.phonePart);
    phoneClicks.text(data.phoneClicks);
    phoneAndroidPart.text(data.phoneAndroidPart);
    phoneAndroidClicks.text(data.phoneAndroidClicks);
    phoneIOSPart.text(data.phoneIOSPart);
    phoneIOSClicks.text(data.phoneIOSClicks);
    phoneWinmobPart.text(data.phoneWinmobPart);
    phoneWinmobClicks.text(data.phoneWinmobClicks);
    phoneJavaPart.text(data.phoneJavaPart);
    phoneJavaClicks.text(data.phoneJavaClicks);
    phoneUnknownPart.text(data.phoneUnknownPart);
    phoneUnknownClicks.text(data.phoneUnknownClicks);
    pcPart.text(data.pcPart);
    pcClicks.text(data.pcClicks);
    pcLinuxPart.text(data.pcLinuxPart);
    pcLinuxClicks.text(data.pcLinuxClicks);
    pcMacosPart.text(data.pcMacosPart);
    pcMacosClicks.text(data.pcMacosClicks);
    pcWindowsPart.text(data.pcWindowsPart);
    pcWindowsClicks.text(data.pcWindowsClicks);
    pcUnknownPart.text(data.pcUnknownPart);
    pcUnknownClicks.text(data.pcUnknownClicks);

    buildMap(data);

}

function buildMap(data) {
    // Datamaps expect data in format:
    // { "USA": { "fillColor": "#42a844", numberOfWhatever: 75},
    //   "FRA": { "fillColor": "#8dc386", numberOfWhatever: 43 } }
    var dataset = {};
    // We need to colorize every country based on "numberOfWhatever"
    // colors should be uniq for every value.
    // For this purpose we create palette(using min/max series-value)
    var onlyValues = data.series.map(function(obj){ return obj[1]; });
    var minValue = Math.min.apply(null, onlyValues),
        maxValue = Math.max.apply(null, onlyValues);
    // create color palette function
    // color can be whatever you wish
    var paletteScale = d3.scale.linear()
        .domain([minValue,maxValue])
        .range(["#EFEFFF","#02386F"]); // blue color
    // fill dataset in appropriate format
    data.series.forEach(function(item){ //
        // item example value ["USA", 70]
        var iso = item[0],
            value = item[1];
        dataset[iso] = { numberOfThings: value, fillColor: paletteScale(value) };
    });
    // render map
    var option = {
        // countries don't listed in dataset will be painted with this color
        fills: { defaultFill: '#F5F5F5' },
        data: dataset,
        geographyConfig: {
            borderColor: '#DEDEDE',
            highlightBorderWidth: 2,
            // don't change color on mouse hover
            highlightFillColor: function(geo) {
                return geo['fillColor'] || '#F5F5F5';
            },
            // only change border
            highlightBorderColor: '#B7B7B7',
            // show desired information in tooltip
            popupTemplate: function(geo, data) {
                // don't show tooltip if country don't present in dataset
                if (!data) { return ; }
                // tooltip content
                return ['<div class="hoverinfo">',
                    '<strong>', geo.properties.name, '</strong>',
                    '<br>Count: <strong>', data.numberOfThings, '</strong>',
                    '</div>'].join('');
            }
        }
    };
    map = $("#map-container").datamaps(option);
}

