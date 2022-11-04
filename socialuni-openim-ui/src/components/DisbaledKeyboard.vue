<template>
  <view
    class="DisbaledKeyboard"
    :disableTimes="disableTimes"
    :change:disableTimes="input.changeDisable"
    :availableTimes="availableTimes"
    :change:availableTimes="input.changeAvailable"
  >
    <editor
      id="editor2"
      @ready="editorReady"
      @focus="editorFocus"
      @blur="editorBlur"
      @input="editorInput"
    />
  </view>
</template>>
    
<script>
export default {
  data() {
    return {
      disableTimes: 0,
      availableTimes: 0,
      editorCtx: null,
      imageData: null,
      draftTextArr: [],
      draftTextIndex: 0,
    };
  },
  methods: {
    setDraftText(arr = []) {
      this.draftTextArr = arr;
      this.draftTextIndex = 0;
      if (this.editorCtx) {
        this.setDraftTextItem();
      }
    },
    setDraftTextItem() {
      const item = this.draftTextArr[this.draftTextIndex];
      if (!item) {
        this.draftTextArr = [];
        this.draftTextIndex = 0;
        return;
      }
      const { content, type, src } = item;
      if (type === "text") {
        this.editorCtx.insertText({
          text: content,
        });
        this.draftTextIndex++;
        this.setDraftTextItem();
      } else if (type === "emoji") {
        this.insertImage(src, "20px", "20px", { emojiText: content });
      }
    },
    editorReady() {
      uni
        .createSelectorQuery()
        .select("#editor2")
        .context((res) => {
          this.$emit("ready", res);
          this.editorCtx = res.context;
          if (this.draftTextArr.length) {
            this.setDraftTextItem();
          }
        })
        .exec();
    },
    insertImage(src = "", width = "20px", height = "20px", data = {}) {
      this.disableTimes++;
      this.imageData = { src, width, height, data };
    },
    insertImage2() {
      this.editorCtx.insertImage({
        src: this.imageData.src,
        width: this.imageData.width,
        height: this.imageData.height,
        data: this.imageData.data,
        complete: () => {
          this.availableTimes++;
          this.draftTextIndex++;
          this.setDraftTextItem();
        },
      });
    },
    editorFocus() {
      this.$emit("focus");
    },
    editorBlur() {
      this.$emit("blur");
    },
    editorInput(e) {
      this.$emit("input", e);
    },
  },
};
</script>
<script module="input" lang="renderjs">
export default{
    methods:{
        changeDisable(newValue, oldValue, ownerVm, vm){
            if(newValue===0)return
            this.$el.querySelector('.ql-editor').setAttribute('inputmode', 'none')
            ownerVm.callMethod('insertImage2')
        },
        changeAvailable(v){
            if(v===0)return
            this.$el.querySelector('.ql-editor').removeAttribute('inputmode')
        },
    },
    mounted(){
    }
}
</script>
<style lang="scss" scoped>
#editor2 {
  background-color: #fff;
  min-height: 31px;
  height: auto;
  padding-top: 5px;
  padding-left: 4px;
  padding-right: 4px;
  word-break: break-all;
}
</style>