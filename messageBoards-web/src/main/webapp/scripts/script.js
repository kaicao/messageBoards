
// This function is used by the login screen to validate user/pass
// are entered.
function validateRequired(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oRequired = new required();

    for (x in oRequired) {
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]];

           fields[i++] = oRequired[x][1];

           bValid = false;
        }
    }

    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));
    }

    return bValid;
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {
    var msg = "Are you sure you want to delete this " + obj + "?";
    ans = confirm(msg);
    return ans;
}

// 18n version of confirmDelete. Message must be already built.
function confirmMessage(obj) {
    var msg = "" + obj;
    ans = confirm(msg);
    return ans;
}

function showMessageDetail(title, sender, url, content) {
    $('#detail_title').val(title);
    $('#detail_sender').val(sender);
    $('#detail_url').val(url);
    $('#detail_content').val(content);

    $('#detail_title').attr('readonly', true);
    $('#detail_sender').attr('readonly', true);
    $('#detail_url').attr('readonly', true);
    $('#detail_content').attr('readonly', true);
}

function isEmpty(val){
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}

function doCreateMessageAjax(url) {
    if ($("#createMessageForm").valid()) {
        var message = new Object();
        message.title = $('#create_title').val();
        message.sender = $('#create_sender').val();
        message.url = $('#create_url').val();
        message.content = $('#create_content').val();

        $.ajax({
            type:"POST",
            url:url,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=ISO-8859-1'
            },
            data:JSON.stringify(message),
            dataType: "json",
            success:function (response) {

                $('#datatable > tbody').prepend(
                    '<tr>' +
                        '<td>' + response.title + '</td>' +
                        '<td>' + response.sender + '</td>' +
                        '<td>' + response.content + '</td>' +
                        '<td>' + response.url + '</td>' +
                    '</tr>'
                );

                // Unset the input fields
                $('#create_title').val('');
                $('#create_sender').val('');
                $('#create_url').val('');
                $('#create_content').val('');

                // Close the modal window
                $('#createMessage').modal('hide');

            },
            error:function (e) {
                Console.log(e.toString());
            }
        });
    }
}