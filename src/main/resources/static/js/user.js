$(function(){
    // 'id' 쿠키값 확인해서 있으면 로그인상태
    // 없으면 게스트 상태
    var session_id = $.cookie('id');
    if(session_id) {
        $("#logout-menu").show();
<<<<<<< HEAD
=======
        $("#change-profile-menu").show();
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
        $("#login-menu").hide();
        $("#signup-menu").hide();
    } else {
        $("#logout-menu").hide();
<<<<<<< HEAD
=======
        $("#change-profile-menu").hide();
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
        $("#login-menu").show();
        $("#signup-menu").show();
    }

     //로그아웃 시 유저 세션 삭제
<<<<<<< HEAD
    $(document).on("click", "#logout-menu",function(){
        var session_id = $.cookie('id');

=======
    $(document).on("click","#logout-button",function(){
        var session_id = $.cookie('id');
        alert("로그아웃 합니다");
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
        $.ajax({
            method: "DELETE",
            url: "/user/session",
            data: {
                "id": session_id,
            }
        })
        .done(function(response) {
            document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
<<<<<<< HEAD
            window.location.href = "/";
        });
    });

    // 로그인할때
    $("#login-button").click(function(){
=======
            window.location.href = "/trending";
        });
    });

    // 로그인 버튼 클릭시
    $("#login-form").submit(function(e){
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
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
<<<<<<< HEAD
            console.log(response);
            window.location.href="/";
=======
            console.log("로그인 성공");
            window.location.href="/trending";
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
        })
        .fail(function(response) {
            alert("입력 정보를 확인해주세요.")
        });
    });

<<<<<<< HEAD
    $("#signup-button").click(function() {
        var account = $("#signup-account").val();
        var password = $("#signup-password").val();
        var name = $("#signup-name").val();
        console.log(account, password, name);
=======
    //회원가입 버튼 클릭시
    $("#signup-form").submit(function(e) {
        var account = $("#signup-account").val();
        var password = $("#signup-password").val();
        var name = $("#signup-name").val();

>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
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
<<<<<<< HEAD
            window.location.href="/";
=======
            window.location.href="/trending";
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
        })
        .fail(function(response) {
            console.log(response);
            alert("계정 생성 실패");
        });
    });
<<<<<<< HEAD
=======


    // 프로필 변경 버튼 클릭시
    $("#profile-change-form").submit(function(e) {
        var nickname = $("#profile-nickname").val();
        var profession = $("#profile-profession").val();
        var gender = parseInt($('input[name="gender"]:checked').val());
        var content = $("#profile-content").val();
        var imageList = $("#profile-image")[0].files;
        var isAuth = parseInt($('input[name="auth"]:checked').val());
        var session_id = $.cookie('id');

        console.log(nickname, profession, gender, content, imageList, isAuth);

        const data = new FormData();
        data.append("nickname", nickname);
        data.append("profession", profession);
        data.append("gender", gender);
        data.append("content", content);
        for(var i=0; i<imageList.length; i++){
            var image = imageList[i];
            data.append("images", image)
        }
        data.append("isAuth", isAuth);

        $.ajax({
            type: "PUT",
            enctype: 'multipart/form-data',
            url: "/user",
            data : data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            beforeSend : function() {
                console.log("데이터 보냄")
            },
            success: function (data) {
                console.log("프로필 변경 성공");
                window.location.href="/trending";
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    });
>>>>>>> 8645c087f49a714d10b09f24e385c89a5b3d99d2
});