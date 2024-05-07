<template>

</template>

<script lang="ts">
import {Component, toNative, Prop, Vue} from 'vue-facing-decorator'


@toNative
@Component({})
export default class SWaterfall extends Vue {
    @Prop({default: 2}) columnNum: number
    @Prop({default: 2}) data: any[]



    fasdf(){
        users.forEach((item) => {
            const itemHeight = Math.min(item.imgs[0].height, 600) + 119
            if (this.leftHeight > this.rightHeight) {
                this.rightAry.push(item)
                this.rightHeight += itemHeight
            } else {
                this.leftAry.push(item)
                this.leftHeight += itemHeight
            }
        })
        if (res.data && res.data.length >= this.lazyLoadNum) {
            this.loadMore = LoadMoreType.more
        } else {
            // 否则没有了
            this.loadMore = LoadMoreType.noMore
        }
    }

}
</script>
