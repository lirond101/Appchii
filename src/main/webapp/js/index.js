function init() {
    document.addEventListener("deviceready", deviceReady, true);
    delete init;

    $(document).bind('mobileinit',function(){
        $.mobile.changePage.defaults.changeHash = false;
        $.mobile.hashListeningEnabled = false;
        $.mobile.pushStateEnabled = false;
    });
}

function checkPreAuth() {
    console.log("checkPreAuth");
    var form = $("#loginForm");
    if(window.localStorage["username"] != undefined && window.localStorage["password"] != undefined) {
        $("#username", form).val(window.localStorage["username"]);
        $("#password", form).val(window.localStorage["password"]);
        handleLogin();
    }
}
function loginto()
{
    window.location.href = '../views/main.html';
}
function handleLogin() {
    var form = $("#loginForm");
    //disable the button so we can't resubmit while we wait
    $("#submitButton",form).attr("disabled","disabled");
    var u = $("#username", form).val();
    var p = $("#password", form).val();
    console.log(u + " " +p);
    //if(u != '' && p!= '') {
    //    $.post("http://www.coldfusionjedi.com/demos/2011/nov/10/service.cfc?method=login&returnformat=json", {username:u,password:p}, function(res) {
    //        if(res == true) {
    //            //store
    //            window.localStorage["username"] = u;
    //            window.localStorage["password"] = p;
    //            $.mobile.changePage("some.html");
    //        } else {
    //            navigator.notification.alert("Your login failed", function() {});
    //        }
    //        $("#submitButton").removeAttr("disabled");
    //    },"json");
    //} else {
    //    alert("You must enter a username and password");
    //    $("#submitButton").removeAttr("disabled");
    //}
    alert(u+" "+p);
    $.mobile.changePage("index.html");
    $( ".selector" ).pagecontainer( "change" );
    $(".mobile-pagecontainer" ).pagecontainer("change", "index.html",{reload:true});

    return false;
}

function deviceReady() {
    console.log("deviceReady");
    $("#loginPage").on("pageinit",function() {
        console.log("pageinit run");
        $("#loginForm").on("submit",handleLogin);
        checkPreAuth();
    });
    $.mobile.changePage("index.html");
}