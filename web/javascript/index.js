$(function () {
    $("#submit").click(function () {
        $.post("", {
            "from": $("#from").html,
            "to": $("#to").html,
            "type": $("#type").html
        }).done(function (result) {
            $("#result").html(result)
        })
    }).preventDefault()
})
