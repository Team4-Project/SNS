$(function() {

    let session_id = $.cookie('id');

    if(!session_id) {
        $("#profile-menu").hide();
        $("#logout-menu").hide();
    }
    else{
        $("#signup-menu").hide();
        $("#login-menu").hide();
    }

    $(document).on("click", "#commentButton", function (){

        var postId = $(this).parent().parent().parent().parent().parent().parent().children("#postId").val();
        var userProfileImage = $(this).parent().parent().parent().parent().parent().parent().children("#userProfileUrl").attr("src");
        var userName = $(this).parent().parent().parent().parent().children().children().children("#userName").text();
        var userNickname = $(this).parent().parent().parent().parent().children().children().children("#userNickname").text();
        var postLikeNum = $(this).parent().parent().children().children().children("#postLikeNum").text();
        var postShareNum = $(this).parent().parent().children().children().children("#postShareNum").text();

        var url = "/comment/" + postId;

        $("#postWriterProfile").attr("src", userProfileImage);
        $("#postWriterName").html(userName);
        $("#postWriterNickname").html(userNickname);
        $("#modalPostLikeNum").html(postLikeNum);
        $("#modalPostShareNum").html(postShareNum);

        $.get(url, function (response, status){

            const comments = document.querySelector(
                ".comments"
            );
            while (comments.hasChildNodes()) {	// 부모노드가 자식이 있는지 여부를 알아낸다
                comments.removeChild(
                    comments.firstChild
                );
            }

            console.log(response)

            if(response.length == 0){
                console.log("댓글 0개")
                $(".comments").append("<input type=\"hidden\" id=\"commentPostId\" value=\"" + postId + "\">")
            }
            else {
                console.log("댓글 있음")
                for(var comment of response){

                    $(".comments").append(
                        "<input type=\"hidden\" id=\"commentPostId\" value=\"" + postId + "\">" +
                        "<div class=\"d-flex mb-2\">" +
                        "<img id=\"commentUserProfile\" src=\"" + comment.user.imageUrl + "\" class=\"img-fluid rounded-circle\" alt=\"profile-img\">" +
                        "<div class=\"ms-2 small\">" +
                        "<div class=\"bg-light px-3 py-2 rounded-4 mb-1 chat-text\">" +
                        "<p id=\"commentUserName\" class=\"fw-500 mb-0\">" + comment.user.name + "</p>" +
                        "<span id=\"commentContent\" class=\"text-muted\">" + comment.content + "</span>" +
                        "</div>" +
                        "<div class=\"d-flex align-items-center ms-2\">" +
                        "<a href=\"#\" class=\"small text-muted text-decoration-none\">Like</a>" +
                        "<span class=\"fs-3 text-muted material-icons mx-1\">circle</span>" +
                        "<a href=\"#\" class=\"small text-muted text-decoration-none\">Reply</a>" +
                        "<span class=\"fs-3 text-muted material-icons mx-1\">circle</span>" +
                        "<span id=\"commentWroteAt\" class=\"small text-muted\">" + comment.wroteAt + "</span>" +
                        "</div>" + "</div>" + "</div>"
                    )
                }
            }
        });

    });

    $(document).on("click", "#postModalCancelButton", function (){
        $(this).parent().parent().children().children().children().children("#postRegisterArea").val("");
        $(this).parent().parent().children().children().children().children("#postImageRegister").val("");
    });

    $(document).on("click", "#postImages", function (){
        const children = $(this).children();
        console.log(children);

        const images = document.querySelector(
            "#imageSlider"
        );
        while (images.hasChildNodes()) {
            images.removeChild(
                images.firstChild
            );
        }

        for(var i=0; i<children.length; i++){

            if(i==0){
                $("#imageSlider").append(
                    "<div class=\"carousel-item active\">" +
                    "<img src=\""+ children[i].getAttribute("src") +"\" class=\"d-block w-100\" alt=\"...\">" +
                    "</div>"
                )
            }
            else {
                $("#imageSlider").append(
                    "<div class=\"carousel-item\">" +
                    "<img src=\""+ children[i].getAttribute("src") +"\" class=\"d-block w-100\" alt=\"...\">" +
                    "</div>"
                )
            }

        }
    })

    $(document).on("click", "#postUploadButton", function (){

        if(!session_id) {
            alert('로그인 후 이용해 주세요')
            window.location.href="/sign-in-up";
        }
        else {
            const content = $("#postRegisterArea").val();
            const imageList = $("#postImageRegister")[0].files;

            const data = new FormData();
            data.append("content", content);
            for(var i=0; i<imageList.length; i++){
                var image = imageList[i];
                data.append("images", image)
            }

            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/post",
                data : data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                beforeSend : function() {
                    console.log("보낸다~")
                },
                success: function (data) {
                    console.log("성공함 db 봐봐라");
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            })
        }
    })

    $(document).on("click", "#commentUploadButton", function (){

        if(!session_id) {
            alert('로그인 후 이용해 주세요')
            window.location.href="/sign-in-up";
        }

        else {
            var postId = $(this).parent().parent().parent().parent().children().children("#commentPostId").val()
            var commentContent = $(this).parent().children("#commentContent").val()
            console.log(postId);
            console.log(commentContent);

            $.ajax({
                type: "POST",
                url: "/comment",
                data : JSON.stringify({
                    "postId" : postId,
                    "content" : commentContent
                }),
                contentType: "application/json",

                beforeSend : function() {
                    console.log("보낸다~")
                },
                success: function (data) {
                    console.log("성공함 db 봐봐라");
                    window.location.href = "/"
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            })
        }
    })
});