/**
 * Created by Simon on 25/02/2016.
 */

var notedata = [];
var notecount = 0;
var noteStorage;
var noteloaded = false;
var contdata = [];
var contcount = 0;
var contloaded = false;
function dataLoad() {
    var location = localStorage.getItem("location");
    if(!noteloaded && location == "ar_notes") {
        console.log("show");
        noteStorage = $.parseJSON(localStorage["note"]);
        if (noteStorage == null) {
            console.log("null here");
            notecount = 0;
        } else {
            $.each(noteStorage, function (key, value) {
                console.log(key + " + " + value.note);
                notedata[key] = value;
                notecount = key + 1;
                var newid = value.id;
                var newText = value.note;
                $('#ar_notes').append('<br />');
                $('#ar_notes').append('<article id="' + value.id + '"><div class="note"><p class="show_note">' + newText + '</p><button onclick="removenote(' + newid + ')"><i class="fa fa-trash-o"></i></button>').fadeIn(500);
                $('textarea').filter('[id*=put_note]').val('');
            });
//            notecount = noteStorage.length;
        }
        noteloaded = true;
    }else if(location == "ar_feed" && !contloaded){
        console.log("show");
        contStorage = $.parseJSON(localStorage["contact"]);
        if (contStorage == null) {
            console.log("null here");
            contcount = 0;
        } else {
            $.each(contStorage, function (key, value) {
                console.log(key + " + " + value.note);
                contdata[key] = value;
                contcount = key + 1;
                var newid = value.id;
                var newText = value.note;
                $('#ar_feed').append('<article id="' + newid + '"><div class="contact"><p class="show_note">'+newText+'</p><button onclick="removecont(' + value.id + ')"><i class="fa fa-trash-o"></i></button>').fadeIn(500);
                $('input').filter('[class*=client_set]').val('');
            });
//            contcount = contStorage.length;
        }
        contloaded = true;
    }
}
function removenote(id) {
    console.log("removing: " + id);
    $('#' + id).remove();
    var i;
    for (i = 0; i < notedata.length; i++) {
        if (notedata[i] != null && notedata[i].id == id) {
            delete notedata[i];
            break;
        }
    }
    var newdata = [];
    notecount = 0;
    for (i = 0; i < notedata.length; i++) {
        if (notedata[i] != null) {
            newdata[notecount] = notedata[i];
            notecount++;
        }
    }
    $('#ar_notes').offsetHeight;
    console.log("new data: " + newdata);
    localStorage.setItem("note", JSON.stringify(newdata));
    notedata = JSON.parse(localStorage.getItem("note"));
    console.log("after delete: " + notedata + "lengtej: " + notedata.length);
}
var noteCount;
var am = 1;
function addmore() {
    var id = new Date().getTime();
    var newText = $('textarea').val();
    $('#ar_notes').append('<br />');
    $('#ar_notes').append('<article id="' + id + '"><div class="note"><p class="show_note">' + newText + '</p><button onclick="removenote(' + id + ')"><i class="fa fa-trash-o"></i></button>').fadeIn(500);
    $('textarea').filter('[id*=put_note]').val('');
    notedata[notecount] = {note: newText, id: id};
    notecount++;
    localStorage.setItem("note", JSON.stringify(notedata));


}


function addContact() {

    var name = $('#c_name').val();
    var adress = $('#c_address').val();
    var phone = $('#c_phone').val();
    var id = new Date().getTime();
    var newText = name + "   " + adress + "   " + phone;

    $('#ar_feed').append('<article id="' + id + '"><div class="contact"><p class="show_note">' + name + "   " + adress + "   " + phone + '</p><button onclick="removecont(' + id + ')"><i class="fa fa-trash-o"></i></button>').fadeIn(500);
    $('input').filter('[class*=client_set]').val('');
    contdata[contcount] = {note: newText, id: id};
    contcount++;
    localStorage.setItem("contact", JSON.stringify(contdata));

}

function removecont(id) {
    console.log("removing: " + id);
    $('#' + id).remove();
    var i;
    for (i = 0; i < contdata.length; i++) {
        if (contdata[i] != null && contdata[i].id == id) {
            delete contdata[i];
            break;
        }
    }
    var newdata = [];
    contcount = 0;
    for (i = 0; i < contdata.length; i++) {
        if (contdata[i] != null) {
            newdata[contcount] = contdata[i];
            contcount++;
        }
    }
    $('#ar_feed').offsetHeight;
    console.log("new data: " + newdata);
    localStorage.setItem("contact", JSON.stringify(newdata));
    contdata = JSON.parse(localStorage.getItem("contact"));
    console.log("after delete: " + contdata + "length: " + contdata.length);
}