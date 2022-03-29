$(function() {

    $(document).on("click", "#commentButton", function (){
        console.log("댓글 열어줘요");

        var id = $(this).parent().parent().parent().parent().parent().parent().children(".post-id").val();
        console.log("post-id : " + id)
    })

});