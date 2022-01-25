<script setup lang="ts">
</script>

<template>
  <div>
    <div :style="Display.block">
      <div>123----</div>
      <div>456</div>
    </div>









<!--    <div>
      <div>789</div>
      <div>45645</div>
    </div>
    <div v-for="talk in talks">
      <div class="bg-white py-sm flex-row pb-10" @click="toUserDetailVue">
        <img
            class="card-title-avatar"
            mode="aspectFill"
            :src="talk.user.avatar"
        />
        <div class="row-between flex-auto">
          <div>
            <div class="h25 row-col-center">
              <text class="text-md">{{ talk.user.nickname }}</text>
              &lt;!&ndash;          <text class="text-md" :class="{'color-red':talk.user.vipFlag}">{{ talk.user.nickname }}</text>&ndash;&gt;
              <template v-if="!talk.globalTop">
                <div v-if="talk.user.gender==='girl'" class="box-nn q-tag-error ml-sm">
                  {{ talk.user.age }}
                  <q-icon class="ml-nn"
                          size="12"
                          :icon="getGenderIcon(talk.user)"/>
                </div>
                <div v-else class="box-nn q-tag-blue ml-sm">
                  {{ talk.user.age }}
                  <q-icon class="ml-nn"
                          size="12"
                          :icon="getGenderIcon(talk.user)"/>
                </div>
              </template>
            </div>
            &lt;!&ndash;            <div class="text-gray text-sm h25 row-col-center">
                          最新回复：{{ talk.updateTime| formatTime }}
                          <div v-if="talk.globalTop" class="ml-5 sm cu-tag round bg-red light">
                            官方
                          </div>
                          &lt;!&ndash;              自己的帖子，或者系统管理员可以删除帖子&ndash;&gt;
                          <text v-if="isMine"
                                class="ml-5 color-blue1 bg-click"
                                @click.stop="confirmDeleteTalk">
                            删除
                          </text>
                        </div>&ndash;&gt;
          </div>
          &lt;!&ndash;                不为自己且未关注&ndash;&gt;
          &lt;!&ndash;          <div v-if="talkTabType!==followType&&!isMine&&!isUserDetail" class="col-center">
                      <button v-if="!talk.hasFollowed" class="cu-btn round bd-theme bg-white text-theme px-smm"
                              @click.stop="addFollow">
                        关注
                      </button>
                      <div v-else class="box-xs" @click.stop="addFollow">已关注</div>
                    </div>&ndash;&gt;
        </div>
      </div>
    </div>-->
  </div>
</template>

