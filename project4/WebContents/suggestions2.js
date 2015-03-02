/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */
function StateSuggestions() {
    this.states = [
        "Alabama", "Alaska", "Arizona", "Arkansas",
        "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii",
        "Idaho", "Illinois", "Indiana", "Iowa",
        "Kansas", "Kentucky", "Louisiana",
        "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
        "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
        "Washington", "West Virginia", "Wisconsin", "Wyoming"
    ];
}

/**
 * Request suggestions for the given autosuggest control.
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
StateSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    //var text = escape(this.searchBar.value);
    var sTextboxValue = escape(oAutoSuggestControl.textbox.value);
    var aSuggestions = [];
    if (sTextboxValue == "") {
        return;
    }
    var request = new XMLHttpRequest();
    var oThis = this;
    var aSuggestions = Array();
    var response;

    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200) {
            response = request.responseXML;
            if (typeof response === "undefined") 
                return [];

            var completeSuggestions = response.documentElement.childNodes;

            for(var i = 0; i < completeSuggestions.length; i++) {
                aSuggestions[i] = completeSuggestions[i].childNodes[0].getAttribute("data");
            }

            //window.alert("Suggestions array created! " + aSuggestions[0] + aSuggestions.length);
            //window.alert("Calling autosuggest now");
            oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
        }
    }

    var url = "suggest?query=" + sTextboxValue;
    request.open("GET", url, true);
    request.send();

    //window.alert("Calling autosuggest now");
    //oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
};
