$('#chooseImage').on('change', function () {
    var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
        fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
    // 检查是否是图片
    if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
        alert('上传错误,文件格式必须为：png/jpg/jpeg');
        return;
    }
    //获取上传的文件
    var arr = document.getElementById('chooseImage').files;
    //遍历文件
    for (var i = 0; i < arr.length; i++) {
        //通过 FormData 将文件信息提交到后台
        var formData = new FormData();
        formData.append('photo', arr[i]);
        $.ajax({
            url: "http://localhost:8080/springmvc-file-demo/uploadPhoto",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.type == "success") {
                    //在图片显示区显示图片
                    var html = "<img id='" + data.filename + "' src='" + ctxPath + data.filepath + data.filename + "' width='200px' height='200px'>&nbsp;";
                    $("#img-div").append(html);
                    //将文件路径赋值给 fileNameStr
                    var path = $("#fileNameStr").val();
                    if (path == "") {
                        $("#fileNameStr").val(data.filename);
                    } else {
                        $("#fileNameStr").val(path + "," + data.filename);
                    }
                } else {
                    alert(data.msg);
                }
            },
            error: function (data) {
                alert("上传失败")
            }
        });
    }
});