//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.EPTPrintPlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	

        //进纸
        feedLine: function(line) {
           exec(null, null, "EPTPrintPlugin", "feedLine", [line]);
        },

        //注销
        logout:function() {
           exec(null, null, "EPTPrintPlugin", "logout", [null]);
        },

    };

});


