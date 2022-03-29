$(function() {

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
            for(var comment of response){
                $(".comments").append(
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
        });

    });

    $(document).on("click", "#postModalCancelButton", function (){
        $(this).parent().parent().children().children().children().children("#postRegisterArea").val("");
        $(this).parent().parent().children().children().children().children("#postImageRegister").val("");
    });

    $(document).on("click", "#postUploadButton", function (){

        var session_id = $.cookie('id')
        const content = $("#postRegisterArea").val();
        const imageList = $("#postImageRegister")[0].files;

        console.log("sessionId", session_id);
        console.log("textInput : ", content);
        console.log("imageInput : ", imageList)

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
    })

});