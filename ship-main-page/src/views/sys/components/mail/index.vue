<template>
  <div class="mail">
    <div class="head">
      <el-button type="primary">发送</el-button>
      <el-button type="primary" plain>预览</el-button>
      <el-button type="primary" plain>存草稿</el-button>
      <el-button type="primary" plain>取消</el-button>
    </div>
    <div class="content">
      <el-form
        :label-position="labelPosition"
        label-width="auto"
        :model="formLabelAlign"
        style="width: 100%"
      >
        <el-form-item label="发件人">
          <el-input v-model="formLabelAlign.name" />
        </el-form-item>
        <el-form-item label="收件船舶">
          <el-input v-model="formLabelAlign.receive" />
        </el-form-item>
        <el-form-item label="抄送">
          <el-input v-model="formLabelAlign.send" />
        </el-form-item>
        <el-form-item label="主题">
          <el-input v-model="formLabelAlign.title" />
        </el-form-item>
        <ek-form-item label="添加附件">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :file-list="fileList"
            list-type="picture-card"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
          
        </ek-form-item>
      </el-form>
      <div class="my-tinymce">
        <Editor v-model="contentValue" :api-key="apiKey" :init="init" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, toRefs } from 'vue'
import Editor from '@tinymce/tinymce-vue'

const contentValue = ref('默认文字 hello word')
const labelPosition = ref('top')
const formLabelAlign = ref({
  name: '',
  send: '',
  receive: '',
  title: ''
})

const tiny = reactive({
      apiKey: "64vt9jtgoi83gm4doanmqae6aklk95fpshab1hcoivygepuv", //https://github.com/tinymce/tinymce-vue/blob/main/src/demo/views/Iframe.vue
      init: {
        language: "zh_CN", //语言类型
        placeholder: "在这里输入文字", //textarea中的提示信息
        min_width: 320,
        min_height: 220,
        height: 500, //注：引入autoresize插件时，此属性失效
        resize: "both", //编辑器宽高是否可变，false-否,true-高可变，'both'-宽高均可，注意引号
        branding: false, //tiny技术支持信息是否显示
        // statusbar: false,  //最下方的元素路径和字数统计那一栏是否显示
        // elementpath: false, //元素路径是否显示

        font_formats:
          "微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;宋体=simsun,serif;仿宋体=FangSong,serif;黑体=SimHei,sans-serif;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;", //字体样式
        plugins:
          "print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount textpattern autosave emoticons", //插件配置 axupimgs indent2em
        toolbar: [
          "fullscreen undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | bullist numlist | blockquote subscript superscript removeformat ",
          "styleselect formatselect fontselect fontsizeselect |  table image axupimgs media emoticons charmap hr pagebreak insertdatetime  selectall visualblocks searchreplace | code print preview | indent2em lineheight formatpainter",
        ], //工具栏配置，设为false则隐藏
        // menubar: "file edit my1", //菜单栏配置，设为false则隐藏，不配置则默认显示全部菜单，也可自定义配置--查看 http://tinymce.ax-z.cn/configure/editor-appearance.php --搜索“自定义菜单”

        // images_upload_url: '/apib/api-upload/uploadimg',  //后端处理程序的url，建议直接自定义上传函数image_upload_handler，这个就可以不用了
        // images_upload_base_path: '/demo',  //相对基本路径--关于图片上传建议查看--http://tinymce.ax-z.cn/general/upload-images.php
        paste_data_images: true, //图片是否可粘贴
        //此处为图片上传处理函数
        images_upload_handler: (blobInfo, success, failure) => {
          // 这里用base64的图片形式上传图片,
          let reader = new FileReader(); //本地预览
          reader.readAsDataURL(blobInfo.blob());
          reader.onloadend = function () {
            const imgbase64 = reader.result;
            success(imgbase64);
          };
        },

        file_picker_types: "file image media", //file image media分别对应三个类型文件的上传：link插件，image和axupimgs插件，media插件。想屏蔽某个插件的上传就去掉对应的参数
        // 文件上传处理函数
        file_picker_callback: function (callback, value, meta) {
          // 使用案例http://tinymce.ax-z.cn/general/upload-images.php
          // meta.filetype  //根据这个判断点击的是什么file image media

          let filetype; //限制文件的上传类型,需要什么就添加什么的后缀
          if (meta.filetype == "image") {
            filetype = ".jpg, .jpeg, .png, .gif, .ico, .svg";
          } else if (meta.filetype == "media") {
            filetype = ".mp3, .mp4, .avi, .mov";
          } else {
            filetype =
              ".pdf, .txt, .zip, .rar, .7z, .doc, .docx, .xls, .xlsx, .ppt, .pptx, .mp3, .mp4, .jpg, .jpeg, .png, .gif, .ico, .svg";
          }
          let inputElem = document.createElement("input"); //创建文件选择
          inputElem.setAttribute("type", "file");
          inputElem.setAttribute("accept", filetype);
          inputElem.click();
          inputElem.onchange = () => {
            let file = inputElem.files[0]; //获取文件信息

            // 所有都转成base64文件流,来自官方文档https://www.tiny.cloud/docs/configure/file-image-upload/#file_picker_callback
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
              // Note: Now we need to register the blob in TinyMCEs image blob
              // registry. In the next release this part hopefully won't be
              // necessary, as we are looking to handle it internally.
              let id = "blobid" + new Date().getTime();
              let blobCache = tinymce.activeEditor.editorUpload.blobCache;
              let base64 = reader.result.split(",")[1];
              let blobInfo = blobCache.create(id, file, base64);
              blobCache.add(blobInfo);

              // call the callback and populate the Title field with the file name
              callback(blobInfo.blobUri(), { title: file.name });
            };
          };
        },
      },
    });

    toRefs(tiny)

</script>

<style scoped>
.mail {
  border-radius: 5px;
  background: #fff;
  height: 100%;
}

.head {
  padding: 30px;
  border-bottom: 1px solid #e6e4e5;
}

.content {
  margin-top: 30px;
  padding: 20px;
}

.my-tinymce > textarea {
  display: none;
}
</style>
<style>
/* 隐藏apikey没有绑定当前域名的提示 */
.tox-notifications-container .tox-notification--warning {
  display: none !important;
}

.tox.tox-tinymce {
  max-width: 100%;
}
</style>