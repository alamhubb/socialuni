<template>
    <div class="h100vh flex-col">
      <div class="flex-none row-col-center px">
        话题筛选
      </div>
<!--        <q-navbar class="flex-none">话题筛选</q-navbar>-->
        <div class="overflow-hidden flex-1 flex-col">
            <div class="cu-bar bg-white search flex-none">
                <div class="search-form round">
                    <q-icon class="mx-5 text-gray" icon="search"></q-icon>
                    <input v-model="searchContent" :adjust-position="false" type="text" @focus="showSearchView"
                           placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
                    <q-icon v-if="searchContent" class="mr text-gray" icon="close"
                            @click="clearSearchContent"
                    ></q-icon>
                </div>
                <div class="action" @click="closeSearch">
                    <span>取消</span>
                </div>
            </div>
            <div class="flex-1 overflow-auto" v-if="searchContent || showSearch">
                <div v-if="searchContent&&isAdd" class="article-row row-col-center solid-bottom color-blue"
                      @click="addTag">
                    未找到相关标签，可点击
                    <q-icon class="ml-sm" icon="arrow-rightward"></q-icon>
                    此处去创建
                </div>
                <div v-for="tag in showTags" :key="tag.id" @click="checkTag(tag)"
                      class="article-row row-between solid-bottom">
                    <span>
                        #{{ tag.name }}
                    </span>
                    <div v-if="tag.count" class="row-col-center">
                        <q-icon addClass="color-red" icon="mdi-fire"></q-icon>
                        {{ tag.count }}
                    </div>
                </div>
            </div>
            <div v-else class="flex-1 overflow-auto">
                <div class="article">
                    <div>
                        已选话题：
                    </div>
                    <div class="grid">
                        <div v-for="tag in selectTags" :key="tag.id" class="row-col-center pb-10 px-mn">
                            <div class="q-tag bd-round bg-pink-plain">
                                #{{ tag.name }}
                                <q-icon v-if="tag.id" class="ml-5" icon="close-circle-fill" size="16"
                                        @click="deleteTag(tag)"></q-icon>
                            </div>
                        </div>
                    </div>
                    <div>
                        热门话题：
                    </div>
                    <div class="grid">
                        <div v-for="tag in showTags" :key="tag.id" class="row-center pb-10 px-mn">
                            <div class="q-tag bd-round bg-pink-plain"
                                  @click="checkTag(tag)">
                                #{{ tag.name }}
                                <block v-if="tag.count">
                                    <q-icon addClass="text-cyan" icon="mdi-fire"></q-icon>
                                    {{ tag.count }}
                                </block>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'
import TagVO from 'socialuni-api-base/src/model/community/tag/TagVO'
import QIcon from 'qingjs-ui/src/components/QIcon.vue'
// import QNavbar from 'qingjs-ui-uni/src/components/QNavbar/QNavbar.vue'
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";

@toNative
@Component({
    components: {QIcon}
})
export default class TalkAddTagSearch extends Vue {
    searchContent = ''
    showSearch = false

    get user() {
        return socialuniUserModule.mineUser
    }

    // 全部tag,因为需要与外部联动，所以使用外部传进来的tags
    @Prop() readonly tags: TagVO []
    @Prop({type: Boolean, default: false}) readonly isAdd: boolean
    // 已选tag
    @Prop() readonly selectTags: TagVO []

    // @appModule.State('userTags') readonly userTags: TagVO []

    get showTags(): TagVO[] {
        if (this.searchContent) {
            return this.tags.filter(tag => tag.name.indexOf(this.searchContent) > -1)
        } else {
            return this.tags
        }
    }

    closeSearch() {
        this.$emit('close')
    }

    @Watch('searchContent')
    watchSearchContent() {
        if (!this.searchContent) {
            this.showSearch = false
        }
    }

    @Emit()
    deleteTag(tag: TagVO) {
        return tag
    }

    @Emit()
    addTag() {
        return ''
    }

    showSearchView() {
        this.showSearch = true
    }

    checkTag(tag: TagVO) {
        this.searchContent = ''
        this.$emit('change', tag)
    }

    clearSearchContent() {
        this.searchContent = ''
    }
}
</script>
