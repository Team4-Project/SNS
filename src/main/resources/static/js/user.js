$(function(){
    // 'id' 쿠키값 확인해서 있으면 로그인상태
    // 없으면 게스트 상태
    var session_id = $.cookie('id');
    if(session_id) {
        $("#logout-menu").show();
        $("#login-menu").hide();
        $("#signup-menu").hide();
    } else {
        $("#logout-menu").hide();
        $("#login-menu").show();
        $("#signup-menu").show();
    }

     //로그아웃 시 유저 세션 삭제
    $(document).on("click", "#logout-menu",function(){
        var session_id = $.cookie('id');

        $.ajax({
            method: "DELETE",
            url: "/user/session",
            data: {
                "id": session_id,
            }
        })
        .done(function(response) {
            document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "/";
        });
    });

    // 로그인할때
    $("#login-button").click(function(){
        var account = $("#login-account").val();
        var password = $("#login-password").val();
        $.ajax({
            method: "POST",
            url: "/user/login",
            data: JSON.stringify({
                "account": account,
                "password": password
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            console.log(response);
            window.location.href="/";
        })
        .fail(function(response) {
            alert("입력 정보를 확인해주세요.")
        });
    });

    $("#signup-button").click(function() {
        var account = $("#signup-account").val();
        var password = $("#signup-password").val();
        var name = $("#signup-name").val();
        console.log(account, password, name);
        $.ajax({
            method: "POST",
            url: "/user/signup",
            data: JSON.stringify({
                "account": account,
                "password": password,
                "name": name
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            alert("계정 생성 성공");
            window.location.href="/";
        })
        .fail(function(response) {
            console.log(response);
            alert("계정 생성 실패");
        });
    });
});