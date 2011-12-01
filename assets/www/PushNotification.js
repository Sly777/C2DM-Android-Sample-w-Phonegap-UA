function PushNotification() {
}

PushNotification.prototype.registerCallback = function(successCallback, failureCallback) {
     return PhoneGap.exec(
            successCallback,           // called when signature capture is successful
            failureCallback,           // called when signature capture encounters an error
            'PushNotificationPlugin',  // Tell PhoneGap that we want to run "PushNotificationPlugin"
            'registerCallback',        // Tell the plugin the action we want to perform
            []);                       // List of arguments to the plugin
};

PushNotification.prototype.notificationCallback = function (json) {
    var data = Ext.util.JSON.decode(json);
    console.log("3");
    console.log(data);
    console.log(data.msg);
};

PhoneGap.addConstructor(function() {
    console.log("4");
    if (typeof navigator.pushNotification == "undefined")
        navigator.pushNotification = new PushNotification();
});