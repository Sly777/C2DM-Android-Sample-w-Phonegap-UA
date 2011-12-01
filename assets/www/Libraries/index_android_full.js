// developed by Ilker Guller - developerarea.blogspot.com

var sampleProject, apid, apidControl;

var gFuncs = {
	getApid : function(){
		apid = window.MyCls.getApid();
		if(apid == undefined || apid == null){
			if(apidControl == null){
				apidControl = setInterval("gFuncs.getApid()", 5000);
			}
			return apid;
		} else {
			clearInterval(apidControl);
			return apid;
		}
	},
    playBeep : function(){
        navigator.notification.beep(3);
    },
    vibrate : function(){
        navigator.notification.vibrate(2000);
    },
    loginCheck : function(){
        var fieldValues = sampleProject.views.viewport.getValues();
        if(fieldValues.username == "" || fieldValues.password == "" || apid == undefined){
            Ext.Msg.alert("Error", "Please enter your information.", Ext.emptyFn());
        } else {
            console.log("APID : " + apid);
            console.log("Device : " + device.platform);
            sampleProject.views.viewport.setLoading(true, false);

            // DO WHAT YOU WANT
        }
    },
    exitfromApp : function(){
         if (confirm('Are you sure?')) {
             navigator.app.exitApp();
         }
    }
};

function errorMsg(msg) {
    console.log("1");
}

document.addEventListener("deviceready", function() {
    window.plugins.pushNotification.registerCallback(function() {
    }, errorMsg);
}, false);

document.addEventListener("backbutton", onBackKeyDown, false);
function onBackKeyDown() {
    var actItem = sampleProject.views.homeScreen.getActiveItem();
    if(actItem == undefined){
        gFuncs.exitfromApp();
    } else {
        if(actItem.id == "friendList"){
            gFuncs.exitfromApp();
        } else {
            gFuncs.backtoFriendList();
        }
    }
}

var views = {
    loginFormFields: {
        xtype: "fieldset",
        id: "loginFormFields",
        instructions : "Please enter your login information.",
        defaults : {
            useClearIcon: false,
            autoCapitalize: false,
            labelWidth: "100px",
            width: "280px",
            required: true
        },
        items : [{
            name: "username",
            label: "User name",
            xtype: "emailfield",
            id: "lgnUsername"
        },{
            name: "password",
            label: "Password",
            xtype: "passwordfield",
            id: "lgnPassword"
        },{
            name: "loginButton",
            text: "Login",
            xtype: "button",
            margin: "15 0 0 0",
            handler: function(){
                gFuncs.loginCheck();
            }
        }]
    }
};

sampleProject = new Ext.Application({
    name : "Sample Project",
    tabletStartupScreen: 'Resources/tablet_startup.png',
    phoneStartupScreen: 'Resources/phone_startup.png',
    glossOnIcon: false,
    launch: function () {

        sampleProject.views.headerBar = new Ext.Toolbar({
            id: "headerBar",
            height: 40,
            dock: "top",
            cls: "ustPanel",
            title: "Sample Project"
        });

        sampleProject.views.homeScreen = new Ext.Panel({
            fullscreen: true,
            hidden: true,
            style: "background-color: #fff;",
            cardAnimation: 'slide',
            dockedItems: [sampleProject.views.headerBar],
            html: "WORKING!"
        });

        sampleProject.views.viewport = new Ext.form.FormPanel({
            fullscreen: true,
            id: "loginForm",
            scroll: "vertical",
            style: "background-color: #fff;",
            cardAnimation: 'slide',
            layout: {
                type: "vbox",
                align: "center",
                pack: "center"
            },
            dockedItems: [sampleProject.views.headerBar],
            items: [views.loginFormFields]
        });
        
        gFuncs.getApid();
    }
});